package com.example.fareshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fareshare.R;

import java.util.ArrayList;
import java.util.List;

public class GenderAdapter extends BaseAdapter {

    List<String> genderList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public GenderAdapter(List<String> genderList, Context context) {
        this.genderList = genderList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return genderList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_gender_spinner, viewGroup, false);
        TextView tvGender = view.findViewById(R.id.item_gender);
        tvGender.setText(genderList.get(i).toString());
        return view;
    }
}
