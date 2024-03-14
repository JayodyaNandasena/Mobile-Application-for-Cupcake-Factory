package com.example.cupcake_factory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {

    EditText signInUsername,signInPassword;
    Button btnSignIn;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btnSignIn= findViewById(R.id.btnSignIn);
        btnSignUp= findViewById(R.id.btnSignUp);
        signInUsername=findViewById(R.id.edtUsername);
        signInPassword=findViewById(R.id.edtPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                    SigninActivity.this.finish();
            }
        });
    }


    public Boolean validateUsername() {
        String val = signInUsername.getText().toString();
        if (val.isEmpty()) {
            signInUsername.setError("Username cannot be empty");
            return false;
        } else {
            signInUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = signInPassword.getText().toString();
        if (val.isEmpty()) {
            signInPassword.setError("Password cannot be empty");
            return false;
        } else {
            signInPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = signInUsername.getText().toString().trim();
        String userPassword = signInPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    signInUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        signInUsername.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(userUsername).child("address").getValue(String.class);
                        String mobileFromDB = snapshot.child(userUsername).child("mobileNumber").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(SigninActivity.this, ViewProfileActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("mobileNumber", mobileFromDB);
                        intent.putExtra("username", usernameFromDB);
                        startActivity(intent);
                    } else {
                        signInPassword.setError("Invalid Credentials");
                        signInPassword.requestFocus();
                    }
                } else {
                    signInUsername.setError("User does not exist");
                    signInUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
