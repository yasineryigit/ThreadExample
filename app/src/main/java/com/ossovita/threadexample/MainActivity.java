package com.ossovita.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleLooperThread looperThread = new ExampleLooperThread();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startThread(View v){
        looperThread.start();//bandı çalıştırır

    }
    public void stopThread(View v){
        looperThread.looper.quit();//bandı durdurur

    }
    public void taskA(View v){
        looperThread.handler.post(new Runnable() {//banda iş koyuyoruz
            @Override
            public void run() {
                for (int i = 0; i < 6 ; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);//her bir döngü sonunda 1 saniye bekle diyoruz
                }
            }
        });
    }
    public void taskB(View v){

    }
}