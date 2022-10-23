package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fareshare.R;

    public class PassengerDashboardActivity extends AppCompatActivity {
        CardView home,profile,logout,notifications,rides,wallet;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_passengerdashboard);
         //profile
            profile = findViewById(R.id.pprofile);
            profile.setOnClickListener(view -> startActivity(new Intent(PassengerDashboardActivity.this,PassengerProfileActivity.class))
            );
            //logout
            logout=findViewById(R.id.passengerlogout);
           logout.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               startActivity(new Intent(PassengerDashboardActivity.this,HomeScreenActivity.class));
                                           }
                                       }
            );
//rides
            //RIDES
            rides=findViewById(R.id.passengerrides);
            rides.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             startActivity(new Intent(PassengerDashboardActivity.this,RidesPhActivity.class));
                                         }
                                     }
            );
        }}
