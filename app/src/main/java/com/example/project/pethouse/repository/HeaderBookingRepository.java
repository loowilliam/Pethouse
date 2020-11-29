package com.example.project.pethouse.repository;

/*
    Created By William Loo
    Date : 9 Mei 2020
    Purpose : Buat repository untuk headerbooking
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsHotel;
import com.example.project.pethouse.model.MsPet;

import java.util.ArrayList;
import java.util.List;

public class HeaderBookingRepository extends DatabaseHelper  {


    public HeaderBookingRepository(Context context) { super(context); }

    public boolean insertToHeaderBooking(HeaderBooking headerBooking){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();
            value.put("UserID", headerBooking.getUserID());
            value.put("PromoID", headerBooking.getPromoID());
            value.put("PetID", headerBooking.getPetID());
            value.put("HotelID", headerBooking.getHotelID());
            value.put("Period", headerBooking.getPeriod());
            value.put("CheckIn", headerBooking.getCheckIn());
            value.put("CheckOut", headerBooking.getCheckOut());
            value.put("TotalPayment", headerBooking.getTotalPayment());
            value.put("BookingDate", headerBooking.getBookingDate());
            value.put("StatusPayment", headerBooking.getStatusPayment());
            db.insert("HeaderBooking", null, value);
            db.close();

         return true;
    }

    public Boolean updateHeaderBooking(HeaderBooking headerBooking){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put("Period", headerBooking.getPeriod());
        value.put("CheckIn", headerBooking.getCheckIn());
        value.put("CheckOut", headerBooking.getCheckOut());
        value.put("TotalPayment", headerBooking.getTotalPayment());
        value.put("BookingDate", headerBooking.getBookingDate());

        db.update("HeaderBooking", value, "BookingID=" + headerBooking.getBookingID(), null);
        db.close();
        return true;
    }

    public Boolean deleteHeaderBooking(HeaderBooking headerBooking){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("HeaderBooking", "BookingID=" + headerBooking.getBookingID(), null);
        db.close();
        return true;
    }

    /*
    Edited By William Loo
    Date : 10 Mei 2020
    Purpose : bikin getHeaderBookingByID sm apus get sebelumnya
*/

    public List<HeaderBooking> getHeaderBookingByID(String Email){
        SQLiteDatabase db = getWritableDatabase();

        List<HeaderBooking> headerBookingList = new ArrayList<>();



        Cursor cursor = db.rawQuery("SELECT * FROM HeaderBooking hb " +
                "JOIN MsUser ms ON hb.UserID = ms.UserID " +
                "WHERE ms.Email LIKE ?", new String[]{Email});

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            HeaderBooking headerBooking = new HeaderBooking();
            headerBooking.setBookingID(cursor.getInt(cursor.getColumnIndex("BookingID")));
            headerBooking.setUserID(cursor.getInt(cursor.getColumnIndex("UserID")));
            headerBooking.setPetID(cursor.getInt(cursor.getColumnIndex("PetID")));
            headerBooking.setPromoID(cursor.getInt(cursor.getColumnIndex("PromoID")));
            headerBooking.setHotelID(cursor.getInt(cursor.getColumnIndex("HotelID")));
            headerBooking.setCheckIn(cursor.getString(cursor.getColumnIndex("CheckIn")));
            headerBooking.setCheckOut(cursor.getString(cursor.getColumnIndex("CheckOut")));
            headerBooking.setBookingDate(cursor.getString(cursor.getColumnIndex("BookingDate")));
            headerBooking.setPeriod(cursor.getString(cursor.getColumnIndex("Period")));
            headerBooking.setTotalPayment(cursor.getString(cursor.getColumnIndex("TotalPayment")));
            headerBooking.setStatusPayment(cursor.getString(cursor.getColumnIndex("StatusPayment")));
            headerBookingList.add(headerBooking);
            cursor.moveToNext();
        }
        db.close();
        return headerBookingList;
    }
}
