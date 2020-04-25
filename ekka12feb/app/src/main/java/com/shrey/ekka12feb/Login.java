package com.shrey.ekka12feb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Method;


public class Login extends AppCompatActivity {


    public View overlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overlay = findViewById(R.id.loginLayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String myLogin = "file:///android_asset/index.html";
        WebView view = findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);

        view.addJavascriptInterface(new WebViewInterface(), "JavascriptInterface");
        view.loadUrl(myLogin);
    }

//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            //get data
//        } else {
//            //get logged out
//        }
//    }
    public void toast(String text) {
        Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }


    public class WebViewInterface {

        @JavascriptInterface
        public void logined() {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(Login.this, "You are logged in!!", Toast.LENGTH_LONG).show();
            finish();
        }

        @JavascriptInterface
        public void error() {
            Toast.makeText(Login.this, "You are not logged in!!", Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void openMain(String userUID) {

            Intent openMain = new Intent(Login.this,CalibrationActivity.class);
            openMain.putExtra("userFB",userUID);
            startActivity(openMain);
        }

        @JavascriptInterface
        public void toast(String text) {
            Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
        }


    }
}

