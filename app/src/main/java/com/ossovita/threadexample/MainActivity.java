package com.ossovita.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button buttonStartThread;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false; //true olursa aşağıda thread'i durduracak

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread= findViewById(R.id.button);

    }
    
    public void startThread(View v){
        stopThread=false;//false olunca thread çalışmaya başlar
        ExampleRunnable runnable = new ExampleRunnable(8);
        //runnable.run();
        new Thread(runnable).start();
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        buttonStartThread.setText("Start");
    }

    public void stopThread(View v){
        stopThread=true;//
    }

    class ExampleThread extends Thread{
        int seconds;

        public ExampleThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for(int i=0;i<seconds;i++){
                Log.d(TAG, "startThread: " + i);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    class ExampleRunnable implements Runnable{
        int seconds;
        ExampleRunnable(int seconds){
            this.seconds=seconds;
        }
        @Override
        public void run() {
            for(int i=0;i<seconds;i++){
                if(stopThread)//volatile boolean true olursa thread duracak
                    return;
                if(i==seconds/2) {//yarısına geldiyse
                    /*
                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("%50");
                        }
                    });*/
                    /*
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("%50");
                        }
                    });*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("%50");
                        }
                    });
                }
                Log.d(TAG, "startThread: " + i);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}