package com.shrey.ekka12feb;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;



public class Customize extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public Typeface myFont;
    public TextView Welcome,seekbar;
    public ImageButton back_btn;
    public VideoView mainVideoView,sideVideoView;
    public FrameLayout mainFrameLayout,sideFrameLayout;
    public Boolean click_flag = true;
    private static final String TAG = "RecyclerViewdAdapter";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);


        mainVideoView= findViewById(R.id.video_main);
        sideVideoView= findViewById(R.id.video_side);

        mainFrameLayout = findViewById(R.id.mainframelayout);
        sideFrameLayout = findViewById(R.id.sideramelayout);

        overlay = findViewById(R.id.customizeLayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);


        ekka = findViewById(R.id.ekka);
        contentBox = findViewById(R.id.ContentBox);
        Welcome = findViewById(R.id.search_txt);
        seekbar = findViewById(R.id.seekbar_text);
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");
        Welcome.setTypeface(myFont);
        seekbar.setTypeface(myFont);
        handleAnimation(overlay);

        mainFrameLayout.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        mainFrameLayout.setClipToOutline(true);

        sideFrameLayout.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        sideFrameLayout.setClipToOutline(true);

         final Uri mainURL = getURLForResource(R.raw.main);

        final Uri sideURL = getURLForResource(R.raw.side);

        mainVideoView.setVideoURI(mainURL);
        mainVideoView.start();

        sideVideoView.setVideoURI(sideURL);
        sideVideoView.start();


        mainVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mainVideoView.start();
            }
        });

        sideVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                sideVideoView.start();
            }
        });



        sideVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(click_flag) {
                    Log.d(TAG, "onClick: true to false");
                    click_flag = false;
                    mainVideoView.setVideoURI(sideURL);
                    mainVideoView.start();

                    sideVideoView.setVideoURI(mainURL);
                    sideVideoView.start();

                }
                else{
                    Log.d(TAG, "onClick: false to true");
                    click_flag = true;
                    mainVideoView.setVideoURI(mainURL);
                    mainVideoView.start();

                    sideVideoView.setVideoURI(sideURL);
                    sideVideoView.start();
                }
            }
        });

















        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customize.super.onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please use navigation keys!", Toast.LENGTH_SHORT).show();
    }

    public Uri getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId);
    }

    public void handleAnimation(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(contentBox, "x", -150f);
        animatorX.setDuration(300);

        ObjectAnimator FadeX = ObjectAnimator.ofFloat(contentBox, View.ALPHA, 0.0f, 1.0f);
        FadeX.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, FadeX);
        animatorSet.start();
    }
}
