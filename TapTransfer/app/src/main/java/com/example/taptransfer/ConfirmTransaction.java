package com.example.taptransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmTransaction extends AppCompatActivity {
    TextView txtAccount;
    TextView txtMoney;


    Button btnDecline;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transaction);

        txtAccount = findViewById(R.id.textView2);
        txtMoney = findViewById(R.id.textView3);

        btnDecline = findViewById(R.id.button);
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmTransaction.this, MainActivity.class));
            }
        });
        btnConfirm = findViewById(R.id.button2);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmTransaction.this, TransferStuff.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String accName = extras.getString("AccName");
            String money = extras.getString("Money");
            String accID = extras.getString("AccId");

            String rands = ""+Integer.parseInt(money)/100;
            int centsI = Integer.parseInt(money)%100;
            String cents = String.format("%02d",centsI);

            txtAccount.setText("Account holder: " + accName);
            txtMoney.setText("Price: R" + rands + "," + cents);

            //The key argument here must match that used in the other activity
        }
    }
}