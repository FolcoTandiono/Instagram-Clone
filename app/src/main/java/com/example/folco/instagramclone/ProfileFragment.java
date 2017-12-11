package com.example.folco.instagramclone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.folco.instagramclone.models.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.folco.instagramclone.Login.userNow;
import static com.example.folco.instagramclone.R.id.followRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public List<User> followersList = new ArrayList<User>();
    public List<User> followingList = new ArrayList<User>();
    public FollowAdapter followersAdapter;
    public FollowAdapter followingAdapter;
    public RecyclerView followersRecyclerView;
    public RecyclerView followingRecyclerView;

    public AlertDialog.Builder builderFollowers;
    public AlertDialog.Builder builderFollowing;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void updateFollowers() {
            followersList.clear();

            for (User user : userNow.getFollowers()) {
                followersList.add(user);
            }

            followersAdapter.notifyDataSetChanged();
    }

    public void updateFollowing() {
        followingList.clear();

        for (User user : userNow.getFollowing()) {
            followingList.add(user);
        }

        followingAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView profileMoreIcon = (ImageView) view.findViewById(R.id.profileMoreIcon);

        profileMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ProfileOptionActivity.class);
                startActivity(i);
            }
        });

        Button profileEditProfile = (Button) view.findViewById(R.id.profileEditProfile);

        profileEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        ((TextView) view.findViewById(R.id.profileUsername)).setText(userNow.getUsername());
        ((TextView) view.findViewById(R.id.profilePosts)).setText(String.valueOf(userNow.getPosts().size()));
        ((TextView) view.findViewById(R.id.profileFollowers)).setText(String.valueOf(userNow.getFollowers().size()));
        ((TextView) view.findViewById(R.id.profileFollowing)).setText(String.valueOf(userNow.getFollowing().size()));
        ((TextView) view.findViewById(R.id.profileName)).setText(userNow.getName());

        Bitmap bmp = userNow.getProfilePicture();

        if (bmp != null) ((CircleImageView) view.findViewById(R.id.profileProfilePicture)).setImageBitmap(bmp);

        GridView profileGridview = (GridView) view.findViewById(R.id.profileGridview);
        profileGridview.setAdapter(new ImageAdapter(getActivity(), userNow));

        builderFollowers = new AlertDialog.Builder(getActivity());
        builderFollowing = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater1 = LayoutInflater.from(getActivity());
        View content = inflater1.inflate(R.layout.follow, null);
        View content1 = inflater1.inflate(R.layout.follow, null);

        builderFollowers.setView(content);
        builderFollowing.setView(content1);

        followersRecyclerView = (RecyclerView) content.findViewById(followRecyclerView);
        followingRecyclerView = (RecyclerView) content1.findViewById(followRecyclerView);

        builderFollowers.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builderFollowing.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        followingAdapter = new FollowAdapter(followingList);
        followersAdapter = new FollowAdapter(followersList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());

        followingRecyclerView.setLayoutManager(mLayoutManager);
        followingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        followersRecyclerView.setLayoutManager(mLayoutManager1);
        followersRecyclerView.setItemAnimator(new DefaultItemAnimator());

        followingRecyclerView.setAdapter(followingAdapter);
        followersRecyclerView.setAdapter(followersAdapter);

        final AlertDialog alertDialogFollowers = builderFollowers.create();
        final AlertDialog alertDialogFollowing = builderFollowing.create();

        updateFollowers();
        updateFollowing();

        ((LinearLayout) view.findViewById(R.id.profileFollowersLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFollowers();
                alertDialogFollowers.show();
            }
        });

        ((LinearLayout) view.findViewById(R.id.profileFollowingLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFollowing();
                alertDialogFollowing.show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView) getView().findViewById(R.id.profileUsername)).setText(userNow.getUsername());
        ((TextView) getView().findViewById(R.id.profilePosts)).setText(String.valueOf(userNow.getPosts().size()));
        ((TextView) getView().findViewById(R.id.profileFollowers)).setText(String.valueOf(userNow.getFollowers().size()));
        ((TextView) getView().findViewById(R.id.profileFollowing)).setText(String.valueOf(userNow.getFollowing().size()));
        ((TextView) getView().findViewById(R.id.profileName)).setText(userNow.getName());

        Bitmap bmp = userNow.getProfilePicture();

        if (bmp != null) ((CircleImageView) getView().findViewById(R.id.profileProfilePicture)).setImageBitmap(bmp);
    }


}
