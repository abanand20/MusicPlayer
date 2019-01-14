package com.example.android.musicplayerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(Payment.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.help:
                Toast.makeText(Payment.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.settings:
                Toast.makeText(Payment.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.expanded_menu:
                Toast.makeText(Payment.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}
