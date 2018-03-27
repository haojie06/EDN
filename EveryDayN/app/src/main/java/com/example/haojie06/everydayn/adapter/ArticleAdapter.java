package com.example.haojie06.everydayn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Articles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/28.
 */

public class ArticleAdapter extends ArrayAdapter<Articles> {
    private String title,author;
    private int id;
    private List<Articles> list;

    public ArticleAdapter(Context context,int id, List<Articles> list)
    {
        super(context,id,list);
    this.list = list;
    this.id = id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Articles articles = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(id,parent,false);
        TextView titleView = (TextView) view.findViewById(R.id.title_item);
        TextView authorView = (TextView) view.findViewById(R.id.author_item);
        titleView.setText(articles.getTitle());
        authorView.setText(articles.getName());
        return view;
    }
}
