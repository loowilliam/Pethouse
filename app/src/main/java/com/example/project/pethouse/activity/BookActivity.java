package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsPet;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.MsPetRepository;
import com.example.project.pethouse.repository.MsUserRepository;


/*
Edited By: Eric
Date: Thursday, 25 April 2020 0:16
Purpose: Making Book Activity
*/

public class BookActivity extends AppCompatActivity {
    private Spinner DropDownPetType;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton, radioMale, radioFemale;
    private Button ButtonContinue;
    private ImageView ImageViewBack;
    private EditText PetName, PetAge, PetDesc;
    private HeaderBooking booking;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        String[] TypeList = new String[]{
                "Dog",
                "Cat"
        };
        ImageViewBack = findViewById(R.id.iv_backbutton);

        DropDownPetType = findViewById(R.id.spinner_type);
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_spinner_dropdown_item, TypeList);
        DropDownPetType.setAdapter(adapterType);
        ButtonContinue = findViewById(R.id.btn_continue);

        PetName = findViewById(R.id.et_name);
        PetAge = findViewById(R.id.et_age);
        PetDesc = findViewById(R.id.et_description);

        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        Intent intent = getIntent();
        booking = (HeaderBooking) intent.getSerializableExtra("HeaderBooking");

        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strPetType = DropDownPetType.getSelectedItem().toString();
                String strPetName = PetName.getText().toString().trim();
                int Gender = radioSexGroup.getCheckedRadioButtonId();
                String strPetAge = PetAge.getText().toString().trim();
                String strPetDesc = PetDesc.getText().toString().trim();

                MsPet msPet = new MsPet();
                SharedPref SharedPref = new SharedPref(getApplicationContext());
                MsUser MsUser = SharedPref.loadUser();
                msPet.setPetOwnerName(MsUser.getUserName());
                msPet.setPetType(strPetType);
                msPet.setPetName(strPetName);
                if(Gender == radioMale.getId()){
                    msPet.setPetGender("Male");
                }else if(Gender==radioFemale.getId()){
                    msPet.setPetGender("Female");
                }
                msPet.setPetAge(strPetAge);
                msPet.setPetDesc(strPetDesc);



                Intent intentToDetail = new Intent(BookActivity.this, TransactionDetailActivity.class);
                intentToDetail.putExtra("HeaderBooking", booking);
                intentToDetail.putExtra("MsPet", msPet);
                startActivity(intentToDetail);

            }
        });
        //panggil radio button
        addOnListenerOnButton();
    }

    private void addOnListenerOnButton(){
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        //disini kita bisa menambahkan kalo sudah menekan booking
    }
}
