package cn.edu.gdmec.android.boxuegutestdemo.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.UserBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.DBUtils;

public class UserInfoActivity extends Activity implements View.OnClickListener{

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private ImageView iv_head_icon;
    private TextView tv_user_name;
    private TextView tv_nickName;
    private TextView tv_sex;
    private TextView tv_signature;
    private RelativeLayout rl_head;
    private RelativeLayout rl_account;
    private RelativeLayout rl_nickName;
    private RelativeLayout rl_sex;
    private RelativeLayout rl_signature;
    private String spUserName;
    private static final int CHANGE_NICKNAME=1;
    private static final int CHANGE_SIGNATURE=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        spUserName= AnalysisUtils.readLoginUserName(this);
        initView();
        initData();
    }


    private void initData() {
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
        if (bean==null){
            bean=new UserBean();
            bean.userName=spUserName;
            bean.nickName="问答精灵";
            bean.sex="男";
            bean.signature="问答精灵";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        satValue(bean);
    }

    private void satValue(UserBean bean) {
        tv_user_name.setText(bean.userName);
        tv_nickName.setText(bean.nickName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        iv_head_icon = (ImageView) findViewById(R.id.iv_head_icon);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
        rl_head = (RelativeLayout) findViewById(R.id.rl_head);
        rl_head.setOnClickListener(this);
        rl_account = (RelativeLayout) findViewById(R.id.rl_account);
        rl_account.setOnClickListener(this);
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_nickName.setOnClickListener(this);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_sex.setOnClickListener(this);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        rl_signature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                //昵称
                String name=tv_nickName.getText().toString();
                Bundle bdName=new Bundle();
                bdName.putString("content",name);
                bdName.putString("title","昵称");
                bdName.putInt("flag", 1);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex=tv_sex.getText().toString();
                sexDiolag(sex);

                break;
            case R.id.rl_signature:
                //signature
                String signature=tv_signature.getText().toString();
                Bundle bdSignature=new Bundle();
                bdSignature.putString("content",signature);
                bdSignature.putString("title","签名");
                bdSignature.putInt("flag", 2);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
                break;
        }
    }

    private void sexDiolag(String sex) {
        int sexFlag=0;
        if ("男".equals(sex)){
            sexFlag=0;
        }else if ("女".equals(sex)){
            sexFlag=1;
        }
        final String item[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(item, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this,item[which],Toast.LENGTH_SHORT).show();
                setSex(item[which]);
            }
        });
        builder.create().show();

    }

    private void setSex(String s) {
        tv_sex.setText(s);
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex",s,spUserName);
    }

    public void enterActivityForResult(Class<?> to,int requestcode,Bundle b){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestcode);
    }
    private String new_info;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_NICKNAME:
                if (data!=null){
                    new_info=data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_nickName.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName",new_info,spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data!=null){
                    new_info=data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_signature.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature",new_info,spUserName);
                }
                break;
        }
    }
}
