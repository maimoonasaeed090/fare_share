package com.example.fareshare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fareshare.R;

public class HomeScreenActivity extends AppCompatActivity {

    CardView cardAmdmin,cardDriver,cardPassenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        cardAmdmin=  findViewById(R.id.cardAdmin);
        cardDriver=  findViewById(R.id.carddriver);
        cardPassenger=  findViewById(R.id.cardpassenger);

        cardAmdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i= new Intent(HomeScreenActivity.this, AdminLoginActivity.class);
                startActivity(i);

            }
        });
        cardDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x;
                x= new Intent(HomeScreenActivity.this, DriverLoginActivity.class);
                startActivity(x);

            }
        });
        cardPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i= new Intent(HomeScreenActivity.this, PassengerLoginActivity.class);
                startActivity(i);

            }
        });
    }
}