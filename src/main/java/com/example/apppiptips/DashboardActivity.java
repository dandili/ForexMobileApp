package com.example.apppiptips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.apppiptips.R.id.newsCard;

public class DashboardActivity extends AppCompatActivity {

    ImageView ProfileBtn;
    ImageView LearnBtn;
    ImageView NewsBtn;
    ImageView CalendarBtn;
    ImageView PairsBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Define Cards
        NewsBtn = findViewById(R.id.NewsIcon);
        NewsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });
        CalendarBtn = findViewById(R.id.CalendarIcon);
        CalendarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
            }
        });
        LearnBtn = findViewById(R.id.LearnIcon);
        LearnBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LearnActivity.class));
            }
        });
        PairsBtn = findViewById(R.id.PairsIcon);
        PairsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PairsActivity.class));
            }
        });
        ProfileBtn = findViewById(R.id.ProfileIcon);
        ProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
    }
}
