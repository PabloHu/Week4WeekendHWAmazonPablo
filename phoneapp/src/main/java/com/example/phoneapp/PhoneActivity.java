package com.example.phoneapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

import java.util.Random;

public class PhoneActivity extends AppCompatActivity {
    private static PhoneActivity ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ins = this;


        Random rand = new Random();

        int  n = rand.nextInt(5000) + 1;
        String randomNum = Integer.toString(n);
        String phoneNumber = "8134516294";
        String smsBody = randomNum;

// Get the default instance of SmsManager
        SmsManager smsManager = SmsManager.getDefault();
// Send a text based SMS
        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
    }

    public static PhoneActivity  getInstace(){
        return ins;
    }

    public void updateTheTextView(final String t) {
        PhoneActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) findViewById(R.id.tvSmsMessage);
                textV1.setText(t);
            }
        });
    }
}
