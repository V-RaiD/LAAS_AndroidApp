package com.benutzer.washbayin.utilities;

/**
 * Created by amitesh on 19/2/16.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.benutzer.washbayin.BuildConfig;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HTTPTasker;

import java.util.concurrent.ExecutionException;

public class CommonUtils implements HTTPResponseInterface{
    private boolean networkStatus = false;
    private Context context;
    private SharedPreferences sharedPreferences;
    public String response;

    public CommonUtils(Context ctx) {
        this.context = ctx;
        response = new String();
    }

    public boolean checkNetwork(){//checking for network info
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        networkStatus = networkInfo != null && networkInfo.isConnected();
        return networkStatus;
    }

    @Override
    public void publishResponse(String response) {
        System.out.println("PR : " + response);
        if(response.indexOf("Error") > 0){
            this.response = null;
            try{
                throw new Exception(response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        this.response = response;
    }

    public void loadInitialData(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS +
                "/w1/verification");
        httpStructure.setMethod("GET");
        httpStructure.setAuthToken(this.getSessionToken());
        httpStructure.setContentType("application/json");

        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        try {
            response = httpTasker.execute(httpStructure, null, null).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delSessionTok(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        System.out.println(sharedPreferences.getString(context.getString(R.string.shared_pref_at), null));

        if(sharedPreferences.contains(context.getString(R.string.shared_pref_at)))
            sharedPreferences.edit().remove(context.getString(R.string.shared_pref_at));


    }
    public String getSessionToken(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        System.out.println(sharedPreferences.getString(context.getString(R.string.shared_pref_at), null));

        if(sharedPreferences.contains(context.getString(R.string.shared_pref_at)))
            return sharedPreferences.getString(context.getString(R.string.shared_pref_at), null);
        else
            return null;
    }
}
