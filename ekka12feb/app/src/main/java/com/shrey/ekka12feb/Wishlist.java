package com.shrey.ekka12feb;

import androidx.annotation.NonNull;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Nullable;

public class Wishlist extends AppCompatActivity {

    public View overlay;
    public ImageView ekka, contentBox;
    public Typeface myFont;
    public ImageButton back_btn;
    public TextView Welcome;
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mWishlist = new ArrayList<>();
    public TextView totalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        overlay = findViewById(R.id.wishlistLayout);
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
        totalItems = findViewById(R.id.total_items);


        final String userUID = getIntent().getExtras().getString("uid");



        DatabaseReference docRef = FirebaseDatabase.getInstance().getReference().child("users").child(userUID).child("wishlist");

        docRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                mImages.clear();
                mWishlist.clear();
                totalItems.setText("Total Items: "+dataSnapshot.getChildrenCount());
                while(items.hasNext()) {
                    DataSnapshot item = items.next();
                    WishModel data = item.getValue(WishModel.class);

                    mImages.add(data.getStyle_image());
                    mWishlist.add(data.getStyle_name());
                }
                initRecyclerView(userUID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wishlist.super.onBackPressed();
            }
        });
    }

    private void initRecyclerView(String uid) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.wishlist_recycler);
        recyclerView.setLayoutManager(layoutManager);
        WishRecyclerViewAdapter adapter =  new WishRecyclerViewAdapter(this,mImages,mWishlist,uid);
        recyclerView.setAdapter(adapter);

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




