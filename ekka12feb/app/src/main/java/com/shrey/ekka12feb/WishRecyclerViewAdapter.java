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
import com.varunest.sparkbutton.SparkEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import mehdi.sakout.fancybuttons.FancyButton;


public class WishRecyclerViewAdapter extends RecyclerView.Adapter<WishRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "WishRecyclerViewdAdapter";
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mWishlist = new ArrayList<>();
    private String mUID;
    private Context mContext;
    public String currentDate, currentTime;


    public WishRecyclerViewAdapter(Context mContext ,ArrayList<String> mImages, ArrayList<String> mWishlist, String mUID) {
        this.mImages = mImages;
        this.mWishlist = mWishlist;
        this.mUID = mUID;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishstyle_cards,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);


        holder.customize_btn.setOnClickListener(new View.OnClickListener() {
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
                holder.customize_btn.callOnClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        FancyButton customize_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.wishstyleImage);
            customize_btn = itemView.findViewById(R.id.customizeWishlist);
        }
    }
}