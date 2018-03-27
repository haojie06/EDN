package com.example.haojie06.everydayn;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import com.example.haojie06.everydayn.util.BaseFragment;
import com.example.haojie06.everydayn.util.FragmentFactor;
import com.example.haojie06.everydayn.view.ArticleCatalog;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity  {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTab;
    private ViewPager mVierPager;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void  initView(){
        mTab = (TabLayout) findViewById(R.id.tab);
        mVierPager = (ViewPager) findViewById(R.id.viewpager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
            if (id == R.id.download_article)
            {
                item.setCheckable(false);
                Toast.makeText(MainActivity.this,"加载文章",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ArticleCatalog.class);
                startActivity(intent);
            }
            else if (id == R.id.download_aboutme)
            {
                item.setCheckable(false);
            }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private  void initData()
    {
        FPagerAdapter adapter = new FPagerAdapter(getSupportFragmentManager());
        mVierPager.setAdapter(adapter);
        mTab.setupWithViewPager(mVierPager);
    }
    private class FPagerAdapter extends FragmentPagerAdapter{
        public String[] mTitle;

        public FPagerAdapter(FragmentManager fm){
            super(fm);
            mTitle = getResources().getStringArray(R.array.tab_Title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

        @Override
        public BaseFragment getItem(int position) {
            BaseFragment fragment = FragmentFactor.createFragment(position);
            return fragment;
        }

        @Override
       public int getCount() {
            String[] num = getResources().getStringArray(R.array.tab_Title);
            return num.length;
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            mDrawerLayout.openDrawer(GravityCompat.START);//打开侧滑菜单
            return true ;
        }

        return super.onOptionsItemSelected(item);
    }

    //使DrawerLayout可以全屏呼出


}
