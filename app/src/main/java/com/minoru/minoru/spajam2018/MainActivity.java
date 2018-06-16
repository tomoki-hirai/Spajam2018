package com.minoru.minoru.spajam2018;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MediaManager Manager = new MediaManager();
    private MediaPlayer mediaPlayer = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer.create(this,R.raw.hakucyou);

//        Manager.setMyActivity(this);
        Manager.setMyActivity(this,mediaPlayer);
//        Manager.getMaxVol();
        Manager.setVolume(14);
        try{
            Manager.prepare();
        }catch( Exception e ){ }
        Manager.playSound();
    }
}
