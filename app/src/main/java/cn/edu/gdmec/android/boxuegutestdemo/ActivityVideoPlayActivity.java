package cn.edu.gdmec.android.boxuegutestdemo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.VideoView;

public class ActivityVideoPlayActivity extends Activity  {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        videoView = (VideoView) findViewById(R.id.videoView);
    }

}
