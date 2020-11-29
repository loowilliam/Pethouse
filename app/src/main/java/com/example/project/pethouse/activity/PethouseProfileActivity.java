package com.example.project.pethouse.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.pethouse.R;
import com.example.project.pethouse.ViewPagerAdapterPetHouseProfile;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsHotel;
import com.example.project.pethouse.repository.MsHotelRepository;

import java.util.Timer;
import java.util.TimerTask;

public class PethouseProfileActivity extends AppCompatActivity {

    /*
    Created By William Loo
    Date : 22 Maret 2020
    Purpose : Customer can view pethouse location profile
    */

    /*
    Updated By Eric Pangiawan
    Date: 26 April 2020 0:28
    Purpose: Adding details to Pethouse Description
    */

    /*
    Updated By Eric Pangiawan
    Date: 10 May 2020 17:33 PM
    Purpose: Add Back Button, add TextView
    */
    ViewPager viewPagerPetHouseProfile;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    private ImageView ButtonBack;
    private Button btnBookNow;
    private TextView TextViewDescription, TextViewName, TextViewLocation, TextViewSlot;
    private Integer[] images;
    private HeaderBooking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pethouse_profile);

        //findViewById
        TextViewName = findViewById(R.id.tv_pethousename);
        TextViewLocation = findViewById(R.id.tv_location);
        TextViewDescription = findViewById(R.id.tv_pethousedescription);
        TextViewSlot = findViewById(R.id.tv_remainingslot);
        Intent intent = getIntent();
        booking = (HeaderBooking) intent.getSerializableExtra("HeaderBooking");

        MsHotelRepository HotelRepository = new MsHotelRepository(this);
        MsHotel MsHotel = HotelRepository.getHotel(booking.getHotelID());

        int index = booking.getHotelID();
        //1 = Jakarta Barat
        //2 = Jakarta Utara
        //3 = Jakarta Selatan
        //4 = Jakarta Timur
        //5 = Jakarta Pusat
        if(index == 1){
            images = new Integer[]{R.drawable.jakbar_1, R.drawable.jakbar_2, R.drawable.jakbar_3};
        }else if(index == 2){
            images = new Integer[]{R.drawable.jakut_1, R.drawable.jakut_2, R.drawable.jakut_3};
        }else if(index == 3){
            images = new Integer[]{R.drawable.jaksel_1, R.drawable.jaksel_2, R.drawable.jaksel_3};
        }else if(index == 4){
            images = new Integer[]{R.drawable.jaktim_1, R.drawable.jaktim_2, R.drawable.jaktim_3};
        }else if(index == 5){
            images = new Integer[]{R.drawable.jakpus_1, R.drawable.jakpus_2, R.drawable.jakpus_3};
        }
        TextViewName.setText(MsHotel.getHotelName());
        TextViewLocation.setText(MsHotel.getHotelLocation());
        System.out.println("Location: " + MsHotel.getHotelLocation());
        TextViewSlot.setText(MsHotel.getRemainingSlot() + " pets");
        TextViewDescription.setText(MsHotel.getHotelDesc());

        //Buat Button Book Now
        btnBookNow = findViewById(R.id.btnBookNow);
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PethouseProfileActivity.this, BookActivity.class);
                intent.putExtra("HeaderBooking", booking);
                startActivity(intent);
            }
        });

        //Set Back Button Boi
        ButtonBack = findViewById(R.id.iv_backbutton);
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Set View Pager Boi

        viewPagerPetHouseProfile = (ViewPager)findViewById(R.id.viewPagerPetHouseProfile);

        sliderDotsPanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapterPetHouseProfile viewPagerAdapterPetHouseProfile = new ViewPagerAdapterPetHouseProfile(this, images);

        viewPagerPetHouseProfile.setAdapter(viewPagerAdapterPetHouseProfile);

        dotsCount = viewPagerAdapterPetHouseProfile.getCount();
        dots = new ImageView[dotsCount];

        for(int i = 0; i < dotsCount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);

            sliderDotsPanel.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        viewPagerPetHouseProfile.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i  < dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            PethouseProfileActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPagerPetHouseProfile.getCurrentItem() == 0){
                        viewPagerPetHouseProfile.setCurrentItem(1);
                    } else if(viewPagerPetHouseProfile.getCurrentItem() == 1){
                        viewPagerPetHouseProfile.setCurrentItem(2);
                    } else {
                        viewPagerPetHouseProfile.setCurrentItem(0);
                    }

                }
            });

        }
    }
}
