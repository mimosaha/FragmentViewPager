package com.w3engineers.fragmentviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

 
/*
============================================================================
Copyright (C) 2019 W3 Engineers Ltd. - All Rights Reserved.
Unauthorized copying of this file, via any medium is strictly prohibited
Proprietary and confidential
============================================================================
*/

public class TabFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout tableLayout;

    private int[] tabIcons = {
            R.mipmap.ic_contacts_cyan,
            R.mipmap.ic_message_cyan,
            R.mipmap.ic_setting_cyan };

    private int[] tabGrey = {
            R.mipmap.ic_contacts_grey,
            R.mipmap.ic_message_grey,
            R.mipmap.ic_setting_grey };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = view.findViewById(R.id.pager);
        tableLayout = view.findViewById(R.id.tabLayout);

        mViewPager.setAdapter(new SamplePagerAdapter(getActivity().getSupportFragmentManager()));
        tableLayout.setupWithViewPager(mViewPager);

        highLightCurrentTab(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                highLightCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void resetPrevious() {
        for (int i = 0; i < tableLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tableLayout.getTabAt(i);
            tab.setIcon(tabGrey[i]);
        }
    }

    private void highLightCurrentTab(int position) {
        resetPrevious();
        tableLayout.getTabAt(position).setIcon(tabIcons[position]);
    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments = {new FragmentOne(), new FragmentTwo(), new FragmentThree()};

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

}
