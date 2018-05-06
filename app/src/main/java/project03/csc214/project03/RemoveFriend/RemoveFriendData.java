package project03.csc214.project03.RemoveFriend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.Main.MainActivity;

/**
 * Created by Daniel on 6/27/2017.
 */

public class RemoveFriendData {

    private ProgressDialog mFriendProgress;
    private DatabaseReference mDatabase;

    public RemoveFriendData(String UID, String friendCode, final Activity activity, final Context context){

        mFriendProgress = new ProgressDialog(context);
        mFriendProgress.setTitle("Removing Friend");
        mFriendProgress.setCanceledOnTouchOutside(false);
        mFriendProgress.show();

        Log.d("Remove Friend Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(UID).child(friendCode);
        mDatabase.removeValue();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chats").child(UID).child(friendCode);
        mDatabase.removeValue();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(UID).child(friendCode);
        mDatabase.removeValue();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(friendCode).child(UID);
        mDatabase.removeValue();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chats").child(friendCode).child(UID);
        mDatabase.removeValue();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(friendCode).child(UID);
        mDatabase.removeValue();
        Log.d("removeFriend Data", "Pass");
        mFriendProgress.dismiss();
        Toast.makeText(activity, "Successfully Removed Friend", Toast.LENGTH_SHORT).show();

    }
}
