package com.example.haojie06.everydayn.util;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.haojie06.everydayn.object.Articles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class webGet {
    String url;

    public webGet(String url) {

        this.url = url;
    }

    public Articles web() {


        Articles ar = new Articles();
        try {
            String titl,author;
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.connect(url).get();
            Document doc2 = Jsoup.connect(url).get();
            //选择“美食天下”所在节点
            Element He = doc.getElementById("article_show");
            Elements mainArticle = doc.select("div.article_text");

            //打印 <a>标签里面的title
            Log.e("！！！！！！qqqq、", "连接成功");
            Log.e("！！！！！！qqqq、", "连接成功");
            Log.e("text",mainArticle.text());
            ar.setContent(mainArticle.text());
            titl = He.select("h1").text();
            author = He.select("p.article_author").text();
            Log.e("!!!!!!!!!!!!!!Title","title:"+titl);
            Log.e("!!!!!!!!!!!!!!Title","title2:"+He.select("p.article_author").text());
            ar.setTitle(titl);
            ar.setName(author);

        } catch (Exception e) {
            Log.e("！！！！！！qqqq、", "连接未成功");
            Log.e("！！！！！！qqqq、", "连接未成功");
            Log.e("！！！！！！qqqq、", "log test");
            Log.i("mytag", e.toString());
        }

        return ar;


    }
}

