package com.example.fareshare.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.manager.NetworkManager;
import com.example.fareshare.model.Passenger;
import com.example.fareshare.remote.RetrofitClient;
import com.example.fareshare.util.AppConstants;
import com.example.fareshare.util.TinyDB;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerLoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, signup;
    boolean isAllFieldsChecked = false;
    TinyDB tinyDB;
    NetworkManager manager = new NetworkManager();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengerlogin);
        email = findViewById(R.id.passenger_mail);
        password = findViewById(R.id.passenger_password);
        signup = findViewById(R.id.p_signup);
        //login button functionality
        login = findViewById(R.id.p_login);

        login.setOnClickListener(view -> {


// whether the entered data is valid or if any fields are left blank.
            isAllFieldsChecked = CheckAllFields();

            // the boolean variable turns to be true then
            // only the user must be proceed to the passenger dashboard
            if (isAllFieldsChecked) {

                if (manager.checkInternetConnection(getApplicationContext())) {
                   passengerLogin();
                } else {
                    Toast.makeText(PassengerLoginActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                }

               /* Intent i = new Intent(PassengerLoginActivity.this, PassengerDashboardActivity.class);
                startActivity(i);*/
            }


        });

        signup.setOnClickListener(view -> {
            Intent i;
            i = new Intent(PassengerLoginActivity.this, PassengerRegistrationActivity.class);
            startActivity(i);

        });


    }

    private boolean CheckAllFields() {

        if (email.getText().toString().length() == 0 && password.getText().toString().length() == 0) {
            email.setError("Email is required");
            password.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            password.setError("Password must be minimum 6 characters");
            return false;
        } else if (!isValidEmailId(email.getText().toString())) {
            email.setError("Email is invalid");
            return false;
        }

        // after all validation return true.
        return true;
    }

    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }



        private void passengerLogin() {
            progressDialog = new ProgressDialog(PassengerLoginActivity.this);
            progressDialog.setTitle("Lognning!!");
            progressDialog.setMessage("please wait");
            progressDialog.show();

            NetworkCalls passengerCall = RetrofitClient.getClient().create(NetworkCalls.class);
            Call passengerLogin = passengerCall.passengerLogin(email.getText().toString(), password.getText().toString());
            passengerLogin.enqueue(new Callback()
            {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    Passenger passenger = (Passenger) response.body();
                    assert passenger != null;
                    if (!passenger.isStatus()) {
                        if (passenger.getP_status().equals("A")) {
                            Toast.makeText(PassengerLoginActivity.this,passenger.getMessage(), Toast.LENGTH_SHORT).show();
                           /*tinyDB.putString(AppConstants.PName,passenger.getP_name());
                            tinyDB.putString(AppConstants.PMail,passenger.getP_email());
                            tinyDB.putString(AppConstants.PCellno,passenger.getP_cellNo());
                          tinyDB.putString(AppConstants.PGender,passenger.getP_gender());*/

                            startActivity(new Intent(getApplicationContext(),PassengerDashboardActivity.class));

                        } else {
                            Toast.makeText(PassengerLoginActivity.this, "Can't login, Need admin approval", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PassengerLoginActivity.this,passenger.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {

                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(PassengerLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}