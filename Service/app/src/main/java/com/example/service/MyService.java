package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MediaPlayer myPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        myPlayer =MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        myPlayer.setLooping(true);

        myPlayer.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        myPlayer.stop();

        super.onDestroy();
    }

}

