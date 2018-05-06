package project03.csc214.project03.Home;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/26/2017.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder{


    private TextView mFNameTV;
    private TextView mLNameTV;
    private Friend mFriend;

    public HomeViewHolder(View view) {
        super(view);
        mFNameTV = (TextView)view.findViewById(R.id.fNameTV);
        mLNameTV = (TextView)view.findViewById(R.id.lNameTV);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCA = (AppCompatActivity)v.getContext();
                FragmentManager manager = appCA.getSupportFragmentManager();
                DialogFriend dialogFriend = DialogFriend.newInstance(mFriend);
                dialogFriend.show(manager, "DialogFriend");
            }
        });
    }

    public void bindFriend(Friend friend) {
        mFriend = friend;
        mFNameTV.setText(friend.getfName());
        mLNameTV.setText(friend.getlName());
    }
}

