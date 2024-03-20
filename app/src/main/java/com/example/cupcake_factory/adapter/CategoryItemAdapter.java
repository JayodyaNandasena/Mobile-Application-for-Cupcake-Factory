package com.example.cupcake_factory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cupcake_factory.model.Category;
import com.example.cupcake_factory.R;
import com.google.firebase.database.core.Context;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    List<Category> categoryList;
    private Context context;

    public CategoryItemAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

//    @NonNull
//    @Override
//    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
//        View items= inflater.inflate(R.layout.activity_category_item,parent,false);
//        ViewHolder holder= new ViewHolder(items);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int position) {
//        Category category= categoryList.get(position);
//
//        holder.txvCategory.setText(category.getName());
//
//        CupcakeItemAdapter cupcakeItemAdapter=new CupcakeItemAdapter(categoryList.get(position).cup)
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return categoryList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView txvCategory;
//        RecyclerView rvCupcakeItem;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txvCategory= itemView.findViewById(R.id.txvCICategory);
//            rvCupcakeItem= itemView.findViewById(R.id.rvCategorizedCupcakes);
//        }
//    }
}
