package com.example.cupcake_factory.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public boolean Save(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
           // values.put("id",Id);
            values.put("name",name);
            db.insert("categories",null,values);
            return true;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void Update(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
            //values.put("id",Id);
            values.put("name",name);
            //db.update("categories",values,"id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void Delete(SQLiteDatabase db)
    {
        try {

            //db.delete("categories","id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public List<Category> GetCategories(SQLiteDatabase db)
    {
        try
        {
            List<Category> categoryList= new ArrayList<Category>();
            String query="select id,name from categories";

            Cursor cursor= db.rawQuery(query,null);

            if(cursor.moveToFirst())
            {
                do {
                    Category category= new Category();

                    //category.setId(cursor.getInt(0));
                    category.setName(cursor.getString(1));

                    categoryList.add(category);

                }while (cursor.moveToNext());
            }
            return categoryList;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

}

