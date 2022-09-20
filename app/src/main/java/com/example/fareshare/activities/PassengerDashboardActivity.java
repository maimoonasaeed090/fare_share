package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fareshare.R;

    public class PassengerDashboardActivity extends AppCompatActivity {
        CardView home,profile,raitings,notifications,rides,wallet;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_passengerdashboard);
         profile = findViewById(R.id.pprofile);
            profile.setOnClickListener(view -> startActivity(new Intent(PassengerDashboardActivity.this,PassengerProfileActivity.class))
            );

        }}
