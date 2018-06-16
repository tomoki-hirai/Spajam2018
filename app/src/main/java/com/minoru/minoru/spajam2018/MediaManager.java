package com.minoru.minoru.spajam2018;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MediaManager extends Activity {
    private Activity myActivity;
    private MediaPlayer player = new MediaPlayer();
    private int Vol;
    private String fileName;

    private AudioManager manager;

    // 　コンストラクタ
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

    //MediaPlayerセット
    public void setMediaPlayer(){
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        manager = (AudioManager)myActivity.getSystemService(Context.AUDIO_SERVICE);
        Log.d("MediaError","seted");
    }

    //Activityセット
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

    //音量を決定
    public int judgeVol(int argJudgeNum){
        Log.d("Media","Volを設定");
        if(argJudgeNum==0){
            return 30;
        }else if(argJudgeNum==1){
            return 20;
        }else if(argJudgeNum==2){
            return 10;
        }else{
            return 0;
        }
    }

    // 音量を設定
    public void setVolume(int argJudgeNum){
        try {
            manager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (judgeVol(argJudgeNum)), 0);
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
            Log.d("MediaError","再生します");
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","再生エラー");
            e.printStackTrace();
        }
    }

    //音をストップ
    public void stop(){
        try {
            player.start();
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.d("MediaError","ストップエラー");
            e.printStackTrace();
        }
    }

    //ここどうしよう///
//    クリックによってここに飛ばそう
    //音をセット
    public void setSound(){
        if(fileName==null) {
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.bassdrum;
        }
        try {
            Log.d("eee","fileName");
            Log.d("eee",fileName);
            player.setDataSource(myActivity, Uri.parse(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("MediaError","読み込みエラー");
        }

    }

    public void selectDrumSound(int num){
        reset();
        if(num==0){
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.bassdrum;
        Log.d("MediaError","bass");}
        else if(num==1){
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.cymbal;
            Log.d("MediaError","cymbal");
        }
        else if(num==2)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.hat;
        else if(num==3)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.snare;
        setSound();
    }

    public void selectPianoSound(int num){
        reset();
        if(num==0)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianodo;
        else if(num==1)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianore;
        else if(num==2)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianomi;
        else if(num==3)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianofa;
        else if(num==4)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianoso;
        else if(num==5)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianora;
        else if(num==6)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianosi;
        else if(num==7)
            fileName = "android.resource://" + myActivity.getPackageName() + "/" + R.raw.pianohighdo;
        setSound();
    }

    public void setup(Activity argActivity){
        setMyActivity(argActivity);
        setMediaPlayer();
        selectDrumSound(2);
//        selectPianoSound(2);

        prepare();


    }

    public void reset(){
        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        setMediaPlayer();

    }

}