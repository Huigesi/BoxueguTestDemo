package cn.edu.gdmec.android.boxuegutestdemo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.View.MyInfoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private FrameLayout main_body;
    private TextView bottom_bar_text_course;
    private ImageView bottom_bar_image_course;
    private TextView bottom_bar_text_exercises;
    private ImageView bottom_bar_image_exercises;
    private TextView bottom_bar_text_myinfo;
    private ImageView bottom_bar_image_myinfo;
    private LinearLayout main_bottom_bar;
    private View mCourseBtn,mExercisesBtn,mMyInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        setInitStatus();
    }



    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        main_body = (FrameLayout) findViewById(R.id.main_body);
        bottom_bar_text_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        bottom_bar_image_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        bottom_bar_text_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        bottom_bar_image_exercises = (ImageView) findViewById(R.id.bottom_bar_image_exercises);
        bottom_bar_text_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        bottom_bar_image_myinfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
        main_bottom_bar = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCourseBtn=findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn=findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn=findViewById(R.id.bottom_bar_myinfo_btn);

        tv_back.setVisibility(View.GONE);
        tv_main_title.setText("博学谷课程");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
        }
    }
    private void setListener() {
        for (int i=0;i<main_bottom_bar.getChildCount();i++){
            main_bottom_bar.getChildAt(i).setOnClickListener(this);
            Log.i("i",""+main_bottom_bar.getChildAt(i));
        }
    }

    private void clearBottomImageState() {
        bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));
        bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
        bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
        bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
        for (int i=0;i<main_bottom_bar.getChildCount();i++){
            main_bottom_bar.getChildAt(i).setSelected(false);
        }

    }
    private void setSelectStatus(int index){
        switch (index){
            case 0:
                mCourseBtn.setSelected(true);
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon_selected);
                bottom_bar_text_course.setTextColor(Color.parseColor("#0097F7"));
                title_bar.setVisibility(View.GONE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#0097F7"));
                title_bar.setVisibility(View.GONE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#0097F7"));
                title_bar.setVisibility(View.GONE);
                break;
        }
    }
    private void removeAllView(){
        for (int i=0;i<main_body.getChildCount();i++){
            main_body.getChildAt(i).setVisibility(View.GONE);
            Log.i("i",""+main_body.getChildAt(i));
        }
    }
    private void setInitStatus(){
        clearBottomImageState();
        setSelectStatus(0);
        createView(0);
    }
    private MyInfoView myInfoView;

    private void createView(int index) {
        switch (index){
            case 0:

                break;
            case 1:
                break;
            case 2:
                if (myInfoView==null){
                    myInfoView=new MyInfoView(this);
                    main_body.addView(myInfoView.getView());
                }else {
                    myInfoView.getView();
                }
                myInfoView.showView();
                break;
        }

    }
    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                MainActivity.this.finish();
                if (readLoginStatus()){
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void clearLoginStatus() {
        SharedPreferences sp=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();

    }

    private boolean readLoginStatus() {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }

    private void selectDisplayView(int i) {
        removeAllView();
        createView(i);
        setSelectStatus(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if (isLogin){
                clearBottomImageState();
                selectDisplayView(0);

            }
            if (myInfoView!=null){
                myInfoView.setLoginParams(isLogin);
            }
        }
    }
}
