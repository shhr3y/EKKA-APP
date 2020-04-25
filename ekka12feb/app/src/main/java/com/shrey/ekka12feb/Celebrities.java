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

import com.google.android.material.textfield.TextInputEditText;

public class Celebrities extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public ImageButton back_btn,search_btn,amir_image,salman_image,akshay_image;
    public TextInputEditText inputSearch;
    public TextView trending_text,salman_text,amir_text,akshay_text;
    public String searchText;
    public Typeface myFont;
    public String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities);

        overlay = findViewById(R.id.celebritiesLayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);


        ekka = findViewById(R.id.ekka);
        contentBox = findViewById(R.id.ContentBox);

        handleAnimation(overlay);

        back_btn = findViewById(R.id.back_btn);

        userUID = getIntent().getExtras().getString("uid");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Celebrities.super.onBackPressed();
            }
        });


        trending_text = findViewById(R.id.celeb_trending_text);
        salman_text = findViewById(R.id.salman_text);
        amir_text = findViewById(R.id.amir_text);
        akshay_text = findViewById(R.id.akshay_text);

        salman_image = findViewById(R.id.salmanButton);
        akshay_image = findViewById(R.id.akshayButton);
        amir_image = findViewById(R.id.amirButton);


        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");
        trending_text.setTypeface(myFont);
        salman_text.setTypeface(myFont);
        amir_text.setTypeface(myFont);
        akshay_text.setTypeface(myFont);


        salman_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salman_intent = new Intent(Celebrities.this,SalmanResults.class);
                salman_intent.putExtra("search_text","Salman Khan");
                salman_intent.putExtra("uid",userUID);
                salman_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(salman_intent);            }
        });

        akshay_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent akshay_intent = new Intent(Celebrities.this,SalmanResults.class);
                akshay_intent.putExtra("search_text","Akshag Kumar");
                akshay_intent.putExtra("uid",userUID);
                akshay_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(akshay_intent);
            }
        });

        amir_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent amir_intent = new Intent(Celebrities.this,AmirResults.class);
                amir_intent.putExtra("search_text","Amir Khan");
                amir_intent.putExtra("uid",userUID);
                amir_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(amir_intent);
            }
        });



        inputSearch = findViewById(R.id.search_editext);
        search_btn = findViewById(R.id.searchButton);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 searchText = inputSearch.getText().toString().trim();
                 if(searchText.equalsIgnoreCase("sharukh khan") || searchText.equalsIgnoreCase("srk") || searchText.equalsIgnoreCase("sharukh")){
                     Toast.makeText(Celebrities.this, " Showing results for Sharukh Khan", Toast.LENGTH_SHORT).show();
                     Intent srk_intent = new Intent(Celebrities.this,SharukhResults.class);
                     srk_intent.putExtra("search_text",searchText);
                     srk_intent.putExtra("uid",userUID);
                     srk_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     startActivity(srk_intent);
                 }
                else if(searchText.equalsIgnoreCase("amir khan") || searchText.equalsIgnoreCase("amir")){
                    Toast.makeText(Celebrities.this, "Showing results for Amir Khan", Toast.LENGTH_SHORT).show();
                     Intent amir_intent = new Intent(Celebrities.this,AmirResults.class);
                     amir_intent.putExtra("search_text",searchText);
                     amir_intent.putExtra("uid",userUID);
                     amir_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     startActivity(amir_intent);
                }
                else if(searchText.equalsIgnoreCase("Salman khan") || searchText.equalsIgnoreCase("Salman")){
                    Toast.makeText(Celebrities.this,  "Showing results for Salman Khan", Toast.LENGTH_SHORT).show();
                     Intent salman_intent = new Intent(Celebrities.this,SalmanResults.class);
                     salman_intent.putExtra("search_text",searchText);
                     salman_intent.putExtra("uid",userUID);
                     salman_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     startActivity(salman_intent);
                }
                else{
                     Toast.makeText(Celebrities.this, "Sorry...Celebrity not in our Database :/", Toast.LENGTH_SHORT).show();
                 }
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
