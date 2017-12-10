package com.example.folco.instagramclone;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.folco.instagramclone.models.User;

import java.sql.Timestamp;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by folco on 12/10/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Pair<Pair<Timestamp, User>, Bitmap>> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle;
        public TextView username;
        public ImageView post;

        public MyViewHolder(View view) {
            super(view);
            circle = (CircleImageView) view.findViewById(R.id.subPostProfilePicture);
            username = (TextView) view.findViewById(R.id.subPostUsername);
            post = (ImageView) view.findViewById(R.id.subPostImageView);
        }
    }


    public PostAdapter(List<Pair<Pair<Timestamp, User>, Bitmap>> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<Pair<Timestamp, User>, Bitmap> post = postList.get(position);

        Bitmap temp = post.first.second.getProfilePicture();
        if (temp == null) holder.circle.setImageResource(R.drawable.user_icon);
        else holder.circle.setImageBitmap(temp);

        holder.username.setText(post.first.second.getUsername().toString());
        holder.post.setImageBitmap(post.second);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}