package com.example.stopwatch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView time;
    private Button start, pause, reset, lap;

    private boolean isRunning;
    private long starttime, elapsedtime, pausedtime;
    ;
    private Handler handler;
    private Runnable runnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        reset = findViewById(R.id.reset);
        lap = findViewById(R.id.lap);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        reset.setOnClickListener(this);
        lap.setOnClickListener(this);

        handler = new Handler();
        isRunning = false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            start();
        } else if (v.getId() == R.id.pause) {
            pause();
        } else if (v.getId() == R.id.reset) {
            reset();
        } else if (v.getId() == R.id.lap) {
            lap();
        }
    }


    private void start() {
        if (!isRunning) {
            if (pausedtime == 0) {
                starttime = System.currentTimeMillis();
            } else {
                starttime = System.currentTimeMillis() - pausedtime;
                pausedtime = 0;
            }
            handler.postDelayed(update, 10);
            isRunning = true;

            start.setEnabled(false);
            pause.setEnabled(true);
            reset.setEnabled(false);
            lap.setEnabled(true);
        }
    }



    private void pause() {
        if (isRunning) {
            handler.removeCallbacks(update);
            pausedtime = System.currentTimeMillis() - starttime;
            isRunning = false;

            start.setEnabled(true);
            pause.setEnabled(false);
            reset.setEnabled(true);
            lap.setEnabled(false);
        }
    }


    private void reset() {
        if (!isRunning) {
            elapsedtime = 0;
            pausedtime = 0;
            starttime = 0;
            time.setText("00:00:00.000");

            start.setEnabled(true);
            pause.setEnabled(false);
            reset.setEnabled(false);
            lap.setEnabled(false);

            LinearLayout lapLayout = findViewById(R.id.laplayout);
            lapLayout.removeAllViews();
        }
    }



    private void lap() {
        if (isRunning) {

            String lapTimeString = time.getText().toString();

            TextView lapTextView = new TextView(this);
            lapTextView.setText(lapTimeString);
            lapTextView.setTextSize(20);

            LinearLayout lapLayout = findViewById(R.id.laplayout);
            lapLayout.addView(lapTextView, 0);
        }
    }





    private Runnable update = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            elapsedtime = currentTime - starttime;

            int milliseconds = (int) (elapsedtime % 1000);
            int seconds = (int) (elapsedtime / 1000) % 60;
            int minutes = (int) ((elapsedtime / (1000 * 60)) % 60);
            int hours = (int) ((elapsedtime / (1000 * 60 * 60)) % 24);
            String timeString = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
            time.setText(timeString);

            handler.postDelayed(this, 10);
        }
    };

}

