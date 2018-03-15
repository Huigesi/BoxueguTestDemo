package cn.edu.gdmec.android.boxuegutestdemo.Test;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.CourseBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegutestdemo.View.CourseView;
import cn.edu.gdmec.android.boxuegutestdemo.View.ViewPagerIndicator;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.CourseAdapter;

public class MainViewCourseFragment extends Fragment {

    public static final int MSG_AD_SLID = 002;
    private ListView lv_list;
    private List<List<CourseBean>> cbl;
    private CourseAdapter adapter;
    private ViewPager adPager;
    private AdBannerAdapter ada;
    private RelativeLayout adBannerLay;
    private ViewPagerIndicator vpi;
    //private CourseView.MHandler mHandler;
    private List<CourseBean> cadl;
    private List<ImageView> viewList;
    private ADViewPagerAdapter viewPagerAdapter;
    private List<View> dots;
    private View dots_1, dots_2, dots_3;
    private Thread thread;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (viewPagerAdapter.getCount() > 0) {
                        if (adPager.getCurrentItem()==viewList.size()-1){
                            adPager.setCurrentItem(0);
                        }else {
                            adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                            Log.i("s",""+adPager.getCurrentItem());
                        }
                    }
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_course_test, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initAdData();
        getCourseData();
        intiView(view);
        setViewPager();

    }

    private void setViewPager() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        handler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        };
        thread.start();
    }


    private void getCourseData() {
        try {
            InputStream is = getActivity().getResources().getAssets().open("chaptertitle.xml");
            cbl= AnalysisUtils.getCourseInfos(is);
            Log.i("cbl",cbl.size()+"");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int oldPoints = 0;


    private void intiView(View view) {
        lv_list = (ListView) view.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(getActivity());
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);
        adPager = view.findViewById(R.id.vp_advertBanner);
        adBannerLay = view.findViewById(R.id.rl_adBanner);
        viewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity());
        ImageView imageView2 = new ImageView(getActivity());
        ImageView imageView3 = new ImageView(getActivity());
        imageView1.setImageResource(R.drawable.banner_1);
        imageView2.setImageResource(R.drawable.banner_2);
        imageView3.setImageResource(R.drawable.banner_3);

        viewList.add(imageView1);
        viewList.add(imageView2);
        viewList.add(imageView3);
        viewPagerAdapter = new ADViewPagerAdapter(getActivity(), viewList);
        adPager.setAdapter(viewPagerAdapter);

        //圆点
        dots = new ArrayList<View>();

        dots_1 = (View) view.findViewById(R.id.dots_1);
        dots_2 = (View) view.findViewById(R.id.dots_2);
        dots_3 = (View) view.findViewById(R.id.dots_3);

        dots.add(dots_1);
        dots.add(dots_2);
        dots.add(dots_3);
        //第一个圆点设置为黑色
        dots.get(0).setBackgroundResource(R.drawable.indicator_on);
        adPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(oldPoints).setBackgroundResource(R.drawable.indicator_off);
                dots.get(position).setBackgroundResource(R.drawable.indicator_on);
                oldPoints = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        resetSize();
    }

    private void resetSize() {
        int sw = getScreenWidth(getActivity());
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

}
