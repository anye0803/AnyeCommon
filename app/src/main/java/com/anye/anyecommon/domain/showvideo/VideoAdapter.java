package com.anye.anyecommon.domain.showvideo;

import android.content.Context;
import android.widget.ImageView;

import com.android.tedcoder.wkvideoplayer.model.Video;
import com.anye.anyecommon.R;
import com.anye.base.ui.widget.imageloader.ImageLoader;
import com.anye.base.ui.widget.imageloader.ImageLoaderUtil;
import com.anye.swiperefreshlibrary.adapter.CommonRecyclerViewAdapter;
import com.anye.swiperefreshlibrary.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anye on 2016/5/7.
 */
public class VideoAdapter extends CommonRecyclerViewAdapter<Video> {

    private List<Video> mdatas;
    private Context context;
    public VideoAdapter(Context context, List<Video> mdatas) {
        super(context, mdatas, R.layout.video_layout);
        this.context = context;
        this.mdatas = mdatas;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Video video) {
        holder.setText(R.id.tv_video_title, video.getVideoTitle());
        holder.setText(R.id.tv_video_desc, video.getVideoDesc());
        ImageView imageView =  holder.getView(R.id.iv_video);
        ImageLoader imageLoader = new ImageLoader.Builder().url(video.getVideoPhotoUrl()).imgView(imageView).build();
        ImageLoaderUtil.getInstance().loadImage(context, imageLoader);
    }

    public void addNewsListAndNofity(List<Video> newsList) {
        if (this.mdatas == null) {
            this.mdatas = new ArrayList<Video>();
        }
        this.mdatas.addAll(newsList);
        notifyDataSetChanged();
    }

    public void updateNewsListAndNotify(List<Video> newsList) {
        this.mdatas = newsList;
        notifyDataSetChanged();
    }
}
