package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fareshare.R;
import com.example.fareshare.util.AppConstants;
import com.example.fareshare.util.TinyDB;

public class DriverProfileActivity extends AppCompatActivity {
    TextView drivername, driveremail, drivercellno, drivercnic, drivergender, drivervehicaltype,
            vehicalnumberPlate, driverlicenseNo;
    TinyDB tinyDB;
    Button editrecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        drivername = findViewById(R.id.driver_name);
        driveremail = findViewById(R.id.driver_mail);
        drivercellno = findViewById(R.id.driver_cellno);
        drivercnic = findViewById(R.id.driver_cnic);
        drivergender = findViewById(R.id.driver_gender);
        drivervehicaltype = findViewById(R.id.driver_vehicletype);
        vehicalnumberPlate = findViewById(R.id.driver_vehiclenumber);
        driverlicenseNo = findViewById(R.id.driver_licenceno);
        //getting values
        tinyDB = new TinyDB(this);
        drivername.setText(tinyDB.getString(AppConstants.dName));
        tinyDB = new TinyDB(this);
        driveremail.setText(tinyDB.getString(AppConstants.dMail));
        tinyDB = new TinyDB(this);
        drivercellno.setText(tinyDB.getString(AppConstants.dCellno));
        tinyDB = new TinyDB(this);
        drivercnic.setText(tinyDB.getString(AppConstants.dCnic));
        tinyDB = new TinyDB(this);
        drivergender.setText(tinyDB.getString(AppConstants.dGender));
        tinyDB = new TinyDB(this);
        drivervehicaltype.setText(tinyDB.getString(AppConstants.dVehicaltype));
        tinyDB = new TinyDB(this);
        vehicalnumberPlate.setText(tinyDB.getString(AppConstants.dVehicalno));
        tinyDB = new TinyDB(this);
        driverlicenseNo.setText(tinyDB.getString(AppConstants.dLicence));


//editrecord
        editrecord = findViewById(R.id.btn_edit_drecord);
        editrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(DriverProfileActivity.this, UpdatedriverActivity.class);
                startActivity(i);

            }

        });


    }
}