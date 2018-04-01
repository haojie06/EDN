package com.example.haojie06.everydayn.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.util.webGet;

public class DialogShow extends AppCompatActivity {

    String getContent = null;
    TextView title,content;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    getContent = bundle.getString("content");
                    content = (TextView) findViewById(R.id.showContent);
                    content.setText(getContent);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_show);
      title =  (TextView) findViewById(R.id.showCatTitle);

     final String url = getIntent().getStringExtra("dialogshow");
     new Thread(new Runnable() {
         @Override
         public void run() {
             String get = null;
             webGet web = new webGet();
             get =  web.getArticle(url);
             Message message = new Message();
             message.what = 1;
             Bundle bundle = new Bundle();
             bundle.putString("content",get);
             message.setData(bundle);
             handler.sendMessage(message);
         }
     }).start();
    }
}
