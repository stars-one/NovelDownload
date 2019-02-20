package com.wan.noveldownload.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wan.noveldownload.Fragment.DownloadedFragment;
import com.wan.noveldownload.Fragment.DownloadingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xen on 2019/2/20 0020.
 */

public class MyViewpagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"正在下载","下载中"};
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList=new ArrayList<>();


    public MyViewpagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        fragmentList.add(DownloadingFragment.newInstance(null, null));
        fragmentList.add(DownloadedFragment.newInstance(null, null));
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
