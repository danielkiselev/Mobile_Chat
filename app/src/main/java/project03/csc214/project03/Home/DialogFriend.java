package project03.csc214.project03.Home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import project03.csc214.project03.Chat.AddChat;
import project03.csc214.project03.Chat.ChatMasterDetailActivity;
import project03.csc214.project03.Main.Login;
import project03.csc214.project03.R;
import project03.csc214.project03.RemoveFriend.RemoveFriendData;

import static android.R.attr.onClick;

/**
 * Created by Daniel on 6/27/2017.
 */

public class DialogFriend extends DialogFragment {

    private static final String ARG_FNAME = "ARG_FNAME";
    private static final String ARG_LNAME = "ARG_LNAME";
    private static final String ARG_FRIENDCODE = "ARG_FRIENDCODE";
    String titleS,friendCode,UID;

    public DialogFriend() {
        // Required empty public constructor
    }


    public static DialogFriend newInstance(Friend friend){
        DialogFriend dialogFriend = new DialogFriend();
        Bundle args = new Bundle();
        args.putString(ARG_FNAME, friend.getfName());
        args.putString(ARG_LNAME, friend.getlName());
        args.putString(ARG_FRIENDCODE, friend.getUID());
        dialogFriend.setArguments(args);
        return dialogFriend;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.friend_dialog, null);

        Bundle args = getArguments();
        if(args!=null) {
            friendCode = args.getString(ARG_FRIENDCODE);
            String dialText = "Selected User: "+args.getString(ARG_FNAME)+ " "+ args.getString(ARG_LNAME);
            titleS = "Friend Menu";
            UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            TextView dialTV = (TextView) view.findViewById(R.id.dialTV);
            dialTV.setText(dialText);
        }

        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle(titleS)
                .setPositiveButton(R.string.dialChat, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AddChat(UID,friendCode,getActivity(),getContext());
                        Intent intentChat = new Intent(getContext(), ChatMasterDetailActivity.class);
                        getContext().startActivity(intentChat);
                    }
                })
                .setNegativeButton(R.string.dialRemove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new RemoveFriendData(UID,friendCode,getActivity(),getContext());
                        Intent intentHome = new Intent(getContext(), HomeActivity.class);//logout
                        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getContext().startActivity(intentHome);
                        dialog.dismiss();
                    }
                })
                .setNeutralButton(R.string.dialCancel, null)
                .create();

    }

}
