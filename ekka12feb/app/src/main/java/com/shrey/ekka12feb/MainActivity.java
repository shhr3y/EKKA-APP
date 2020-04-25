package com.shrey.ekka12feb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    public ImageView ekka, contentBox;
    public ImageButton customize_btn, celebrities_btn, recommended_btn, trending_btn, wishlist_btn, user_profile_btn, logout_btn;
    public Typeface myFont;
    public TextView heyText;
    public String username;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View overlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overlay = findViewById(R.id.mainlayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY  | View.SYSTEM_UI_FLAG_FULLSCREEN);


        ekka = findViewById(R.id.ekka);
        contentBox = findViewById(R.id.ContentBox);
        customize_btn = findViewById(R.id.customize_btn);
        celebrities_btn = findViewById(R.id.celebrities_btn);
        recommended_btn = findViewById(R.id.recommended_btn);
        trending_btn = findViewById(R.id.trending_btn);
        wishlist_btn= findViewById(R.id.wishlist_btn);
        user_profile_btn = findViewById(R.id.user_profile);
        logout_btn = findViewById(R.id.logoutButton);


        final String userUID = getIntent().getExtras().getString("userFB");



        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");
        heyText = findViewById(R.id.heyText);
        heyText.setTypeface(myFont);

        DocumentReference docRef = db.collection("users").document(userUID);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot doc, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    Toast.makeText(MainActivity.this, "Error: "+ e.toString(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (doc.exists()) {
                    username = doc.getString("name");
                    heyText.setText("Hey "+username+",");
                } else {
                    heyText.setText("Hey,");
                }
            }
        });

        handleAnimation(overlay);

        customize_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Customize.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("uid",userUID);
                startActivity(i);
            }
        });

        celebrities_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Celebrities.class);
                i.putExtra("uid",userUID);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        recommended_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Recommended.class);
                i.putExtra("uid",userUID);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        trending_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Trending.class);
                i.putExtra("uid",userUID);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        wishlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Wishlist.class);
                i.putExtra("uid",userUID);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        user_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,UserProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You have been logged out.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "If you want to exit, please Logout!", Toast.LENGTH_SHORT).show();
    }

    public void handleAnimation(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(contentBox, "x", -440f);
        animatorX.setDuration(450);

        ObjectAnimator FadeX = ObjectAnimator.ofFloat(contentBox, View.ALPHA, 0.0f, 1.0f);
        FadeX.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, FadeX);
        animatorSet.start();
    }

}
