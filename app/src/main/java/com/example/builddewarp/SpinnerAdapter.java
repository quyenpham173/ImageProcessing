package com.example.builddewarp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quyenpham.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<SpinnerItem> {
    public SpinnerAdapter(Context context, ArrayList<SpinnerItem> languageList){
        super(context, 0, languageList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.tv_spinner);
        SpinnerItem spinnerItem = getItem(position);
        if (spinnerItem != null)
            textView.setText(spinnerItem.getmLanguage());
        return convertView;
    }
}
