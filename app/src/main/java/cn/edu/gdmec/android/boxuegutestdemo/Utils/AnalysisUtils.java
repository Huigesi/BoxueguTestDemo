package cn.edu.gdmec.android.boxuegutestdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/11.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sharedPreferences.getString("loginUserName","");
        return userName;
    }
}
