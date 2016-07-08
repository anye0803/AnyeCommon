package com.anye.swiperefreshlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anye.swiperefreshlibrary.adapter.call.MyItemClickListener;
import com.anye.swiperefreshlibrary.adapter.call.MyItemLongClickListener;

import java.util.List;

/**
 * recyclerView 公共adapter
 * Created by lwz on 2016/3/21.
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected Context context;
    protected List<T> mdatas;
    protected LayoutInflater minflater;
    protected int layoutid;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;

    public CommonRecyclerViewAdapter(Context context, List<T> mdatas, int layoutid) {
        this.context = context;
        this.mdatas = mdatas;
        this.minflater = LayoutInflater.from(context);
        this.layoutid = layoutid;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(layoutid, parent, false);
        return new RecyclerViewHolder(context, view, mListener, mLongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert(holder,mdatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }
    public abstract void convert(RecyclerViewHolder holder, T t);
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mLongClickListener = listener;
    }
}
