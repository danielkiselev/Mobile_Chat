package project03.csc214.project03.ChatData;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import project03.csc214.project03.Chat.Chat;
import project03.csc214.project03.Chat.ChatAdapter;
//import project03.csc214.project03.model.User;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 6/28/2017.
 */

public class SendMessage {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRefDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private String UID, currentFName,currentLName,currentUser;
    //private DatabaseReference mDatabase;

    public SendMessage(final String friendCode, final String message, final Activity activity, final Context context) {
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("AddChat Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);

        Log.d("AddFriend Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String key = database.getReference().push().getKey();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(UID).child(friendCode).child(key);
        mDatabase.setValue("Sent: "+message);
        String key1 = database.getReference().push().getKey();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(friendCode).child(UID).child(key1);
        mDatabase.setValue("Received: "+message);
        Log.d("send message Data", "Pass");



/*
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRefDatabase = mFirebaseDatabase.getReference().child("Users").child(UID);
        FirebaseUser user = mAuth.getCurrentUser();
        UID = user.getUid();
        Log.d("chat Collection: ", UID );
        mRefDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("onData Change ", "start");
                currentFName = dataSnapshot.child("fName").getValue().toString();
                currentLName = dataSnapshot.child("lName").getValue().toString();
                currentUser = currentFName+" "+currentLName+": ";
                String userMessage = "Sent By "+currentUser+message;
                sendMessage(userMessage,friendCode);
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void sendMessage(String userMessage, String friendCode){
 */
    }

}
