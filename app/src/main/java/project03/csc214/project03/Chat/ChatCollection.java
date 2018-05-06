package project03.csc214.project03.Chat;

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

import project03.csc214.project03.Home.Friend;
import project03.csc214.project03.Home.HomeAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 6/27/2017.
 */

public class ChatCollection {

    private FirebaseAuth mAuth;
    private DatabaseReference mRefDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private String UID;
    private List<Chat> chats;

    public ChatCollection(final RecyclerView mRecyclerView, final Context c, final Activity activity, final FragmentManager fragmentManager){

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRefDatabase = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        UID = user.getUid();
        Log.d("chat Collection: ", UID );
        mRefDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                chats = new ArrayList<>();
                Log.d("onData Change ", "start");
                showData(dataSnapshot);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(c));
                Log.d("onCreate:", "2");
                ChatAdapter adapter = new ChatAdapter(chats,c,activity,fragmentManager);
                Log.d("onCreate:", "3");
                mRecyclerView.setAdapter(adapter);
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        Log.d("show Data", "arrive");
        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {
        };
        Map<String, String> chatMap = dataSnapshot.child("Chats").child(UID).getValue(genericTypeIndicator);
        int chatSize = 0;
        try {
            chatSize = chatMap.size();
        } catch (NullPointerException e) {
            Log.d(TAG, "empty chat list");
        }
        Log.d("chat list size", Integer.toString(chatSize));

        try {
            for (Map.Entry<String, String> entry : chatMap.entrySet()) {
                String temp = entry.getValue();
                Log.d(TAG, "showData: " + temp);

                String fNameS, lNameS, friendUIDS;

                fNameS = dataSnapshot.child("Users").child(temp).child("fName").getValue().toString();
                lNameS = dataSnapshot.child("Users").child(temp).child("lName").getValue().toString();
                friendUIDS = temp;

                Chat f = new Chat(fNameS, lNameS, friendUIDS);

                Log.d(TAG, "showData: fName: " + f.getfName());
                Log.d(TAG, "showData: lName: " + f.getlName());
                Log.d(TAG, "showData: UID: " + f.getUID());

                chats.add(f);
                Log.d("add chats list size", Integer.toString(chats.size()));

                if (chats.size() == chatSize) {
                    break;
                }
            }
        }
        catch (NullPointerException e) {
            Log.d(TAG, "empty chats list");
        }
    }

    public List<Chat> getChats(){
        Log.d("get chats list size",Integer.toString(chats.size()));
        return chats;
    }
}
