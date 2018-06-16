package com.minoru.minoru.spajam2018;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MediaPlayer01  {
        private Activity myActivity;
　　　　MediaPlayer player = new MediaPlayer();

　　　　AudioManager manager = (AudioManager)myActivity.getSystemService(Context.AUDIO_SERVICE);
　　　　try {
　　　　　　// 音声メディアの指定&再生
　　　　　　try {
            player.setDataSource("/sdcard/MusicSample/sample.mp3");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
　　　　// ストリームタイプの設定
　　　　player.setAudioStreamType(AudioManager.STREAM_MUSIC);
　　　　// 最大音量値を取得
　　　　int vol = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
　　　　// 音量を設定
　　　　manager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(vol/2), 0);
　　　　try {
            player.prepare();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
　　　　player.start();
　　　　} catch (IllegalArgumentException e) {
　　　　　　// TODO Auto-generated catch block
　　　　　　e.printStackTrace();
　　　　} catch (IllegalStateException e) {
　　　　　　// TODO Auto-generated catch block
　　　　　　e.printStackTrace();
　　　　} catch (IOException e) {
　　　　　　// TODO Auto-generated catch block
　　　　　　e.printStackTrace();
　　　　}
　　
}