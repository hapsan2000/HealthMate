package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class  regiterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etPassword2;
    private CheckBox cbAgree;
    private Button btnSignUp;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter); // Ensure XML file name is correct

        // Bind UI components
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        cbAgree = findViewById(R.id.cbAgree);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);

        // Sign-up button click event
        btnSignUp.setOnClickListener(view -> registerUser());

        // Redirect to login screen if user already has an account
        tvSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(regiterActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etPassword2.getText().toString().trim();

        // Validation checks
        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required");
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Valid email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return;
        }
        if (!password.equals(confirmPassword)) {
            etPassword2.setError("Passwords do not match");
            return;
        }
        if (!cbAgree.isChecked()) {
            Toast.makeText(this, "You must agree to the terms", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registration successful
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

        // Redirect to Login Activity
        Intent intent = new Intent(regiterActivity.this, loginActivity.class);
        startActivity(intent);
        finish();
    }
}
