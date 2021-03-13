package com.example.apppiptips;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SettingsActivity extends AppCompatActivity {

    private WebView webV;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        webV = findViewById(R.id.user_guide);

        WebSettings ws = webV.getSettings();
        ws.setJavaScriptEnabled(true);


        // not loading correct User Guide File. All code adds up and worls but unknown why it loads chart instead
        // Please see index.html for User Guide
        webV.loadUrl("file:///android_asset/index.html");
    }
}
