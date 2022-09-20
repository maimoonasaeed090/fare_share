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
import com.example.fareshare.model.Passenger;
import com.example.fareshare.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerRegistrationActivity extends AppCompatActivity {

    List<String> genderList = new ArrayList<String>();
    NetworkManager manager = new NetworkManager();
    private ProgressDialog progressDialog;
    private GenderAdapter genderAdapter;
    Button signUp;
    EditText pname, pemail, ppassword, pcellno;
    Spinner spinner_gender;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);
        genderList.add("Select Gender");
        genderList.add("Male");
        genderList.add("Female");
        signUp = findViewById(R.id.passenger_signup);
        spinner_gender = findViewById(R.id.spinner_gender_passenger);
        pname = findViewById(R.id.passenger_name);
        pemail = findViewById(R.id.passenger_mail);
        ppassword = findViewById(R.id.p_password);
        pcellno = findViewById(R.id.passenger_cellno);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genderList);
        spinner_gender.setAdapter(genderAdapter);
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

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manager.checkInternetConnection(getApplicationContext())) {

                    if (validation()) {

                        PassengerRegistration();


                    } else {
                        Toast.makeText(PassengerRegistrationActivity.this,
                                "One or more selection is missing!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(PassengerRegistrationActivity.this,
                            "Check internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void PassengerRegistration() {
        progressDialog = new ProgressDialog(PassengerRegistrationActivity.this);
        progressDialog.setTitle("New Account");
        progressDialog.setMessage("Creating your account please wait!");
        progressDialog.show();

        NetworkCalls passengerCall = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<Passenger> passenger;
        passenger = passengerCall.passengerSignup(pname.getText().toString().trim(),
                pemail.getText().toString().trim(), ppassword.getText().toString().trim(),
                pcellno.getText().toString().trim(), gender);

        passenger.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    Passenger passenger = (Passenger) response.body();

                    if (!passenger.isStatus()) {

                        Toast.makeText(PassengerRegistrationActivity.this, passenger.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(PassengerRegistrationActivity.this, passenger.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(PassengerRegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validation() {


        if (pname.getText().toString().trim().isEmpty() || pname.length() < 6) {
            pname.setError("Enter Name at least 6 character long");

        } else {
            pname.setError(null);
        }
        if (!isValiedtEmailId(pemail.getText().toString().trim())) {
            pemail.setError("Enter a Valid Email");

        } else {
            pemail.setError(null);
        }

        if (ppassword.getText().toString().trim().isEmpty() || ppassword.length() < 6) {
            ppassword.setError("password must be greater or equal to 6");

        } else {
            ppassword.setError(null);
        }
        if (pcellno.getText().toString().trim().isEmpty() || pcellno.length() < 11) {
            pcellno.setError("Enter Valid Mobile Phone Number");

        } else {
            pcellno.setError(null);
        }


        // after all validation return true.
        return true;
    }

    private boolean isValiedtEmailId(String edtEmail) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(edtEmail).matches();
    }

}