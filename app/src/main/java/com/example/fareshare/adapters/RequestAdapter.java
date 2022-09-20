package com.example.fareshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fareshare.NetworkCalls.NetworkCalls;
import com.example.fareshare.R;
import com.example.fareshare.model.Driver;
import com.example.fareshare.model.Requests;
import com.example.fareshare.remote.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Requests> mPassenger;


    public RequestAdapter(Context context, ArrayList<Requests> passengers) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mPassenger = passengers;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.requests_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Requests passenger = mPassenger.get(position);
        holder.name.setText(passenger.getP_name());
        holder.accBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
                Call<JsonObject> call = service.updatepassengerstatus(passenger.getP_id(), "A");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                                    Toast.makeText(mContext, "Accepted", Toast.LENGTH_SHORT).show();
                                    mPassenger.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());

                                } catch (JSONException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.rejBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
                Call<JsonObject> call = service.updatepassengerstatus(passenger.getP_id(), "R");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                                    Toast.makeText(mContext, "Rejected", Toast.LENGTH_SHORT).show();
                                    mPassenger.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());

                                } catch (JSONException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return mPassenger.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button accBtn, rejBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.reqItemName);
            accBtn = itemView.findViewById(R.id.accBtn);
            rejBtn = itemView.findViewById(R.id.rejBtn);
        }
    }
}
