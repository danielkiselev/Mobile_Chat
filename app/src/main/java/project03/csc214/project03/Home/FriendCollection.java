package project03.csc214.project03.Home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 6/25/2017.
 */

public class FriendCollection {

    private FirebaseAuth mAuth;
    private DatabaseReference mRefDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private String UID;
    private List<Friend> friends;

    public FriendCollection(final RecyclerView mRecyclerView,final Context c){

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRefDatabase = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        UID = user.getUid();
        Log.d("Friend Collection: ", UID );
        mRefDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                friends = new ArrayList<>();
                Log.d("onData Change ", "start");
                showData(dataSnapshot);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(c));
                Log.d("onCreate:", "2");
                HomeAdapter adapter = new HomeAdapter(friends);
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
        Map<String, String> friendMap = dataSnapshot.child("Friends").child(UID).getValue(genericTypeIndicator);
        int friendSize = 0;
        try {
            friendSize = friendMap.size();
        } catch (NullPointerException e) {
            Log.d(TAG, "empty friends list");
        }
        Log.d("friend list size", Integer.toString(friendSize));

        try {
            for (Map.Entry<String, String> entry : friendMap.entrySet()) {
                String temp = entry.getValue();
                Log.d(TAG, "showData: " + temp);

                String fNameS, lNameS, friendUIDS;

                fNameS = dataSnapshot.child("Users").child(temp).child("fName").getValue().toString();
                lNameS = dataSnapshot.child("Users").child(temp).child("lName").getValue().toString();
                friendUIDS = temp;

                Friend f = new Friend(fNameS, lNameS, friendUIDS);

                Log.d(TAG, "showData: fName: " + f.getfName());
                Log.d(TAG, "showData: lName: " + f.getlName());
                Log.d(TAG, "showData: UID: " + f.getUID());

                friends.add(f);
                Log.d("add friends list size", Integer.toString(friends.size()));

                if (friends.size() == friendSize) {
                    break;
                }
            }
        }
        catch (NullPointerException e) {
            Log.d(TAG, "empty friends list");
        }
    }

    public List<Friend> getFriends(){
        Log.d("get friends list size",Integer.toString(friends.size()));
        return friends;
    }
}
