package com.anye.test.adapter;

import android.content.Context;
import android.widget.TextView;

import com.anye.swiperefreshlibrary.adapter.CommonRecyclerViewAdapter;
import com.anye.swiperefreshlibrary.adapter.RecyclerViewHolder;
import com.anye.test.R;
import com.anye.test.mvp.model.GoodsInfoBean;

import java.util.List;

/**
 * Created by lwz on 2016/6/24.
 */

public class GoodsInfoAdapter extends CommonRecyclerViewAdapter<GoodsInfoBean> {

    private Context context;
    private List<GoodsInfoBean> newsList;
    public GoodsInfoAdapter(Context context, List<GoodsInfoBean> mdatas, int layoutid) {
        super(context, mdatas, layoutid);
        this.context = context;
        this.newsList = mdatas;
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodsInfoBean goodsListBean) {
        holder.setText(R.id.item_num1, goodsListBean.getName());
        holder.setText(R.id.item_num2, goodsListBean.getSn());
        holder.setText(R.id.item_num3, goodsListBean.getOutnum());
        holder.setText(R.id.item_num4, goodsListBean.getUnit_id());
        TextView textView = holder.getView(R.id.item_goods_info);
        textView.setText(goodsListBean.getDate() + " " + goodsListBean.getTime());
    }
//
//    public void addNewsListAndNofity(List<GoodsInfoBean> newsList) {
//        if (this.newsList == null) {
//            this.newsList = new ArrayList<GoodsInfoBean>();
//        }
//        this.newsList.addAll(newsList);
//        notifyDataSetChanged();
//    }
//
//    public void updateNewsListAndNotify(List<GoodsInfoBean> newsList) {
//        this.newsList = newsList;
//        notifyDataSetChanged();
//    }

}
