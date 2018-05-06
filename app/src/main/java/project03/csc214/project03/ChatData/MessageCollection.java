package project03.csc214.project03.ChatData;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import project03.csc214.project03.Chat.Chat;
import project03.csc214.project03.Chat.ChatAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 6/28/2017.
 */

public class MessageCollection {

    private FirebaseAuth mAuth;
    private DatabaseReference mRefDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private LinearLayoutManager mLinearLayoutManager;
    private String UID,mFriendCode;
    private List<Message> messages;

    public MessageCollection(final RecyclerView mRecyclerView, String friendCode ,final Context c, final Activity activity, final FragmentManager fragmentManager){

        mFriendCode = friendCode;
        Log.d(TAG, "friend code"+mFriendCode);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRefDatabase = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        UID = user.getUid();
        Log.d("message Collection: ", UID );
        mRefDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages = new ArrayList<>();

                Log.d("onData Change ", "start");
                showData(dataSnapshot);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(c));
                Log.d("onCreate:", "2");
                MessageAdapter adapter = new MessageAdapter(messages);
                Log.d("onCreate:", "3");
                mRecyclerView.setAdapter(adapter);
                mLinearLayoutManager = new LinearLayoutManager(c);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                mLinearLayoutManager.scrollToPosition(messages.size()-1);
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        Log.d("show Data", "arrive");
        for(DataSnapshot dsp: dataSnapshot.child("Messages").child(UID).child(mFriendCode).getChildren()){
            String messageTemp;
            messageTemp = dsp.getValue().toString();
            Message f = new Message(messageTemp,mFriendCode);

            Log.d(TAG, "showData: message: " + f.getMessage());
            Log.d(TAG, "showData: UID of friend: " + f.getUID());
            messages.add(f);
        }
    }

    public List<Message> getMessages(){
        Log.d("get messages list size",Integer.toString(messages.size()));
        return messages;
    }
}
