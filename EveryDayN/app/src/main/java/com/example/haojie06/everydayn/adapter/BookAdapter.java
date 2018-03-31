package com.example.haojie06.everydayn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Books;

import java.util.List;

/**
 * Created by haojie06 on 2018/3/31.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Books> booksList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView bookImg;
        TextView titleText,authorText;
        public ViewHolder(View view)
        {

            super(view);
            bookImg = (ImageView) view.findViewById(R.id.book_picture);
            titleText = (TextView) view.findViewById(R.id.book_title);
            authorText = (TextView) view.findViewById(R.id.book_aa);

        }
    }

    public BookAdapter(List<Books> list)
    {
        booksList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
      //  final   BookAdapter.ViewHolder holder = new BookAdapter.ViewHolder(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Books book = booksList.get(position);
        Log.e("holder:------",book.getBookTitle());
       holder.titleText.setText(book.getBookTitle());
        holder.authorText.setText(book.getBookAuthor());
        Glide.with(context).load(book.getBookPicUrl()).into(holder.bookImg);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
