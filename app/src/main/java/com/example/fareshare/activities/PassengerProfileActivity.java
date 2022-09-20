package com.example.fareshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fareshare.R;
import com.example.fareshare.util.AppConstants;
import com.example.fareshare.util.TinyDB;

public class PassengerProfileActivity extends AppCompatActivity {
  TextView passengername, passengeremail, passengercellno, passengergender;

    TinyDB tinyDB;
   Button editrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_passenger_profile);
        passengername=findViewById(R.id.pname);
        passengeremail=findViewById(R.id.pemail);
        passengercellno=findViewById(R.id.pcellno);
        passengergender=findViewById(R.id.pgender);
        tinyDB = new TinyDB(this);
        passengername.setText(tinyDB.getString(AppConstants.PName));
        tinyDB = new TinyDB(this);
       passengeremail.setText(tinyDB.getString(AppConstants.PMail));
        tinyDB = new TinyDB(this);
       passengercellno.setText(tinyDB.getString(AppConstants.PCellno));
        tinyDB = new TinyDB(this);
     passengergender.setText(tinyDB.getString(AppConstants.PGender));
        editrecord=findViewById(R.id.btn_edit_precord);
        editrecord.setOnClickListener(view -> {
            Intent i;
            i = new Intent(PassengerProfileActivity.this, UpdatePassengerActivity.class);
            startActivity(i);

        });




}}
