package com.example.fareshare.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.manager.NetworkManager;
import com.example.fareshare.model.Driver;
import com.example.fareshare.remote.RetrofitClient;
import com.example.fareshare.util.AppConstants;
import com.example.fareshare.util.TinyDB;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverLoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, signup;
    boolean isAllFieldsChecked = false;
    TinyDB tinyDB;
    NetworkManager manager = new NetworkManager();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogin);
        tinyDB = new TinyDB(this);
        email = findViewById(R.id.drivermail);
        password = findViewById(R.id.driverpassword);
        login = findViewById(R.id.driverlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


// whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the passenger dashboard
                if (isAllFieldsChecked) {

                    if (manager.checkInternetConnection(getApplicationContext())) {
                        driverLogin();
                    } else {
                        Toast.makeText(DriverLoginActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                    }

                   /* Intent i = new Intent(PassengerLoginActivity.this, PassengerDashboardActivity.class);
                    startActivity(i);*/
                }


            }
        });
        signup = findViewById(R.id.driver_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(DriverLoginActivity.this, DriverRegisterActivity.class);
                startActivity(i);

            }

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

    private void driverLogin() {
        progressDialog = new ProgressDialog(DriverLoginActivity.this);
        progressDialog.setTitle("Lognning!!");
        progressDialog.setMessage("please wait");
        progressDialog.show();

        NetworkCalls driverCall = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<Driver> Driverlogin = driverCall.driverLogin(email.getText().toString(),
                password.getText().
                        toString());
        Driverlogin.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    Driver driver = (Driver) response.body();

                    if (!driver.isStatus()) {
                        if (driver.getD_status().equals("A")) {
                            Toast.makeText(DriverLoginActivity.this, driver.getMessage(), Toast.LENGTH_SHORT).show();
                            tinyDB.putInt("DRIVER_ID", driver.getD_id());
                            tinyDB.putString("PREF", "D");
                            tinyDB.putString(AppConstants.dName, driver.getD_name());
                            tinyDB.putString(AppConstants.dMail, driver.getD_email());
                            tinyDB.putString(AppConstants.dCellno, driver.getD_cellno());
                            tinyDB.putString(AppConstants.dCnic, driver.getD_cnic());
                            tinyDB.putString(AppConstants.dGender, driver.getD_gender());
                            tinyDB.putString(AppConstants.dVehicaltype, driver.getD_vehicaltype());
                            tinyDB.putString(AppConstants.dVehicalno, driver.getD_vehical_numplate());
                            tinyDB.putString(AppConstants.dLicence, driver.getD_licence());
                            startActivity(new Intent(getApplicationContext(),
                                    DriverDashboardActivity.class));
                            finish();

                        } else {
                            Toast.makeText(DriverLoginActivity.this, "Can't login, Need admin approval", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DriverLoginActivity.this, driver.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(DriverLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}