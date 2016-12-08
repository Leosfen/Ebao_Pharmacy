package com.ebaonet.pharmacy.sdk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.entity.drug.sort.level1.DrugLevelOneData;
import com.ebaonet.pharmacy.entity.drug.sort.level2.CateleveltwoInfo;
import com.ebaonet.pharmacy.entity.drug.sort.level2.DrugLevelTwoData;
import com.ebaonet.pharmacy.manager.DrugManager;
import com.ebaonet.pharmacy.manager.config.DrugConfig;
import com.ebaonet.pharmacy.manager.params.DrugParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.SortSearchActivity;
import com.ebaonet.pharmacy.sdk.activity.StartActivity;
import com.ebaonet.pharmacy.sdk.fragment.adapter.MyLeftlistviewAdapter;
import com.ebaonet.pharmacy.sdk.fragment.adapter.MyRightlistviewAdapter;
import com.ebaonet.pharmacy.util.ResourceUitls;
import com.ebaonet.pharmacy.view.AutoListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao.feng on 2016/8/11.
 */
public class ShoppingSortFragment extends BaseFragment implements
        AdapterView.OnItemClickListener, AutoListView.OnRefreshListener, AutoListView.OnLoadListener {
    private ListView mPharamcy_leftlistview;
    private MyLeftlistviewAdapter mMyLeftlistviewAdapter;
    private List<DrugLevelOneData.SonCateInfos> leftlist = new ArrayList<DrugLevelOneData.SonCateInfos>();
    private List<CateleveltwoInfo> rightlist = new ArrayList<CateleveltwoInfo>();
    private AutoListView mPharamcy_rightlistview;
    private MyRightlistviewAdapter mMyRightlistviewAdapter;
    private ImageButton mRightBtn;
    private DrugLevelOneData.SonCateInfos mSonCateInfos;
    private String mLeftId;//左边一级分类id
    private int sta;
    private List<CateleveltwoInfo> mResult2;
    private final static int PAGE_LENGTH = 3;//代表一次请求n条，不定
    private String mSonLength;

    private int mCurClickLeftItemPos = 0;//当前点击左边一级分类的位置
    private ImageButton mPharmacy_leftBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(ResourceUitls.getLayoutId(mContext, "pharmacy_fragment_sort"), container, false);
//            initImageLoader(getActivity());//初始化imageloader
            initView();
        }
        return mView;
    }

    @Override
    protected void lazyLoad() {
        initDataLeft();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 加载左侧数据
     */
    private void initDataLeft() {
        //测试接口
        DrugManager.getInstance().getCatelevelone();
    }

    /**
     * 加载右侧数据
     *
     * @param start 第一次进入刷新：0   上拉加载：不定
     */
    private void initDataRight(String cateId, int start, int state) {
        sta = state;
        RequestParams params = DrugParamsHelper.getCateleveltwo(cateId, "" + start, PAGE_LENGTH + "");
        DrugManager.getInstance().getCateleveltwo(params);
    }

    private void initView() {
        mPharmacy_leftBtn = (ImageButton) mView.findViewById(R.id.pharmacy_leftBtn);
        mPharmacy_leftBtn.setVisibility(View.GONE);
        mView.findViewById(R.id.pharmacy_leftBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StartActivity) mContext).pageToFragmentByPosition(0);
            }
        });
        TextView title = (TextView) mView.findViewById(ResourceUitls.getId(mContext, "tv_title"));
        title.setText("分类");
        mRightBtn = (ImageButton) mView.findViewById(R.id.rightBtn);
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setImageResource(R.drawable.pharmacy_search);
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SortSearchActivity.class);
                startActivity(intent);
            }
        });
        mPharamcy_leftlistview = (ListView) mView.findViewById(R.id.pharamcy_leftlistview);
        mPharamcy_rightlistview = (AutoListView) mView.findViewById(R.id.pharamcy_rightlistview);
        mPharamcy_rightlistview.setOnRefreshListener(this);//删
        mPharamcy_rightlistview.setOnLoadListener(this);
        mMyRightlistviewAdapter = new MyRightlistviewAdapter(mContext);
        mPharamcy_rightlistview.setAdapter(mMyRightlistviewAdapter);

        mMyLeftlistviewAdapter = new MyLeftlistviewAdapter(mContext);
        mPharamcy_leftlistview.setAdapter(mMyLeftlistviewAdapter);
        mPharamcy_rightlistview.setPageSize(PAGE_LENGTH);
    }

    @Override
    public void onLoad() {
        if (rightlist != null && rightlist.size() > 0) {
            initDataRight(mLeftId, rightlist.size(), AutoListView.LOAD);
        } else {
            mPharamcy_rightlistview.onLoadComplete();
        }
    }

    @Override
    public void onRefresh() {
        if (mPharamcy_rightlistview != null) {
            mPharamcy_rightlistview.onRefreshComplete();
        }
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (DrugConfig.GETCATELEVELONE.equals(tag)) {
            if (code == 1) {
                List<DrugLevelOneData.SonCateInfos> result = ((DrugLevelOneData) obj).getData();//返回的是集合
                if (!(result.isEmpty())) {
                    leftlist.clear();
                    leftlist.addAll(result);
                    mMyLeftlistviewAdapter.setDefault(leftlist);
                    //设置条目点击事件
                    mPharamcy_leftlistview.setOnItemClickListener(this);
                    mSonCateInfos = leftlist.get(0);
                    mSonCateInfos.setIsChecked(true);
                    mLeftId = mSonCateInfos.getCateId();
                    mSonLength = mSonCateInfos.getSonLength();
//                    try {
//                        int len = Integer.valueOf(mSonLength);
//                        mPharamcy_rightlistview.setPageSize(len);
//                    } catch (Exception e) {
//                    }
                    initDataRight(mLeftId, 0, AutoListView.REFRESH);//请求二级分类（第一个条目对应的右侧数据）

                } else {//左侧数据为空

                }
            }
        }
        if (DrugConfig.GETCATELEVELTWO.equals(tag)) {
            if (code == 1) {
                mResult2 = ((DrugLevelTwoData) obj).getData();
                switch (sta) {
                    case AutoListView.REFRESH:
                        mPharamcy_rightlistview.onRefreshComplete();
                        rightlist.clear();
                        if (mResult2 != null) {
                            rightlist.addAll(mResult2);
                        }
                        break;
                    case AutoListView.LOAD:
                        mPharamcy_rightlistview.onLoadComplete();
                        if (rightlist != null)
                            rightlist.addAll(mResult2);
                        break;
                }
                if (mResult2 != null && mResult2.size() > 0) {
                    mPharamcy_rightlistview.setResultSize(mResult2.size());
                } else {
                    mPharamcy_rightlistview.setResultSize(0);
                }
                mMyRightlistviewAdapter.setDefault(rightlist);
                /*if (!(result2.isEmpty())){
                    rightlist.clear();
                    rightlist.addAll(result2);
                    mMyRightlistviewAdapter.setDefault(rightlist);
                }else{//二级列表返回数据为空
                    
                }*/

            }
        }

    }

    /**
     * 左侧list条目点击
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCurClickLeftItemPos != position) {
            mCurClickLeftItemPos = position;
            for (int i = 0; i < leftlist.size(); i++) {
                leftlist.get(i).setIsChecked(false);
            }
            if (leftlist.get(position).isChecked()) {
                leftlist.get(position).setIsChecked(false);
            } else {
                leftlist.get(position).setIsChecked(true);
            }
            mMyLeftlistviewAdapter.notifyDataSetChanged();
//        initDataRight(leftlist.get(position).getCateId(), 0, leftlist.get(position).getSonLength());
//        mSonLength = leftlist.get(position).getSonLength();
            mLeftId = leftlist.get(position).getCateId();
            initDataRight(mLeftId, 0, AutoListView.REFRESH);
        }
    }

}
