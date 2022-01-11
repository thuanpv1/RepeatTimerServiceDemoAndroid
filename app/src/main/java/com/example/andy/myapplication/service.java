package com.example.andy.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class service extends Service {
    private ScheduledThreadPoolExecutor mDialogDaemon;
    static private int count = 0;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        spawnDialogs();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();

    }

    private void spawnDialogs() {
        if (mDialogDaemon != null) {
            mDialogDaemon.shutdown();
            mDialogDaemon = null;
        }
        mDialogDaemon = new ScheduledThreadPoolExecutor(1);
        // This process will execute immediately, then execute every 3 seconds.
        mDialogDaemon.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                count++;
                Log.d("===========thuanpv:", "========================count===" + String.valueOf(count));
            }
        }, 0L, 1000L, TimeUnit.MILLISECONDS);
    }
}
