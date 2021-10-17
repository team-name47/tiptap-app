package com.example.taptransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TransferStuff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_stuff);

        Bundle extras = getIntent().getExtras();

        String accName = extras.getString("AccName");
        String money = extras.getString("Money");
        String accID = extras.getString("AccId");
        String networkInfo = extras.getString("network");


        // Handle stuff

        startActivity(new Intent(TransferStuff.this, MainActivity.class));
    }
}