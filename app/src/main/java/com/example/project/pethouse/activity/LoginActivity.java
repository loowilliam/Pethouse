package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.MsUserRepository;

public class LoginActivity extends AppCompatActivity {
    private Button btnRegister, btnLogin;
    private EditText Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ambil Id dari layout untuk diambil datanya
        btnRegister = (Button)findViewById(R.id.btn_register);
        btnLogin = (Button)findViewById(R.id.btn_login);

        /*
        Edited By William Loo
        Date : 9 Mei 2020
        Purpose : Buat shared preferences dan edittext email, password
        */
        Email = (EditText)findViewById(R.id.et_email);
        Password = (EditText)findViewById(R.id.et_password);

        //men-set button ketika di tekan dan melakukan sesuatu, hal serupa juga sama untuk file yang lain
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fungsi ini akan mengarahkan activity ke activity lain
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = Email.getText().toString().trim();
                String strPassword = Password.getText().toString().trim();

                boolean isEmail = false, isPassword = false;
                isEmail = checkEmail(strEmail);
                isPassword = passwordisAlphanumeric(strPassword);
                if (isEmail == false) {
                    Toast.makeText(LoginActivity.this, "Wrong email input i.e : me@me.me", Toast.LENGTH_SHORT).show();
                } else if (isPassword == false) {
                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }else if(isEmail && isPassword){
                    loginUser(strEmail, strPassword);
                }


            }
        });

    }

    private void loginUser(String strEmail, String strPassword){
        boolean in = false;
        MsUser user = new MsUser();
        MsUserRepository userRepository = new MsUserRepository(LoginActivity.this);
        user = userRepository.checkLogin(strEmail, strPassword);
        //cek kosong apa nggak
        if(user != null)
            in = true;
        else
            in = false;

        if(in == true){
            user.setEmail(strEmail);
            user.setPassword(strPassword);

            // save the user data in shared preferences
            SharedPref sharedPref = new SharedPref(LoginActivity.this);
            sharedPref.saveUser(user);

            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
        else{
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    //    Email
    private boolean checkEmail(String email){
//        length
        if(email.length() > 31){
            return false;
        }
//        endsWith
        if(!email.endsWith( ".com") && !email.endsWith( ".co.id") && !email.endsWith( ".ac.id") && !email.endsWith( ".edu")){
            return false;
        }
//        charat
        if(Character.isAlphabetic(email.charAt(0)) != true){
            return false;
        }
//        '@'/'.'
        if(email.contains(".@") || email.contains("@.")){
            return false;
        }
        return true;
    }

    //    password
    private boolean passwordisAlphanumeric(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isAlphabetic(c))
                return false;
        }
//        length
        if(str.length() < 8 || str.length() > 15){
            return false;
        }
        return true;
    }
}
