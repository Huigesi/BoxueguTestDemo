package cn.edu.gdmec.android.boxuegutestdemo.mvp.model;

import android.content.Context;

/**
 * Created by Administrator on 2018/4/25.
 */

public interface ExercisesModel {
    //加载题目的方法，传入章节和加载监听
    void loadExercises( OnLoadListener loadListener);
}
