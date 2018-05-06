package project03.csc214.project03.ChatData;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import project03.csc214.project03.Home.Friend;
import project03.csc214.project03.Home.HomeViewHolder;
import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/28/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{

    private List<Message> mMessages;

    public MessageAdapter(List<Message> messages){
        Log.d("arrived", "MessageAdapter: ");
        mMessages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout_message, parent, false);
        MessageViewHolder holder = new MessageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bindMessage(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

}
