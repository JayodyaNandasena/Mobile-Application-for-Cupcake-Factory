package com.example.cupcake_factory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private int Id;
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Save(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
            values.put("id",Id);
            values.put("name",name);
            db.insert("Category",null,values);
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
            values.put("id",Id);
            values.put("name",name);
            db.update("Category",values,"id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void Delete(SQLiteDatabase db)
    {
        try {

            db.delete("Category","id= "+Id,null);
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
            List<Category> categories= new ArrayList<Category>();
            String query="select id,name from category";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do {
                    Category category= new Category();

                    category.setId(cursor.getInt(0));
                    category.setName(cursor.getString(1));

                    categories.add(category);


                }while (cursor.moveToNext());
            }
            return categories;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }




}

