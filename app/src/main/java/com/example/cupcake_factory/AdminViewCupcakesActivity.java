package com.example.cupcake_factory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.cupcake_factory.adapter.CupcakeItemAdapter;
import com.example.cupcake_factory.model.Cupcake;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AdminViewCupcakesActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<Cupcake> cupcakeList;
    CupcakeItemAdapter adapter;
    ImageButton btnHome, btnAdd, btnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_cupcakes);
        recyclerView = findViewById(R.id.rvACupcakes);
        btnAdd = findViewById(R.id.ibtnAdd);
        btnAccount = findViewById(R.id.ibtnAccount);
        btnHome = findViewById(R.id.ibtnHome);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AdminViewCupcakesActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        cupcakeList = new ArrayList<>();
        adapter = new CupcakeItemAdapter(AdminViewCupcakesActivity.this, cupcakeList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cupcakes");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cupcakeList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    Cupcake cupcake = itemSnapshot.getValue(Cupcake.class);
                    cupcake.setName(itemSnapshot.getKey());
                    cupcakeList.add(cupcake);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), AddCupcakesActivity.class);
                startActivity(intent);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), AdminViewCupcakesActivity.class);
                startActivity(intent);
            }
        });
    }
}
