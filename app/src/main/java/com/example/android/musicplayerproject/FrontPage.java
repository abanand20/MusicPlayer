package com.example.android.musicplayerproject;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FrontPage extends AppCompatActivity {

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_frontpage);

        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                Intent i = new Intent(FrontPage.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
