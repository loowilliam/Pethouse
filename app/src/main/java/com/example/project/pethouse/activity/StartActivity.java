package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.model.MsUser;

public class StartActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(StartActivity.this);
        final MsUser user2 = sharedPref.loadUser();
        if(!user2.getEmail().equals("") && !user2.getPassword().equals("")){
            // Move to Home Activity
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

    }
}
