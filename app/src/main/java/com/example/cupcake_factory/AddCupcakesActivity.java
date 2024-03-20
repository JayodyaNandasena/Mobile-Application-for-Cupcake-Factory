package com.example.cupcake_factory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cupcake_factory.model.Category;
import com.example.cupcake_factory.model.Cupcake;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddCupcakesActivity extends AppCompatActivity {
    private EditText edtCName, edtCDesciption, edtCPrice;
    private ImageView imgCPhoto;
    private Button btnCAdd, btnGetImage, btnCtNav;
    ImageButton btnAdd, btnAccount, btnHome;
    private Spinner dropdown;
    private FirebaseDatabase database;
    private DatabaseReference fdReference;
    private StorageReference storageReference;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cupcakes);

        edtCName=findViewById(R.id.edtACName);
        edtCDesciption=findViewById(R.id.edtACDescription);
        edtCPrice=findViewById(R.id.edtACPrice);
        imgCPhoto=findViewById(R.id.imgACImage);
        btnCAdd=findViewById(R.id.btnACAdd);
        btnGetImage=findViewById(R.id.btnACAddImage);
        btnCtNav=findViewById(R.id.btnACNavCt);
        btnAdd = findViewById(R.id.ibtnAdd);
        btnAccount = findViewById(R.id.ibtnAccount);
        btnHome = findViewById(R.id.ibtnHome);
        dropdown = (Spinner) findViewById(R.id.spnCategory);
        database=FirebaseDatabase.getInstance();
        fdReference=database.getReference("cupcakes");
        storageReference = FirebaseStorage.getInstance().getReference();


        populateDropdown();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            imgCPhoto.setImageURI(imageUri);
                        } else {
                            Toast.makeText(AddCupcakesActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        imgCPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnCAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                } else  {
                    Toast.makeText(AddCupcakesActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCtNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), AddCategoryActivity.class);
                startActivity(intent);
                AddCupcakesActivity.this.finish();
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

    private void uploadToFirebase(Uri uri){
        String cupcakeName=edtCName.getText().toString();
        String description=edtCDesciption.getText().toString();
        double unitPrice= (Double.parseDouble(edtCPrice.getText().toString())) ;
        String category=dropdown.getSelectedItem().toString();

        fdReference = database.getReference("Cupcakes");

        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Cupcake cupcake = new Cupcake(cupcakeName, description, unitPrice, category, uri.toString());
                        String key = fdReference.push().getKey();
                        fdReference.child(cupcakeName).setValue(cupcake);
                        Toast.makeText(AddCupcakesActivity.this, "New Cupcake Added Successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddCupcakesActivity.this, AddCupcakesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                //progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AddCupcakesActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }

    private void populateDropdown() {

        fdReference = database.getReference("categories");

        ArrayList<Category> categoryList = new ArrayList<>();

        fdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    String categoryName = childSnapshot.child("name").getValue(String.class);

                    Category item = new Category(categoryName);

                    categoryList.add(item);
                }
                ArrayAdapter<Category> adapter = new ArrayAdapter<>(AddCupcakesActivity.this,
                        android.R.layout.simple_spinner_item, categoryList);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                dropdown.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
                Log.w("Firebase", "Failed to read data", error.toException());
            }
        });
    }
}