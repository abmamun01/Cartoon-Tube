package com.mamunsproject.youtubekids.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentPagerAdapter {

    private final ArrayList<String> fragmentTitleList = new ArrayList<>();
    private final ArrayList<Fragment> fragmentArrayList;

    public AdapterViewPager(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        fragmentTitleList.add("Videos");
        fragmentTitleList.add("PlayList");

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);

    }
}
