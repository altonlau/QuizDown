package com.ag.quizdown.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.ag.quizdown.R;

public class Sound {

    private static MediaPlayer mMediaPlayer;// = new MediaPlayer();
    //private static final Uri sheepUri = Uri.parse("file:///sdcard/music/screaming_sheep.mp3");

    public static void screamSheep(Context context) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.screaming_sheep);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.start();
    }

}
