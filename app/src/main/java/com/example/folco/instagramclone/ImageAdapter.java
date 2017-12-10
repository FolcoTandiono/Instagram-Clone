package com.example.folco.instagramclone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.folco.instagramclone.models.User;

/**
 * Created by folco on 12/10/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private User user;

    public ImageAdapter(Context c, User user) {
        mContext = c;
        this.user = user;
    }

    public int getCount() {
        return user.getPosts().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(170, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(user.getPosts().get(position).second);
        return imageView;
    }
}