package com.example.rafalklat.prostujkurna;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int INTERVAL = 15 * 1000 * 60; //15 min.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tekst = (TextView) findViewById(R.id.tekst);
        tekst.setText("Jak klikniesz START, to co " + INTERVAL/60/1000 + "min będzie wibrowało. STOP zatrzyma tę farsę.");

        TextView titel = (TextView) findViewById(R.id.titel);
        titel.setText("PROSTUJ KURNA!!!");

        Button startVibrations = (Button) findViewById(R.id.startVibrations);
        startVibrations.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRepeatingTask();
            }
        });

        Button stopVibrations = (Button) findViewById(R.id.stopVibrations);
        stopVibrations.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRepeatingTask();
            }
        });
    }

    public void wibrujta() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        long[] pattern = {0, 1000, 200, 200};
        vibrator.vibrate(pattern , -1);
    }

    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            wibrujta();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    void startRepeatingTask() {
        mHandlerTask.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask);
    }

}
