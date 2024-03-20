package com.example.cupcake_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cupcake_factory.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText inputName, inputMobileNumber, inputHomeAddress, inputUsername,inputPassword;
    Button btnSignIn, btnSignUp;
    FirebaseDatabase database;
    DatabaseReference fdreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializing the variables
        btnSignIn=findViewById(R.id.btnSSignIn);
        btnSignUp=findViewById(R.id.btnSSignUp);
        inputName =findViewById(R.id.edtName);
        inputMobileNumber=findViewById(R.id.edtMobileNumber);
        inputHomeAddress=findViewById(R.id.edtAddress);
        inputUsername=findViewById(R.id.edtSUsername);
        inputPassword=findViewById(R.id.edtSPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                fdreference=database.getReference("users");

                String name=inputName.getText().toString();
                String address=inputHomeAddress.getText().toString();
                int mobile= (Integer.valueOf(inputMobileNumber.getText().toString())) ;
                String username=inputUsername.getText().toString();
                String password=inputPassword.getText().toString();

                User user=new User(name,address,mobile,username,password);

                fdreference.child(username).setValue(user);

                Toast.makeText(SignUpActivity.this, "Sign Up successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }
        });
    }

}