package com.example.cupcake_factory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profileName = findViewById(R.id.txvName);
        profileAddress = findViewById(R.id.txvAddress);
        profileUsername = findViewById(R.id.txvUsername);
        profileMobile = findViewById(R.id.txvMobileNumber);
        btnSignOut=findViewById(R.id.btnASignOut);

        showAllUserData();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                ViewProfileActivity.this.finish();
            }
        });
    }
    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String addressUser = intent.getStringExtra("address");
        String usernameUser = intent.getStringExtra("username");
        String mobileUser = intent.getStringExtra("mobileNumber");
        profileName.setText(nameUser);
        profileAddress.setText(addressUser);
        profileUsername.setText(usernameUser);
        profileMobile.setText(mobileUser);
    }
//    public void passUserData(){
//        String userUsername = profileUsername.getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
//                    Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("username", usernameFromDB);
//                    intent.putExtra("password", passwordFromDB);
//                    startActivity(intent);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
}