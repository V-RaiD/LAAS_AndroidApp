package com.benutzer.washbayin.utilities;

import android.content.Intent;

import com.benutzer.washbayin.activities.LoginActivity;
import com.benutzer.washbayin.activities.SignupActivity;

/**
 * Created by amitesh on 19/2/16.
 */
public class LoginUtilities {
    private static boolean checkLoginValue = false;
    private static boolean loginStatus = false;
    private static boolean logoutStatus = false;
    private static boolean signupStatus = false;
    private static boolean cleanStatus = false;
    private static boolean errorUsername = false;

    public static boolean validateUsername(){
        return errorUsername;
    }
    public static boolean checkForLogin(){
        return checkLoginValue;
    }

    public static boolean intiateLogin(String username, String password){
        return loginStatus;
    }

    public static boolean oldLogin(){
        return loginStatus;
    }

    public static boolean intiateLogout(){
        cleanSharedPref();
        return logoutStatus;
    }

    public static boolean initiateSignup(UserModel userModel){
        return signupStatus;
    }

    public static boolean cleanSharedPref(){
        return cleanStatus;
    }
}
