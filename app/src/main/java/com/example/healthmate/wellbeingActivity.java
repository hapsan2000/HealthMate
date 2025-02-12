package com.example.healthmate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class wellbeingActivity extends AppCompatActivity {

    private TextView tipTextView;
    private Button newTipButton, saveTipButton;
    private ProgressBar progressBar;
    private String currentTip = "";

    private String[] tips = {
            "Take a deep breath and count to 10.",
            "Spend 5 minutes meditating today.",
            "Write down three things you are grateful for.",
            "Go for a short walk to clear your mind.",
            "Listen to your favorite relaxing music.",
            "Drink a glass of water and stretch.",
            "Smile at yourself in the mirror.",
            "Limit your social media time today.",
            "Read a positive quote before sleeping.",
            "Call or text a friend to check in on them."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellbeing);

        // Initialize UI components
        tipTextView = findViewById(R.id.tipTextView);
        newTipButton = findViewById(R.id.newTipButton);
        saveTipButton = findViewById(R.id.saveTipButton);
        progressBar = findViewById(R.id.progressBar);

        // Load last saved tip if available
        loadSavedTip();

        newTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewTip();
            }
        });

        saveTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTip();
            }
        });
    }

    private void showNewTip() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);
        tipTextView.setVisibility(View.INVISIBLE);

        // Simulate loading time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int index = random.nextInt(tips.length);
                currentTip = tips[index];

                // Hide progress bar and show tip
                progressBar.setVisibility(View.GONE);
                tipTextView.setText(currentTip);
                tipTextView.setVisibility(View.VISIBLE);
            }
        }, 1500); // Simulating a 1.5-second loading delay
    }

    private void saveTip() {
        if (!currentTip.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("WellnessTips", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("savedTip", currentTip);
            editor.apply();
            Toast.makeText(this, "Tip saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No tip to save!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSavedTip() {
        SharedPreferences sharedPreferences = getSharedPreferences("WellnessTips", MODE_PRIVATE);
        String savedTip = sharedPreferences.getString("savedTip", "Tap the button for a mental wellness tip.");
        tipTextView.setText(savedTip);
    }
}
