package com.example.project.pethouse.repository;


    /*
    Created By: Jeffrey
    Date: 09 May 2020, 18.00PM
    Purpose: insert,update,delete
     */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.project.pethouse.model.MsPet;

public class MsPetRepository extends DatabaseHelper{

    public MsPetRepository(Context Context){super(Context);};

    public boolean insert(MsPet pet){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("PetType",pet.getPetType());
        Values.put("PetName",pet.getPetName());
        Values.put("PetGender",pet.getPetGender());
        Values.put("PetAge",pet.getPetAge());
        Values.put("PetDesc",pet.getPetDesc());
        Values.put("PetOwnerName",pet.getPetOwnerName());
        System.out.println(pet.getPetName());
        DB.insert("MsPet", null, Values);
        DB.close();
        return checkLastPet();
    }

    public boolean checkLastPet(){
        //testing when inserting
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsPet WHERE PetID = " + getSize(), null);

        if(cursor.getCount() != 0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean update(MsPet pet){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("PetType",pet.getPetType());
        Values.put("PetName",pet.getPetName());
        Values.put("PetGender",pet.getPetGender());
        Values.put("PetAge",pet.getPetAge());
        Values.put("PetDesc",pet.getPetDesc());
        Values.put("PetOwnerName",pet.getPetOwnerName());

        String[] whereArgs = new String[]{
                String.valueOf(pet.getPetID())
        };

        DB.update("MsPet", Values, "PetID = ?", whereArgs);
        DB.close();
        return true;
    }

    public MsPet getPet(int id){
        MsPet pet = new MsPet();
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM MsPet WHERE PetID = "+ id,null);

        cursor.moveToFirst();
        pet.setPetID(cursor.getInt(cursor.getColumnIndex("PetID")));
        pet.setPetType(cursor.getString(cursor.getColumnIndex("PetType")));
        pet.setPetName(cursor.getString(cursor.getColumnIndex("PetName")));
        pet.setPetGender(cursor.getString(cursor.getColumnIndex("PetGender")));
        pet.setPetAge(cursor.getString(cursor.getColumnIndex("PetAge")));
        pet.setPetDesc(cursor.getString(cursor.getColumnIndex("PetDesc")));
        pet.setPetOwnerName(cursor.getString(cursor.getColumnIndex("PetOwnerName")));
        DB.close();
        return pet;
    }

    public int getSize(){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM MsPet", null);
        return cursor.getCount();
    }

    public void onCreate(SQLiteDatabase db){
        super.onCreate(db);
    }
}