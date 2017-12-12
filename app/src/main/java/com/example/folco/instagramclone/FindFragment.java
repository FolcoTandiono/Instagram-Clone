package com.example.folco.instagramclone;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import static com.example.folco.instagramclone.models.User.listUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User userFind;
    public String findWhat;

    private OnFragmentInteractionListener mListener;

    public List<User> followersList = new ArrayList<User>();
    public List<User> followingList = new ArrayList<User>();
    public FollowAdapter followersAdapter;
    public FollowAdapter followingAdapter;
    public RecyclerView followersRecyclerView;
    public RecyclerView followingRecyclerView;

    public AlertDialog.Builder builderFollowers;
    public AlertDialog.Builder builderFollowing;

    public ProgressDialog progressDialog;
    public int progress;

    public FindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
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

    public boolean findUser() {
        for (User user : listUser) {
            if (user.getUsername().equals(findWhat)) {
                userFind = user;
                return true;
            }
        }
        return false;
    }

    public void updateProfile() {
        ((TextView) getView().findViewById(R.id.findProfileUsername)).setText(userFind.getUsername());
        ((TextView) getView().findViewById(R.id.findProfilePosts)).setText(String.valueOf(userFind.getPosts().size()));
        ((TextView) getView().findViewById(R.id.findProfileFollowers)).setText(String.valueOf(userFind.getFollowers().size()));
        ((TextView) getView().findViewById(R.id.findProfileFollowing)).setText(String.valueOf(userFind.getFollowing().size()));
        ((TextView) getView().findViewById(R.id.findProfileName)).setText(String.valueOf(userFind.getName()));
        if (userFind.getProfilePicture() != null) {
            ((CircleImageView) getView().findViewById(R.id.findProfilePicture)).setImageBitmap(userFind.getProfilePicture());
        }
    }

    public void updateFollowers() {
        followersList.clear();

        for (User user : userFind.getFollowers()) {
            followersList.add(user);
        }

        followersAdapter.notifyDataSetChanged();
    }

    public void updateFollowing() {
        followingList.clear();

        for (User user : userFind.getFollowing()) {
            followingList.add(user);
        }

        followingAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        ImageView findFindIcon = (ImageView) view.findViewById(R.id.findFindIcon);
        final TextView findUsername = (TextView) view.findViewById(R.id.findUsername);

        progressDialog = new ProgressDialog(getContext());

        findFindIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                progress = 0;

                progressDialog.setMessage("Please wait....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.show();

                progress = 0;

                final Handler handler = new Handler();

                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress < 2) {
                            // Update the progress status
                            progress += 1;

                            // Try to sleep the thread for 20 milliseconds
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the progress status
                                    progressDialog.setProgress(progress);
                                    // If task execution completed
                                    if (progress == 2) {
                                        // Dismiss/hide the progress dialog
                                        progress++;
                                        progressDialog.dismiss();

                                        findWhat = findUsername.getText().toString();
                                        if (findUser()) {
                                            getView().findViewById(R.id.findFound).setVisibility(View.VISIBLE);
                                            getView().findViewById(R.id.findNotFound).setVisibility(View.GONE);
                                            updateProfile();

                                            GridView findGridview = (GridView) getView().findViewById(R.id.findGridview);
                                            findGridview.setAdapter(new ImageAdapter(getActivity(), userFind));

                                            final Button editProfileFollow = ((Button) getView().findViewById(R.id.findProfileFollow));

                                            if (findWhat.equals(userNow.getUsername())) {
                                                editProfileFollow.setText("Edit your profile");
                                                editProfileFollow.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                                                        startActivity(intent);
                                                    }

                                                });
                                            }
                                            else {
                                                if (userFind.getFollowers().contains(userNow)) {
                                                    editProfileFollow.setText("Unfollow");
                                                }
                                                else {
                                                    editProfileFollow.setText("Follow");
                                                }
                                                editProfileFollow.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        if (editProfileFollow.getText().toString().equals("Follow")) {
                                                            editProfileFollow.setText("Unfollow");
                                                            userNow.getFollowing().add(userFind);
                                                            userFind.getFollowers().add(userNow);
                                                            updateProfile();
                                                        }
                                                        else {
                                                            editProfileFollow.setText("Follow");
                                                            userNow.getFollowing().remove(userFind);
                                                            userFind.getFollowers().remove(userNow);
                                                            updateProfile();
                                                        }
                                                    }
                                                });
                                            }

                                            builderFollowers = new AlertDialog.Builder(getActivity());
                                            builderFollowing = new AlertDialog.Builder(getActivity());

                                            LayoutInflater inflater1 = LayoutInflater.from(getActivity());
                                            View content = inflater1.inflate(R.layout.follow, null);
                                            View content1 = inflater1.inflate(R.layout.follow, null);

                                            builderFollowers.setView(content);
                                            builderFollowing.setView(content1);

                                            followersRecyclerView = (RecyclerView) content.findViewById(R.id.followRecyclerView);
                                            followingRecyclerView = (RecyclerView) content1.findViewById(R.id.followRecyclerView);

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

                                            ((LinearLayout) getView().findViewById(R.id.findProfileFollowersLayout)).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    updateFollowers();
                                                    alertDialogFollowers.show();
                                                }
                                            });

                                            ((LinearLayout) getView().findViewById(R.id.findProfileFollowingLayout)).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    updateFollowing();
                                                    alertDialogFollowing.show();
                                                }
                                            });
                                        }
                                        else {
                                            getView().findViewById(R.id.findFound).setVisibility(View.GONE);
                                            getView().findViewById(R.id.findNotFound).setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }).start(); // Start the operation

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
        if (getView().findViewById(R.id.findFound).getVisibility() == View.VISIBLE){
            updateProfile();
        }
    }
}
