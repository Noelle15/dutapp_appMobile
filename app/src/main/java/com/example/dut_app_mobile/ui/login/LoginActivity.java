package com.example.dut_app_mobile.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //BDD
        mAuth = FirebaseAuth.getInstance();

        //Views
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        // On click to connect
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Boolean connect = true;

                loadingProgressBar.setVisibility(View.VISIBLE);
                if (!LoginActivity.isUserNameValid(email)) {
                    usernameEditText.setError("une adresse mail doit contenir un @");
                    connect = false;
                }
                if (!LoginActivity.isPasswordValid(password)) {
                    passwordEditText.setError("doit être supérieur à 5");
                    connect = false;
                }

                if (connect) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("Login", "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            usernameEditText.setText("");
                                            passwordEditText.setText("");
                                            updateUiWithUser(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("Login", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(LoginActivity.this, "Authentication failed, Unknown User.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            });
                }
            }
        });
    }
    /*When the connexion succeed */
    private void updateUiWithUser(FirebaseUser user) {
        LoginActivity.this.finish();
        // Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
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
            return false;
        }
    }
    // A placeholder password validation check
    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
