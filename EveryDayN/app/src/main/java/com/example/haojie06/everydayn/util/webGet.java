package com.example.haojie06.everydayn.util;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.haojie06.everydayn.object.Articles;
import com.example.haojie06.everydayn.object.Sound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class webGet {
    String url;

    public webGet(String url) {

        this.url = url;
    }

    public Articles articleGet() {


        Articles ar = new Articles();
        try {
            String titl,author;
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.connect(url).get();
            Document doc2 = Jsoup.connect(url).get();
            Element He = doc.getElementById("article_show");
            Elements mainArticle = doc.select("div.article_text");

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

    public ArrayList<Sound> soundGet(int page)
    {

        String getUrl = "http://voice.meiriyiwen.com/voice/past?page=" + String.valueOf(page);
        ArrayList<Sound> soundList = new ArrayList<Sound>();
        Document doc = null;
        try {
             doc = Jsoup.connect(getUrl).get();
             Elements soundLinks = doc.select("div.list_box");
           //  Elements picLinks = doc.select("img[src]");
             for(Element e : soundLinks)
             {
                 String title = e.select("div.list_author").select("a").text();
                 String name = e.select("div.author_name").text();
                 String picUrl = "http://voice.meiriyiwen.com" + e.select("a").next().select("img").attr("src").replace("_250","");
                 Sound sound = new Sound();
                 sound.setSoundTitle(title);
                 sound.setSoundAuthor(name);
                 sound.setSoundPicUrl(picUrl);
                 soundList.add(sound);
             }
        }catch (Exception ex){
            Log.e("www!!!!!!!!!","未取出sound");
            ex.printStackTrace();}
        return soundList;
    }

}

