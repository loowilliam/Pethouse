package com.example.project.pethouse.SharedPreference;

/*
    Created By William Loo
    Date : 9 Mei 2020
    Purpose : buat sharedpref untuk simpen data user sementara
*/

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.pethouse.model.MsUser;

public class SharedPref {

    // What's the function of this class :
    // take care of saving and loading data from shared preferences xml.
    // remember, the data is saved in the form of xml.

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

    }

    public void saveUser(MsUser msUser){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserID", msUser.getUserID());
        editor.putString("UserName", msUser.getUserName());
        editor.putString("Email", msUser.getEmail());
        editor.putString("Phone", msUser.getPhone());
        editor.putString("Password", msUser.getPassword());

        editor.apply();
    }

    public MsUser loadUser(){
        MsUser msUser = new MsUser();
        msUser.setUserID(sharedPreferences.getInt("UserID", 0));
        msUser.setUserName(sharedPreferences.getString("UserName", ""));
        msUser.setEmail(sharedPreferences.getString("Email", ""));
        msUser.setPhone(sharedPreferences.getString("Phone", ""));
        msUser.setPassword(sharedPreferences.getString("Password", ""));
        return msUser;
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
