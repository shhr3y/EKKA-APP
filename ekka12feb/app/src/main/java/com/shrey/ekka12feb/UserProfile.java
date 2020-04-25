package com.shrey.ekka12feb;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public Typeface myFont;
    public ImageButton back_btn;
    public TextView Welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        overlay = findViewById(R.id.userprofileLayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        ekka = findViewById(R.id.ekka);
        contentBox = findViewById(R.id.ContentBox);
        Welcome = findViewById(R.id.search_txt);
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");
        Welcome.setTypeface(myFont);
        handleAnimation(overlay);


        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UserProfile.super.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please use navigation keys!", Toast.LENGTH_SHORT).show();
    }

    public void handleAnimation(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(contentBox, "x", -440f);
        animatorX.setDuration(300);

        ObjectAnimator FadeX = ObjectAnimator.ofFloat(contentBox, View.ALPHA, 0.0f, 1.0f);
        FadeX.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, FadeX);
        animatorSet.start();
    }
}
