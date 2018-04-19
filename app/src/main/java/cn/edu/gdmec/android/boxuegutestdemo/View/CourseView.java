package cn.edu.gdmec.android.boxuegutestdemo.View;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.CourseBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.CourseAdapter;

/**
 * Created by Administrator on 2018/3/12.
 */

public class CourseView {
    public static final int MSG_AD_SLID = 002;
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

    public CourseView(FragmentActivity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    private void createView() {
        mHandler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }

    private void getCourseData() {
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            //cbl= AnalysisUtils.getCourseInfos(is);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public View getView(){
        if (mCurrentView==null){
            createView();
        }
        return mCurrentView;
    }
    public void showView(){
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

    private void initAdData() {
        cadl = new ArrayList<CourseBean>();
        for (int i = 0; i < 3; i++) {
            CourseBean bean = new CourseBean();
            bean.id = (i + 1);
            switch (i) {
                case 0:
                    bean.icon = "banner_1";
                    break;
                case 1:
                    bean.icon = "banner_2";
                    break;
                case 2:
                    bean.icon = "banner_3";
                    break;
            }
            cadl.add(bean);
        }
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);
        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(mContext);
        adapter.setData(cbl);


        lv_list.setAdapter(adapter);
        adPager = (ViewPager) mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        ada = new AdBannerAdapter(mContext.getSupportFragmentManager(), mHandler);
        adPager.setAdapter(ada);
        adPager.setOnTouchListener(ada);
        vpi = (ViewPagerIndicator) mCurrentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(ada.getSize());
        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (ada.getSize() > 0) {
                    vpi.setCurrentPosition(position % ada.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        resetSize();
        if (cadl != null) {
            if (cadl.size() > 0) {
                vpi.setCount(cadl.size());
                vpi.setCurrentPosition(0);
            }
            ada.setDatas(cadl);
        }
    }


    private void resetSize() {
        int sw = getScreenWidth(mContext);
        int adLheight = sw / 2;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

    private int getScreenWidth(Activity mContext) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = mContext.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_AD_SLID:
                    if (ada.getCount() > 0) {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }

    class AdAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null) {
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        }
    }
}
