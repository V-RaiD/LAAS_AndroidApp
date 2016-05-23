package com.benutzer.washbayin.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benutzer.washbayin.R;
import com.benutzer.washbayin.views.CustomPagerAdapter;

/**
 * Created by amitesh on 19/2/16.
 */
public class HomeTopFragments extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pagerView = inflater.inflate(R.layout.fragment_home_top, container, false);

        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(pagerView.getContext());
        ViewPager viewPager = (ViewPager) pagerView.findViewById(R.id.pagerHomeTopId);

        viewPager.setAdapter(customPagerAdapter);

        return pagerView;
    }
}
