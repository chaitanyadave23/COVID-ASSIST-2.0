package com.example.covidassist;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageAdapter extends FragmentPagerAdapter {


    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
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
