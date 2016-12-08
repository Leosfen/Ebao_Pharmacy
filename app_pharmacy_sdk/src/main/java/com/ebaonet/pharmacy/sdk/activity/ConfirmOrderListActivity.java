package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.entity.order.CreateOrderEntry;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.DrugListAdapter;
import com.ebaonet.pharmacy.view.AutoListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 确认订单
 * Created by zhaojun.gao on 2016/9/13.
 */
public class ConfirmOrderListActivity extends BaseActivity {
    private AutoListView mListView;
    private List<CreateOrderEntry.DataBean.DrugBean> list;
    private LinearLayout layoutSearch;
    private TextView tvTitle, tvTotal;
    private ImageView imgMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_sortdetail_activity);
        imageLoader = ImageLoader.getInstance();
        CreateOrderEntry createOrderEntry = (CreateOrderEntry) getIntent().getSerializableExtra("createOrderEntry");
        if (createOrderEntry != null) {
            list = createOrderEntry.getData().getDrug();
            initView();
            // Logger.d("mylist:" + JsonUtil.toJSONString(list));
        }
    }


    private void initView() {
        findViewById(R.id.empty_view).setVisibility(View.GONE);
        mListView = (AutoListView) findViewById(R.id.search_listview);
        mListView.setLoadEnable(false);
        mListView.setHeaderVisible(false);
        layoutSearch = (LinearLayout) findViewById(R.id.layout_search);
        tvTitle = (TextView) findViewById(R.id.layout_title);
        imgMore = (ImageView) findViewById(R.id.moreImg);
        tvTotal = (TextView) findViewById(R.id.total_count);
        imgMore.setVisibility(View.GONE);
        layoutSearch.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTotal.setVisibility(View.VISIBLE);
        tvTotal.setText("共" + getTotal(list) + "件");
        tvTitle.setText("商品清单");
        mListView.setVisibility(View.VISIBLE);
        DrugListAdapter drugListAdapter = new DrugListAdapter(mContext);
        mListView.setAdapter(drugListAdapter);
        drugListAdapter.setAdaptData3(list, SortDetailActivity.CONFIRM_ORDER_LIST);
    }

    private int getTotal(List<CreateOrderEntry.DataBean.DrugBean> list) {
        int total = 0;
        for (CreateOrderEntry.DataBean.DrugBean bean : list) {
            total += bean.getDrugNum();
        }
        return total;
    }

}
