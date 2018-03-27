package com.example.haojie06.everydayn.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Articles;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticleCatalog extends AppCompatActivity {
    TextView artTest;
    List<Articles> inList = new ArrayList<Articles>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_catalog);
         artTest = (TextView) findViewById(R.id.Articlev);
        readData();
        //artTest.setText(savedArrayList.get(0).getContent());
    }

    protected void readData()
    {
        try {
            Articles[] get = new Articles[10];
            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream;
            fileInputStream = openFileInput("hello.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
              //  ArrayList<Articles> list =(ArrayList<Articles>) objectInputStream.readObject();
            try {int i = 0;
                while (true) {
                    get[i] = (Articles) objectInputStream.readObject();
                    Log.e("！！！！！！", "读取文章成功---" + get[i].getTitle());
                    i++;
                }

            }catch (EOFException EOF){Log.e("----------tag","读取完成");}
            finally {
                fileInputStream.close();
                objectInputStream.close();
            }

        }catch (Exception ex){
            Log.e("警告！！！！！！","读取文章出错");
            ex.printStackTrace();}


    }
}
