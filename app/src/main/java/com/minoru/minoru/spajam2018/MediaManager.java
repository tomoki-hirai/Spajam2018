package com.minoru.minoru.spajam2018;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class MediaManager extends Activity {
    private Activity myActivity;
    private MediaPlayer player = new MediaPlayer();
    private int Vol;

    private AudioManager manager;


    public void MediaManager(){
        // ストリームタイプの設定
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.reset();
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","ストリームタイプ");
            e.printStackTrace();
        }
    }

    //Activityセット
    public void setMyActivity(Activity argActivity,MediaPlayer argMediaPlayer){
        setMyActivity(argActivity);
        player =  argMediaPlayer;
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        manager = (AudioManager)myActivity.getSystemService(Context.AUDIO_SERVICE);
        prepare();
        Log.d("MediaError","seted");
    }

    public void setMyActivity(Activity argActivity){
        myActivity = argActivity;
    }

    //prepare
    public void prepare(){
        try {
            player.prepare();
            Log.d("MediaError","prepared");
        } catch (IOException e1) {
            Log.d("MediaError","prepareエラー");
            e1.printStackTrace();
        }
    }

    // 音量を設定
    public void setVolume(int argVol){
        try {
            manager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (argVol / 2), 0);
            Log.d("MediaError","音量を設定しました");
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","音量設定エラー");
            e.printStackTrace();
        }
    }

    // 最大音量値を取得
    public void getMaxVol(){
        try {
            Vol = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            Log.d("MediaError","最大音量を設定しました");
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","最大値取得");
            e.printStackTrace();
        }
    }

    // 音を再生
    public void playSound() {
        try {
            player.start();
            Log.d("MediaError",Integer.toString(Vol));
            Log.d("MediaError","再生します");
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","再生エラー");
            e.printStackTrace();
        }
    }
}