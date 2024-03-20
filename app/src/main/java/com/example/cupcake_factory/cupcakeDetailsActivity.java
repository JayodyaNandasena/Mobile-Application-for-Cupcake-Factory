package com.example.cupcake_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class cupcakeDetailsActivity extends AppCompatActivity {
    TextView cupcakeName, cupcakeDes, cupcakePrice;
    ImageView cupcakeImage;
    ImageButton btnAccount, btnHome;
    String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcake_details);
        cupcakeName = findViewById(R.id.cupcakeName);
        cupcakeDes = findViewById(R.id.cupcakeDesc);
        cupcakePrice = findViewById(R.id.cupcakePrice);
        cupcakeImage=findViewById(R.id.cupcakeImage);
        btnAccount = findViewById(R.id.ibtnCAccount);
        btnHome = findViewById(R.id.ibtnCHome);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            cupcakeDes.setText(bundle.getString("description"));
            cupcakeName.setText(bundle.getString("name"));

            double unitPrice = bundle.getDouble("unitPrice");
            cupcakePrice.setText(String.valueOf(unitPrice));

            imageUrl = bundle.getString("photoURL");
            Glide.with(this).load(bundle.getString("photoURL")).into(cupcakeImage);
        }
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