package com.benutzer.washbayin.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

import com.benutzer.washbayin.R;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.LoginUtilities;

/**
 * Created by amitesh on 19/2/16.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.userFieldLoginScreenId) EditText _usernameText;
    @Bind(R.id.passwordFieldLoginScreenId) EditText _passwordText;
    @Bind(R.id.loginButtonLoginScreenId) AppCompatButton _loginButton;
    @Bind(R.id.signupLinkOptionLoginScreen) TextView _signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ButterKnife.bind(this);

        handleClicks();
    }

    private void handleClicks(){
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginHandler();
            }
        });

        _passwordText.setImeActionLabel("Login", EditorInfo.IME_ACTION_DONE);
        _passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginHandler();
                    return true;
                }
                return false;
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginHandler(){
        if(!CommonUtils.checkNetwork()){
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
        else{
            if(!LoginUtilities.validateUsername() || !LoginUtilities.intiateLogin(_usernameText.getText().toString(), _passwordText.getText().toString())){
                Toast.makeText(this, getResources().getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
            }
            else{
                    handleDataLoading();
            }
        }
    }

    private void handleDataLoading(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    CommonUtils.loadInitialData();
                    progressDialog.dismiss();
                }catch(Exception ex){
                    ex.printStackTrace();
                }finally {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}
