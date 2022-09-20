package com.example.fareshare.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.adapters.GenderAdapter;
import com.example.fareshare.manager.NetworkManager;
import com.example.fareshare.model.Driver;
import com.example.fareshare.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRegisterActivity extends AppCompatActivity {
    List<String> genderList = new ArrayList<>();
    List<String> vehicleList = new ArrayList<>();
    NetworkManager manager = new NetworkManager();
    private ProgressDialog progressDialog;
    private GenderAdapter genderAdapter;
    EditText demail, dpassword, drivername, dcellno, dvehicalnumber, dlicence, dcnic;

    Button signup;
    Spinner spinner_gender, spinner_vehicle_driver;
    String gender = "";
    String selectedVehicle = "";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        genderList.add("Select Gender");
        genderList.add("Male");
        genderList.add("Female");
        vehicleList.add("Select Vehicle");
        vehicleList.add("4 Seater");
        vehicleList.add("5 Seater");
        vehicleList.add("6 Seater");
        vehicleList.add("7 Seater");
        spinner_gender = findViewById(R.id.spinner_gender_driver);
        demail = findViewById(R.id.driver_mail);
        dcnic = findViewById(R.id.driver_cnic);
        spinner_vehicle_driver = findViewById(R.id.spinner_vehicle_driver);
        dlicence = findViewById(R.id.driver_licence);
        dvehicalnumber = findViewById(R.id.vehical_numberplate);
        drivername = findViewById(R.id.driver_name);
        dcellno = findViewById(R.id.driver_cellno);
        dpassword = findViewById(R.id.driver_password);
        signup = findViewById(R.id.driver_signup);
        genderAdapter = new GenderAdapter(genderList, getApplicationContext());
        ArrayAdapter<String> genAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genderList);
        spinner_gender.setAdapter(genAdapter);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    gender = genderList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> vehAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                vehicleList);
        spinner_vehicle_driver.setAdapter(vehAdapter);
        spinner_vehicle_driver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedVehicle = spinner_vehicle_driver.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manager.checkInternetConnection(getApplicationContext())) {

                    if (validation()) {

                        driverRegistration();

                    } else {
                        Toast.makeText(DriverRegisterActivity.this,
                                "One or more selection is missing!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(DriverRegisterActivity.this,
                            "Check internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void driverRegistration() {
        progressDialog = new ProgressDialog(DriverRegisterActivity.this);
        progressDialog.setTitle("New Account");
        progressDialog.setMessage("Creating your account please wait!");
        progressDialog.show();

        NetworkCalls driverCall = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<Driver> driverSignup = driverCall.driverSignup(drivername.getText().toString().trim(),
                demail.getText().toString().trim(),
                dpassword.getText().toString().trim(),
                dcnic.getText().toString().trim(),
                dcellno.getText().toString().trim(),
                selectedVehicle,
                dvehicalnumber.getText().toString().trim(),
                dlicence.getText().toString().trim(), gender);

        driverSignup.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    Driver driver = (Driver) response.body();

                    if (!driver.isStatus()) {
                        Toast.makeText(DriverRegisterActivity.this, driver.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();

                    } else {

                        Toast.makeText(DriverRegisterActivity.this, driver.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(DriverRegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validation() {


        if (drivername.getText().toString().trim().isEmpty() || drivername.length() < 6) {
            drivername.setError("Enter Name at least 6 character long");

        } else {
            drivername.setError(null);
        }
        if (!isValidEmailId(demail.getText().toString().trim())) {
            demail.setError("Enter a Valid Email");

        } else {
            demail.setError(null);
        }
        if (dpassword.getText().toString().trim().isEmpty() || dpassword.length() < 6) {
            dpassword.setError("password must be greater or equal to 6");

        } else {
            dpassword.setError(null);
        }
        if (dcnic.length() != 13) {
            dcnic.setError("CNIC should be 13 character long");

        } else {
            dcnic.setError(null);
        }

        if (dcellno.getText().toString().trim().isEmpty() || dcellno.length() < 11) {
            dcellno.setError("Enter Valid Mobile Phone Number");

        } else {
            dcellno.setError(null);
        }

        if (dvehicalnumber.getText().toString().trim().isEmpty() || (dvehicalnumber.length() > 7)) {
            dvehicalnumber.setError("Enter Valid vehicle Number");

        } else {
            dvehicalnumber.setError(null);
        }
        if (dlicence.length() != 13) {
            dlicence.setError("Enter Valid License Number");
        } else {
            dlicence.setError(null);
        }


        // after all validation return true.
        return true;
    }

    private boolean isValidEmailId(String demail) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(demail).matches();
    }


}

