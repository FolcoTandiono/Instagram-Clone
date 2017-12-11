package com.example.folco.instagramclone;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.folco.instagramclone.models.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.folco.instagramclone.Login.userNow;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecyclerView postRecyclerView;
    public View view;
    public PostAdapter postAdapter;
    public List<Pair<Pair<Timestamp, User>, Bitmap>> postList = new ArrayList<Pair<Pair<Timestamp, User>, Bitmap>>();

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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

    public void updatePost() {

        postList.clear();

        for (Pair<Timestamp, Bitmap> temp : userNow.getPosts()) {
            postList.add(new Pair<Pair<Timestamp, User>, Bitmap>(new Pair<Timestamp, User>(temp.first, userNow), temp.second));
        }

        for (User user : userNow.getFollowing()) {
            for (Pair<Timestamp, Bitmap> temp : user.getPosts()) {
                postList.add(new Pair<Pair<Timestamp, User>, Bitmap>(new Pair<Timestamp, User>(temp.first, user), temp.second));
            }
        }

        Collections.sort(postList, new Comparator<Pair<Pair<Timestamp, User>, Bitmap>>() {
            @Override
            public int compare(final Pair<Pair<Timestamp, User>, Bitmap> a, final Pair<Pair<Timestamp, User>, Bitmap> b) {
                // TODO: implement your logic here
                Long aa = a.first.first.getTime(), bb = b.first.first.getTime();
                if (aa != bb) return bb.compareTo(aa);
                else {
                    return (a.first.second.getUsername().compareTo(b.first.second.getUsername()));
                }

            }
        });

        postAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_post, container, false);

        postRecyclerView = (RecyclerView) view.findViewById(R.id.postRecyclerView);

        postAdapter = new PostAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        postRecyclerView.setLayoutManager(mLayoutManager);
        postRecyclerView.setItemAnimator(new DefaultItemAnimator());
        postRecyclerView.setAdapter(postAdapter);

        ((ImageView) view.findViewById(R.id.postInstagramLogo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LinearLayoutManager layoutManager = (LinearLayoutManager) postRecyclerView
//                        .getLayoutManager();
//                layoutManager.scrollToPositionWithOffset(0, 0);
                postRecyclerView.smoothScrollToPosition(0);
            }
        });

        updatePost();

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
        updatePost();
    }
}
