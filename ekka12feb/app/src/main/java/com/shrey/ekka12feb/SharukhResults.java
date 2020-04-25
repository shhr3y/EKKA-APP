package com.shrey.ekka12feb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
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

public class SharukhResults extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public Typeface myFont;
    public ImageButton back_btn;
    public TextView search;

    private static final String TAG = "TrendingActivity";
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mWishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharukh_results);


        overlay = findViewById(R.id.sharukh_results);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        String search_text = bundle.getString("search_text");

        ekka = findViewById(R.id.ekka);
        contentBox = findViewById(R.id.ContentBox);
        search = findViewById(R.id.search_txt);

        search.setText("Showing results for "+search_text+",");

        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");
        search.setTypeface(myFont);
        handleAnimation(overlay);

        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharukhResults.super.onBackPressed();
            }
        });

        getHairStyles(bundle.getString("uid"));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please use navigation keys!", Toast.LENGTH_SHORT).show();
    }

    public void handleAnimation(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(contentBox, "x", -150);
        animatorX.setDuration(300);

        ObjectAnimator FadeX = ObjectAnimator.ofFloat(contentBox, View.ALPHA, 0.0f, 1.0f);
        FadeX.setDuration(500);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, FadeX);
        animatorSet.start();
    }

    private void getHairStyles(String uid){
        Log.d(TAG, "initHairStyles: preparing Bitmaps");

        mImages.clear();
        mWishlist.clear();

        mImages.add(getURLForResource(R.drawable.sharukhpic1));
        mWishlist.add("sharukhpic1");

        mImages.add(getURLForResource(R.drawable.sharukhpic2));
        mWishlist.add("sharukhpic2");

        mImages.add(getURLForResource(R.drawable.sharukhpic3));
        mWishlist.add("sharukhpic3");

        mImages.add(getURLForResource(R.drawable.sharukhpic4));
        mWishlist.add("sharukhpic4");

        mImages.add(getURLForResource(R.drawable.sharukhpic5));
        mWishlist.add("sharukhpic5");

        mImages.add(getURLForResource(R.drawable.sharukhpic6));
        mWishlist.add("sharukhpic6");

        mImages.add(getURLForResource(R.drawable.sharukhpic7));
        mWishlist.add("sharukhpic7");

        mImages.add(getURLForResource(R.drawable.sharukhpic8));
        mWishlist.add("sharukhpic8");

        mImages.add(getURLForResource(R.drawable.sharukhpic9));
        mWishlist.add("sharukhpic9");

        mImages.add(getURLForResource(R.drawable.sharukhpic10));
        mWishlist.add("sharukhpic10");

        mImages.add(getURLForResource(R.drawable.sharukhpic11));
        mWishlist.add("sharukhpic11");

        mImages.add(getURLForResource(R.drawable.sharukhpic12));
        mWishlist.add("sharukhpic12");

        mImages.add(getURLForResource(R.drawable.sharukhpic13));
        mWishlist.add("sharukhpic13");

        mImages.add(getURLForResource(R.drawable.sharukhpic14));
        mWishlist.add("sharukhpic14");

        initRecyclerView(uid);
    }

    private void initRecyclerView(String uid) {
        Log.d(TAG, "iniitRecyclerView: init RecyclerView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.sharukh_card_recycler);
        recyclerView.setLayoutManager(layoutManager);
        CardRecyclerViewAdapter adapter =  new CardRecyclerViewAdapter(this,mImages,mWishlist,uid);
        recyclerView.setAdapter(adapter);

    }


    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
