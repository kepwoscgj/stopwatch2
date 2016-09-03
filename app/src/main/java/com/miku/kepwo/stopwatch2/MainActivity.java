package com.miku.kepwo.stopwatch2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float seconds = 0.0F;
    int check = 0;
    boolean running = false;
    Button buttonRunning;
    String zeroTime = "00:00:00";
    TextView printView, time1View, time2View, time3View, time4View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRunning = (Button) findViewById(R.id.buttonRunning);
        printView = (TextView) findViewById(R.id.printView);
        time1View = (TextView) findViewById(R.id.time1View);
        time2View = (TextView) findViewById(R.id.time2View);
        time3View = (TextView) findViewById(R.id.time3View);
        time4View = (TextView) findViewById(R.id.time4View);
        runTimer();
    }

    public void onClickedRunningBtn(View view) {
        if (!running) running = true;
        check = check + 1;
        if (check == 4) buttonRunning.setText("再按要停止了喔");
        else if (check == 5) {
            running = false;
            buttonRunning.setEnabled(false);
        }
    }

    public void onClickedResetBtn(View view) {
        seconds = 0;
        check = 0;
        running = false;
        buttonRunning.setEnabled(true);
        buttonRunning.setText("Running");
        printView.setText(zeroTime);
        time1View.setText(zeroTime);
        time2View.setText(zeroTime);
        time3View.setText(zeroTime);
        time4View.setText(zeroTime);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = (int) seconds / 3600;
                int minutes = ((int) seconds % 3600) / 60;
                int secs = (int) seconds % 60;
                String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                printView.setText(time);
                if (check == 1) time1View.setText(time);
                else if (check == 2) time2View.setText(time);
                else if (check == 3) time3View.setText(time);
                else if (check == 4) time4View.setText(time);
                if (running) seconds = seconds + 0.1F;
                handler.postDelayed(this, 100);
            }
        });
    }
}
