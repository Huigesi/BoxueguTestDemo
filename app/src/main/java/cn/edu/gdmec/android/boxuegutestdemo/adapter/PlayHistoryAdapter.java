package cn.edu.gdmec.android.boxuegutestdemo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegutestdemo.Activity.VideoPlayActivity;
import cn.edu.gdmec.android.boxuegutestdemo.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;

public class PlayHistoryAdapter extends BaseAdapter {

    private List<VideoBean> objects = new ArrayList<VideoBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public PlayHistoryAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<VideoBean> vbl) {
        this.objects = vbl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.play_history_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((VideoBean) getItem(position), (ViewHolder) convertView.getTag(), position,convertView);
        return convertView;
    }

    private void initializeViews(VideoBean object, ViewHolder holder, int position,View convertView) {
        //TODO implement
        final VideoBean bean = getItem(position);
        if (bean != null) {
            holder.tvAdapterTitle.setText(bean.title);
            holder.tvVideoTitle.setText(bean.secondTitle);
            switch (bean.chapterId) {
                case 1:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon1);
                    break;
                case 2:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon2);
                    break;
                case 3:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon3);
                    break;
                case 4:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon4);
                    break;
                case 5:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon5);
                    break;
                case 6:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon6);
                    break;
                case 7:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon7);
                    break;
                case 8:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon8);
                    break;
                case 9:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon9);
                    break;
                case 10:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon10);
                    break;
                default:
                    holder.ivVideoIcon.setImageResource(R.drawable.video_play_icon1);
                    break;
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean == null) {
                        return;
                    }
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("videoPath", bean.videoPath);
                    context.startActivity(intent);
                }
            });
        }
    }

    protected class ViewHolder {
        private ImageView ivVideoIcon;
        private TextView tvAdapterTitle;
        private TextView tvVideoTitle;

        public ViewHolder(View view) {
            ivVideoIcon = (ImageView) view.findViewById(R.id.iv_video_icon);
            tvAdapterTitle = (TextView) view.findViewById(R.id.tv_adapter_title);
            tvVideoTitle = (TextView) view.findViewById(R.id.tv_video_title);
        }
    }
}