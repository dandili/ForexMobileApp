package com.example.apppiptips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.apppiptips.models.Article;
import com.example.apppiptips.models.News;
import com.example.apppiptips.models.NewsFunctions;
import com.example.apppiptips.newsapi.Adapter;
import com.example.apppiptips.newsapi.Api;
import com.example.apppiptips.newsapi.Client;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsActivity extends AppCompatActivity {

    public static final String KEY = "6d8eb55a21e64c75b03e206e8e834f58";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String tag = NewsActivity.class.getSimpleName();
    private TextView Headline;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        getJson();
    }


    public void getJson(){
        Api api = Client.getApi().create(Api.class);

        String country = NewsFunctions.getCountry();
        Call<News> call;
        call = api.getNews("fxstreet.com", KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()){
                        articles.clear();
                    }
                articles = response.body().getArticle();
                adapter = new Adapter(articles, NewsActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                // initListener(); Not working becaue of an XML incompatibility.

            } else {
                    Toast.makeText(NewsActivity.this, "No results!", Toast.LENGTH_SHORT).show(); }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }

    // Called the NewspageActivity but it is defunct
   /* private void initListener(){

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(NewsActivity.this, NewsPageActivity.class);

                Article article = articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("image",  article.getUrlToImage());
                intent.putExtra("date",  article.getPublishedAt());
                intent.putExtra("source",  article.getSource().getName());
                intent.putExtra("author",  article.getAuthor());
                startActivity(intent);
            }
        });

    }*/
}
