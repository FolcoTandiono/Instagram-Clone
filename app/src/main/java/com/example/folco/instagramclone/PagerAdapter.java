package com.example.folco.instagramclone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by folco on 11/12/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;
    public EmailFragment emailFragment;
    public PhoneFragment phoneFragment;

    public PagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numberOfTabs = tabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                phoneFragment = new PhoneFragment();
                return phoneFragment;
            case 1:
                emailFragment = new EmailFragment();
                return emailFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
