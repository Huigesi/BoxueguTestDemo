package cn.edu.gdmec.android.boxuegutestdemo.mvp.view;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.ExercisesAdapter;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.presenter.ExercisesPresenter;
import cn.edu.gdmec.android.boxuegutestdemo.mvp.presenter.ExercisesPresenterImpel;

public class MainViewExercisesActivity extends Activity  implements ExercisesView{

    private ListView lvList;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private ExercisesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view_exercises);
        presenter=new ExercisesPresenterImpel(this);
        lvList = (ListView) findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(this);
        lvList.setAdapter(adapter);
        presenter.loadExercises();
    }


    @Override
    public void getExercises(List<ExercisesBean> list) {
       if (ebl==null){
           ebl=new ArrayList<>();
       }
       ebl.addAll(list);
        adapter.setData(ebl);

    }

    @Override
    public void showFail() {
        Toast.makeText(this,"加载失败",Toast.LENGTH_SHORT).show();
    }
}
