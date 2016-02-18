package com.benutzer.washbayin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.benutzer.washbayin.activities.LoginActivity;

/**
 * Created by amitesh on 18/2/16.
 */
public class SplashScreen extends AppCompatActivity {
    int trigger = 0;
    int timerStop = 0;
    ImageView dynamoImageView;
    int[] listImagesSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_screen);

        listImagesSplash = new int[]{
                R.drawable.icon_washbay_watery_iron_label,
                R.drawable.icon_washbay_watery_dry_label,
                R.drawable.icon_washbay_watery_all_label
        };
        dynamoImageView = (ImageView) findViewById(R.id.laas_splashScreenDynamic);

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
                    dynamoImageView = (ImageView) findViewById(R.id.laas_SplashTag);
                    dynamoImageView.setVisibility(View.VISIBLE);
                    return;
                }
                dynamoImageView.postDelayed(this, 500);
            }
        };
        dynamoImageView.postDelayed(runnable, 500);
        System.out.println("Post Run trigger : " + trigger);
    }

    private void handleSplashScreen(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (Exception ex){

                }finally {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
