package cn.edu.gdmec.android.boxuegutestdemo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

public class PlayHistoryActivity extends Activity  {

    private ListView lvList;
    private TextView tvNone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);

        lvList = (ListView) findViewById(R.id.lv_list);
        tvNone = (TextView) findViewById(R.id.tv_none);
    }

}
