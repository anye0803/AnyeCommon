package com.anye.anyecommon.domain.showgril;

import android.content.Context;

import com.anye.anyecommon.R;
import com.anye.anyecommon.model.entily.Girl;
import com.anye.base.ui.widget.RatioImageview;
import com.anye.base.ui.widget.imageloader.ImageLoader;
import com.anye.base.ui.widget.imageloader.ImageLoaderUtil;
import com.anye.swiperefreshlibrary.adapter.CommonRecyclerViewAdapter;
import com.anye.swiperefreshlibrary.adapter.RecyclerViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

/**
 * Created by CoXier on 2016/3/20.
 */
public class GirlAdapter extends CommonRecyclerViewAdapter<Girl> {
    private Context context;
    public GirlAdapter(Context context, List<Girl> mdatas, int layoutid) {
        super(context, mdatas, layoutid);
        this.context = context;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Girl girl) {
        RatioImageview imageView = holder.getView(R.id.iv_girl);
        Random random = new Random();
        int originalHeight = random.nextInt(10) + 50;
        imageView.setOriginalSize(50, originalHeight);
        ImageLoader imageLoader = new ImageLoader.Builder().url(girl.getUrl()).imgView(imageView).build();
        ImageLoaderUtil.getInstance().loadImage(context, imageLoader);
//        Glide.with(context)
//                .load(girl.getUrl())
//                .centerCrop()
//                .into(imageView);
        holder.setText(R.id.tv_date, girl.getDesc());
    }


}