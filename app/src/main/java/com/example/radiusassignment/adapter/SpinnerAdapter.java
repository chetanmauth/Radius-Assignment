package com.example.radiusassignment.adapter;

import static com.example.radiusassignment.util.Constants.getImageRes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radiusassignment.R;
import com.example.radiusassignment.pojo.SpinnerPojo;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    ArrayList<SpinnerPojo> arrayList;
    private LayoutInflater inflater;


    public SpinnerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    public void setOptions(ArrayList<SpinnerPojo> options) {
        this.arrayList = options;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public SpinnerPojo getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        inflater = LayoutInflater.from(parent.getContext());
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_facilities, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.ItemName);
            holder.imageView = convertView.findViewById(R.id.itemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SpinnerPojo option = getItem(position);

        // Set the option text
        if (option != null) {
            holder.textView.setText(option.getName());
            holder.imageView.setImageResource(getImageRes(option.getIcon().toLowerCase()));
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
