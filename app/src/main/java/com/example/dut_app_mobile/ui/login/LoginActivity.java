package com.example.dut_app_mobile.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dut_app_mobile.MainActivity;
import com.example.dut_app_mobile.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean connect = true ;
                loadingProgressBar.setVisibility(View.VISIBLE);
                if (!LoginActivity.isUserNameValid(usernameEditText.getText().toString())) {
                    usernameEditText.setError("une adresse mail doit contenir un @");
                    connect = false ;
                }
                if (!LoginActivity.isPasswordValid(passwordEditText.getText().toString())) {
                    passwordEditText.setError("doit être supérieur à 5");
                    connect = false ;
                }
                if (connect){
                    updateUiWithUser();
                }
            }
        });
    }
    /*When the connexion succeed */
    private void updateUiWithUser() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    // A placeholder username validation check
    public static boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }
    // A placeholder password validation check
    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
