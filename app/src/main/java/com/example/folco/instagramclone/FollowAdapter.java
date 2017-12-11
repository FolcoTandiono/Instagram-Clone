package com.example.folco.instagramclone;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.folco.instagramclone.models.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by folco on 12/10/2017.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.MyViewHolder> {

    private List<User> listFollow;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle;
        public TextView username;

        public MyViewHolder(View view) {
            super(view);
            circle = (CircleImageView) view.findViewById(R.id.subFollowProfilePicture);
            username = (TextView) view.findViewById(R.id.subFollowUsername);
        }
    }


    public FollowAdapter(List<User> listFollow) {
        this.listFollow = listFollow;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_follow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User follow = listFollow.get(position);

        Bitmap temp = follow.getProfilePicture();
        if (temp == null) holder.circle.setImageResource(R.drawable.user_icon);
        else holder.circle.setImageBitmap(temp);

        holder.username.setText(follow.getUsername().toString());
    }

    @Override
    public int getItemCount() {
        return listFollow.size();
    }
}