package com.ag.quizdown.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.ag.quizdown.R;

public class Sound {

    private static MediaPlayer mMediaPlayer;// = new MediaPlayer();
    private static AudioManager audioManager;
    private static int originalVolume;

    public static void screamSheep(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        originalVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

        mMediaPlayer = MediaPlayer.create(context, R.raw.screaming_sheep);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);
                mp.release();
            }
        });

        mMediaPlayer.start();

    }

}
