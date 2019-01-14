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

public class Profile extends AppCompatActivity {
    Button folder;
    Button purchase;
    private Button home;
    private Button queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        home = (Button) findViewById(R.id.home);
        queue = (Button) findViewById(R.id.queue_music);
        folder = (Button) findViewById(R.id.folder);
        purchase = (Button) findViewById(R.id.purchase);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.runningActivity != null) {
                    MainActivity.QUEUE = true;
                    Intent intent = MainActivity.runningActivity.getIntent();
                    startActivity(intent);
                    intent.putExtra("keys", intent.getStringExtra("keys"));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing in queue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Folder.class);
                startActivity(intent);
                finish();
            }
        });
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Payment.class);
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
                Toast.makeText(Profile.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.help:
                Toast.makeText(Profile.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.settings:
                Toast.makeText(Profile.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.expanded_menu:
                Toast.makeText(Profile.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

}

