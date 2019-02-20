package com.wan.noveldownload.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wan.noveldownload.Adapter.MyViewpagerAdapter;
import com.wan.noveldownload.BaseActivity;
import com.wan.noveldownload.R;


public class DownloadActivity extends BaseActivity {

    private ViewPager mViewpager;
    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        MyViewpagerAdapter adapter = new MyViewpagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);
        
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
    }
}