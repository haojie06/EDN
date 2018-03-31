package com.example.haojie06.everydayn.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.haojie06.everydayn.MainActivity;
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Sound;
import com.example.haojie06.everydayn.view.SoundFragment;
import com.example.haojie06.everydayn.view.SoundPlay;

import java.util.List;

/**
 * Created by haojie06 on 2018/3/28.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {

    private Context mContex;
    private List<Sound> mSoundList;

    static  class ViewHolder extends  RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView titleView;
        TextView aaView;
        public ViewHolder(View view)
        {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.sound_picture);
            titleView = (TextView) view.findViewById(R.id.sound_title);
            aaView = (TextView) view.findViewById( R.id.sound_aa);
        }
    }
    public SoundAdapter(List<Sound> soundList)
    {
        mSoundList = soundList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //了解一下
        if(mContex == null)
        {
            mContex = parent.getContext();
        }
        View view = LayoutInflater.from(mContex).inflate(R.layout.sound_item,parent,false);
        //设置item点击监听
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Sound sound = mSoundList.get(position);
                Intent go = new Intent(mContex, SoundPlay.class);
                go.putExtra("playSound",sound);

                Glide.with(mContex).load(sound.getSoundPicUrl()).centerCrop().preload();
               // mContex.startActivity(go, ActivityOptions.makeSceneTransitionAnimation((MainActivity) mContex).toBundle());
                mContex.startActivity(go, ActivityOptions.makeSceneTransitionAnimation((MainActivity)mContex,view, "soundImg").toBundle());

            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sound sound = mSoundList.get(position);
        holder.titleView.setText(sound.getSoundTitle());
        holder.aaView.setText(sound.getSoundAuthor());
        Glide.with(mContex).load(sound.getSoundPicUrl()).centerCrop().preload();
        Glide.with(mContex).load(sound.getSoundPicUrl()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mSoundList.size();
    }
}
