package cn.edu.gdmec.android.boxuegutestdemo.View;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.List;
import java.util.logging.LogRecord;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.CourseBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.CourseAdapter;

/**
 * Created by Administrator on 2018/3/12.
 */

public class CourseView {
    public static final int MSG_AD_SLID=02;
    private ListView lv_list;
    private List<List<CourseBean>> cbl;
    private FragmentActivity mContext;
    private CourseAdapter adapter;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;
    private View adBannerLay;
    private AdBannerAdapter ada;
    private ViewPagerIndicator vpi;
    private MHandler mHandler;
    private List<CourseBean> cadl;
    public CourseView(FragmentActivity context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        mHandler=new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }

    private void getCourseData() {

    }

    private void initAdData() {

    }

    private void initView() {
        mCurrentView=mInflater.inflate(R.layout.main_view_course,null);
        lv_list=(ListView)mCurrentView.findViewById(R.id.lv_list);
        adapter=new CourseAdapter(mContext);
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);
        adPager=(ViewPager)mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        ada=new AdBannerAdapter(mContext.getSupportFragmentManager(),mHandler);
        adPager.setOnTouchListener(ada);
        vpi=(ViewPagerIndicator) mCurrentView.findViewById(R.id.vpi_advert_indicator);
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (ada.getCount()>0){
                        adPager.setCurrentItem(adPager.getCurrentItem()+1);
                    }
                    break;
            }
        }
    }
    class AdAutoSlidThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler!=null){
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        }
    }
}
