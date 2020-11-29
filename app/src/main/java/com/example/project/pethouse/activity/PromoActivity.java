package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.model.MsPromo;
import com.example.project.pethouse.repository.MsPromoRepository;

public class PromoActivity extends AppCompatActivity {

    private ImageView ImageViewPromo;
    private TextView TextViewPromo, TextViewPromoCode, TextViewName;
    private ImageView ImageViewBack;

    /*
    Edited By: Eric
    Date: 25 April 2020 23:46
    Purpose: Adding Back Button, Description Text
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        ImageViewPromo = findViewById(R.id.iv_cardpromo);
        TextViewPromo = findViewById(R.id.tv_contentdescription);
        ImageViewBack = findViewById(R.id.iv_backbutton);
        TextViewName = findViewById(R.id.tv_headerpromo);
        ImageViewPromo.setImageResource(R.drawable.banner_pengguna_baru);
        TextViewPromoCode = findViewById(R.id.tv_kodepromo);
        MsPromoRepository PromoRepository = new MsPromoRepository(this);
        MsPromo MsPromo = PromoRepository.getPromo(1);
        System.out.println(MsPromo.getPromoDesc());
        TextViewPromo.setText(MsPromo.getPromoDesc());
        TextViewName.setText(MsPromo.getPromoName());
        TextViewPromoCode.setText(MsPromo.getPromoCode());
        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PromoActivity.this, MainActivity.class));
            }
        });
    }
}
