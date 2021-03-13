package com.example.apppiptips;
/**
 *


// Not functional
// Purpose was to open a webpage on the news card to see more info but XML incompatibilities


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.apppiptips.models.NewsFunctions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class NewsPageActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView appbarTitle, appbarSubtitle, date, time, title;
    private boolean toolbarHidden = false;
    private FrameLayout dateBehaviour;
    private LinearLayout titleAppBar;
    // private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;

    @SuppressLint({"WrongViewCast", "CheckResult", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        toolbar = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapseToolbar = findViewById(R.id.collapsing_toolbar);
        collapseToolbar.setTitle("");

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((AppBarLayout.BaseOnOffsetChangedListener) this);
        dateBehaviour = findViewById(R.id.date_behavior);
        appbarTitle = findViewById(R.id.title_on_appbar);
        appbarSubtitle = findViewById(R.id.subtitle_on_appbar);
        imageView = findViewById(R.id.backdrop);
        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mAuthor = intent.getStringExtra("author");
        mDate = intent.getStringExtra("date");
        mSource = intent.getStringExtra("source");
        mTitle = intent.getStringExtra("title");

        RequestOptions request = new RequestOptions();
        request.error(NewsFunctions.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(request)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        appbarTitle.setText(mSource);
        appbarSubtitle.setText(mUrl);
        date.setText(NewsFunctions.DateFormat(mDate));
        title.setText(mTitle);

        String author;
        if (mAuthor != null){
            author = " \u2022 " + mAuthor;
        } else {
            author = "";
        }
        time.setText(mSource + author +"\u2022" + NewsFunctions.DateToTimeFormat(mDate));

        initWebView(mUrl);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
        int maxScroll = appBarLayout.getTotalScrollRange();
        float pc = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (pc == 1f && toolbarHidden) {
            dateBehaviour.setVisibility(View.GONE);
            titleAppBar.setVisibility(View.VISIBLE);
            toolbarHidden = !toolbarHidden;

        } else if (pc < 1f && !toolbarHidden) {
            dateBehaviour.setVisibility(View.VISIBLE);
            titleAppBar.setVisibility(View.GONE);
            toolbarHidden = !toolbarHidden;
        }
    }

    private void initWebView(String url){
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
 */