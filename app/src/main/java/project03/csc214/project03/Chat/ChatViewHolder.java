package project03.csc214.project03.Chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import project03.csc214.project03.Home.DialogFriend;
import project03.csc214.project03.Home.Friend;
import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/27/2017.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder{

    private TextView mFNameTV;
    private TextView mLNameTV;
    private Chat mChat;

    public ChatViewHolder(View view, final Context context, final Activity activity, final FragmentManager fragmentManager) {
        super(view);
        mFNameTV = (TextView)view.findViewById(R.id.fNameTV);
        mLNameTV = (TextView)view.findViewById(R.id.lNameTV);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click detected", "onClick: ");
                if(activity.findViewById(R.id.frame_layout_detail) == null) {
                    Log.d("click detected", "phone");
                    Intent chatIntent = new Intent(context,ChatDetailActivity.class);
                    chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    chatIntent.putExtra("friendCode",mChat.getUID());
                    chatIntent.putExtra("fName",mChat.getfName());
                    chatIntent.putExtra("lName",mChat.getlName());
                    context.startActivity(chatIntent);
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("friendCode", mChat.getUID());
                    bundle.putString("fName", mChat.getfName());
                    bundle.putString("lName", mChat.getlName());
                    Log.d("click detected", "tablet");
                    DetailChatFragment fragment = new DetailChatFragment();
                    fragment.setArguments(bundle);
                    FragmentManager manager = fragmentManager;
                    manager.beginTransaction()
                            .replace(R.id.frame_layout_detail, fragment)
                            .commit();
                }
            }
        });

    }


    public void bindChat(Chat chat) {
        mChat = chat;
        mFNameTV.setText(chat.getfName());
        mLNameTV.setText(chat.getlName());
    }


}

