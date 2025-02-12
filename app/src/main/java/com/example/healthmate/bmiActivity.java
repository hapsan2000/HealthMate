package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bmiActivity extends AppCompatActivity {

    private EditText etWeight, etHeight;
    private Button btnCalculate;
    private TextView tvResult;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton ACC = findViewById(R.id.btnBack2);
        ACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( bmiActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        etWeight = findViewById(R.id.etweight);
        etHeight = findViewById(R.id.etheight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        progressBar = findViewById(R.id.progressBar);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculateBMI();
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1500);
            }
        });
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                if (height > 0) {
                    double bmi = weight / (height * height);
                    String bmiCategory = getBMICategory(bmi);
                    tvResult.setText("Your BMI: " + String.format("%.2f", bmi) + "\nCategory: " + bmiCategory);
                } else {
                    tvResult.setText("Height must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                tvResult.setText("Please enter valid numbers.");
            }
        } else {
            tvResult.setText("Please enter both weight and height.");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
