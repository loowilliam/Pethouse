package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.MsUserRepository;

public class EditActivity extends AppCompatActivity {

    private EditText EditTextName, EditTextEmail, EditTextPhone,EditTextPassword, EditTextReenterPassword;
    private Button ButtonSave;
    private ImageView ButtonBack;
    private MsUser MsUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

//        Name
        EditTextName = findViewById(R.id.et_namevalue);
//        Email
        EditTextEmail = findViewById(R.id.et_emailvalue);
//        Phone
        EditTextPhone = findViewById(R.id.et_phonevalue);
//        Password
        EditTextPassword = findViewById(R.id.et_passwordvalue);
        EditTextReenterPassword = findViewById(R.id.et_repasswordvalue);
        ButtonSave = findViewById(R.id.btnSave);
        ButtonBack = findViewById(R.id.iv_backbutton);
        SharedPref SharedPref = new SharedPref(EditActivity.this);


        MsUser = SharedPref.loadUser();
        EditTextName.setText(MsUser.getUserName());
        EditTextEmail.setText(MsUser.getEmail());
        EditTextPhone.setText(MsUser.getPhone());

        setListener();
    }

    private void setListener(){
        //kalau udah mau save
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = false;
                String name = EditTextName.getText().toString().trim();
                String email= EditTextEmail.getText().toString().trim();
                String phone= EditTextPhone.getText().toString().trim();
                String password= EditTextPassword.getText().toString().trim();
                String reenter = EditTextReenterPassword.getText().toString().trim();
                MsUser checkUser = new MsUser();
                MsUserRepository UserRepository = new MsUserRepository(EditActivity.this);
                if(password.length() == 0){
                    Toast.makeText(EditActivity.this, "Please input password", Toast.LENGTH_SHORT).show();
                }

                if(checkName(name) == false){
                    isExist = true;
                    Toast.makeText(EditActivity.this, "Name is Wrong Format", Toast.LENGTH_SHORT).show();
                }
                else if(checkEmail(email) == false){
                    isExist = true;
                    Toast.makeText(EditActivity.this, "Email is Wrong Format", Toast.LENGTH_SHORT).show();
                }
                else if(checkPhone(phone) == false){
                    isExist = true;
                    Toast.makeText(EditActivity.this, "Phone Number is Wrong Format", Toast.LENGTH_SHORT).show();
                }
                else if(passwordisAlphanumeric(password) == false){
                    isExist = true;
                    Toast.makeText(EditActivity.this, "Password is Wrong Format", Toast.LENGTH_SHORT).show();
                }else if(reenter.length() != 0){
                    if(reenter.equals(password) == true){
                        isExist = true;
                        Toast.makeText(EditActivity.this, "Your new password is the same as the old one", Toast.LENGTH_SHORT).show();
                    }else if(passwordisAlphanumeric(reenter) == false){
                        isExist = true;
                        Toast.makeText(EditActivity.this, "New Password is Wrong Format", Toast.LENGTH_SHORT).show();
                    }
                }
                //check Unique dari Email
                else{
                    if((checkUser = UserRepository.checkLogin(email, password)) != null){
                        //berarti ada
                        //check apakah emailnya diri sendiri
                        if(checkUser.getUserID() != MsUser.getUserID()){
                            //ini terjadi kalau ternyata sudah ada email yang sama
                            Toast.makeText(EditActivity.this, "This email has been used!", Toast.LENGTH_SHORT).show();
                            isExist = true;
                        }else{
                            //ini sebenarnya udah oke (email password udah benar, dan hanya ganti nama dll), tp sambungin ke bawah aja
                            isExist = false;
                        }
                    }else{
                        Toast.makeText(EditActivity.this, "Your Email or Password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                if(isExist == false){
                    //update data
                    //disini pasti sudah unique jadi tinggal update
                    MsUser.setEmail(email);
                    MsUser.setUserName(name);
                    MsUser.setPhone(phone);
                    if(reenter.length() != 0){
                        MsUser.setPassword(reenter);
                    }else{
                        MsUser.setPassword(password);
                    }
                    //Disini save SharedPrefnya dulu
                    SharedPref SharedPref = new SharedPref(EditActivity.this);
                    SharedPref.saveUser(MsUser);
                    //Disini simpan ke dalam database
                    Boolean isUpdated = UserRepository.updateMsUser(MsUser);
                    if(isUpdated){
                        Toast.makeText(EditActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(EditActivity.this, "Oops! Something's wrong", Toast.LENGTH_SHORT).show();
                    }

                    startActivity(new Intent(EditActivity.this, MainActivity.class));
                }
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish an activity
                finish();
            }
        });
    }

    public boolean checkName(String Name) {
        if(Name.length() < 4) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEmail(String email){

        if(email.length() > 31){
            return false;
        }

        if(!email.endsWith( ".com") && !email.endsWith( ".co.id") && !email.endsWith( ".ac.id") && !email.endsWith( ".edu")){
            return false;
        }

        if(Character.isAlphabetic(email.charAt(0)) != true){
            return false;
        }

        if(email.contains(".@") || email.contains("@.")){
            return false;
        }
        return true;
    }

    private boolean checkPhone(String phone){

        if(phone.length() - 2 < 11 || phone.length() - 2 > 12){
            return false;
        }

        if(!phone.startsWith("+628")){
            return false;
        }
        return true;
    }

    private boolean passwordisAlphanumeric(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isAlphabetic(c))
                return false;
        }

        if(str.length() < 8 || str.length() > 15){
            return false;
        }
        return true;
    }
}
