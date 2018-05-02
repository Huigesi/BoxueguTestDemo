package cn.edu.gdmec.android.boxuegutestdemo.mvp.presenter;

import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.model.ExercisesModel;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.model.ExercisesModelImpel;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.model.OnLoadListener;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.view.ExercisesView;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ExercisesPresenterImpel implements ExercisesPresenter ,OnLoadListener{
    private ExercisesView view;
    private ExercisesModel model;

    public ExercisesPresenterImpel(ExercisesView view) {
        this.view = view;
        this.model = new ExercisesModelImpel();
    }

    @Override
    public void loadExercises() {
        model.loadExercises(this);
    }

    @Override
    public void onSuccess(List<ExercisesBean> ebl) {
        view.getExercises(ebl);
    }

    @Override
    public void onFailed(String s) {
        view.showFail();
    }
}
