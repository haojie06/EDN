package com.example.haojie06.everydayn.adapter;

import android.content.Context;
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
import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.Sound;

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
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sound sound = mSoundList.get(position);
        holder.titleView.setText(sound.getSoundTitle());
        holder.aaView.setText(sound.getSoundAuthor());
        Glide.with(mContex).load(sound.getSoundPicUrl()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mSoundList.size();
    }
}
