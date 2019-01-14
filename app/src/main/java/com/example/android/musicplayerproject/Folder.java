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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Folder extends AppCompatActivity {
    Button home;
    Button search;
    Button queue;
    Button folder;
    Button profile;
    EditText edit_search;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        MainActivity.QUEUE = false;
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listItems);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        home = (Button) findViewById(R.id.home);
        search = (Button) findViewById(R.id.search);
        queue = (Button) findViewById(R.id.queue_music);
        folder = (Button) findViewById(R.id.folder);
        profile = (Button) findViewById(R.id.profile);
        edit_search = (EditText) findViewById(R.id.editText_search);
        listItems.add("Ghoomar");
        listItems.add("Aj Se Teri");
        listItems.add("Jogi");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) (listView.getItemAtPosition(i));
                Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();
                String s = "";
                switch (i) {
                    case 0:
                        s = "Padmavat";
                        break;
                    case 1:
                        s = "Padman";
                        break;
                    case 2:
                        s = "Saadi";
                        break;
                }
                Intent intent = new Intent(Folder.this, PlayPause.class);
                intent.putExtra("keys", s);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Folder.this, MainActivity.class);
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
                Folder.this.adapter.getFilter().filter(editable);
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
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Folder.this, Profile.class);
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
                Toast.makeText(Folder.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.help:
                Toast.makeText(Folder.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.settings:
                Toast.makeText(Folder.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.expanded_menu:
                Toast.makeText(Folder.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}
