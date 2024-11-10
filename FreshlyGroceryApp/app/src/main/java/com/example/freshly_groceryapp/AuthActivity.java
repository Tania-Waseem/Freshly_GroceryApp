package com.example.freshly_groceryapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    private TextView loginTab, signupTab;
    private LinearLayout loginLayout, signupLayout;
    private EditText nameEditText, emailEditText, passwordEditText;
    private Button signupButton, loginButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Initialize views
        loginTab = findViewById(R.id.loginTab);
        signupTab = findViewById(R.id.signupTab);
        loginLayout = findViewById(R.id.loginLayout);
        signupLayout = findViewById(R.id.signupLayout);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);

        loginRedirectText = findViewById(R.id.loginRedirectText);
        loginButton = findViewById(R.id.loginButton);

        // Set the default view to Sign-up (or you can set to Login if preferred)
        showSignupLayout();

        // Set click listeners for tab switching
        loginTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginLayout();
            }
        });

        signupTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignupLayout();
            }
        });

        // Click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.loginEmailEditText)).getText().toString();
                String password = ((EditText) findViewById(R.id.loginPasswordEditText)).getText().toString();
                // Implement login logic here
                Toast.makeText(AuthActivity.this, "Login clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Click listener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // Implement signup logic here
                Toast.makeText(AuthActivity.this, "Sign-up clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Redirect text to switch to login
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginLayout();
            }
        });
    }

    private void showLoginLayout() {
        loginLayout.setVisibility(View.VISIBLE);
        signupLayout.setVisibility(View.GONE);
        loginTab.setTextColor(getResources().getColor(R.color.green_700)); // Active color
        signupTab.setTextColor(getResources().getColor(R.color.gray)); // Inactive color
    }

    private void showSignupLayout() {
        signupLayout.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.GONE);
        signupTab.setTextColor(getResources().getColor(R.color.green_700)); // Active color
        loginTab.setTextColor(getResources().getColor(R.color.gray)); // Inactive color
    }
}

