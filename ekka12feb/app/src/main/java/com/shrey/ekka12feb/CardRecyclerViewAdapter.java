package com.shrey.ekka12feb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import mehdi.sakout.fancybuttons.FancyButton;


public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewdAdapter";
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mWishlist = new ArrayList<>();
    private String mUID;
    private Context mContext;
    public String currentDate , currentTime;





    public CardRecyclerViewAdapter( Context mContext, ArrayList<String> mImages, ArrayList<String> mWishlist , String mUID) {
        this.mImages = mImages;
        this.mWishlist = mWishlist;
        this.mContext = mContext;
        this.mUID = mUID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hairstyle_cards,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);

        final DatabaseReference DataRef = FirebaseDatabase.getInstance().getReference();

        DataRef.child("users").child(mUID).child("wishlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(mWishlist.get(position))){
                    holder.like.setChecked(true);
                }else {
                    holder.like.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.like.setEventListener(new SparkEventListener() {         //SPARK BUTTON
            @Override
            public void onEvent(ImageView button, boolean buttonState) {

                if(buttonState){
                    final HashMap<String,Object> stylesMap = new HashMap<>();

                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                    currentDate = dateFormat.format(new Date());

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
                    currentTime = timeFormat.format(calendar.getTime());

                    stylesMap.put("style_image",mImages.get(position));
                    stylesMap.put("style_name",mWishlist.get(position));
                    stylesMap.put("date",currentDate);
                    stylesMap.put("time",currentTime);

                    DataRef.child("users").child(mUID).child("wishlist").child(mWishlist.get(position))
                            .updateChildren(stylesMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(mContext, "Added to Wishlist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else{
                    DatabaseReference DataRef = FirebaseDatabase.getInstance().getReference();

                    DataRef.child("users").child(mUID).child("wishlist").child(mWishlist.get(position))
                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(mContext, "Removed From Wishlist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() ,Customize.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                view.getContext().startActivity(intent);
                Toast.makeText(mContext, "Selected: " +mWishlist.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btn.callOnClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        FancyButton btn;
        SparkButton like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.hairstyleImage);
            btn = itemView.findViewById(R.id.addWishlist);
            like = itemView.findViewById(R.id.like_btn);

        }
    }
}
