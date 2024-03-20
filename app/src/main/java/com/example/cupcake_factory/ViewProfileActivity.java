package com.example.cupcake_factory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileActivity extends AppCompatActivity {

    TextView profileName, profileAddress, profileMobile, profileUsername;
    Button btnSignOut;
    ImageButton btnAccount, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profileName = findViewById(R.id.txvName);
        profileAddress = findViewById(R.id.txvAddress);
        profileUsername = findViewById(R.id.txvUsername);
        profileMobile = findViewById(R.id.txvMobileNumber);
        btnSignOut=findViewById(R.id.btnASignOut);
        btnAccount = findViewById(R.id.ibtnCAccount);
        btnHome = findViewById(R.id.ibtnCHome);

        showAllUserData();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                ViewProfileActivity.this.finish();
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
    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String addressUser = intent.getStringExtra("address");
        String usernameUser = intent.getStringExtra("username");
        int mobileUser = intent.getIntExtra("mobileNumber", 0);
        profileName.setText(nameUser);
        profileAddress.setText(addressUser);
        profileUsername.setText(usernameUser);
        profileMobile.setText(String.valueOf(mobileUser));
    }
}