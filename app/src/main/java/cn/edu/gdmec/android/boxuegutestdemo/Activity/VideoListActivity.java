package cn.edu.gdmec.android.boxuegutestdemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegutestdemo.Utils.DBUtils;
import cn.edu.gdmec.android.boxuegutestdemo.adapter.VideoListAdapter;

public class VideoListActivity extends Activity {

    private TextView tv_intro;
    private TextView tv_video;
    private ListView lv_video_list;
    private TextView tv_chapter_intro;
    private ScrollView sv_chapter_intro;
    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
    private DBUtils db;
    private VideoListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        chapterId=getIntent().getIntExtra("id",0);
        intro = getIntent().getStringExtra("intro");
        db=DBUtils.getInstance(VideoListActivity.this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_video = (TextView) findViewById(R.id.tv_video);
        lv_video_list = (ListView) findViewById(R.id.lv_video_list);
        tv_chapter_intro = (TextView) findViewById(R.id.tv_chapter_intro);
        sv_chapter_intro = (ScrollView) findViewById(R.id.sv_chapter_intro);
        adapter=new VideoListAdapter(this, new VideoListAdapter.OnSelectListener() {
            @Override
            public void onSelect(int position, ImageView iv) {
                adapter.setSelectedPosition(position);
                VideoBean bean=videoList.get(position);
                String videoPath=bean.videoPath;
                adapter.notifyDataSetChanged();
                if (TextUtils.isEmpty(videoPath)){
                    Toast.makeText(VideoListActivity.this, "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (readLoginStatus()){
                        String userName = AnalysisUtils.readLoginUserName(VideoListActivity.this);
                        db.saveVideoPlayList(videoList.get(position), userName);
                    }
                }
            }
        });
    }

    private boolean readLoginStatus() {
        return false;
    }
}
