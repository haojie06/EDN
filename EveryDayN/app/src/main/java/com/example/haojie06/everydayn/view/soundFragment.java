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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class SoundFragment extends BaseFragment {

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
                 Bundle bundle;
                 bundle =  msg.getData();
                 soundList = bundle.getParcelableArrayList("bundle");
                 soundAdapter = new SoundAdapter(soundList);
                 recyclerView.setAdapter(soundAdapter);
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
               soundList =  soundGet.soundGet();
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

        return mView;
    }
}
