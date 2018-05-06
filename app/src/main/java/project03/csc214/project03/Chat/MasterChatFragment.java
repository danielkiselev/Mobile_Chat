package project03.csc214.project03.Chat;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project03.csc214.project03.Home.FriendCollection;
import project03.csc214.project03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterChatFragment extends Fragment {

    RecyclerView mRecyclerView;
    static ChatCollection cColl;


    public MasterChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_master_chat, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleChatV);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        cColl = new ChatCollection (mRecyclerView,getContext(),getActivity(),getFragmentManager());
        Log.d("onCreate:", "1");

        return view;
    }

}
