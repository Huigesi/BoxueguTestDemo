package cn.edu.gdmec.android.boxuegutestdemo.mvp.view;

import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.ExercisesBean;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface ExercisesView {
    void getExercises(List<ExercisesBean> list);

    void showFail();
}
