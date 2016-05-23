package com.benutzer.washbayin.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.benutzer.washbayin.R;

/**
 * Created by amitesh on 20/2/16.
 */
public class HomeBelowFragments extends Fragment {
    //@Bind(R.id.linearLayoutOneHomeBelowFragBelowId) LinearLayout _oneLinearLayout;
    //@Bind(R.id.linearLayoutTwoHomeBelowFragBelowId) LinearLayout _twoLinearLayout;
    //@Bind(R.id.textViewLinearLayoutTwoHomeFragBelowId) TextView _oneDiscountMessage;
    //@Bind(R.id.textView2LinearLayoutTwoHomeFragBelowId) TextView _twoDiscoutnMessage;

    LinearLayout _oneLinearLayout;
    LinearLayout _twoLinearLayout;
    SendHandlersToHome activityJacker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewBelow = inflater.inflate(R.layout.fragment_home_below, container);
        //ButterKnife.bind(viewBelow);

        _oneLinearLayout = (LinearLayout) viewBelow.findViewById(R.id.linearLayoutOneHomeBelowFragBelowId);
        _twoLinearLayout = (LinearLayout) viewBelow.findViewById(R.id.linearLayoutTwoHomeBelowFragBelowId);

        handleClickListeners();
        handleTextMessages();

        return viewBelow;
    }

    public interface SendHandlersToHome{
        public void handleIntentCreationHome(String message);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityJacker = (SendHandlersToHome) activity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void handleClickListeners(){
        _oneLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityJacker.handleIntentCreationHome("LIST_WASH");
            }
        });

        _twoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityJacker.handleIntentCreationHome("LIST_DRY");
            }
        });
    }

    private void handleTextMessages(){
        //handle the discount messages here
    }
}
