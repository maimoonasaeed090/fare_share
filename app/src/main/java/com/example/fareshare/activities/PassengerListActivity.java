package com.example.fareshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.adapters.PassengerlistAdapter;
import com.example.fareshare.model.Passenger;
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

public class PassengerListActivity extends AppCompatActivity {
   private ProgressDialog progressDialog;
   ArrayList<Passenger> passengerList;
   RecyclerView passengerRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_list);
        passengerList = new ArrayList<Passenger>();
        passengerRv = findViewById(R.id.preqRv);
        GetRequest();
    }
    private void GetRequest() {
        passengerList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait getting  all passengers......");
        progressDialog.show();
        //GET ALL PASSENGERS  HAVING STATUS A.
        NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
        Call<JsonObject> call = service.getAllPassengers();
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
                                if (data.getString("p_status").equals("A")) {
                                   passengerList.add(new Passenger(
                                            data.getInt("p_id"),
                                            data.getString("p_name"),
                                            data.getString("p_email"),
                                            data.getString("p_cellno"),
                                            data.getString("p_status")
                                    ));
                                }
                            }
                           PassengerlistAdapter adapter = new PassengerlistAdapter(PassengerListActivity.this,passengerList);
                            passengerRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException exception) {
                            passengerRv.setAdapter(null);
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