package com.example.haojie06.everydayn.view;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.adapter.SoundAdapter;
import com.example.haojie06.everydayn.object.Sound;
import com.example.haojie06.everydayn.util.BaseFragment;
import com.example.haojie06.everydayn.util.webGet;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class SoundFragment extends BaseFragment {
    private int curPage = 1;
    private ArrayList<Sound> soundList = new ArrayList<Sound>();
    private Sound sound = new Sound();
    private SoundAdapter soundAdapter;
    RecyclerView recyclerView;
    View mView;
    webGet soundGet;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
         switch (msg.what){
             case 3:
                 Bundle bundle1;
                 bundle1 =  msg.getData();
                 soundList = bundle1.getParcelableArrayList("bundle");
                 soundAdapter = new SoundAdapter(soundList);
                 recyclerView.setAdapter(soundAdapter);
                 break;
             case 4:
                 Bundle bundle2 = msg.getData();
                 ArrayList<Sound> addList = bundle2.getParcelableArrayList("bundle2");
                 soundList.addAll(addList);
                 soundAdapter.notifyItemRangeChanged(12 * curPage,12);
                 break;
         }

        }
    };


    @Override
    protected void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Sound> soundList;
                soundGet = new webGet("http://voice.meiriyiwen.com/voice/past?page=1");
               soundList =  soundGet.soundGet(curPage);
               for (Sound sound : soundList)
               {
                   Log.e("qqqqqqqqqq",sound.getSoundAuthor());
               }
               Message message = new Message();
               message.what = 3;
               Bundle bundle = new Bundle();
               bundle.putParcelableArrayList("bundle",soundList);
               message.setData(bundle);
               handler.sendMessage(message);
            }
        }).start();
    }

 /*   @Override
    protected View initiew() {
        TextView mView = new TextView(mContext);
        mView.setText("Fragment声音");
        mView.setGravity(Gravity.CENTER);
        mView.setTextSize(18);
        mView.setTextColor(Color.BLACK);
        return mView;
    }
*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.view_two,null);//第二个参数？
        }
        loadData();
        recyclerView = (RecyclerView) mView.findViewById(R.id.sound_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        RefreshLayout refreshLayout = (RefreshLayout) mView.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(false/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                moreSound();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        return mView;
    }

    private void moreSound()
    {
        curPage++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Sound> moreSound = soundGet.soundGet(curPage);
                Message message = new Message();
                message.what = 4;
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("bundle2",moreSound);
                message.setData(bundle2);
                handler.sendMessage(message);
            }
        }).start();
    }
}
