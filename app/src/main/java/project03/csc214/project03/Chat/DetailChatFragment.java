package project03.csc214.project03.Chat;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project03.csc214.project03.ChatData.Message;
import project03.csc214.project03.ChatData.MessageCollection;
import project03.csc214.project03.ChatData.SendMessage;
import project03.csc214.project03.R;
import project03.csc214.project03.Registration.RegActivity;
import project03.csc214.project03.Sound.SoundManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailChatFragment extends Fragment {

    TextView chatNameTV;
    RecyclerView mRecyclerView;
    EditText messageET;
    at.markushi.ui.CircleButton sendBut;
    String fName;
    String lName;
    String messageS;
    String friendCode;
    private LinearLayoutManager mLinearLayoutManager;
    MessageCollection mColl;



    public DetailChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail_chat, container, false);
        friendCode = getArguments().getString("friendCode");
        fName = getArguments().getString("fName");
        lName = getArguments().getString("lName");
        Log.d("friendCode", friendCode);
        new SoundManager(getContext());
        chatNameTV = (TextView) view.findViewById(R.id.chatNameTV);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleConvV);
        messageET = (EditText) view.findViewById(R.id.messageET);
        sendBut = (at.markushi.ui.CircleButton) view.findViewById(R.id.sendBut);
        chatNameTV.setText("Chatting With: "+ fName+ " "+lName);
        mColl = new MessageCollection (mRecyclerView,friendCode,getContext(),getActivity(),getFragmentManager());
        sendBut.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                messageS = messageET.getText().toString();
                SendMessage s = new SendMessage(friendCode,messageS,getActivity(),getContext());
                SoundManager.getInstance.playSound(SoundManager.sound_send);
                messageET.setText("");
            }

        });

        return view;
    }

}
