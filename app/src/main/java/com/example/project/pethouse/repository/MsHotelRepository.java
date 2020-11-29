package com.example.project.pethouse.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.pethouse.model.MsHotel;

public class MsHotelRepository extends DatabaseHelper{

    public MsHotelRepository(Context context) { super(context); }

    public boolean insert(MsHotel hotel){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put("HotelName", hotel.getHotelName());
        Values.put("HotelLocation", hotel.getHotelLocation());
        Values.put("HotelDesc", hotel.getHotelDesc());
        Values.put("RemainingSlot", hotel.getRemainingSlot());
        Values.put("OwnerName", hotel.getOwnerName());
        Values.put("OwnerEmail", hotel.getOwnerEmail());
        Values.put("OwnerPhone", hotel.getOwnerPhone());
        Values.put("OwnerPrice", hotel.getOwnerPrice());

        DB.insert("MsHotel", null, Values);
        DB.close();
        return true;
    }

    public boolean update(MsHotel hotel){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("HotelName", hotel.getHotelName());
        Values.put("HotelLocation", hotel.getHotelLocation());
        Values.put("HotelDesc", hotel.getHotelDesc());
        Values.put("RemainingSlot", hotel.getRemainingSlot());
        Values.put("OwnerName", hotel.getOwnerName());
        Values.put("OwnerEmail", hotel.getOwnerEmail());
        Values.put("OwnerPhone", hotel.getOwnerPhone());
        Values.put("OwnerPrice", hotel.getOwnerPrice());

        String[] whereArgs = new String[]{
                String.valueOf(hotel.getHotelID())
        };

        DB.update("MsHotel", Values, "HotelID = ?", whereArgs);
        DB.close();
        return true;
    }

    public MsHotel getHotel(int id){
        MsHotel hotel = new MsHotel();
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM MsHotel WHERE HotelID = "+ id, null);
        cursor.moveToFirst();
        hotel.setHotelID(cursor.getInt(cursor.getColumnIndex("HotelID")));
        hotel.setHotelName(cursor.getString(cursor.getColumnIndex("HotelName")));
        hotel.setHotelLocation(cursor.getString(cursor.getColumnIndex("HotelLocation")));
        hotel.setHotelDesc(cursor.getString(cursor.getColumnIndex("HotelDesc")));
        hotel.setRemainingSlot(cursor.getInt(cursor.getColumnIndex("RemainingSlot")));
        hotel.setOwnerName(cursor.getString(cursor.getColumnIndex("OwnerName")));
        hotel.setOwnerEmail(cursor.getString(cursor.getColumnIndex("OwnerEmail")));
        hotel.setOwnerPhone(cursor.getString(cursor.getColumnIndex("OwnerPhone")));
        hotel.setOwnerPrice(cursor.getInt(cursor.getColumnIndex("OwnerPrice")));
        DB.close();
        return hotel;
    }

    public boolean checkInserted(){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM MsHotel", null);
        if(cursor.getCount() == 5){
            DB.close();
            return true;
        }
        DB.close();
        return false;
    }
}
