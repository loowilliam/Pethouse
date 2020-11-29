package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsHotel;
import com.example.project.pethouse.model.MsPet;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.HeaderBookingRepository;
import com.example.project.pethouse.repository.MsHotelRepository;
import com.example.project.pethouse.repository.MsPetRepository;

import java.util.Calendar;

/*
Last Edited By: Eric
Date: 25 April 2020 19:00
Purpose: Add Back Button

 */

/*
Last Edited By: Eric
Date: 11 May 2020 01:39 AM
Purpose: Add Owner Detail

 */

public class TransactionDetailActivity extends AppCompatActivity {

    private Button ButtonPay;
    private ImageView ImageViewBack;
    private HeaderBooking booking;
    private MsPet MsPet;
    private TextView TextViewOwnerName, TextViewOwnerPhone, TextViewOwnerEmail, TextViewOwnerPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactiondetail);
        ButtonPay = findViewById(R.id.btn_pay);
        ImageViewBack = findViewById(R.id.iv_backbutton);
        TextViewOwnerName = findViewById(R.id.tv_nameperson);
        TextViewOwnerEmail = findViewById(R.id.tv_emailname);
        TextViewOwnerPhone = findViewById(R.id.tv_phonenumber);
        TextViewOwnerPrice = findViewById(R.id.tv_price);

        Intent intent = getIntent();
        booking = (HeaderBooking) intent.getSerializableExtra("HeaderBooking");
        MsPet = (MsPet) intent.getSerializableExtra("MsPet");

        //Retrieve data from Ms Hotel
        MsHotelRepository HotelRepository = new MsHotelRepository(this);
        MsHotel MsHotel;
        MsHotel = HotelRepository.getHotel(booking.getHotelID());

        //Set Data to TextView
        TextViewOwnerName.setText(MsHotel.getOwnerName());
        TextViewOwnerEmail.setText(MsHotel.getOwnerEmail());
        TextViewOwnerPhone.setText(MsHotel.getOwnerPhone());
        if(booking.getPeriod().equals("Long Term")){
            TextViewOwnerPrice.setText("Rp. " + 200000 + ",- /days");
        }else if(booking.getPeriod().equals("Short Term")){
            TextViewOwnerPrice.setText("Rp. " + 15000 + ",- /hours");
        }
        setOnClickListener();
    }

    private void setOnClickListener(){
        //Button Pay
        ButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perform inserting Header Booking
                //done inserting pet
                MsPetRepository petRepository = new MsPetRepository(TransactionDetailActivity.this);
                boolean Insert = petRepository.insert(MsPet);


                int id = petRepository.getSize();
                System.out.println("a"+id);
                //inserting header
                SharedPref sharedPref = new SharedPref(TransactionDetailActivity.this);
                MsUser MsUser = sharedPref.loadUser();
                booking.setUserID(MsUser.getUserID());
                booking.setPetID(id);

                int total = 0;
                if(booking.getPeriod().equals("Long Term")){
                    booking.setTotalPayment("200000");
                }else if(booking.getPeriod().equals("Short Term")){
                    booking.setTotalPayment("15000");
                }
                Calendar calendar = Calendar.getInstance();
                int day, month, year;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                String date = String.format("%02d/%02d/%d", day, month, year);
                booking.setBookingDate(date);
                booking.setStatusPayment("Pending");

                HeaderBookingRepository bookingRepository = new HeaderBookingRepository(TransactionDetailActivity.this);
                boolean insert = bookingRepository.insertToHeaderBooking(booking);
                System.out.println("Booking" + insert);
                Intent intent = new Intent(TransactionDetailActivity.this, TransactionSuccessActivity.class);

                startActivity(intent);
            }
        });
        //Button Back
        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
