package com.example.project.pethouse.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.pethouse.R;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.MsUserRepository;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLogin;
    /*
    Edited By William Loo
    Date : 9 Mei 2020
    Purpose : - Nambahin edittext username, phone, email, password dan reneterpassword
              - gabunging validasi jeff
              - buttonlogin ditambah insert ke dbnya
    */

    private EditText UserName, Phone, Email, Password, ReEnterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        UserName = (EditText)findViewById(R.id.et_username);
        Phone = (EditText)findViewById(R.id.et_phone);
        Email = (EditText)findViewById(R.id.et_email);
        Password = (EditText)findViewById(R.id.et_password);
        ReEnterPassword = (EditText)findViewById(R.id.et_re_enter_password);


        //Add event click, pada saat klik terjadi sesuatu sesuai keinginan kita
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = UserName.getText().toString().trim();
                String strPhone = Phone.getText().toString().trim();
                String strEmail = Email.getText().toString().trim();
                String strPassword = Password.getText().toString().trim();
                String strReEnterPassword = ReEnterPassword.getText().toString().trim();

                MsUser user = new MsUser();
                MsUserRepository userRepository = new MsUserRepository(getApplicationContext());

                user.setUserName(strUserName);
                user.setPhone(strPhone);
                user.setEmail(strEmail);
                user.setPassword(strPassword);

                if(!checkName(strUserName)) {
                    Toast.makeText(RegisterActivity.this, "Name must be 4 alphabet or higher", Toast.LENGTH_SHORT).show();
                } else if (!checkPhone(strPhone)) {
                    Toast.makeText(RegisterActivity.this, "Phone cannot be empty, or should be between 11 and 12 numbers", Toast.LENGTH_SHORT).show();
                } else if (!checkEmail(strEmail)) {
                    Toast.makeText(RegisterActivity.this, "Wrong email input i.e : me@me.me", Toast.LENGTH_SHORT).show();
                } else if (!passwordisAlphanumeric(strPassword)) {
                    Toast.makeText(RegisterActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                } else if (!rePassword(strPassword, strReEnterPassword)) {
                    Toast.makeText(RegisterActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isExist = userRepository.checkEmailExist(strEmail);
                    boolean Insert = userRepository.insertToMsUser(user);

                    if(Insert == true && isExist == false) {
                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();

                        Intent Intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(Intent);
                        finish();

                    }else {
                        Toast.makeText(RegisterActivity.this, "Register Failed, Make sure your email is unique", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    /*
    Edited By: Jeffrey
    Date: 08 May 2020, 11.00PM
    Purpose: Add all Validation
     */

    //Validasi Nama
    public boolean checkName(String Name) {
        if(Name.length() < 4) {
            return false;
        } else {
            return true;
        }
    }

    //    Phone Number
    private boolean checkPhone(String phone){
//        length
        if(phone.length() - 2 < 11 || phone.length() - 2 > 12){
            return false;
        }
//      startsWith
        if(!phone.startsWith("+628")){
            return false;
        }
        return true;
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

    //    re.password
    private boolean rePassword(String str, String repassword){
        if(!str.equals(repassword)){
            return false;
        }
        return true;
    }
}
