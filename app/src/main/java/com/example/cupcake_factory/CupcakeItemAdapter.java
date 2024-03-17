package com.example.cupcake_factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CupcakeItemAdapter extends RecyclerView.Adapter<CupcakeItemAdapter.MyViewHolder> {

    private ArrayList<Cupcake> cupcakeList;

    private Context context;

    public CupcakeItemAdapter() {
    }

    public CupcakeItemAdapter(ArrayList<Cupcake> cupcakeList, Context context) {
        this.cupcakeList = cupcakeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_cupcake_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(cupcakeList.get(position).getPhotoURL()).into(holder.imvPhoto);
        holder.txvName.setText(cupcakeList.get(position).getName());
        holder.txvPrice.setText(String.valueOf(cupcakeList.get(position).getUnitPrice()));
    }


    @Override
    public int getItemCount() {
        return cupcakeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txvName,txvPrice;
        ImageView imvPhoto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvName= itemView.findViewById(R.id.txvCuIName);
            txvPrice= itemView.findViewById(R.id.txvCuIPrice);
            imvPhoto= itemView.findViewById(R.id.imgCuIPhoto);
        }
    }
}

