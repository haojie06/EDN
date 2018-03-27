package com.example.haojie06.everydayn.object;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class Articles extends DataSupport implements Serializable{
    private String title;
    private String name;
    private String content;
    private String time;
    private String aUrl;

    public Articles() {
    }

    public Articles(String title, String name, String content, String time, String aUrl) {
        this.title = title;
        this.name = name;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }





}
