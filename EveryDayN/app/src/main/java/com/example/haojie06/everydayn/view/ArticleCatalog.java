package com.example.haojie06.everydayn.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.adapter.ArticleAdapter;
import com.example.haojie06.everydayn.object.Articles;

import org.litepal.crud.DataSupport;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticleCatalog extends AppCompatActivity {
    TextView artTest;
    List<Articles> articlesList;
    List<Articles> inList = new ArrayList<Articles>();
String[] title, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_catalog);
        readData();
        ListView listView = (ListView) findViewById(R.id.article_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Articles articles = articlesList.get(i);
                Intent intent = new Intent(ArticleCatalog.this,ArticleShow.class);
                intent.putExtra("showArt",articles);
                startActivity(intent);
            }
        });
        //artTest.setText(savedArrayList.get(0).getContent());
    }

    protected void readData() {
         articlesList = DataSupport.findAll(Articles.class);
        ArticleAdapter adapter = new ArticleAdapter(ArticleCatalog.this, R.layout.article_item, articlesList);
        ListView listView = (ListView) findViewById(R.id.article_list);
        listView.setAdapter(adapter);

    }
}

