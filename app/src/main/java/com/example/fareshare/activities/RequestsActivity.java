package com.example.fareshare.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.adapters.DriverRequestAdapter;
import com.example.fareshare.adapters.RequestAdapter;
import com.example.fareshare.model.Driver;
import com.example.fareshare.model.Requests;
import com.example.fareshare.remote.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    ArrayList<Requests> requestList;
    ArrayList<Driver> DriverRequestList;
    RecyclerView reqRv;
    Spinner reqTypeSp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_activity);
        requestList = new ArrayList<>();
        DriverRequestList = new ArrayList<>();
        reqRv = findViewById(R.id.reqRv);
        reqTypeSp = findViewById(R.id.reqSpinner);
        String[] reqType = {"Passenger", "Driver"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                reqType);
        reqTypeSp.setAdapter(Adapter);
        reqTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                    GetRequest("P");
                else if (i == 1)
                    getDriversReq("P");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    private void GetRequest(String status) {
        requestList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait getting passenger requests..");
        progressDialog.show();

//GET ALL PASSENGERS  HAVING STATUS P.
        NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<JsonObject> call = service.getAllPassengers();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                            JSONObject records = jsonObject.getJSONObject("record");
                            JSONArray jsonArray = records.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                if (data.getString("p_status").equals(status)) {
                                    requestList.add(new Requests(
                                            data.getInt("p_id"),
                                            data.getString("p_name"),
                                            data.getString("p_email"),
                                            data.getString("p_cellno"),
                                            data.getString("p_status")
                                    ));
                                }
                            }

                            RequestAdapter adapter = new RequestAdapter(RequestsActivity.this, requestList);
                            reqRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException exception) {
                            reqRv.setAdapter(null);
                            exception.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    void getDriversReq(String status) {

        DriverRequestList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait getting drivers request..");
        progressDialog.show();

        NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<JsonObject> call = service.getAllDrivers();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                            JSONObject records = jsonObject.getJSONObject("record");
                            JSONArray jsonArray = records.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                if (data.getString("d_status").equals(status)) {
                                    DriverRequestList.add(new Driver(
                                            data.getInt("d_id"),
                                            data.getString("d_name"),
                                            data.getString("d_email"),
                                            data.getString("d_cnic"),
                                            data.getString("d_cellno"),
                                            data.getString("d_vehicaltype"),
                                            data.getString("d_vehical_numplate"),
                                            data.getString("d_licence")
                                    ));
                                }
                            }

                            DriverRequestAdapter adapter = new DriverRequestAdapter(RequestsActivity.this, DriverRequestList);
                            reqRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException exception) {
                            reqRv.setAdapter(null);
                            exception.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
