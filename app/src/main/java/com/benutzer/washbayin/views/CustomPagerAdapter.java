package com.benutzer.washbayin.views;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.benutzer.washbayin.R;

/**
 * Created by amitesh on 19/2/16.
 */
public class CustomPagerAdapter extends PagerAdapter {
    Context pagerContext;
    LayoutInflater pagerInflator;
    int[] pagerDrawableArray = {
            R.drawable.hanger_clothes,
            R.drawable.ironing,
            R.drawable.unclean_clean,
            R.drawable.hanger_clothes,
            R.drawable.washing_machine
    };

    public CustomPagerAdapter(Context context){
        pagerContext = context;
        pagerInflator = (LayoutInflater) pagerContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pagerDrawableArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pagerInflator.inflate(R.layout.custom_pager, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.pagerImageViewTickerID);
        imageView.setImageResource(pagerDrawableArray[(position%5)]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
