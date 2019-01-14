package com.example.android.musicplayerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Album extends AppCompatActivity {
    EditText edit_search;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ImageView imageView;
    private Button home;
    private Button search;
    private Button queue;
    private Button folder;
    private Button profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listItems);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        Intent iin = getIntent();
        final String message = iin.getStringExtra("keys");
        home = (Button) findViewById(R.id.home);
        search = (Button) findViewById(R.id.search);
        queue = (Button) findViewById(R.id.queue_music);
        folder = (Button) findViewById(R.id.folder);
        profile = (Button) findViewById(R.id.profile);
        edit_search = (EditText) findViewById(R.id.editText_search);
        imageView = (ImageView) findViewById(R.id.image_album);
        if (message.equals("Kajal")) {
            imageView.setImageResource(R.drawable.kajalg);
            listItems.add("Ghoomar");
        }
        if (message.equals("Salim")) {
            imageView.setImageResource(R.drawable.salim);
            listItems.add("Aj Se Teri");
        }
        if (message.equals("Chak")) {
            imageView.setImageResource(R.drawable.chakde);
            listItems.add("Jogi");
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_search.setVisibility(View.GONE);
                Intent intent = new Intent(Album.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_search.setVisibility(View.VISIBLE);
            }
        });
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Album.this.adapter.getFilter().filter(editable);
            }
        });
        queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Album.this, PlayPause.class);
                startActivity(intent);
                finish();
            }
        });
        folder.setOnClickListener(new View.OnClickListener() {
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
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Album.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) (listView.getItemAtPosition(i));
                Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(Album.this, PlayPause.class);
                intent.putExtra("keys", message);
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
                Toast.makeText(Album.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.help:
                Toast.makeText(Album.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.settings:
                Toast.makeText(Album.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.expanded_menu:
                Toast.makeText(Album.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}