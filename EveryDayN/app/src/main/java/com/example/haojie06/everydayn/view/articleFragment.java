package com.example.haojie06.everydayn.view;
import android.content.*;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Articles;
import com.example.haojie06.everydayn.util.BaseFragment;
import com.example.haojie06.everydayn.util.webGet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class articleFragment extends BaseFragment {
    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefreshLayout;
    View mView ;
    TextView articleText,titleText,nameText;
    Articles articles;
    Boolean isNeedDownload = true;//点击收藏后是否有必要下载，防止重复点击
    private Handler handler = new Handler(){

        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case 1:
                    Articles articles =(Articles) msg.obj;
                    articleText = (TextView) mView.findViewById(R.id.artTextV);
                    titleText = (TextView) mView.findViewById(R.id.artTitle);
                    nameText = (TextView) mView.findViewById(R.id.artAuthor);
                    Log.i("handler","成功接收");
                    articleText.setText("    " + articles.getContent());
                    titleText.setText(articles.getTitle());
                    nameText.setText(articles.getName());
                    fab.setVisibility(View.VISIBLE);
                    break;

                case 2:
                    isNeedDownload = true;
                    Articles ranArticle =(Articles) msg.obj;
                    Log.i("handler","成功接收");
                    swipeRefreshLayout.setRefreshing(false);
                    articleText.setText("    " + ranArticle.getContent());
                    titleText.setText(ranArticle.getTitle());
                    nameText.setText(ranArticle.getName());

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.view_one,container,false);
        fab = mView.findViewById(R.id.fab);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //下拉刷新文章
            @Override
            public void onRefresh() {
                refreshArticle();
            }
        });

        //点击收藏按钮将文章下载到本地
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNeedDownload) {
                    Toast.makeText(getContext(), "文章已收藏，保存到本地", Toast.LENGTH_SHORT).show();
                    isNeedDownload = false;
                    Articles saveAer = new Articles();
                    saveAer.setTitle((String) titleText.getText());
                    saveAer.setName((String) nameText.getText());
                    saveAer.setContent((String) articleText.getText());
                    Log.e("-------标题栏里面有",(String) titleText.getText());
                    saveArticle(saveAer);
                }else {Toast.makeText(getContext(), "文章已下载，不要再点啦！", Toast.LENGTH_SHORT).show();}
            }
        });
        return mView;
    }


    @Override
    protected void loadData() {


new Thread(new Runnable() {
    @Override
    public void run() {


                Articles result;
                String getUrl = "https://meiriyiwen.com/";
                webGet we = new webGet(getUrl);
                result = we.web();
                Message message = new Message();
                message.what = 1;
                message.obj = result;
                handler.sendMessage(message);


        //Toast.makeText(mContext,"此处将会有文章",Toast.LENGTH_SHORT).show();

    }

}).start();
    }

    private void refreshArticle()
    {

        new Thread(new Runnable() {
            //刷新文章
            @Override
            public void run() {
                Articles ranArticle = new Articles();
                String getUrl = "https://meiriyiwen.com/random";
                webGet newGet = new webGet(getUrl);
                ranArticle = newGet.web();
                Message message = new Message();
                message.what = 2;
                message.obj = ranArticle;
                try{
                    Thread.sleep(600);
                }catch (InterruptedException ex){ex.printStackTrace();}
                handler.sendMessage(message);

            }
        }).start();
    }

    private void saveArticle(Articles art)
    {
        art.save();
}
}


