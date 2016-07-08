package com.anye.swiperefreshlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.anye.swiperefreshlibrary.adapter.call.MyItemClickListener;
import com.anye.swiperefreshlibrary.adapter.call.MyItemLongClickListener;

/**
 * RecyclerViewHolder
 * Created by lwz on 2016/3/21.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener {
    private SparseArray<View> mview;
    private View convertView;
    private Context con;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;

    public RecyclerViewHolder(Context context,View itemView,MyItemClickListener mListener,MyItemLongClickListener mLongClickListener) {
        super(itemView);
        this.convertView = itemView;
        this.con = context;
        this.mview = new SparseArray<View>();
        this.mListener = mListener;
        this.mLongClickListener = mLongClickListener;
        this.convertView.setOnClickListener(this);
        this.convertView.setOnLongClickListener(this);
    }
    /**
     * 点击监听
     */
    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,getPosition());
        }
    }
    /**
     * 长按监听
     */
    @Override
    public boolean onLongClick(View view) {
        if(mLongClickListener != null){
            mLongClickListener.onItemLongClick(view, getPosition());
        }
        return true;
    }


    /**
     * 通过 viewids 获得控件
     * @param viewids
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewids) {
        View view = mview.get(viewids);
        if (view == null) {
            view = convertView.findViewById(viewids);
            mview.put(viewids, view);
        }
        return (T) view;
    }
    /**
     * 封装 textview的值
     *
     * @param viewid
     * @param text
     * @return
     */
    public RecyclerViewHolder setText(int viewid, String text) {
        TextView tv = getView(viewid);
        tv.setText(text);
        return this;
    }

    /**
     * 是否显示
     * @param viewid
     * @param isShow
     * @return
     */
    public RecyclerViewHolder setVisibility(int viewid,boolean isShow) {
        View tv = getView(viewid);
        if (isShow) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }

        return this;
    }
}
