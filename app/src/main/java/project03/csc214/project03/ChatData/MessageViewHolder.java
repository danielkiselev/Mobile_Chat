package project03.csc214.project03.ChatData;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import project03.csc214.project03.Home.DialogFriend;
import project03.csc214.project03.Home.Friend;
import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/28/2017.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView mMessageTV;
    private Message mMessage;

    public MessageViewHolder(View view) {
        super(view);
        mMessageTV = (TextView)view.findViewById(R.id.messageTV);
    }

    public void bindMessage(Message message) {
        mMessage = message;
        mMessageTV.setText(message.getMessage());
    }
}
