package com.example.cupcake_factory.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cupcake_factory.cupcakeDetailsActivity;
import com.example.cupcake_factory.model.Cupcake;
import com.example.cupcake_factory.R;

import java.util.ArrayList;
import java.util.List;

public class CupcakeItemAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Cupcake> cupcakeList;
    public CupcakeItemAdapter(Context context, List<Cupcake> cupcakeList) {
        this.context = context;
        this.cupcakeList = cupcakeList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cupcake_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(cupcakeList.get(position).getPhotoURL()).into(holder.cupcakeImage);
        holder.txvName.setText(cupcakeList.get(position).getName());
        holder.txvPrice.setText(String.valueOf(cupcakeList.get(position).getUnitPrice()));
        holder.cakeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, cupcakeDetailsActivity.class);
                intent.putExtra("photoURL", cupcakeList.get(holder.getAdapterPosition()).getPhotoURL());
                intent.putExtra("description", cupcakeList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("name", cupcakeList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("unitPrice",cupcakeList.get(holder.getAdapterPosition()).getUnitPrice());

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return cupcakeList.size();
    }
    public void searchDataList(ArrayList<Cupcake> searchList){
        cupcakeList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView cupcakeImage;
    TextView txvName, txvPrice;
    CardView cakeCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        cupcakeImage = itemView.findViewById(R.id.imgCuIPhoto);
        cakeCard = itemView.findViewById(R.id.recCard);
        txvName = itemView.findViewById(R.id.txvCuIName);
        txvPrice = itemView.findViewById(R.id.txvCuIPrice);
    }
}