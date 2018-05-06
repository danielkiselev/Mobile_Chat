package project03.csc214.project03.Chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import project03.csc214.project03.Sound.SoundManager;

/**
 * Created by Daniel on 6/27/2017.
 */

public class AddChat {

    private DatabaseReference mDatabase;

    public AddChat(String UID,String friendCode, final Activity activity, final Context context){
        Log.d("AddChat Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);

        Log.d("AddFriend Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chats").child(UID).child(friendCode);
        mDatabase.setValue(friendCode);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chats").child(friendCode).child(UID);
        mDatabase.setValue(UID);
        Toast.makeText(context, "Chat Added", Toast.LENGTH_SHORT).show();
        Log.d("Addchat Data", "Pass");
    }
}
