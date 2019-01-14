package com.example.android.musicplayerproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PlayPause extends AppCompatActivity {
    public static int oneTimeOnly = 0;
    public MediaPlayer song_first;
    Button home;
    Button folder;
    Button profile;
    String songName;
    private Button fast_forward;
    private Button search;
    private Button previous;
    private Button next;
    private Button replay;
    private Button mute;
    private Button unmute;
    private Button shuffle;
    private Button pause;
    private Button play;
    private Button rewind;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tv_left_seek;
    private TextView tv_right_seek;
    private TextView tv_middle_seek;
    private TextView songTitle_textView;
    private String mSongTitle = "Untitle";
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = song_first.getCurrentPosition();
            startTime = song_first.getCurrentPosition();
            tv_left_seek.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playpause);
        if (MainActivity.runningActivity != null && !MainActivity.QUEUE) {
            MainActivity.runningActivity.finish();
        }
        songTitle_textView = (TextView) findViewById(R.id.tv_song_title);
        home = (Button) findViewById(R.id.home);
        folder = (Button) findViewById(R.id.folder);
        profile = (Button) findViewById(R.id.profile);
        fast_forward = (Button) findViewById(R.id.forward);
        search = (Button) findViewById(R.id.search);
        pause = (Button) findViewById(R.id.pause_id);
        play = (Button) findViewById(R.id.play_id);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        replay = (Button) findViewById(R.id.replay);
        mute = (Button) findViewById(R.id.mute);
        unmute = (Button) findViewById(R.id.unmute);
        shuffle = (Button) findViewById(R.id.shuffle);
        rewind = (Button) findViewById(R.id.rewind_id);
        tv_left_seek = (TextView) findViewById(R.id.text_left_seek);
        tv_right_seek = (TextView) findViewById(R.id.text_right_seek);
        tv_middle_seek = (TextView) findViewById(R.id.text_middle_seek);
        String message = getIntent().getStringExtra("keys");
        if (message.equals("Padmavat") || message.equals("Lata") || message.equals("Kajal")) {
            song_first = MediaPlayer.create(PlayPause.this, R.raw.ghoomar);
            songName = "Ghoomar";
        } else {
            if (message.equals("Padman") || message.equals("Udit") || message.equals("Salim")) {
                song_first = MediaPlayer.create(PlayPause.this, R.raw.aj_se_teri);
                songName = "Aj Se Teri";
            } else {
                if (message.equals("Saadi") || message.equals("Shreya") || message.equals("Chak")) {
                    song_first = MediaPlayer.create(PlayPause.this, R.raw.jogi);
                    songName = "Jogi";
                }
            }
        }
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        pause.setEnabled(false);
        if (MainActivity.QUEUE) {
            playing();
            play.setEnabled(false);
            pause.setEnabled(false);
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.QUEUE)
                    MainActivity.runningActivity = PlayPause.this;
                Intent intent = new Intent(PlayPause.this, MainActivity.class);
                startActivity(intent);
            }
        });
        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.QUEUE)
                    MainActivity.runningActivity = PlayPause.this;
                Intent intent = new Intent(PlayPause.this, Folder.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.QUEUE)
                    MainActivity.runningActivity = PlayPause.this;
                Intent intent = new Intent(PlayPause.this, Profile.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mSongTitle = songName;
                tv_left_seek.setVisibility(View.VISIBLE);
                tv_middle_seek.setVisibility(View.VISIBLE);
                tv_right_seek.setVisibility(View.VISIBLE);
                tv_middle_seek.setText(mSongTitle);
                songTitle_textView.setText(mSongTitle);
                song_first.start();
                finalTime = song_first.getDuration();
                startTime = song_first.getCurrentPosition();
                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                tv_right_seek.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );
                tv_left_seek.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                seekbar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                pause.setEnabled(true);
                play.setEnabled(false);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_SHORT).show();
                song_first.pause();
                pause.setEnabled(false);
                play.setEnabled(true);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.QUEUE)
                    MainActivity.runningActivity = PlayPause.this;
                Intent intent = new Intent(PlayPause.this, Folder.class);
                startActivity(intent);
            }
        });
        fast_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    song_first.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    song_first.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Previous Button : updating soon", Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Next Button : updating soon", Toast.LENGTH_SHORT).show();
            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Replay Button : updating soon", Toast.LENGTH_SHORT).show();
            }
        });
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_first.setVolume(0, 0);
            }
        });
        unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_first.setVolume(1, 1);
            }
        });
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Shuffle Button : updating soon", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        song_first.stop();
    }

    private void playing() {
        Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
        mSongTitle = songName;
        tv_left_seek.setVisibility(View.VISIBLE);
        tv_middle_seek.setVisibility(View.VISIBLE);
        tv_right_seek.setVisibility(View.VISIBLE);
        tv_middle_seek.setText(mSongTitle);
        songTitle_textView.setText(mSongTitle);
        //song_first.start();
        finalTime = song_first.getDuration();
        startTime = song_first.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        tv_right_seek.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );
        tv_left_seek.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );
        seekbar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime, 100);
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
                Toast.makeText(PlayPause.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.help:
                Toast.makeText(PlayPause.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.settings:
                Toast.makeText(PlayPause.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.expanded_menu:
                Toast.makeText(PlayPause.this, "Updating soon", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}

