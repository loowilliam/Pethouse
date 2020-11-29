package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsHotel;
import com.example.project.pethouse.model.MsPet;
import com.example.project.pethouse.repository.MsHotelRepository;
import com.example.project.pethouse.repository.MsPetRepository;

/*
Edited By: Eric
Purpose: Add the Detail
Date: 11 May 2020 3:17 AM
 */

public class TransactionStatusActivity extends AppCompatActivity {
    private TextView TextViewBookingId, TextViewPetName, TextViewPetType, TextViewPetGender,
            TextViewPetAge, TextViewPetDescription, TextViewHotelName,
            TextViewCheckIn,TextViewCheckOut, TextViewTotalPayment, TextViewStatusPayment;
    private ImageView ButtonBack;
    private HeaderBooking booking;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_status);
        TextViewBookingId = findViewById(R.id.tv_bookingidcode); //done
        TextViewPetName = findViewById(R.id.tv_namepet); //done
        TextViewPetType = findViewById(R.id.tv_type);//done
        TextViewPetGender = findViewById(R.id.tv_gender);//done
        TextViewPetAge = findViewById(R.id.tv_age);//done
        TextViewPetDescription = findViewById(R.id.tv_description);//done
        TextViewHotelName = findViewById(R.id.tv_pethouse);
        TextViewCheckIn = findViewById(R.id.tv_checkindate); //done
        TextViewCheckOut = findViewById(R.id.tv_checkoutdate);//done
        TextViewTotalPayment = findViewById(R.id.tv_price);//done
        TextViewStatusPayment = findViewById(R.id.tv_status);//done
        ButtonBack = findViewById(R.id.iv_backbutton);

        Intent intent = getIntent();
        booking = (HeaderBooking) intent.getSerializableExtra("HeaderBooking");

        //set Text for Header Booking
        TextViewBookingId.setText(String.format("PH-1270%06d", booking.getBookingID()));
        TextViewCheckIn.setText(booking.getCheckIn());
        TextViewCheckOut.setText(booking.getCheckOut());

        if(booking.getPeriod().equals("Long Term")){
            TextViewTotalPayment.setText("Rp. "+ booking.getTotalPayment() +",-/days");
        }else if(booking.getPeriod().equals("Short Term")){
            TextViewTotalPayment.setText("Rp. "+ booking.getTotalPayment() +",-/hours");
        }
        TextViewStatusPayment.setText(booking.getStatusPayment());

        //set Text for Pet
        MsPet MsPet;
        MsPetRepository petRepository = new MsPetRepository(getApplicationContext());
        System.out.println(booking.getPetID());
        MsPet = petRepository.getPet(booking.getPetID());
        TextViewPetName.setText(MsPet.getPetName());
        TextViewPetDescription.setText(MsPet.getPetDesc());
        TextViewPetAge.setText("" + MsPet.getPetAge());
        TextViewPetGender.setText(MsPet.getPetGender());
        TextViewPetType.setText(MsPet.getPetType());

        //set Text for Hotel
        MsHotel MsHotel;
        MsHotelRepository HotelRepository = new MsHotelRepository(getApplicationContext());

        MsHotel = HotelRepository.getHotel(booking.getHotelID());
        TextViewHotelName.setText(MsHotel.getHotelName());

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
