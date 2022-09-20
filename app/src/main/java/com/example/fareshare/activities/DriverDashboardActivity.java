package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fareshare.R;

public class DriverDashboardActivity extends AppCompatActivity {
    CardView home,profile,raitings,notifications,rides,wallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverdashboard);
        //
     profile= findViewById(R.id.driverprofile);
        profile.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                         startActivity(new Intent(DriverDashboardActivity.this,DriverProfileActivity.class));
                                       }
                                   }
        );
//rides

        rides= findViewById(R.id.driverrides);
        rides.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           startActivity(new Intent(DriverDashboardActivity.this,RidesPhActivity.class));
                                       }
                                   }
        );

    }
}