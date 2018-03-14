package cn.edu.gdmec.android.boxuegutestdemo.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.UserBean;
import cn.edu.gdmec.android.boxuegutestdemo.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegutestdemo.Sqlite.SQLiteHelper;

/**
 * Created by Administrator on 2018/3/12.
 */

public class DBUtils {
    private static DBUtils instance=null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper=new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }
    public void saveUserInfo(UserBean bean){
        ContentValues contentValues=new ContentValues();
        contentValues.put("userName",bean.userName);
        contentValues.put("nickName",bean.nickName);
        contentValues.put("sex",bean.sex);
        contentValues.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,contentValues);
    }
    public UserBean getUserInfo(String userName){
        String sql="SELECT * FROM "+SQLiteHelper.U_USERINFO+" WHERE userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});
        UserBean userBean=null;
        while (cursor.moveToNext()){
            userBean=new UserBean();
            userBean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            userBean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            userBean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            userBean.signature=cursor.getString(cursor.getColumnIndex("signature"));

        }
        cursor.close();
        return userBean;
    }
    public void updateUserInfo(String key,String value,String userName){
        ContentValues contentValues=new ContentValues();
        contentValues.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,contentValues,"userName=?",new String[]{userName});
    }

    public void saveVideoPlayList(VideoBean videoBean, String userName) {
        if (hasVideoPlay(videoBean.chapterId,videoBean.videoId,userName)){
            boolean isDelete=delVideoPlay(videoBean.chapterId,videoBean.videoId,userName);
            if (!isDelete){
                return;
            }
        }
    }

    private boolean delVideoPlay(int chapterId, int videoId, String userName) {
        return false;
    }

    private boolean hasVideoPlay(int chapterId, int videoId, String userName) {
        return false;
    }
}
