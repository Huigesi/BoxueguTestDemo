package cn.edu.gdmec.android.boxuegutestdemo.Test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegutestdemo.Activity.ModifyPswActivity;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;

public class SettingActivity_test extends AppCompatActivity{

    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;
    private RelativeLayout rl_title_bar;
    private TextView tv_back;
    private TextView tv_main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        rl_modify_psw = (RelativeLayout) findViewById(R.id.rl_modify_psw);
        rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity_test.this.finish();
            }
        });
        tv_main_title.setText("设置");
        rl_modify_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity_test.this,ModifyPswActivity.class);
                startActivity(intent);
            }
        });
        rl_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity_test.this,FindPswActivity_test.class);
                intent.putExtra("from","security");
                startActivity(intent);
            }
        });
        rl_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity_test.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                AnalysisUtils.clearLoginStatus(SettingActivity_test.this);
                Intent data=new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity_test.this.finish();
            }
        });
    }



}
