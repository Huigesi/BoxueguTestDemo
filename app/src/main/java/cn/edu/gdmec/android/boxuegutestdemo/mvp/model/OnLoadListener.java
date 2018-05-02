package cn.edu.gdmec.android.boxuegutestdemo.mvp.model;

import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.ExercisesBean;

/**
 * Created by Administrator on 2018/4/25.
 */

public interface OnLoadListener {
    void onSuccess(List<ExercisesBean> ebl);
    void onFailed(String s);
}
