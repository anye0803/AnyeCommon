package com.anye.anyecommon.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anye.anyecommon.R;
import com.anye.anyecommon.model.NewsBean;
import com.anye.base.util.LogUtil;
import com.anye.swiperefreshlibrary.adapter.CommonRecyclerViewAdapter;
import com.anye.swiperefreshlibrary.adapter.RecyclerViewHolder;
import com.anye.swiperefreshlibrary.widget.KRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lwz on 2016/6/23.
 */

public class NewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.news_list)
    KRecyclerView krecyclerView;
    @Bind(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;

    private NewsBean newsBean;
    private CommonRecyclerViewAdapter<NewsBean> mAdapter;
    private List<NewsBean> mList = new ArrayList<>();

    private int maxScore = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
        ButterKnife.bind(NewsActivity.this);
        initUI();
        initAdapter();
        respouseNews();
    }

    private void initUI() {
        // 设置Adapter
        krecyclerView.setLoadDataLintener(new KRecyclerView.LoadDataListener() {
            @Override
            public void loadData(int page) {
                LogUtil.e(page+"");
                maxScore ++;
                respouseNews();

            }
        });
        //make KRecyclerView know how many items in a page
//        krecyclerView.setItemCount(10);
//        krecyclerView.mPtrFrameLayout.setEnabled(false);
        krecyclerView.enableLoadMore();

        // 设置手势滑动监听器
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 设置刷新时动画的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_primary);
    }

    private void respouseNews() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("libraryID", "12");
//        map.put("pageSize", 5);
//        map.put("page", maxScore);
//        CommonRequest.setNetRequest(NewsActivity.this, "http://123.57.13.118/warehouse/queryWareGoods.action", map, new NetModelCallback() {
//            @Override
//            public void onSuccess(String response) {
//                try {
//                    JSONObject jo = new JSONObject(response);
//                    String respResult = jo.getString("RespResult");
//                    String errorMsg = jo.getString("ErrorMsg");
//                    if (respResult.equalsIgnoreCase("0")) {
//                        Gson gson = new Gson();
//                        JSONArray ja = jo.getJSONArray("data");
//                        for (int i = 0; i < ja.length(); i++) {
//                            JSONObject jo2 = ja.getJSONObject(i);
//                            newsBean = gson.fromJson(jo2.toString(), NewsBean.class);
//                            mList.add(newsBean);
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    } else {
//                        ToastUtils.getInstance().showToast(errorMsg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }


    private void initAdapter() {
        mAdapter = new CommonRecyclerViewAdapter<NewsBean>(NewsActivity.this, mList, R.layout.item_goods) {
            @Override
            public void convert(RecyclerViewHolder holder, NewsBean goodsListBean) {
                holder.setText(R.id.item_num1, goodsListBean.getName());
                holder.setText(R.id.item_num2, goodsListBean.getSn());
                holder.setText(R.id.item_num3, goodsListBean.getOutnum());
                holder.setText(R.id.item_num4, goodsListBean.getUnit_id());
                TextView textView = holder.getView(R.id.item_goods_info);
                textView.setText(goodsListBean.getDate() + " " + goodsListBean.getTime());
            }
        };
        krecyclerView.setAdapter(mAdapter,1, LinearLayout.VERTICAL);

    }

    @Override
    public void onRefresh() {
        maxScore = 0;
        mList.clear();
        respouseNews();
        mSwipeRefreshLayout.setRefreshing(false);
        LogUtil.e("onRefresh");
    }
}
