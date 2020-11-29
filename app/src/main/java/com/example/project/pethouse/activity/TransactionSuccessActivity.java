package com.example.project.pethouse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.pethouse.R;

public class TransactionSuccessActivity extends AppCompatActivity {
    private Button ButtonDetailTransaction;
    private TextView TextViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);
        ButtonDetailTransaction = findViewById(R.id.btnDetailTransaction);
        TextViewDescription = findViewById(R.id.tv_successdescription);
        TextViewDescription.setText("Congratulations!\n" +
                "You have successfully booked our pet service!\n" +
                "The payment will be done when you are at the pethouse hotel!\n" +
                "See you there!");

        ButtonDetailTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransactionSuccessActivity.this, MainActivity.class));
            }
        });
    }
}
