package com.example.haojie06.everydayn.view;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.adapter.BookAdapter;
import com.example.haojie06.everydayn.object.Books;
import com.example.haojie06.everydayn.util.BaseFragment;
import com.example.haojie06.everydayn.util.webGet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class BookFragment extends BaseFragment {
    View mView;
    private List<Books> booksList = new ArrayList<>();
RecyclerView recyclerView;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    Bundle bundle = msg.getData();
                    booksList = bundle.getParcelableArrayList("bookList");
                    BookAdapter bookAdapter = new BookAdapter(booksList);
                    recyclerView = mView.findViewById(R.id.books_recyclerview);
                    recyclerView.setAdapter(bookAdapter);
                    break;
                case 2:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void loadData() {
        Toast.makeText(mContext, "声音将会出现在这里", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Books> getBooks;
                webGet webGet = new webGet();
                getBooks = webGet.getBooks(1);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("bookList",getBooks);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();




    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.view_three,null);//第二个参数？

        }
        loadData();

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.books_recyclerview);
      //
          LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(booksList);
        recyclerView.setAdapter(adapter);
        return mView;
    }




}
