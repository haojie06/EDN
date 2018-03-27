package com.example.haojie06.everydayn.view;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.util.BaseFragment;

/**
 * Created by haojie06 on 2018/3/25.
 */

public class bookFragment extends BaseFragment {
    View mView;
    @Override
    protected void loadData() {
        Toast.makeText(mContext,"声音将会出现在这里",Toast.LENGTH_SHORT).show();
    }
/*
    @Override
    protected View initiew() {
        TextView mView = new TextView(mContext);
        mView.setText("Fragment书架");
        mView.setGravity(Gravity.CENTER);
        mView.setTextSize(18);
        mView.setTextColor(Color.BLACK);
        return mView;
    }
*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.view_three,null);//第二个参数？

        }

        return mView;
    }
}
