package com.example.haojie06.everydayn.util;

import com.example.haojie06.everydayn.view.articleFragment;
import com.example.haojie06.everydayn.view.bookFragment;
import com.example.haojie06.everydayn.view.soundFragment;

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
                case 0: baseFragment = new articleFragment();
                break;
                case 1: baseFragment = new soundFragment();
                break;
                case 2: baseFragment = new bookFragment();
                break;
            }
            mBaseFragments.put(pos,baseFragment);
        }
        return  baseFragment;
    }
}
