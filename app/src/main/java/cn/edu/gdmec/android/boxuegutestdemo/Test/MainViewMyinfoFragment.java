package cn.edu.gdmec.android.boxuegutestdemo.Test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegutestdemo.Activity.LoginActivity;
import cn.edu.gdmec.android.boxuegutestdemo.Activity.SettingActivity;
import cn.edu.gdmec.android.boxuegutestdemo.Activity.UserInfoActivity;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;

public class MainViewMyinfoFragment extends Fragment  {

    private LinearLayout llHead;
    private ImageView ivHeadIcon;
    private TextView tvUserName;
    private RelativeLayout rlCourseHistory;
    private ImageView ivCourseHistoryicon;
    private RelativeLayout rlSetting;
    private ImageView ivUserinfoIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_myinfo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llHead = (LinearLayout) view.findViewById(R.id.ll_head);
        ivHeadIcon = (ImageView) view.findViewById(R.id.iv_head_icon);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        rlCourseHistory = (RelativeLayout) view.findViewById(R.id.rl_course_history);
        ivCourseHistoryicon = (ImageView) view.findViewById(R.id.iv_course_historyicon);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        ivUserinfoIcon = (ImageView) view.findViewById(R.id.iv_userinfo_icon);

        setLoginParams(readLoginStatus());

        llHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                    Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }
            }
        });
        rlCourseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){

                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                    Intent intent=new Intent(getActivity(), SettingActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setLoginParams(boolean b) {
        if (b){
            tvUserName.setText(AnalysisUtils.readLoginUserName(getActivity()));
        }else {
            tvUserName.setText("点击登录");
        }
    }
    private boolean readLoginStatus() {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }

}
