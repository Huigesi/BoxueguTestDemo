package cn.edu.gdmec.android.boxuegutestdemo.Test;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.ExercisesAdapter;

public class MainViewExercisesFragment extends Fragment  {

    private ListView lvList;
    private View view1;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_exercises, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvList = (ListView) view.findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(getActivity());
        initData();
        mInflater = LayoutInflater.from(getActivity());
        view1=mInflater.inflate(R.layout.load_more,null);

        view1.setVisibility(View.VISIBLE);
        adapter.setData(ebl);
        lvList.setAdapter(adapter);
    }
    private void initData() {
        ebl =new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.title="第1章 Android基础入门";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title="第2章 Android UI开发";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title="第3章 Activity";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title="第4章 数据存储";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title="第5章 SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title="第6章  广播接收者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title="第7章 服务";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title="第8章 内容提供者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
              case 8:
                    bean.title="第9章 网络编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title="第10章 高级编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
            }
            ebl.add(bean);
        }
    }
    public List<ExercisesBean> newData(){
        ebl =new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++) {
            ExercisesBean bean = new ExercisesBean();
            bean.id = (i + 1);
            switch (i) {
                case 0:
                    bean.title = "第1章 Android基础入门";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title = "第2章 Android UI开发";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title = "第3章 Activity";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title = "第4章 数据存储";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title = "第5章 SQLite数据库";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title = "第6章  广播接收者";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title = "第7章 服务";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title = "第8章 内容提供者";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title = "第9章 网络编程";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title = "第10章 高级编程";
                    bean.content = "共计5题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
            }
            ebl.add(bean);
        }
        return ebl;
    }

    /*@Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        last_index = firstVisibleItem+visibleItemCount;
        total_index = totalItemCount;
        Log.i("last_index",last_index+"");
        Log.i("total_index",totalItemCount+"");

    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE))
        {
            //表示此时需要显示刷新视图界面进行新数据的加载(要等滑动停止)
            if(!isLoading)
            {
                //不处于加载状态的话对其进行加载
                isLoading = true;
                //设置刷新界面可见

                view1.setVisibility(View.VISIBLE);

                onLoad();
            }

        }

    }

    private void onLoad2() {
        if(adapter == null)
        {
            adapter=new ExercisesAdapter(getActivity());
            adapter.setData(ebl);
            lvList.setAdapter(adapter);
        }else
        {
            adapter.updateView(newData2());
        }
        loadComplete();//刷新结束
    }

    *//**
     * 刷新加载
     *//*
    public void onLoad()
    {
        if(adapter == null)
        {
            adapter=new ExercisesAdapter(getActivity());
            adapter.setData(ebl);
            lvList.setAdapter(adapter);
        }else
        {
            adapter.updateView(newData());
        }
        loadComplete();//刷新结束
    }

    *//**
     * 加载完成
     *//*
    public void loadComplete()
    {
        view1.setVisibility(View.GONE);//设置刷新界面不可见
        isLoading = false;//设置正在刷新标志位false

        lvList.removeFooterView(view1);//如果是最后一页的话，则将其从ListView中移出
        lvList.removeHeaderView(view1);
    }*/


}
