package com.example.cupcake_factory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Cupcake {

    private int Id;
    private String name;
    private String description;
    private double unitPrice;
    private String cakeBase;
    private String frosting;
    private byte[] photo;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCakeBase() {
        return cakeBase;
    }

    public void setCakeBase(String cakeBase) {
        this.cakeBase = cakeBase;
    }

    public String getFrosting() {
        return frosting;
    }

    public void setFrosting(String frosting) {
        this.frosting = frosting;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void Save(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
            values.put("id",Id);
            values.put("name",name);
            values.put("description",description);
            values.put("unitPrice",unitPrice);
            values.put("cakeBase",cakeBase);
            values.put("frosting",frosting);
            values.put("photo",photo);
            db.insert("cupcake",null,values);
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
            values.put("description",description);
            values.put("unitPrice",unitPrice);
            values.put("cakeBase",cakeBase);
            values.put("frosting",frosting);
            values.put("photo",photo);
            db.update("cupcake",values,"id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void Delete(SQLiteDatabase db)
    {
        try {

            db.delete("cupcake","id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public List<Cupcake> GetBooks(SQLiteDatabase db)
    {
        try
        {
            List<Cupcake> cupcakes= new ArrayList<Cupcake>();
            String query="select id,name,description,unitPrice,cakeBase,frosting,photo from cupcake";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do {
                    Cupcake cupcake= new Cupcake();

                    cupcake.setId(cursor.getInt(0));
                    cupcake.setName(cursor.getString(1));
                    cupcake.setDescription(cursor.getString(2));
                    cupcake.setUnitPrice(cursor.getDouble(3));
                    cupcake.setCakeBase(cursor.getString(4));
                    cupcake.setFrosting(cursor.getString(5));
                    cupcake.setPhoto(cursor.getBlob(6));
                    cupcakes.add(cupcake);


                }while (cursor.moveToNext());
            }
            return cupcakes;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }




}

