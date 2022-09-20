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

import com.example.fareshare.remote.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverslistAdapter extends RecyclerView.Adapter<DriverslistAdapter.ViewHolder>
{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Driver> alldrivers;

    public DriverslistAdapter(Context context, ArrayList<Driver> driver) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.alldrivers = driver;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.dlistitem, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DriverslistAdapter.ViewHolder holder, int position) {
        Driver driver = alldrivers.get(position);
        holder.name.setText(driver.getD_name());
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkCalls service = RetrofitClient.getClient().create(NetworkCalls.class);
                Call<JsonObject> call = service.updatedriverstatus(driver.getD_id(), "B");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                                    Toast.makeText(mContext, "Blocked", Toast.LENGTH_SHORT).show();
                                    alldrivers.remove(holder.getAdapterPosition());
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
        return alldrivers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button  delBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ditemname);
            delBtn = itemView.findViewById(R.id.d_block);

        }
    }
}
