package com.example.haojie06.everydayn.util;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.haojie06.everydayn.object.Articles;
import com.example.haojie06.everydayn.object.Books;
import com.example.haojie06.everydayn.object.CatalogIt;
import com.example.haojie06.everydayn.object.Sound;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class webGet {
    String url;

    public webGet() {
    }

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
                 Element f = e;
                 String soundUrl1 = "http://voice.meiriyiwen.com/" + f.select("div.list_box").select("a").attr("href");
                 String title = e.select("div.list_author").select("a").text();
                 String name = e.select("div.author_name").text();
                 String picUrl = "http://voice.meiriyiwen.com" + e.select("a").next().select("img").attr("src").replace("_250","");
                 Document doc2 = Jsoup.connect(soundUrl1).get();
                 Elements sourceElement = doc2.getElementById("voice_show").select("p.p_file").select("embed");
                 f = sourceElement.first();
                 String soundUrl2,parseUrl;
                 soundUrl2 = sourceElement.get(0).attr("src");
                 String[] a = soundUrl2.split("=");
                 parseUrl = new String(android.util.Base64.decode(a[1], android.util.Base64.DEFAULT));

                 //由于播放器暂时无法播放网络音频，先使用Webview代替
               Log.e("----------声音一级URL",parseUrl);
                 Log.e("----------声音一级URL",soundUrl1);
                 Log.e("----------声音2级URL",soundUrl2);
                 Log.e("----------声音2级URL",soundUrl2);
                 Sound sound = new Sound();
                 sound.setSoundTitle(title);
                 sound.setSoundAuthor(name);
                 sound.setSoundPicUrl(picUrl);
                 sound.setSoundSoundUrl(soundUrl1);
                 soundList.add(sound);
             }
        }catch (Exception ex){
            Log.e("www!!!!!!!!!","未取出sound");
            ex.printStackTrace();}
        return soundList;
    }

    public ArrayList<Books> getBooks(int page)
    {
        String getUrl = "http://book.meiriyiwen.com/book?page=" + String.valueOf(page);
        ArrayList<Books> booksList = new ArrayList<Books>();
        Document doc = null;
        try{
            doc = Jsoup.connect(getUrl).get();
            Elements booklinks = doc.getElementsByClass("book-bg");
            Elements authorlinks = doc.getElementsByClass("book-author");
            int count = 0;
            for(Element e : booklinks)
            {

                Books books = new Books();
                books.setBookAuthor(authorlinks.get(count).text());
                books.setBookTitle(e.attr("title"));
                books.setBookCatalog("http://book.meiriyiwen.com" + e.attr("href"));
                String picurl = "http://book.meiriyiwen.com" + e.select("img").attr("src");
                books.setBookPicUrl(picurl);
                Log.e("书名",books.getBookPicUrl());
                booksList.add(books);
                count++;

            }

        }catch (Exception ex){ex.printStackTrace();
        Log.e("error-----------","获取目录失败");}
        return  booksList;

    }

    public ArrayList<CatalogIt> getBookCatalog(String catDialog)
    {   ArrayList<CatalogIt> sendList = new ArrayList<>();
        try {
            Document doc;
            doc = Jsoup.connect(catDialog).get();
            Elements elements = doc.getElementsByClass("chapter-list").select("li");
            for(Element e : elements)
            {
                CatalogIt cat = new CatalogIt();
                cat.setName(e.text());
                cat.setUrl("http://book.meiriyiwen.com" + e.select("li").select("a").attr("href"));
                sendList.add(cat);
            }
        }catch (Exception ex){ex.printStackTrace();}

         return sendList;
    }

    public String getArticle(String u)
    {String sendString = null;
        try {
            Document doc;
            doc = Jsoup.connect(u).get();
            sendString = doc.select("div.chapter-bg").text();

            /*for(Element e : elements)
            {
                CatalogIt cat = new CatalogIt();
                cat.setName(e.text());
                cat.setUrl("http://book.meiriyiwen.com" + e.select("li").select("a").attr("href"));
                sendList.add(cat);
            }*/
        }catch (Exception ex){ex.printStackTrace();}
        return sendString;
    }


}

