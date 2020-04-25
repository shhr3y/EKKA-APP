package com.shrey.ekka12feb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class Trending extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public Typeface myFont;
    public TextView Welcome;
    public ImageButton back_btn;

    private static final String TAG = "TrendingActivity";
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mWishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        final String userUID = getIntent().getExtras().getString("uid");
        getHairStyles(userUID);

        overlay = findViewById(R.id.trendingLayout);
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
                Trending.super.onBackPressed();
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

    private void getHairStyles(String uid){
        Log.d(TAG, "initHairStyles: preparing Bitmaps");

        mImages.add(getURLForResource(R.drawable.modelpic1));
        mWishlist.add("modelpic1");

        mImages.add(getURLForResource(R.drawable.modelpic2));
        mWishlist.add("modelpic2");

        mImages.add(getURLForResource(R.drawable.modelpic3));
        mWishlist.add("modelpic3");

        mImages.add(getURLForResource(R.drawable.modelpic4));
        mWishlist.add("modelpic4");

        mImages.add(getURLForResource(R.drawable.modelpic5));
        mWishlist.add("modelpic5");

        mImages.add(getURLForResource(R.drawable.modelpic6));
        mWishlist.add("modelpic6");

        initRecyclerView(uid);
    }

    private void initRecyclerView(String uid) {
        Log.d(TAG, "iniitRecyclerView: init RecyclerView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.trending_card_recycler);
        recyclerView.setLayoutManager(layoutManager);
        CardRecyclerViewAdapter adapter =  new CardRecyclerViewAdapter(this,mImages,mWishlist,uid);
        recyclerView.setAdapter(adapter);

    }

    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
