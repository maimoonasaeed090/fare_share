package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fareshare.R;

public class AdminDashboardActivity extends AppCompatActivity {

    CardView requests,allpassengers,alldrivers,createfare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        allpassengers=findViewById(R.id.allpassengers);


        allpassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(AdminDashboardActivity.this, PassengerListActivity.class);
                startActivity(i);

            }
        });
        alldrivers=findViewById(R.id.alldrivers);
        alldrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c;
                c = new Intent(AdminDashboardActivity.this, DriversListActivity.class);
                startActivity(c);

            }
        });

        createfare = findViewById(R.id.create_fare);
        createfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(AdminDashboardActivity.this, AddFareActivity.class);
                startActivity(i);

            }
        });
        requests = findViewById(R.id.requests);
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(AdminDashboardActivity.this, RequestsActivity.class);
                startActivity(i);
            }
        });
    }
}