package com.example.cupcake_factory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddCategoryActivity extends AppCompatActivity {
    EditText edtName;
    Button btnAdd;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_category);

        edtName=findViewById(R.id.edtACtName);
        btnAdd=findViewById(R.id.btnACtAdd);

        SetDB();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Category category=new Category();

                    category.setName(edtName.getText().toString());

                    category.Save(db);
                    Toast.makeText(getApplicationContext(),
                            "New Category added",Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),
                            ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SetDB()
    {
        try {
            db=openOrCreateDatabase("CupcakeFactoryDB",MODE_PRIVATE,null);
            db.execSQL("Create table if not exists "+
                    "Category(id integer primary key autoincrement " +
                    "   ,name text unique)");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}