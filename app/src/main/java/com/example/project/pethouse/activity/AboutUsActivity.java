package com.example.project.pethouse.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView ImageViewBack, ImageViewFacebook, ImageViewInstagram, ImageViewTwitter;

    /*
    Edited By: Eric
    Purpose: Add UI + back button to main menu
    Date: 08 May 2020
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageViewBack = findViewById(R.id.iv_backbutton);
        ImageViewFacebook = findViewById(R.id.iv_facebook);
        ImageViewInstagram = findViewById(R.id.iv_instagram);
        ImageViewTwitter = findViewById(R.id.iv_twitter);
        setOnClickListener();
    }

    private void setOnClickListener(){
        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, MainActivity.class));
            }
        });
        ImageViewInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com/pethouse_jkt/?hl=id")));
                finish();
            }
        });
        ImageViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/PetHouse_jkt")));
                finish();
            }
        });
        ImageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://web.facebook.com/andi.delara.94")));
                finish();
            }
        });
    }
}
