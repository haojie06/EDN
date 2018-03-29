package com.example.haojie06.everydayn.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Sound;

import android.media.*;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.jsoup.helper.StringUtil;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundPlay extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {
    Uri playUri = null;
    Button play, pause, stop;
    SeekBar mSeekBar;
    TextView cur, tot;
    Sound playSound;
    ImageView imageView;
    LinearLayout linearLayout;
    MediaPlayer mediaPlayer;
    boolean hadDestroy = false;

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0x01:

                    break;

                default:
                    break;
            }
        }

        ;
    };

    Runnable runnable = new Runnable() {


        @Override
        public void run() {

            if (!hadDestroy) {
                mHandler.postDelayed(this, 1000);
                int currentTime = Math
                        .round(mediaPlayer.getCurrentPosition() / 1000);
                String currentStr = String.format("%s%02d:%02d", "当前时间 ",
                        currentTime / 60, currentTime % 60);
                cur.setText(currentStr);
                mSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //  getWindow().setEnterTransition(new Explode().setDuration(500));
        //  getWindow().setExitTransition(new Fade().setDuration(50));


        playSound = (Sound) getIntent().getParcelableExtra("playSound");
        setContentView(R.layout.activity_sound_play);
        imageView = (ImageView) findViewById(R.id.soundPlayPic);
        TextView textView = (TextView) findViewById(R.id.soundPlayWord);
        textView.setText(playSound.getSoundAuthor());
        Glide.with(SoundPlay.this).load(playSound.getSoundPicUrl()).into(imageView);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        cur = (TextView) findViewById(R.id.tv);
        tot = (TextView) findViewById(R.id.tv2);
        mSeekBar.setOnSeekBarChangeListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        mediaPlayer = new MediaPlayer();
        playUri = Uri.parse( "http://sc1.111ttt.cn/2018/1/03/13/396131232171.mp3");

        Log.e("URI----------", playUri.getPath());
        preparePlayer();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //按下播放键
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    int totalTime = Math.round(mediaPlayer.getDuration() / 1000);
                    String str = String.format("%02d:%02d", totalTime / 60,
                            totalTime % 60);
                    tot.setText(str);
                    mSeekBar.setMax(mediaPlayer.getDuration());
                    mHandler.postDelayed(runnable, 1000);

                }
                break;

            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;

            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    preparePlayer();
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (mediaPlayer != null && b) {//b防止卡顿
            mediaPlayer.seekTo(seekBar.getProgress());//？？？？
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    void preparePlayer() {
        try {
           // mediaPlayer.setDataSource(" http://sc1.111ttt.cn/2018/1/03/13/396131232171.mp3");
            mediaPlayer.setDataSource(SoundPlay.this,playUri);
           // AssetFileDescriptor fd = getAssets().openFd("123.mp3");
           // mediaPlayer.setDataSource( fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());

            mediaPlayer.prepareAsync();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {

            super.onDestroy();
            mediaPlayer.stop();
            hadDestroy = true;
            mediaPlayer.release();
        }
    }


}
