package com.example.fareshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.manager.NetworkManager;
import com.example.fareshare.model.AddFare;
import com.example.fareshare.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFareActivity extends AppCompatActivity {
    EditText basefare;
    EditText kmfare;
    Button add, update;

    boolean isAllFieldsChecked = false;
    NetworkManager manager = new NetworkManager();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfare);
        basefare = findViewById(R.id.base_fare);
        kmfare = findViewById(R.id.km_fare);
        add = findViewById(R.id.add_fare);
        update = findViewById(R.id.update_fare);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFare();
            }
        });

    }

    private void addFare() {
        progressDialog = new ProgressDialog(AddFareActivity.this);
        progressDialog.setTitle("Adding!!");
        progressDialog.setMessage("please wait");
        progressDialog.show();


        NetworkCalls addfareCall = RetrofitClient.getClient().create(NetworkCalls.class);
        Call passengerLogin = addfareCall.addFare(Integer.parseInt(basefare.getText().toString()),
                Integer.parseInt(kmfare.getText().toString()));
        passengerLogin.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    AddFare addfare = (AddFare) response.body();

                    if (!addfare.isError()) {

                        Toast.makeText(AddFareActivity.this, addfare.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(AddFareActivity.this, addfare.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call call, @NonNull Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(AddFareActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}