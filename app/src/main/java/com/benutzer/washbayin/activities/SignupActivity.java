package com.benutzer.washbayin.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HomeActivity;
import com.benutzer.washbayin.UserAPI.ServiceHomeActivity;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.LoginUtilities;
import com.benutzer.washbayin.utilities.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    @Bind(R.id.fullnameSignupId) EditText _fullnameSignUp;
    @Bind(R.id.emailSignupId) EditText _emailSignUp;
    @Bind(R.id.passwordSignupId) EditText _passwordSignUp;
    @Bind(R.id.signupButtonSignupId) AppCompatButton _buttonSignUp;
    @Bind(R.id.phoneNumSignupId) EditText _phoneNumber;
    LoginUtilities LoginUtilities;
    CommonUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        LoginUtilities = new LoginUtilities(this.getApplicationContext());
        commonUtils = new CommonUtils(this.getApplicationContext());
        Toast.makeText(this, commonUtils.getSessionToken(), Toast.LENGTH_SHORT).show();
        handleClicks();
    }

    private void handleClicks(){
        _buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                handleSignUpAndLogin();
            }
        });
    }

    private void handleSignUpAndLogin(){
        if(!LoginUtilities.validateUsername(_emailSignUp.getText().toString())){
            Toast.makeText(this, getResources().getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
        }
        else{
            if(!LoginUtilities.initiateSignup(new UserModel(
                    _emailSignUp.getText().toString(),
                    _fullnameSignUp.getText().toString(),
                    _passwordSignUp.getText().toString(),
                    _phoneNumber.getText().toString(),
                    0
            ))){
                Toast.makeText(this, getResources().getString(R.string.failed_signup), Toast.LENGTH_SHORT).show();
            }
            else{
                if(!LoginUtilities.checkForLogin()){
                    LoginUtilities.cleanSharedPref();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    handleDataLoading();
                }
            }
        }
    }

    private void handleDataLoading(){
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    commonUtils.loadInitialData();
                    progressDialog.dismiss();
                }catch(Exception ex){
                    ex.printStackTrace();
                }finally {
                    JSONObject jsonObject = null;
                    try{
                        jsonObject = new JSONObject(commonUtils.response);
                        if((jsonObject.get("utype")) == 1) {
                            Intent intent =  new Intent(SignupActivity.this, ServiceHomeActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent =  new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, 3000);
    }
}
