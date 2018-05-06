package project03.csc214.project03.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import project03.csc214.project03.R;

/**
 * Created by Daniel on 6/25/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

    private List<Friend> mFriends;

    public HomeAdapter(List<Friend> friends){
        mFriends = friends;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout_friends, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bindFriend(mFriends.get(position));
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

}
