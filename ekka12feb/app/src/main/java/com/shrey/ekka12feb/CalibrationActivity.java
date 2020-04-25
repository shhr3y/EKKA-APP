package com.shrey.ekka12feb;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.race604.drawable.wave.WaveDrawable;

public class CalibrationActivity extends AppCompatActivity {

    public TextView cali_txt;
    public Typeface myFont;
    public View overlay;
    public ImageView mEkkaView;
    public WaveDrawable mWaveDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);


        cali_txt = findViewById(R.id.cali_txt);
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/avenir.otf");

        cali_txt.setTypeface(myFont);



        mEkkaView = (ImageView) findViewById(R.id.image);
        mWaveDrawable = new WaveDrawable(this, R.drawable.ekka);
        mEkkaView.setImageDrawable(mWaveDrawable);

    }
}
