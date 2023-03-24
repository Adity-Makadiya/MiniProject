package com.example.MiniProject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView. Adapter <Adapter.MyHolder>{

    List<CountryData> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public Adapter(List<CountryData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, @SuppressLint("RecyclerView") final int position) {

        final CountryData countryData = list.get(position);

        holder.tv_name.setText(countryData.getCname());
        holder.tv_email.setText(countryData.getAbout());

        holder.itemView.setOnClickListener(view -> itemClickListener.OnItemClick(position, countryData));

        holder.tv_delete.setOnClickListener(view -> {
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_email,tv_delete;

        public MyHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name_item);
            tv_email = itemView.findViewById(R.id.tv_email_item);
            tv_delete = itemView.findViewById(R.id.tv_delete_item);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void UpdateData(int position, CountryData countryData){

        list.remove(position);
        list.add(countryData);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

}
