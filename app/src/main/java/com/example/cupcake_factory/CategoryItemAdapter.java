package com.example.cupcake_factory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    List<Category> categoryList;
    //Context context;



    @NonNull
    @Override
    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View items= inflater.inflate(R.layout.activity_category_item,parent,false);
        ViewHolder holder= new ViewHolder(items);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int position) {
        Category category= categoryList.get(position);

        holder.txvCategory.setText(category.getName());

        //CupcakeItemAdapter cupcakeItemAdapter=new CupcakeItemAdapter(categoryList.get(position).cu)


//        Bitmap bitmap= BitmapFactory.decodeByteArray(cupcake.getCover(),
//                0,book.getCover().length);
//        holder.imvCover.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvCategory;
        RecyclerView rvCupcakeItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvCategory= itemView.findViewById(R.id.txvCICategory);
            rvCupcakeItem= itemView.findViewById(R.id.rvCategorizedCupcakes);
        }
    }
}
