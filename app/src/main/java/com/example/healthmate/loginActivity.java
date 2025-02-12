package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUp, tvForgotPassword;
    private ImageView backButton;

    // Hardcoded credentials (For Testing Only)
    private final String VALID_EMAIL = "admin@example.com";
    private final String VALID_PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind UI elements
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Login button click event
        btnLogin.setOnClickListener(view -> loginUser());

        // Back button click event
        backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Sign-up navigation (Redirect to Register Page)
        tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(loginActivity.this, regiterActivity.class);
            startActivity(intent);
        });


    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }

        // Hardcoded login validation
        if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)) {
            Toast.makeText(loginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

            // Navigate to Main Activity (Dashboard)
            Intent intent = new Intent(loginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(loginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }
}
