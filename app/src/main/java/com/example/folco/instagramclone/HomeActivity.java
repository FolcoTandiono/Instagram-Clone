package com.example.folco.instagramclone;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.folco.instagramclone.models.User;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity implements PostFragment.OnFragmentInteractionListener, FindFragment.OnFragmentInteractionListener, AddFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    public TabLayout profileTabLayout;
    public ViewPager profileViewPager;
    public ImageView profileMoreIcon;
    public User user;
    public ListView findListView;
    public ArrayAdapter<String> arrayAdapter;

    public View findFound;
    public View findNotFound;

    public ImageView findSearchIcon;

    public TextView findProfileUsername;
    public TextView findProfilePosts;
    public TextView findProfileFollowers;
    public TextView findProfileFollowing;
    public TextView findProfileName;

    public View getFindFound() {
        return findFound;
    }

    public void setFindFound(View findFound) {
        this.findFound = findFound;
    }

    public View getFindNotFound() {
        return findNotFound;
    }

    public void setFindNotFound(View findNotFound) {
        this.findNotFound = findNotFound;
    }

    public ImageView getFindSearchIcon() {
        return findSearchIcon;
    }

    public void setFindSearchIcon(ImageView findSearchIcon) {
        this.findSearchIcon = findSearchIcon;
    }

    public TextView getFindProfileUsername() {
        return findProfileUsername;
    }

    public void setFindProfileUsername(TextView findProfileUsername) {
        this.findProfileUsername = findProfileUsername;
    }

    public TextView getFindProfilePosts() {
        return findProfilePosts;
    }

    public void setFindProfilePosts(TextView findProfilePosts) {
        this.findProfilePosts = findProfilePosts;
    }

    public TextView getFindProfileFollowers() {
        return findProfileFollowers;
    }

    public void setFindProfileFollowers(TextView findProfileFollowers) {
        this.findProfileFollowers = findProfileFollowers;
    }

    public TextView getFindProfileFollowing() {
        return findProfileFollowing;
    }

    public void setFindProfileFollowing(TextView findProfileFollowing) {
        this.findProfileFollowing = findProfileFollowing;
    }

    public TextView getFindProfileName() {
        return findProfileName;
    }

    public void setFindProfileName(TextView findProfileName) {
        this.findProfileName = findProfileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TabLayout getProfileTabLayout() {
        return profileTabLayout;
    }

    public void setProfileTabLayout(TabLayout profileTabLayout) {
        this.profileTabLayout = profileTabLayout;
    }

    public ViewPager getProfileViewPager() {
        return profileViewPager;
    }

    public void setProfileViewPager(ViewPager profileViewPager) {
        this.profileViewPager = profileViewPager;
    }

    public ListView getFindListView() {
        return findListView;
    }

    public void setFindListView(ListView findListView) {
        this.findListView = findListView;
    }

    public ImageView getProfileMoreIcon() {
        return profileMoreIcon;
    }

    public void setProfileMoreIcon(ImageView profileMoreIcon) {
        this.profileMoreIcon = profileMoreIcon;
    }

    public ArrayAdapter<String> getArrayAdapter() {
        return arrayAdapter;
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);

        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeLinearLayout, new PostFragment()).commit();
        bnve.setCurrentItem(0);

        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch(item.getItemId()) {
                    case R.id.homeIcon:
                        fragmentTransaction.replace(R.id.homeLinearLayout, new PostFragment()).commit();
                        return true;
                    case R.id.findIcon:
                        fragmentTransaction.replace(R.id.homeLinearLayout, new FindFragment()).commit();
                        return true;
                    case R.id.addIcon:
                        fragmentTransaction.replace(R.id.homeLinearLayout, new AddFragment()).commit();
                        return true;
                    case R.id.personIcon:
                        fragmentTransaction.replace(R.id.homeLinearLayout, new ProfileFragment()).commit();
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
