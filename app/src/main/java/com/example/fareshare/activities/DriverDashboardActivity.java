package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fareshare.R;

public class DriverDashboardActivity extends AppCompatActivity {
    CardView home,profile,logout,notifications,rides,wallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverdashboard);
        //logout
        logout=findViewById(R.id.driverlogout);
        logout.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           startActivity(new Intent(DriverDashboardActivity.this,HomeScreenActivity.class));
                                       }
                                   }
        );
        //RIDES
        rides=findViewById(R.id.driverrides);
        rides.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           startActivity(new Intent(DriverDashboardActivity.this,RidesPhActivity.class));
                                       }
                                   }
        );
        //goto profile
     profile= findViewById(R.id.driverprofile);
        profile.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                         startActivity(new Intent(DriverDashboardActivity.this,DriverProfileActivity.class));
                                       }
                                   }
        );

    }
}