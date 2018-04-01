package com.example.haojie06.everydayn.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import com.bumptech.glide.Glide;
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.adapter.CatalogAdapter;
import com.example.haojie06.everydayn.object.Books;
import com.example.haojie06.everydayn.object.CatalogIt;
import com.example.haojie06.everydayn.object.Sound;
import com.example.haojie06.everydayn.util.webGet;

import java.util.ArrayList;
import java.util.List;

public class BookCatalog extends AppCompatActivity {
    private List<CatalogIt> cataList = new ArrayList<>();
    Books getBookk;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_catalog);
        getBookk = (Books) getIntent().getParcelableExtra("book");
        ImageView imageView = (ImageView) findViewById(R.id.cat_picture);
        Glide.with(this).load(getBookk.getBookPicUrl()).into(imageView);
        TextView catTitle,catAa;
        catTitle = (TextView) findViewById(R.id.cat_title);
        catAa = (TextView) findViewById(R.id.cat_aa);
        catTitle.setText(getBookk.getBookTitle());
        catAa.setText(getBookk.getBookAuthor());
        loadBooks();

    }
   private Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what)
           {
               case 1:
                Bundle bundle = msg.getData();
                cataList = bundle.getParcelableArrayList("booksbundle");
                   CatalogAdapter adapter = new CatalogAdapter(BookCatalog.this,R.layout.catalog_item,cataList);
                   ListView listView = (ListView) findViewById(R.id.cata_list);
                   listView.setAdapter(adapter);

                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                           CatalogIt catalogIt = cataList.get(i);
                           Intent intent = new Intent(BookCatalog.this,DialogShow.class);
                           intent.putExtra("dialogshow",catalogIt.getUrl());
                           startActivity(intent);
                       }
                   });
                break;
           }
       }
   };

    public void loadBooks()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<CatalogIt> getList = new ArrayList<>();
                webGet webget = new webGet();
                Message message = new Message();
                message.what = 1;
                getList =  webget.getBookCatalog(getBookk.getBookCatalog());
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("booksbundle",getList);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();

    }
}
