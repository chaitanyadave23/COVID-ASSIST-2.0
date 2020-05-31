package com.example.covidassist.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covidassist.Fragments.ChatFragment;
import com.example.covidassist.Fragments.MyFeedFragment;
import com.example.covidassist.Fragments.UserFeedFragment;

public class PageAdapter extends FragmentPagerAdapter {


    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm,numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new UserFeedFragment();
            case 1:
                return new ChatFragment();
            case 2:
                return new MyFeedFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
