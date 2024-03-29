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

public class CustomerViewCupcakesActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<Cupcake> cupcakeList;
    CupcakeItemAdapter adapter;
    ImageButton btnAccount, btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_cupcakes);
        recyclerView = findViewById(R.id.rvCupcakes);
        btnAccount = findViewById(R.id.ibtnCAccount);
        btnHome = findViewById(R.id.ibtnCHome);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CustomerViewCupcakesActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        cupcakeList = new ArrayList<>();
        adapter = new CupcakeItemAdapter(CustomerViewCupcakesActivity.this, cupcakeList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cupcakes");
        //dialog.show();
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
                Intent intent= new Intent(getApplicationContext(), CustomerViewCupcakesActivity.class);
                startActivity(intent);
            }
        });


    }
}
