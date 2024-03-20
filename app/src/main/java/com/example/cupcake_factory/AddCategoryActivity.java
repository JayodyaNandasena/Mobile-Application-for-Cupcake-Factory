package com.example.cupcake_factory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake_factory.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddCategoryActivity extends AppCompatActivity {
    EditText edtName;
    TextView edtList;
    Button btnAddCt;
    ImageButton btnHome, btnAdd, btnAccount;
    FirebaseDatabase database;
    DatabaseReference fdReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_category);

        edtName = findViewById(R.id.edtACtName);
        edtList = findViewById(R.id.edtListCategories);
        btnAddCt = findViewById(R.id.btnACtAdd);
        btnHome = findViewById(R.id.ibtnHome);
        btnAdd = findViewById(R.id.ibtnAdd);
        btnAccount = findViewById(R.id.ibtnAccount);

        edtList.setMovementMethod(new ScrollingMovementMethod());
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        database = FirebaseDatabase.getInstance();
        fdReference = database.getReference("categories");

        showList();

        btnAddCt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String categoryName = edtName.getText().toString();

                // Check if category already exists
                Query query = fdReference.orderByChild("name").equalTo(categoryName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Category already exists, show an error message
                            edtName.setError("Category '" + categoryName + "' already exists!");

                        } else {
                            // Category doesn't exist, add it
                            Category category = new Category(categoryName);
                            fdReference.child(categoryName).setValue(category);
                            Toast.makeText(AddCategoryActivity.this,
                                    "New Category Added Successfully!", Toast.LENGTH_LONG).show();
                            showList();
                            edtName.setText("");
                            inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("AddCategoryActivity", "Failed to read categories.", error.toException());
                    }
                });
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

    protected void showList(){
        fdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder categoryNames = new StringBuilder();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Category category = childSnapshot.getValue(Category.class);
                    if (category != null) {
                        categoryNames.append(category.getName() + "\n");
                    }
                }
                edtList.setText(categoryNames.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("AddCategoryActivity", "Failed to read categories.", error.toException());
            }
        });
    }
}