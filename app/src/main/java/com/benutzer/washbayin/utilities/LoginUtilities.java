package com.benutzer.washbayin.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.benutzer.washbayin.BuildConfig;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HTTPTasker;
import com.benutzer.washbayin.activities.LoginActivity;
import com.benutzer.washbayin.activities.SignupActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amitesh on 19/2/16.
 */
public class LoginUtilities implements HTTPResponseInterface {
    private  boolean checkLoginValue = false;
    private  boolean loginStatus = false;
    private  boolean logoutStatus = false;
    private  boolean signupStatus = false;
    private  boolean cleanStatus = false;
    private  boolean errorUsername = false;
    private SharedPreferences sharedPreferences;
    Context context;
    CommonUtils commonUtils;

    public LoginUtilities(Context ctx){
        context = ctx;
        commonUtils = new CommonUtils(ctx);
    }

    public  boolean validateUsername(String username){//validating email *@*.*
        if(username.indexOf("@") > 0){
            if(username.split("@")[1].indexOf(".") > 0){
                errorUsername = true;
            }
        }
        return errorUsername;
    }
    public  boolean checkForLogin(){
        System.out.println("GST" + commonUtils.getSessionToken());
        if(commonUtils.getSessionToken() == null || commonUtils.getSessionToken().length() <= 1)
            checkLoginValue = false;
        else
            checkLoginValue = true;

        System.out.println("CFL : " + checkLoginValue);
        return checkLoginValue;
    }

    @Override
    public void publishResponse(String response) {
        System.out.println(response);
        if(!(response.indexOf("Error") > 0)){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString(context.getString(R.string.shared_pref_at), response);
            editor.commit();
        }
        else{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
        System.out.println("LU : " + sharedPreferences.getString(context.getString(R.string.shared_pref_at), null));
        //loginStatus = true;
    }

    public boolean intiateLogin(String username, String password){
        //login request
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setContentType("application/json");
        httpStructure.setMethod("POST");
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS +
                "/w1/verification/signin");
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("username", username.toString());
            jsonObject.put("password", password.toString());
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }

        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker tasker = new HTTPTasker();
        tasker.responseInterface = this;
        try{
            if(tasker.execute(httpStructure, null, null).get().toString() == "Error"){
                loginStatus = false;
            }else{
                loginStatus = true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return loginStatus;
    }

    public  boolean intiateLogout(){
        cleanSharedPref();
        return logoutStatus;
    }

    public  boolean initiateSignup(UserModel userModel){
        //initiate signup
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setContentType("application/json");
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS +
                "/w1/verification/signup");
        httpStructure.setMethod("POST");

        JSONObject jsonObject = null;
        JSONObject jsonName = null;
        try{
            jsonObject = new JSONObject();
            jsonName = new JSONObject();
            jsonName.put("fname",userModel.get_name().split(" ")[0]);
            jsonName.put("lname",userModel.get_name().split(" ")[1]);
            jsonObject.put("name", jsonName);
            jsonObject.put("email", userModel.get_email());
            jsonObject.put("password", userModel.get_password());
            jsonObject.put("phone", userModel.get_phone());
            jsonObject.put("utype", userModel.get_utype());
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }

        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;

        try{
            if(httpTasker.execute(httpStructure, null, null).get().toString().indexOf("Error") > 0){
                signupStatus = false;
            }else{
                signupStatus = true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return signupStatus;
    }

    public  boolean cleanSharedPref(){
        return cleanStatus;
    }
}
