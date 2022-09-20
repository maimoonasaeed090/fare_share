package com.example.fareshare.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.adapters.DriverslistAdapter;

import com.example.fareshare.model.Driver;

import com.example.fareshare.remote.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriversListActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    ArrayList<Driver> driverslist;
    RecyclerView driverreqRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        driverslist = new ArrayList<Driver>();
        driverreqRv = findViewById(R.id.dreqRv);
        GetRequest();
    }

    void GetRequest() {
        driverslist.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait getting  all drivers......");
        progressDialog.show();
        //GET ALL driverS  HAVING STATUS A.
        NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<JsonObject> call = service.getAllDrivers();
        call.enqueue(new Callback<JsonObject>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        try {
                            assert response.body() != null;
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                            JSONObject records = jsonObject.getJSONObject("record");
                            JSONArray jsonArray = records.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                if (data.getString("d_status").equals("A")) {
                                    driverslist.add(new Driver(
                                            data.getInt("d_id"),
                                            data.getString("d_name"),
                                            data.getString("d_email"),
                                            data.getString("d_cnic"),
                                            data.getString("d_cellno"),
                                            data.getString("d_vehicaltype"),
                                            data.getString("d_vehical_numplate"),
                                            data.getString("d_licence"),
                                            data.getString("d_status")
                                    ));
                                }
                            }

                            DriverslistAdapter adapter = new DriverslistAdapter(DriversListActivity.this,driverslist);
                            driverreqRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException exception) {
                            driverreqRv.setAdapter(null);
                            exception.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}