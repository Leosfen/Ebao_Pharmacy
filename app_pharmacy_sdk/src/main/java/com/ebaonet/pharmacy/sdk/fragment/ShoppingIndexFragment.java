package com.ebaonet.pharmacy.sdk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.entity.index.ADEntity;
import com.ebaonet.pharmacy.entity.index.IndexInfoEntity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.IndexManager;
import com.ebaonet.pharmacy.manager.config.IndexConfig;
import com.ebaonet.pharmacy.manager.params.ShoppIndexParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.DrugInfoActivity;
import com.ebaonet.pharmacy.sdk.activity.SortDetailActivity;
import com.ebaonet.pharmacy.sdk.activity.SortSearchActivity;
import com.ebaonet.pharmacy.sdk.fragment.index.MyShowAdapter;
import com.ebaonet.pharmacy.sdk.fragment.index.ShowConfig;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.ResourceUitls;
import com.ebaonet.pharmacy.util.ScreenUtil;
import com.ebaonet.pharmacy.view.EditTextWithDelete;
import com.ebaonet.pharmacy.view.NoScrollGridView;
import com.ebaonet.pharmacy.view.RefreshScrollView;
import com.ebaonet.pharmacy.view.TextViewAd;
import com.ebaonet.pharmacy.view.infiniteviewpager.indicator.CirclePageIndicator;
import com.ebaonet.pharmacy.view.infiniteviewpager.myadapter.CarryPagerAdapter;
import com.ebaonet.pharmacy.view.infiniteviewpager.viewpager.InfiniteViewPager;
import com.ebaonet.pharmacy.web.EbaoWebViewActivity;
import com.ebaonet.pharmacy.zxing.CaptureActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.ebaonet.pharmacy.sdk.R.id.top_btn;

/**
 * Created by yao.feng on 2016/8/11.
 */
