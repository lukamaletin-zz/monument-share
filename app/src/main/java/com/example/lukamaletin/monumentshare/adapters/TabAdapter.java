package com.example.lukamaletin.monumentshare.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.fragments.AllMonumentsFragment_;
import com.example.lukamaletin.monumentshare.fragments.TypesFragment_;
import com.example.lukamaletin.monumentshare.fragments.UserMonumentsFragment_;

public class TabAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_TABS = 3;

    private Context context;

    public TabAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        this.context = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllMonumentsFragment_.builder().build();
            case 1:
                return UserMonumentsFragment_.builder().build();
            case 2:
                return TypesFragment_.builder().build();
            default:
                throw new RuntimeException("Tab index out of range");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.tab1_name);
            case 1:
                return context.getString(R.string.tab2_name);
            case 2:
                return context.getString(R.string.tab3_name);
            default:
                throw new RuntimeException("Tab index out of range");
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
