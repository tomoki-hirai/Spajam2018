package com.minoru.minoru.spajam2018;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaManager Manager = new MediaManager();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mediaPlayer.create(this,R.raw.hakucyou);
        String fileName = "android.resource://" + getPackageName() + "/" + R.raw.hakucyou;
        try {
            mediaPlayer.setDataSource(this, Uri.parse(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Manager.setMyActivity(this,mediaPlayer);
        Manager.setVolume(30);

        try{
            Manager.prepare();
        }catch( Exception e ){ }
        Manager.playSound();
    }
}
