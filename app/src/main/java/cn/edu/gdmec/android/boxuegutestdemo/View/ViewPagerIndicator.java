package cn.edu.gdmec.android.boxuegutestdemo.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.gdmec.android.boxuegutestdemo.R;

/**
 * Created by Administrator on 2018/3/12.
 */

public class ViewPagerIndicator extends LinearLayout{
    private int mCount;
    private int mIndex;
    private Context context;

    public ViewPagerIndicator(Context context) {
        this(context,null);

    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER);
    }
    public void setCurrentPosition(int currentIndex){
        mIndex=currentIndex;
        removeAllViews();
        int pex=5;
        for (int i=0;i<mCount;i++){
            ImageView imageView=new ImageView(context);
            if (mIndex==i){
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);
            addView(imageView);
        }
    }
    public void setCount(int count){
        this.mCount=count;
    }
}
