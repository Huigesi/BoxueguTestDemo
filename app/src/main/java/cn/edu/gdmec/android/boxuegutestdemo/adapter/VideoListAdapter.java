package cn.edu.gdmec.android.boxuegutestdemo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegutestdemo.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegutestdemo.R;

public class VideoListAdapter extends BaseAdapter {

    private List<VideoBean> objects = new ArrayList<VideoBean>();
    private int selectedPosition=-1;
    private OnSelectListener onSelectListener;


    private Context context;
    private LayoutInflater layoutInflater;


    public VideoListAdapter(Context context,OnSelectListener onSelectListener) {
        this.context = context;
        this.onSelectListener=onSelectListener;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void setSelectedPosition(int position){
        selectedPosition=position;
    }

    public void setData(List<VideoBean> vbl) {
        this.objects=vbl;
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
            convertView = layoutInflater.inflate(R.layout.video_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((VideoBean)getItem(position), (ViewHolder) convertView.getTag(),position,convertView);
        return convertView;
    }

    private void initializeViews(VideoBean objects, final ViewHolder holder, final int position, View convertView) {
       final VideoBean object =getItem(position);
        holder.iv_icon.setImageResource(R.drawable.course_bar_icon);
        holder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
        if (object!=null){
            holder.tvVideoTitle.setText(object.secondTitle);
            if (selectedPosition == position) {
                holder.iv_icon.setImageResource(R.drawable.course_intro_icon);
                holder.tvVideoTitle.setTextColor(Color.parseColor("#009958"));
            }else {
                holder.iv_icon.setImageResource(R.drawable.course_bar_icon);
                holder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object==null){
                    return;
                }
                onSelectListener.onSelect(position,holder.iv_icon);
            }
        });
        //TODO implement
    }

    protected class ViewHolder {
        private TextView tvVideoTitle;
        private ImageView iv_icon;
        public ViewHolder(View view) {
            tvVideoTitle = (TextView) view.findViewById(R.id.tv_video_title);
            iv_icon=(ImageView)view.findViewById(R.id.iv_left_icon);
        }
    }
    public interface OnSelectListener{
        void onSelect(int position, ImageView iv);
    }
}
