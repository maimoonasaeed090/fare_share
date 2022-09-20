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

import com.example.fareshare.model.Passenger;

import com.example.fareshare.remote.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerlistAdapter extends RecyclerView.Adapter<PassengerlistAdapter.ViewHolder> {
    private final Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Passenger> allpassengers;
    public PassengerlistAdapter(Context context, ArrayList<Passenger> passenger) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.allpassengers=passenger;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.plistitem, parent, false);
        return new  ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull PassengerlistAdapter.ViewHolder holder, int position) {
        Passenger passenger =allpassengers.get(position);
        holder.name.setText(passenger.getP_name());
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
                Call<JsonObject> call = service.updatepassengerstatus(passenger.getP_id(), "B");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                                    Toast.makeText(mContext, "Blocked", Toast.LENGTH_SHORT).show();
                                    allpassengers.remove(holder.getAdapterPosition());
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
        return allpassengers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button delBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pitemname);
            delBtn = itemView.findViewById(R.id.p_block);

        }
    }
}
