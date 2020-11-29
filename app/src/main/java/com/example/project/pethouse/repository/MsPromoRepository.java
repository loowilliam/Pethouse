package com.example.project.pethouse.repository;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat repository untuk promo
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.pethouse.model.MsPromo;

public class MsPromoRepository extends DatabaseHelper {

    public MsPromoRepository(Context context) { super(context); }

    public boolean insertToMsPromo(MsPromo promo){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

            value.put("PromoName", promo.getPromoName());
            value.put("PromoDesc", promo.getPromoDesc());
            value.put("PromoCode", promo.getPromoCode());
            value.put("ValidDate", promo.getValidDate());

            db.insert("MsPromo", null, value);
            db.close();
            return true;
    }

    public boolean isInserted(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsPromo", null);
        if(cursor.getCount() == 0){
            return false;
        }
        db.close();
        return true;
    }

    public void deleteMsPromo(MsPromo promo){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("MsPromo", "PromoID=" + promo.getPromoID(), null);
        db.close();
    }

    public MsPromo getPromo(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsPromo WHERE PromoID = " + id, null);
        cursor.moveToFirst();
        System.out.println(cursor.getCount());
        if(cursor.getCount() > 0){
            MsPromo MsPromo = new MsPromo();
            MsPromo.setPromoID(cursor.getInt(cursor.getColumnIndex("PromoID")));
            MsPromo.setPromoName(cursor.getString(cursor.getColumnIndex("PromoName")));
            MsPromo.setPromoDesc(cursor.getString(cursor.getColumnIndex("PromoDesc")));
            MsPromo.setPromoCode(cursor.getString(cursor.getColumnIndex("PromoCode")));
            MsPromo.setValidDate(cursor.getString(cursor.getColumnIndex("ValidDate")));
            return MsPromo;
        }
        db.close();
        return null;
    }
}
