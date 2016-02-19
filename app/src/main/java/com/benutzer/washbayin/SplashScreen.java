package com.benutzer.washbayin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.Bind;

import com.benutzer.washbayin.activities.HomeActivity;
import com.benutzer.washbayin.activities.LoginActivity;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.LoginUtilities;

/**
 * Created by amitesh on 18/2/16.
 */
public class SplashScreen extends AppCompatActivity {
    int trigger = 0;
    int timerStop = 0;
    int[] listImagesSplash;

    @Bind(R.id.laas_splashScreenDynamic) ImageView dynamoImageView;
    @Bind(R.id.laas_SplashTag) ImageView dynamoImageViewSplashTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_screen);

        ButterKnife.bind(this);

        listImagesSplash = new int[]{
                R.drawable.icon_washbay_white_iconsiron,
                R.drawable.icon_washbay_white_iconsdry,
                R.drawable.icon_washbay_white_iconstag
        };

        handleSplashTicker();
        handleSplashScreen();
    }

    private void handleSplashTicker(){
        dynamoImageView.setVisibility(View.VISIBLE);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dynamoImageView.setVisibility(View.INVISIBLE);
                dynamoImageView.setImageResource(listImagesSplash[trigger]);
                dynamoImageView.setVisibility(View.VISIBLE);
                trigger++;
                if(trigger >= listImagesSplash.length){
                    dynamoImageViewSplashTag.setVisibility(View.VISIBLE);
                    return;
                }
                dynamoImageView.postDelayed(this, 500);
            }
        };
        dynamoImageView.postDelayed(runnable, 500);
    }

    private void handleSplashScreen(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    if(!LoginUtilities.checkForLogin())
                        sleep(2000);
                    else{
                        long timeStamp = System.currentTimeMillis();
                        if(LoginUtilities.oldLogin())
                        CommonUtils.loadInitialData();
                        else {
                            LoginUtilities.cleanSharedPref();
                            if ((timeStamp + 2000) > System.currentTimeMillis()) {
                                sleep((timeStamp + 2000) - System.currentTimeMillis());
                            }
                        }
                    }
                }catch (Exception ex){

                }finally {
                    if(!LoginUtilities.checkForLogin()){
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent =  new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();
    }
}
