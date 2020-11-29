package com.example.project.pethouse.repository;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat DatabaseHelper untuk model yang tersedia
*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "PethouseDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String MsUserQuery = "CREATE TABLE MsUser(" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserName TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Phone TEXT NOT NULL," +
                "Password TEXT NOT NULL)";

        db.execSQL(MsUserQuery);

        String MsPromoQuery = "CREATE TABLE MsPromo(" +
                "PromoID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "PromoName TEXT NOT NULL," +
                "PromoDesc TEXT NOT NULL," +
                "PromoCode TEXT NOT NULL," +
                "ValidDate STRING NOT NULL)";

        db.execSQL(MsPromoQuery);

        String MsPetQuery = "CREATE TABLE MsPet(" +
                "PetID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "PetType TEXT NOT NULL," +
                "PetName TEXT NOT NULL," +
                "PetGender TEXT NOT NULL," +
                "PetAge INTEGER NOT NULL," +
                "PetDesc TEXT NOT NULL," +
                "PetOwnerName TEXT NOT NULL)";

        db.execSQL(MsPetQuery);

        String MsHotelQuery = "CREATE TABLE MsHotel(" +
                "HotelID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "HotelName TEXT NOT NULL," +
                "HotelLocation TEXT NOT NULL," +
                "HotelDesc TEXT NOT NULL," +
                "RemainingSlot INTEGER NOT NULL," +
                "OwnerName TEXT NOT NULL," +
                "OwnerEmail TEXT NOT NULL," +
                "OwnerPhone TEXT NOT NULL," +
                "OwnerPrice INTEGER NOT NULL)";

        db.execSQL(MsHotelQuery);

        /*
   Edited By William Loo
    Date : 10 Mei 2020
    Purpose : - promo di bikin nullable
              - tambah string queryHeaderBooking buat data dummy
*/

        String HeaderBookingQuery = "CREATE TABLE HeaderBooking(" +
                "BookingID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserID INTEGER NOT NULL," +
                "PromoID INTEGER," +
                "PetID INTEGER NOT NULL," +
                "HotelID INTEGER NOT NULL," +
                "Period TEXT NOT NULL," +
                "CheckIn TEXT NOT NULL," +
                "CheckOut TEXT," +
                "TotalPayment TEXT NOT NULL," +
                "BookingDate TEXT NOT NULL," +
                "StatusPayment TEXT NOT NULL)";

        db.execSQL(HeaderBookingQuery);

//        String queryHeaderBooking = "insert into HeaderBooking values (null, 0,0,0,0,'long','15/04/2020','12/08/2020','10000','10/05/2020','pending')";
//        db.execSQL(queryHeaderBooking);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String MsUserQuery = "DROP TABLE IF EXISTS MsUser";
        db.execSQL(MsUserQuery);

        String MsPromoQuery = "DROP TABLE IF EXISTS MsPromo";
        db.execSQL(MsPromoQuery);

        String MsHotelQuery = "DROP TABLE IF EXISTS MsHotel";
        db.execSQL(MsHotelQuery);

        String MsPetQuery = "DROP TABLE IF EXISTS MsPet";
        db.execSQL(MsPetQuery);

        String HeaderBookingQuery = "DROP TABLE IF EXISTS HeaderBooking";
        db.execSQL(HeaderBookingQuery);



        onCreate(db);
    }
}
