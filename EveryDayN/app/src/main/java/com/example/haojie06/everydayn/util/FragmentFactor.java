package com.example.haojie06.everydayn.util;

import com.example.haojie06.everydayn.view.ArticleFragment;
import com.example.haojie06.everydayn.view.BookFragment;
import com.example.haojie06.everydayn.view.SoundFragment;

import java.util.HashMap;

/**
 * Created by haojie06 on 2018/3/25.
 * 碎片的工厂类
 */

public class FragmentFactor {
    private static HashMap<Integer,BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int pos){
        BaseFragment baseFragment = mBaseFragments.get(pos);

        if (baseFragment == null){//多态
            switch (pos){
                case 0: baseFragment = new ArticleFragment();
                break;
                case 1: baseFragment = new SoundFragment();
                break;
                case 2: baseFragment = new BookFragment();
                break;
            }
            mBaseFragments.put(pos,baseFragment);
        }
        return  baseFragment;
    }
}
