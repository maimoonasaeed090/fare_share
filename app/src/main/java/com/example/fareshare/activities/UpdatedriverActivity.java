package com.example.fareshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fareshare.R;
import com.example.fareshare.manager.NetworkManager;
import com.example.fareshare.model.Driver;
import com.example.fareshare.util.AppConstants;
import com.example.fareshare.util.TinyDB;

public class UpdatedriverActivity extends AppCompatActivity {
    TinyDB tinyDB;
    EditText DriverName, Drivermail,DriverCellno,
            Drivervehicaltype,DriverNoPlate,DriverLicense;
    String  drivername,demail,  dcellno,dvehicaltype, dvehicalnumber, dlicence, dcnic,d_id;
    String  d_name,d_email,  dcellno,dvehicaltype, dvehicalnumber, dlicence, dcnic,d_id;
    private AppConstants appConstants=new AppConstants();
    Button updaterecord;
    Driver driver;
    NetworkManager manager = new NetworkManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedriver);
        DriverName=findViewById(R.id.edtUpdateDriverName);
        Drivermail=findViewById(R.id.edtUpdateDriverMail);
        DriverCellno=findViewById(R.id.edtUpdateDriverCellno);
        Drivervehicaltype=findViewById(R.id.edtUpdateDrivervehicaltype);
        DriverNoPlate=findViewById(R.id.edtUpdateDrivernoplate);
        DriverLicense=findViewById(R.id.edtUpdateDriverLicence);
        d_id=AppConstants.d_Id
tinyDB.putInt(AppConstants.d_Id,driver.getD_id());
       DriverName= appConstants.getD_name());
        tinyDB.putString(AppConstants.dMail, driver.getD_email());
        tinyDB.putString(AppConstants.dCellno, driver.getD_cellno());
        tinyDB.putString(AppConstants.dVehicaltype, driver.getD_vehicaltype());
        tinyDB.putString(AppConstants.dVehicalno, driver.getD_vehical_numplate());
        tinyDB.putString(AppConstants.dLicence, driver.getD_licence());
        updaterecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validation()){
                    UpdateDriverRecord(Integer.parseInt(),drivername,demail,  dcellno,dvehicaltype, dvehicalnumber, dlicence, dcnic);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(),
                            "One or more fields are missing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}