public class ShoppingIndexFragment extends BaseFragment implements RefreshScrollView.OnRefreshScrollViewListener,
        RefreshScrollView.OnScrollListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private View mContentView;
    private NoScrollGridView mMiddle_gridview;
    private LinearLayout mTopbar;
    private Button mTop_btn;
    private RefreshScrollView refreshScrollview;
    private InfiniteViewPager mViewPager2;
    private CirclePageIndicator mCircleIndicator;
    private CarryPagerAdapter pagerAdapter2;
    private static final int AUTO_SCROLL_DELAY_TIME = 3000;
    private List<IndexInfoEntity.DataBean.RollImageConfigBean> mPagesList;
    private final static String TAG = "IndexFragment";
    private NoScrollGridView mTop_gridview;
    private NoScrollGridView mMiddle2_gridview;
    private ImageView mImg_discount;
    private NoScrollGridView mBottom_gridview;
    private MyShowAdapter myTopShowAdapter;
    private MyShowAdapter mMyShowAdapterSp;
    private int scrollY = 0;// 标记上次滑动位置
    private EditTextWithDelete mSearchEt;
    private TextViewAd mTv_ad;
    private List<ADEntity> mList;
    private List<IndexInfoEntity.DataBean.RecommendConfigBean> mRecommendConfigList;
    private MyShowAdapter mMyShowAdapterRe;
    private ImageView mImg_discount1;
    private LinearLayout mLl_content_item;
    private View mContent;
    private List<IndexInfoEntity.DataBean.ClassesConfigBean> mClassesConfigList;
    private List<IndexInfoEntity.DataBean.SpecailConfigBean> mSpecailConfigList;
    private ImageLoader mImageLoader;
    private List<IndexInfoEntity.DataBean.GundongConfigBean> mGundongConfigList;
    public static final int SEARCH_CLASS = 1;
    public static final int SEARCH_SPECAIL = 2;
    public static final int SEARCH_SALES = 3;
    private List<IndexInfoEntity.DataBean.SalesConfigBean> mSalesConfigList;
    private LinearLayout mLl_recommond;
    private LinearLayout mLl_search;
    private ImageView mImg_scan;
    private LinearLayout mFirstContent, mBlankContent;
    private LinearLayout mLl_roll_vertical;
    private ImageButton mPharmacy_leftBtn;

    @Override
    public void onRefresh() {
        refreshScrollview.stopRefresh();
        getHeaderImage();
    }

    private void getHeaderImage() { // 从网络获取首页headerImage
        initData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.top_gridview) {
            Intent intent = new Intent(mContext, SortDetailActivity.class);
            String jumpType = mClassesConfigList.get(position).getJumpType();
            if ("1".equals(jumpType) || "4".equals(jumpType)) {//列表
                intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.INDEX_CLASS);
                intent.putExtra("configid", mClassesConfigList.get(position).getConfigId());
                intent.putExtra("name", mClassesConfigList.get(position).getName());
                startActivity(intent);
            } else if ("2".equals(jumpType)) {
                intent = new Intent(mContext, EbaoWebViewActivity.class);
                intent.putExtra(EbaoWebViewActivity.ExtraWebViewURL, mClassesConfigList.get(position).getJumpTypeVal());
                mContext.startActivity(intent);
            } else if ("3".equals(jumpType)) {
                intent = new Intent(mContext, DrugInfoActivity.class);
                intent.putExtra("fromSortDetail", "yes");
                intent.putExtra("drugid", mClassesConfigList.get(position).getJumpTypeVal());
                mContext.startActivity(intent);
            }

        } else if (parent.getId() == R.id.middle_gridview) {//专题
            Intent intent = new Intent(mContext, SortDetailActivity.class);
            String jumpType = mSpecailConfigList.get(position).getJumpType();
            if ("1".equals(jumpType) || "4".equals(jumpType)) {//列表
                intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.INDEX_SPECIAL);
                intent.putExtra("configid", mSpecailConfigList.get(position).getConfigId());
                intent.putExtra("name", mSpecailConfigList.get(position).getName());
                startActivity(intent);
            } else if ("2".equals(jumpType)) {
                intent = new Intent(mContext, EbaoWebViewActivity.class);
                intent.putExtra(EbaoWebViewActivity.ExtraWebViewURL, mSpecailConfigList.get(position).getJumpTypeVal());
                mContext.startActivity(intent);
            } else if ("3".equals(jumpType)) {
                intent = new Intent(mContext, DrugInfoActivity.class);
                intent.putExtra("drugid", mSpecailConfigList.get(position).getJumpTypeVal());
                mContext.startActivity(intent);
            }

        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == top_btn) {
            refreshScrollview.post(new Runnable() {
                @Override
                public void run() {
                    refreshScrollview.fullScroll(ScrollView.FOCUS_UP);  //该方法不能直接被调用
                }
            });
            mTop_btn.setVisibility(View.GONE);
            mTopbar.setBackgroundColor(this.getResources().getColor(android.R.color.transparent));
        } else if (i == R.id.img_scan) {
            Intent intent = new Intent(mContext, CaptureActivity.class);
            startActivity(intent);
        } else if (i == R.id.pharmacy_leftBtn) {
            getActivity().finish();
        }
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY > 0) {
            mTopbar.setBackgroundColor(this.getResources().getColor(R.color.white));
        } else {
            mTopbar.setBackgroundColor(this.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(ResourceUitls.getLayoutId(mContext, "pharmacy_fragment_index"), container, false);
            mContentView = inflater.inflate(R.layout.pharmacy_fragment_index_content, null);
            initView();
        }
        return mView;
    }

    @Override
    protected void lazyLoad() {
        initData();
    }

    private void initView() {
        mPharmacy_leftBtn = (ImageButton) mView.findViewById(R.id.pharmacy_leftBtn);
        mPharmacy_leftBtn.setOnClickListener(this);
        refreshScrollview = (RefreshScrollView) mView.findViewById(R.id.pharmacy_header_scrollView);
        mTopbar = (LinearLayout) mView.findViewById(R.id.topbar);
        mLl_search = (LinearLayout) mView.findViewById(R.id.ll_search);
        mSearchEt = (EditTextWithDelete) mView.findViewById(R.id.searchEt);
        mSearchEt.setFocusable(false);
        mSearchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SortSearchActivity.class);
                startActivity(intent);
            }
        });
        mTop_btn = (Button) mView.findViewById(R.id.top_btn);//置顶按钮
        mTop_btn.setOnClickListener(this);
        mImg_scan = (ImageView) mView.findViewById(R.id.img_scan);
        mImg_scan.setOnClickListener(this);
        refreshScrollview.setupContainer(mContext, mContentView);
        refreshScrollview.setOnRefreshScrollViewListener(this);
        refreshScrollview.setOnScrollListener(this);
        refreshScrollview.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {//未移动
                            handleStop(scroller);
                        } else {//移动
                            handler.sendMessageDelayed(handler.obtainMessage(
                                    touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();//获取移动的距离
                        }
                    }
                }
            };

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }

            /**
             * ScrollView 停止
             *
             * @param view
             */
            private void handleStop(Object view) {

                Log.i(TAG, "handleStop");
                ScrollView scroller = (ScrollView) view;
                scrollY = scroller.getScrollY();

                doOnBorderListener();
            }
        });
        mViewPager2 = (InfiniteViewPager) mView.findViewById(R.id.viewpager2);
        mCircleIndicator = (CirclePageIndicator) mView.findViewById(R.id.indicator2);
        mLl_roll_vertical = (LinearLayout) mView.findViewById(R.id.ll_roll_vertical);
        mTv_ad = (TextViewAd) mView.findViewById(R.id.tv_ad);
        mLl_recommond = (LinearLayout) mView.findViewById(R.id.ll_recommond);
        //所有gridview及对应适配器
        initAdapter();
        initAutoScrollView();

        mBlankContent = (LinearLayout) mContentView.findViewById(R.id.index_content_last);
        mFirstContent = (LinearLayout) mContentView.findViewById(R.id.index_content_first);
    }

    private void initAdapter() {
        /**分类区*/
        mTop_gridview = (NoScrollGridView) mView.findViewById(R.id.top_gridview);//第一个
        mTop_gridview.setOnItemClickListener(this);
        myTopShowAdapter = new MyShowAdapter(mContext, ShowConfig.INDEX_TOP);
        mTop_gridview.setAdapter(myTopShowAdapter);
        /**专题区*/
        mMiddle_gridview = (NoScrollGridView) mView.findViewById(R.id.middle_gridview);//中间第一个
        mMiddle_gridview.setOnItemClickListener(this);
        mMyShowAdapterSp = new MyShowAdapter(mContext, ShowConfig.INDEX_MIDDLE);
        mMiddle_gridview.setAdapter(mMyShowAdapterSp);
        /**促销商品*/
        mLl_content_item = (LinearLayout) mView.findViewById(R.id.ll_content_item);
        /**推荐药品*/
        mBottom_gridview = (NoScrollGridView) mView.findViewById(R.id.recommend_gridview);//推荐药品gridview
        mBottom_gridview.setOnItemClickListener(this);
        mMyShowAdapterRe = new MyShowAdapter(mContext, ShowConfig.INDEX_BOTTOM);
        mBottom_gridview.setAdapter(mMyShowAdapterRe);
    }

    private void initAutoScrollView() {
        pagerAdapter2 = new CarryPagerAdapter(mContext);
        mViewPager2.setAdapter(pagerAdapter2);
        mViewPager2.setAutoScrollTime(AUTO_SCROLL_DELAY_TIME);
//        mViewPager2.startAutoScroll();
        mCircleIndicator.setViewPager(mViewPager2);
    }

    private void initData() {
        RequestParams params = ShoppIndexParamsHelper.getIndexInfoParams("2", "1.0");
        IndexManager.getInstance().getShoppingIndexInfo(params);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "index.onResume");
        mViewPager2.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "index.onPause");
        mViewPager2.stopAutoScroll();
    }

    private void doOnBorderListener() {
        Log.i(TAG, ScreenUtil.getScreenViewBottomHeight(refreshScrollview) + "  "
                + refreshScrollview.getScrollY() + " " + ScreenUtil
                .getScreenHeight(mContext));

        // 底部判断
        if (mContentView != null
                && mContentView.getMeasuredHeight() <= refreshScrollview.getScrollY()
                + refreshScrollview.getHeight()) {
            mTop_btn.setVisibility(View.VISIBLE);
            Log.i(TAG, "bottom");
        }
        // 顶部判断
        else if (refreshScrollview.getScrollY() == 0) {
            Log.i(TAG, "top");
            mTop_btn.setVisibility(View.GONE);
        } else if (refreshScrollview.getScrollY() > 30) {
            mTop_btn.setVisibility(View.VISIBLE);
            Log.i(TAG, "test");
        }

    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (IndexConfig.GETINDEXINFO.equals(tag)) {
            refreshScrollview.stopRefresh();
            if (code == 1) {
                final IndexInfoEntity.DataBean data = ((IndexInfoEntity) obj).getData();
                if (data != null) {
                    //轮播图
                    mPagesList = data.getRollImageConfigList();
                    pagerAdapter2 = new CarryPagerAdapter(mContext);
                    mViewPager2.setAdapter(pagerAdapter2);
                    pagerAdapter2.setDataList(mPagesList);
                    if (mPagesList.size() > 1) {
                        mViewPager2.startAutoScroll(AUTO_SCROLL_DELAY_TIME);
                    } else {
                        mViewPager2.stopAutoScroll();
                    }
                    //类别
                    mClassesConfigList = data.getClassesConfigList();
                    if (mClassesConfigList != null && mClassesConfigList.size() > 0) {
                        myTopShowAdapter.setClassesList(mClassesConfigList, ShowConfig.INDEX_TOP);
                    }
                    //专题区
                    mSpecailConfigList = data.getSpecailConfigList();
                    if (mSpecailConfigList != null && mSpecailConfigList.size() > 0) {
                        mMyShowAdapterSp.setSpecialList(mSpecailConfigList, ShowConfig.INDEX_MIDDLE);
                    }
                    //滚动图
                    mGundongConfigList = data.getGundongConfigList();
                    if (mGundongConfigList != null && mGundongConfigList.size() > 0) {
                        mLl_roll_vertical.setVisibility(View.VISIBLE);
                        mTv_ad.setmTexts(mGundongConfigList);
                        mTv_ad.setBackColor(getResources().getColor(R.color.textcolor_a4a1a1));
                        mTv_ad.setmDuration(10);
                        mTv_ad.setmInterval(1000);
                        mTv_ad.setOnClickLitener(new TextViewAd.onClickLitener() {
                            @Override
                            public void onClick(Object obj) {
                                IndexInfoEntity.DataBean.GundongConfigBean gundongBean = (IndexInfoEntity.DataBean.GundongConfigBean) obj;
                                String mJumpType = gundongBean.getJumpType();
                                if ("1".equals(mJumpType) || "4".equals(mJumpType)) {//列表
                                    Intent intent = new Intent(mContext, SortDetailActivity.class);
                                    intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.INDEX_ROLL);
                                    intent.putExtra("name", gundongBean.getName());
                                    intent.putExtra("configid", gundongBean.getConfigId());
                                    mContext.startActivity(intent);
                                } else if ("2".equals(mJumpType)) {//h5页面
                                    Intent mIntent1 = new Intent(mContext, EbaoWebViewActivity.class);
                                    mIntent1.putExtra(EbaoWebViewActivity.ExtraWebViewURL, gundongBean.getJumpTypeVal());
                                    mContext.startActivity(mIntent1);
                                } else if ("3".equals(mJumpType)) {//药品详情
                                    Intent mIntent1 = new Intent(mContext, DrugInfoActivity.class);
                                    mIntent1.putExtra("drugid", gundongBean.getJumpTypeVal());
                                    mContext.startActivity(mIntent1);
                                }
                            }
                        });
                    } else {
                        mLl_roll_vertical.setVisibility(View.GONE);
                    }
                    //推荐药品区
                    mRecommendConfigList = data.getRecommendConfigList();
                    if (mRecommendConfigList != null && mRecommendConfigList.size() > 0) {
                        mLl_recommond.setVisibility(View.VISIBLE);
                        mMyShowAdapterRe.setRecommendList(mRecommendConfigList, ShowConfig.INDEX_BOTTOM);
                    } else {
                        mLl_recommond.setVisibility(View.GONE);
                    }
                    mImageLoader = ImageLoader.getInstance();
                    //促销区 
                    mSalesConfigList = data.getSalesConfigList();
                    mLl_content_item.removeAllViews();
                    MyShowAdapter mMyShowAdapterSa1;
                    if (mSalesConfigList != null && mSalesConfigList.size() > 0) {
                        final int size = mSalesConfigList.size();
                        for (int i = 0; i < size; i++) {
                            mContent = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_index_item, null);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            mLl_content_item.addView(mContent, params);
                            Log.e("getChildCount", "====================" + mLl_content_item.getChildCount());
                            final IndexInfoEntity.DataBean.SalesConfigBean salesConfigBean = mSalesConfigList.get(i);
                            final String imagePath = salesConfigBean.getImagePath();//促销大图片
                            List<IndexInfoEntity.DataBean.SalesConfigBean.DrugDsBean> drugDsList = salesConfigBean.getDrugDsList();//促销商品
                            mMyShowAdapterSa1 = new MyShowAdapter(mContext, ShowConfig.INDEX_SALE);
                            mMiddle2_gridview = (NoScrollGridView) mContent.findViewById(R.id.middle2_gridview);
                            mMiddle2_gridview.setOnItemClickListener(this);
                            mImg_discount1 = (ImageView) mContent.findViewById(R.id.img_discount);
                            mImg_discount1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, SortDetailActivity.class);
                                    intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.INDEX_DISCOUNT);
                                    intent.putExtra("prSowId", salesConfigBean.getPrSowId());
                                    startActivity(intent);
                                }
                            });
                            mMiddle2_gridview.setAdapter(mMyShowAdapterSa1);
                            if (drugDsList != null && drugDsList.size() > 0) {
                                if (drugDsList.size() < 3) {
                                    mLl_content_item.setVisibility(View.GONE);
                                    mImg_discount1.setVisibility(View.GONE);
                                } else {
                                    mLl_content_item.setVisibility(View.VISIBLE);
                                    mImg_discount1.setVisibility(View.VISIBLE);
                                    mImageLoader.displayImage(imagePath, mImg_discount1, ImageDisOpt.getDefaultImgDisOpt());
                                    mMyShowAdapterSa1.setSaleList(drugDsList, ShowConfig.INDEX_SALE);

                                }
                            } else {
                                mLl_content_item.setVisibility(View.GONE);
                            }

                        }
                    }


                }

            }

            setBlankContentHeight();
        }
    }

    private int mAllContentHeight = 0;
    private int mFirstContentHeight = 0;

    private void setBlankContentHeight() {
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAllContentHeight = mView.getMeasuredHeight();
                Logger.d("...mView..height...." + mAllContentHeight);
                mView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mFirstContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = mFirstContent.getMeasuredHeight();
                Logger.d("...mFirstContent..height...." + height);
                if (mFirstContentHeight == height) {
                    mView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                    int mLastHei = mAllContentHeight - mFirstContentHeight;
                    if (mLastHei >= 0) {
                        LinearLayout.LayoutParams mLlp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, mLastHei + 10);
                        mBlankContent.setLayoutParams(mLlp);
                    }
                } else {
                    mFirstContentHeight = height;
                }
            }
        });
    }
}
