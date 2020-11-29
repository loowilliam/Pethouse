package com.example.project.pethouse.repository;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat repository untuk msuser
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.pethouse.model.MsUser;

public class MsUserRepository extends DatabaseHelper{

    public MsUserRepository(Context context) { super(context); }

    public boolean insertToMsUser(MsUser user){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        //kalo email udah ada, akan return false
        Cursor result =  db.rawQuery( "SELECT * FROM MsUser WHERE Email LIKE '" + user.getEmail() + "'", null);

        if(result.getCount() == 0) {
            value.put("UserName", user.getUserName());
            value.put("Email", user.getEmail());
            value.put("Phone", user.getPhone());
            value.put("Password", user.getPassword());

            db.insert("MsUser", null, value);
            db.close();
            return true;
        }
        return false;
    }

    public Boolean updateMsUser(MsUser user){
        SQLiteDatabase db = getWritableDatabase();

        //kalo email udah ada, akan return false
        Cursor result =  db.rawQuery( "SELECT * FROM MsUser WHERE Email = '" + user.getEmail() + "'", null);

        ContentValues value = new ContentValues();
        result.moveToFirst();
        if(result.getCount() == 0 || result.getInt(result.getColumnIndex("UserID")) == user.getUserID()) {
            value.put("UserName", user.getUserName());
            value.put("Email", user.getEmail());
            value.put("Phone", user.getPhone());
            value.put("Password", user.getPassword());

            db.update("MsUser", value, "UserID = " + user.getUserID(), null);
            db.close();
            return true;
        }
        return false;
    }

    public void deleteMsUser(MsUser user){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("MsUser", "UserID=" + user.getUserID(), null);
        db.close();
    }

    public boolean checkEmailExist(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsUser WHERE Email = ?", new String[]{email});
        if(cursor.getCount() > 0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public MsUser checkLogin(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsUser WHERE Email = ? AND Password = ?", new String[]{email, password});
        if(cursor.getCount() > 0){
            //jika dapat, maka kita menambahkan MsUsernya
            cursor.moveToFirst();
            MsUser MsUser = new MsUser();
            MsUser.setUserID(cursor.getInt(cursor.getColumnIndex("UserID")));
            MsUser.setEmail(email);
            MsUser.setPassword(password);
            MsUser.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));
            MsUser.setUserName(cursor.getString(cursor.getColumnIndex("UserName")));
            db.close();
            return MsUser;
        }
        else{
            return null;
        }
    }

    public void updatePassword(MsUser user, String newPassword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("Password", newPassword);
        db.update("MsApplicant", value, "UserID = " + user.getUserID(), null);
        db.close();
    }

}
