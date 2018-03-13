package cn.edu.gdmec.android.boxuegutestdemo.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.CourseBean;
import cn.edu.gdmec.android.boxuegutestdemo.Fragment.AdBannerFragment;
import cn.edu.gdmec.android.boxuegutestdemo.View.CourseView;

/**
 * Created by Administrator on 2018/3/12.
 */

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener{
    private Handler mHandler;
    private List<CourseBean> cadl;
    public void setDatas(List<CourseBean> cadl){
        this.cadl=cadl;
        notifyDataSetChanged();
    }

    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        cadl=new ArrayList<CourseBean>();
    }
    public AdBannerAdapter(FragmentManager fm,Handler handler) {
        super(fm);
        cadl=new ArrayList<CourseBean>();
        mHandler=handler;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args=new Bundle();
        if (cadl.size()>0){
            args.putString("ad",cadl.get(position%cadl.size()).icon);
        }
        return AdBannerFragment.newInstance(args);

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    public int getSize(){
        return cadl==null?0:cadl.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

        
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mHandler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
}
