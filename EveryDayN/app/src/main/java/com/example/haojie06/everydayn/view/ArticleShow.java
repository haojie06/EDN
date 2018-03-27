package com.example.haojie06.everydayn.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Articles;

public class ArticleShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_show);
        TextView showTitle = (TextView) findViewById(R.id.showTitle);
        TextView showAuthor = (TextView) findViewById(R.id.showAuthor);
        TextView showContent = (TextView) findViewById(R.id.showContent);

        Articles getArticle = (Articles) getIntent().getSerializableExtra("showArt");
        showTitle.setText(getArticle.getTitle());
        showAuthor.setText(getArticle.getName());
        showContent.setText(getArticle.getContent());
    }
}
