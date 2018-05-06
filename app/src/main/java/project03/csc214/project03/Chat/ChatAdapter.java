package project03.csc214.project03.Chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import project03.csc214.project03.Home.Friend;
import project03.csc214.project03.Home.HomeViewHolder;
import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/27/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder>{

    private List<Chat> mChats;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;

    public ChatAdapter(List<Chat> chats, Context context, Activity activity,FragmentManager fragmentManager){
        mChats = chats;
        mContext = context;
        mActivity = activity;
        mFragmentManager = fragmentManager;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout_friends, parent, false);
        ChatViewHolder holder = new ChatViewHolder(view,mContext, mActivity, mFragmentManager);//context, activity, frag manage
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.bindChat(mChats.get(position));
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

}

