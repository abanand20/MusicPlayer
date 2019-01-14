package com.example.android.musicplayerproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Activity runningActivity;
    public static boolean QUEUE;
    EditText edit_search;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ScrollView scrollView;
    private Button home;
    private Button search;
    private Button queue;
    private Button folder;
    private Button profile;
    ImageView padmavat,padman,saadi,lata,shreya,udit,kajalg,salim,chakde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QUEUE = false;
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listItems);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        scrollView = (ScrollView) findViewById(R.id.scrollView_id);
        home = (Button) findViewById(R.id.home);
        search = (Button) findViewById(R.id.search);
        queue = (Button) findViewById(R.id.queue_music);
        folder = (Button) findViewById(R.id.folder);
        profile = (Button) findViewById(R.id.profile);
        edit_search = (EditText) findViewById(R.id.editText_search);
        padmavat = (ImageView) findViewById(R.id.padmavat);
        padman = (ImageView) findViewById(R.id.padman);
        saadi = (ImageView) findViewById(R.id.saadi);
        lata = (ImageView) findViewById(R.id.lata);
        shreya = (ImageView) findViewById(R.id.shreya);
        udit = (ImageView) findViewById(R.id.udit);
        kajalg = (ImageView) findViewById(R.id.kajalg);
        salim = (ImageView) findViewById(R.id.salim);
        chakde = (ImageView) findViewById(R.id.chakde);
        padmavat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTrending.class);
                intent.putExtra("keys", "Padmavat");
                startActivity(intent);
                finish();
            }
        });
        padman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTrending.class);
                intent.putExtra("keys", "Padman");
                startActivity(intent);
                finish();
            }
        });
        saadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTrending.class);
                intent.putExtra("keys", "Saadi");
                startActivity(intent);
                finish();
            }
        });
        lata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Artist.class);
                intent.putExtra("keys", "Lata");
                startActivity(intent);
                finish();
            }
        });
        shreya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Artist.class);
                intent.putExtra("keys", "Shreya");
                startActivity(intent);
                finish();
            }
        });
        udit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Artist.class);
                intent.putExtra("keys", "Udit");
                startActivity(intent);
                finish();
            }
        });
        kajalg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Album.class);
                intent.putExtra("keys", "Kajal");
                startActivity(intent);
                finish();
            }
        });
        salim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Album.class);
                intent.putExtra("keys", "Salim");
                startActivity(intent);
                finish();
            }
        });
        chakde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Album.class);
                intent.putExtra("keys", "Chak");
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                edit_search.setVisibility(View.GONE);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Folder.class);
                startActivity(intent);
                finish();
            }
        });
        queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (runningActivity != null) {
                    QUEUE = true;
                    Intent intent = runningActivity.getIntent();
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
                Intent intent = new Intent(MainActivity.this, Folder.class);
                startActivity(intent);
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });


    }

}