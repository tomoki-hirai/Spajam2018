package com.minoru.minoru.spajam2018;

import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import layout.DrumFragment;
import layout.HomeFragment;

public class MainActivity extends AppCompatActivity implements SensorEventListener ,HomeFragment.OnFragmentInteractionListener ,DrumFragment.OnFragmentInteractionListener{
    private SensorManager manager;
    String TAG = MainActivity.class.getName();

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

//        Manager.setMyActivity(this);
        Manager.setMyActivity(this,mediaPlayer);
//        Manager.getMaxVol();
        Manager.setVolume(14);
        try{
            Manager.prepare();
        }catch( Exception e ){ }
        Manager.playSound();

//        setContentView(R.layout.content_main);

        //        初期画面をhomefragmentにする
//        HomeFragment homeFragment = new HomeFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, homeFragment);
//        transaction.commit();

        //        デバック用
//        DrumFragment drumFragment = new DrumFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, drumFragment);
//        transaction.commit();

//        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
//        if(sensors.size() > 0) {
//            Sensor s = sensors.get(0);
//            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
//            float acc_x = sensorEvent.values[0];
//            float acc_y = sensorEvent.values[1];
//            float acc_z = sensorEvent.values[2];
//
//            Log.d(TAG,Float.toString(acc_x)+","+Float.toString(acc_y)+","+Float.toString(acc_z));
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
