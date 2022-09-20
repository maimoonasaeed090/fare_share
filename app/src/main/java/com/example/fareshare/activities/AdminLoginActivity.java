package com.example.fareshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.model.Admin;
import com.example.fareshare.remote.RetrofitClient;
import com.example.fareshare.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {
    EditText name, password;
    Button login;
    TinyDB tinyDB;
    Button cancel;
    ProgressDialog progressDialog;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        tinyDB = new TinyDB(this);
        name = findViewById(R.id.adminname);
        password = findViewById(R.id.adminPassword);
        cancel = findViewById(R.id.clickcanceladmin);
        login = findViewById(R.id.clickloginadmin);
        //if click cancel move bck to home screen
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(AdminLoginActivity.this, HomeScreenActivity.class);
                startActivity(i);

            }

        });

        //login will gwt us to adminportal after validation
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(), password.getText().toString());

            }


        });
    }

    private void validate(String adminname, String adminpassward) {
        admin = new Admin();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        NetworkCalls adminLogin = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<Admin> call = adminLogin.adminLogin(adminname, adminpassward);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    admin = response.body();
                    if (!admin.isError()) {
                        Toast.makeText(AdminLoginActivity.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();
                        tinyDB.putString("PREF", "A");
                        startActivity(new Intent(getApplicationContext(),
                                AdminDashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(AdminLoginActivity.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdminLoginActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



