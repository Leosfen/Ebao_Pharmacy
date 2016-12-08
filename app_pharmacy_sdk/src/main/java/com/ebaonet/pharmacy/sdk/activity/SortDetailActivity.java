package com.ebaonet.pharmacy.sdk.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.drug.sort.DrugSearchEntity;
import com.ebaonet.pharmacy.entity.drug.sort.filter.BrandInfo;
import com.ebaonet.pharmacy.entity.drug.sort.filter.DrugFilterCondition;
import com.ebaonet.pharmacy.entity.drug.sort.filter.DrugFormInfo;
import com.ebaonet.pharmacy.entity.drug.sort.filter.FilterCondition;
import com.ebaonet.pharmacy.entity.drug.sort.filter.TypeInfo;
import com.ebaonet.pharmacy.entity.drug.sort.level2.CateleveltwoInfo;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugLevelThreeList;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugThreeListInfo;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugTotalQuantity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.DrugManager;
import com.ebaonet.pharmacy.manager.ShoppingCarManager;
import com.ebaonet.pharmacy.manager.config.DrugConfig;
import com.ebaonet.pharmacy.manager.params.DrugParamsHelper;
import com.ebaonet.pharmacy.manager.params.ShoppingCarParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.DrugListAdapter;
import com.ebaonet.pharmacy.sdk.fragment.search.SearchFragment;
import com.ebaonet.pharmacy.util.SoftInputUtils;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.AutoListView;
import com.ebaonet.pharmacy.view.EditTextWithDelete;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;
import com.ebaonet.pharmacy.view.ShowToast;
import com.ebaonet.pharmacy.view.filter.FilterDoubleListView;
import com.ebaonet.pharmacy.view.filter.FilterSingleListView;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterDoubleItem;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterSingleListItem;
import com.ebaonet.pharmacy.view.filter.obj.DoubleFilterObj;
import com.ebaonet.pharmacy.view.filter.obj.FilterParams;
import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;
import com.ebaonet.pharmacy.view.tabview.SuperExpandTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要判断是搜索关键字页面（SortSearchActivity）页面跳转过来/三级列表条目跳转过来
 * 分类搜索页面（筛选列表显示）
 * Created by zhaojun.gao on 2016/8/17.
 */
public class SortDetailActivity extends BaseActivity implements AutoListView.OnLoadListener, AutoListView.OnRefreshListener,
        ViewTreeObserver.OnGlobalLayoutListener, OnClickFilterSingleListItem, OnClickFilterDoubleItem, SearchFragment.OnSearchActionListener {

    public static final String THIRD_LEVEL_OBJ = "cate";
    public static final String KEY_WORD = "key_word";
    public static final String FROM_WHERE = "from";
    public static int INDEX_ROLLIMAGE = 9;//点击首页轮播图某条目
    public static int INDEX_ROLL = 8;//点击首页滚动图某条目
    public static int INDEX_SPECIAL = 7;//点击首页促销某条目
    public static int INDEX_DISCOUNT = 5;//点击首页促销某条目
    public static int INDEX_RECOMMEND = 4;//点击首页推荐某条目
    public static int INDEX_CLASS = 6;//点击首页分类某条目
    public static int TWO_LEVEL_SORT = 1;//三级分类图片进入
    public static int SORT_SEARCH_ACTIVITY = 2;//搜索历史记录界面点击进入
    public static int CONFIRM_ORDER_LIST = 3;//确认订单页面点击图片进入（药品展示列表界面）
    private EditTextWithDelete searchEt;
    private ImageView mMoreImg;
    private AutoListView mSearch_listview;
    private View mEmptyView, mContentListView;
    private DrugListAdapter mDrugListAdapter;

    private SuperExpandTabView mExpTabView;

    private ArrayList<String> mBigTitles = new ArrayList<String>();
    private ArrayList<View> mSpinners = new ArrayList<View>();

    private FilterSingleListView mSingleFilter;
    private FilterDoubleListView mDoubleFilter;
    private LinearLayout mContentLayout;

    private int mFromWhere = -1;
    private CateleveltwoInfo.SonCateInfos mCtwoInfo;
    private String mSearchKey;

    private static final int PAGE_SIZE = 10;
    public FilterParams mFilterParams = new FilterParams();
    private int refreshState;
    private FrameLayout mFragmentLayout;

    private boolean isShowKeyBoard = false;
    private int mFragmentLayoutHeight;
    private SearchFragment mSearchFragment;
    private RightTopActionPopWin popupWindow;
    private int searchtype;
    private LinearLayout lySerach;
    private TextView lyTitle;
    private TextView itemName;
    private String configid = "";
    private String promotionId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_sortdetail_activity);
        getIntentData();
//        searchtype = getIntent().getIntExtra("searchtype", 0);
        initView();
        initExpandTabView();
        initData(1, AutoListView.REFRESH);
    }

    private void showSearchFragment() {
        FragmentTransaction mFt = getSupportFragmentManager().beginTransaction();
        if (mSearchFragment == null) {
            mSearchFragment = new SearchFragment();
        }
        if (!mSearchFragment.isAdded()) {
            mFt.add(R.id.search_fragment_layout, mSearchFragment);
        }
        mFt.show(mSearchFragment);
        mFt.commit();
    }

    private void hideSearchFragment() {
        FragmentTransaction mFt = getSupportFragmentManager().beginTransaction();
        if (mSearchFragment == null) {
            mSearchFragment = new SearchFragment();
        }
        if (!mSearchFragment.isAdded()) {
            mFt.add(R.id.search_fragment_layout, mSearchFragment);
        }
        mFt.hide(mSearchFragment);
        mFt.commit();
    }

    private void getIntentData() {
        Intent mGetIntent = getIntent();
        if (mGetIntent != null) {
            mFromWhere = mGetIntent.getIntExtra(FROM_WHERE, -1);
            mSearchKey = mGetIntent.getStringExtra(KEY_WORD);//输入关键字搜索
            mCtwoInfo = (CateleveltwoInfo.SonCateInfos) mGetIntent.getSerializableExtra(THIRD_LEVEL_OBJ);//点击三级分类图片
        }
    }

    //后期还要根据是点击三级分类图片发送请求/输入关键字（两个接口），如是同一个接口，需统一传过来的string
    private void initData(int pageNum, int refreshState) {
        this.refreshState = refreshState;
        if (mFromWhere == TWO_LEVEL_SORT) {//三级分类图片进入,关键字必定为空
            if (TextUtils.isEmpty(mSearchKey)) {
                if (mCtwoInfo != null) {
                    String name = getIntent().getStringExtra("name");
                    if (TextUtils.isEmpty(name)) {
                        itemName.setVisibility(View.GONE);
                    } else {
                        itemName.setVisibility(View.VISIBLE);
                        itemName.setText(name);
                    }
                    RequestParams params = DrugParamsHelper.getDruglevelthree(mCtwoInfo.getCateId(), mFilterParams.typeId.toString(),
                            mFilterParams.brandId.toString(), mFilterParams.drugFormId.toString(), mFilterParams.lowPrice,
                            mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
                    DrugManager.getInstance().getDruglevelthree(params);
                }
            } else {
                itemName.setVisibility(View.GONE);
                RequestParams params = DrugParamsHelper.getDrugSearch(mSearchKey, mFilterParams.typeId.toString(),
                        mFilterParams.brandId.toString(), mFilterParams.drugFormId.toString(),
                        mFilterParams.lowPrice, mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
                DrugManager.getInstance().getDrugSearch(params);
            }
        } else if (mFromWhere == SORT_SEARCH_ACTIVITY) {//搜索界面进入或在三级分类界面进行搜索关键字
            RequestParams params = DrugParamsHelper.getDrugSearch(mSearchKey, mFilterParams.typeId.toString(),
                    mFilterParams.brandId.toString(), mFilterParams.drugFormId.toString(),
                    mFilterParams.lowPrice, mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
            DrugManager.getInstance().getDrugSearch(params);

        } else if (mFromWhere == INDEX_DISCOUNT) {//促销进入
            mExpTabView.setVisibility(View.GONE);
            lySerach.setVisibility(View.GONE);
            lyTitle.setVisibility(View.VISIBLE);
            lyTitle.setText("打折促销");
            promotionId = getIntent().getStringExtra("prSowId");
            RequestParams params = DrugParamsHelper.getDrugActivityList(configid, promotionId, mSearchKey, mFilterParams.typeId.toString(), mFilterParams.brandId.toString()
                    , mFilterParams.drugFormId.toString(), mFilterParams.lowPrice, mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
            DrugManager.getInstance().getDrugAcvityList(params);
        } else {
            String name = getIntent().getStringExtra("name");
            if (TextUtils.isEmpty(name)) {
                itemName.setVisibility(View.GONE);
            } else {
                itemName.setVisibility(View.VISIBLE);
                itemName.setText(name);
            }
            configid = getIntent().getStringExtra("configid");
            if (TextUtils.isEmpty(mSearchKey)) {
                mSearchKey = "";
                RequestParams params = DrugParamsHelper.getDrugActivityList(configid, promotionId, mSearchKey, mFilterParams.typeId.toString(), mFilterParams.brandId.toString()
                        , mFilterParams.drugFormId.toString(), mFilterParams.lowPrice, mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
                DrugManager.getInstance().getDrugAcvityList(params);
            } else {
                itemName.setVisibility(View.GONE);
                RequestParams params = DrugParamsHelper.getDrugSearch(mSearchKey, mFilterParams.typeId.toString(),
                        mFilterParams.brandId.toString(), mFilterParams.drugFormId.toString(),
                        mFilterParams.lowPrice, mFilterParams.highPrice, mFilterParams.totalSort, pageNum + "", PAGE_SIZE + "");
                DrugManager.getInstance().getDrugSearch(params);
            }
        }
    }

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        searchEt = (EditTextWithDelete) findViewById(R.id.searchEt);
        searchEt.setHint("商品名、品牌、厂商、症状");
        if (mFromWhere == TWO_LEVEL_SORT) {
            // searchEt.setText(mCtwoInfo.getCateName());
        } else if (mFromWhere == SORT_SEARCH_ACTIVITY) {
            searchEt.setText(mSearchKey);
        }
        searchEt.setFocusable(false);
        searchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchFragment();
            }
        });
        lySerach = (LinearLayout) findViewById(R.id.layout_search);
        lyTitle = (TextView) findViewById(R.id.layout_title);
        itemName = (TextView) findViewById(R.id.item_name);
        mMoreImg = (ImageView) findViewById(R.id.moreImg);
        mMoreImg.setVisibility(View.VISIBLE);
        mMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow(v);
            }
        });
        mFragmentLayout = (FrameLayout) findViewById(R.id.fragment_layout);
        mFragmentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFragmentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mFragmentLayoutHeight = mFragmentLayout.getHeight();
                Logger.d("fragmentLayout...height=" + mFragmentLayoutHeight);
            }
        });
        mExpTabView = (SuperExpandTabView) findViewById(R.id.action_filter);
        mExpTabView.setExpandContentId(R.id.fragment_layout);
        mContentLayout = (LinearLayout) findViewById(R.id.linearlayout_content);
        mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                Logger.d("onGlobalLayout....Keyboard Size...." + "Size: " + heightDifference);
                if (heightDifference != 0) {//键盘出现
                    if (!isShowKeyBoard) {
                        mDoubleFilter.setLayHeight(mFragmentLayoutHeight - heightDifference + bottomHeight);
                        isShowKeyBoard = true;
                    }
                } else {//键盘隐藏
                    if (isShowKeyBoard) {
                        mDoubleFilter.setLayHeight(0);
                        isShowKeyBoard = false;
                    }
                }
            }
        });

        mEmptyView = findViewById(R.id.empty_view);
        mEmptyView.setVisibility(View.GONE);
        TextView mEmptyTxt = (TextView) mEmptyView.findViewById(R.id.my_empty_text);
        mEmptyTxt.setText("很抱歉，没有为您找到相关商品");

        mContentListView = findViewById(R.id.list_content);
        mSearch_listview = (AutoListView) findViewById(R.id.search_listview);

        mSearch_listview.setPageSize(PAGE_SIZE);
        mSearch_listview.setOnLoadListener(this);
        mSearch_listview.setOnRefreshListener(this);
        mSearch_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TextUtils.isEmpty(mSearchKey)) {
                    Intent intent = new Intent(SortDetailActivity.this, DrugInfoActivity.class);
                    int pos = position - mSearch_listview.getHeaderViewsCount();
                    if (pos >= 0 && mDrugListAdapter.getCount() > 0) {
                        if (mFromWhere == TWO_LEVEL_SORT) {//三级分类进入列表页
                            DrugThreeListInfo mDtli = (DrugThreeListInfo) mDrugListAdapter.getItem(pos);
                            final String drugid = mDtli.drugDsId;
                            final String StoreName = mDtli.drugStoreName;
                            intent.putExtra("drugid", drugid);
                            intent.putExtra("StoreName", StoreName);
                        } else {//非搜索入口进入
                            DrugSearchEntity.DataBean mDtli = (DrugSearchEntity.DataBean) mDrugListAdapter.getItem(pos);
                            final String drugid = mDtli.getDrugDsId();
                            intent.putExtra("drugid", drugid);
                        }
                        intent.putExtra("fromSortDetail", "yes");
                        startActivity(intent);
                    }

                } else {
                    Intent intent = new Intent(SortDetailActivity.this, DrugInfoActivity.class);
                    int pos = position - mSearch_listview.getHeaderViewsCount();
                    if (pos >= 0 && mDrugListAdapter.getCount() > 0) {
                        DrugSearchEntity.DataBean mDsDb = (DrugSearchEntity.DataBean) mDrugListAdapter.getItem(pos);
                        final String drugid = mDsDb.getDrugDsId();
                        intent.putExtra("drugid", drugid);
                        startActivity(intent);
                    }

                }

            }
        });
        mDrugListAdapter = new DrugListAdapter(this);

        mSearch_listview.setAdapter(mDrugListAdapter);
    }

    @Override
    public void onLoad() {
        if (mSearch_listview != null) {
            if (mDrugListAdapter != null) {
                initData(mDrugListAdapter.getCount() / PAGE_SIZE + 1, AutoListView.LOAD);
            } else {
                mSearch_listview.onLoadComplete();
            }
        }
    }


    private void initExpandTabView() {
        mBigTitles.add("综合排序");
        mBigTitles.add("筛选");

        mSingleFilter = new FilterSingleListView(mContext);
        mSingleFilter.setOnClickFilterSingleListItem(this);
        mDoubleFilter = new FilterDoubleListView(mContext);
        mDoubleFilter.setOnClickFilterDoubleListItem(this);

        mSpinners.add(mSingleFilter);
        mSpinners.add(mDoubleFilter);

        mExpTabView.setValue(mBigTitles, mSpinners);
    }

    private void initFilterData(DrugFilterCondition mDfc) {
        ArrayList<SingleFilterObj> filterObjs = new ArrayList<SingleFilterObj>();
        filterObjs.add(new SingleFilterObj("0", "综合排序"));
        filterObjs.add(new SingleFilterObj("1", "价格由高到低"));
        filterObjs.add(new SingleFilterObj("2", "价格由低到高"));
        mSingleFilter.setFilterData(filterObjs);

        ArrayList<DoubleFilterObj> dfoList = new ArrayList<DoubleFilterObj>();
        if (mDfc != null) {
            FilterCondition mFc = mDfc.getData();
            if (mFc != null) {
                List<TypeInfo> mFcTypeList = mFc.getTypeList();
                if (mFcTypeList != null && mFcTypeList.size() > 0) {
                    DoubleFilterObj mDfoType = new DoubleFilterObj();
                    mDfoType.setName("类型");
                    mDfoType.setId("0");
                    mDfoType.getmChild().add(new SingleFilterObj(SingleFilterObj.NOT_LIMIT_ID, SingleFilterObj.NOT_LIMIT));
                    for (TypeInfo typeInfo : mFcTypeList) {
                        mDfoType.getmChild().add(new SingleFilterObj(typeInfo.getDictId(), typeInfo.getDispName()));
                    }
                    dfoList.add(mDfoType);
                }

                List<BrandInfo> mFcBrandList = mFc.getBrandList();
                if (mFcBrandList != null && mFcBrandList.size() > 0) {
                    DoubleFilterObj mDfoBrand = new DoubleFilterObj();
                    mDfoBrand.setName("品牌");
                    mDfoBrand.setId("1");
                    mDfoBrand.getmChild().add(new SingleFilterObj(SingleFilterObj.NOT_LIMIT_ID, SingleFilterObj.NOT_LIMIT));
                    for (BrandInfo brandInfo : mFcBrandList) {
                        mDfoBrand.getmChild().add(new SingleFilterObj(brandInfo.getBrandId(), brandInfo.getBrandName()));
                    }
                    dfoList.add(mDfoBrand);
                }

                List<DrugFormInfo> mFcFormList = mFc.getFormList();
                if (mFcFormList != null && mFcFormList.size() > 0) {
                    DoubleFilterObj mDfoForm = new DoubleFilterObj();
                    mDfoForm.setName("剂型");
                    mDfoForm.setId("2");
                    mDfoForm.getmChild().add(new SingleFilterObj(SingleFilterObj.NOT_LIMIT_ID, SingleFilterObj.NOT_LIMIT));
                    for (DrugFormInfo formInfo : mFcFormList) {
                        mDfoForm.getmChild().add(new SingleFilterObj(formInfo.getDrugFormId(), formInfo.getDrugFormName()));
                    }
                    dfoList.add(mDfoForm);
                }

            }
        }
        mDoubleFilter.setDoubleFilterData(dfoList);
    }


    @Override
    public void onGlobalLayout() {
        mContentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int hei = mContentLayout.getHeight() * 2 / 3;
        Logger.d("onGlobalLayout.........height...=" + hei);

        mSingleFilter.setLayHeight(hei);
        mDoubleFilter.setInitHeight(hei);
        DrugManager.getInstance().getDrugFilterConditions(null);//获取网络筛选项

    }

    @SuppressLint("ShowToast")
    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (DrugConfig.GETDRUGLEVELTHREE.equals(tag)) {
            if (refreshState == AutoListView.REFRESH) {
                if (code == 1) {
                    DrugLevelThreeList mDtl = (DrugLevelThreeList) obj;
                    if (mDtl != null) {
                        List<DrugThreeListInfo> mMDList = mDtl.getData();
                        if (mMDList != null && mMDList.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onRefreshComplete();
                            mSearch_listview.setResultSize(mMDList.size());
                            mDrugListAdapter.setAdaptData(mMDList, TWO_LEVEL_SORT);
                        } else {
                            mContentListView.setVisibility(View.GONE);
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mContentListView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                    }
                } else {
                    mContentListView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                }
            } else if (refreshState == AutoListView.LOAD) {
                if (code == 1) {
                    DrugLevelThreeList mDtl = (DrugLevelThreeList) obj;
                    if (mDtl != null) {
                        List<DrugThreeListInfo> mDList = mDtl.getData();
                        if (mDList != null && mDList.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(mDList.size());
                            mDrugListAdapter.addAdaptData(mDList);
                        } else {
                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(0);
                        }
                    } else {
                        mSearch_listview.onLoadComplete();
                        mSearch_listview.setResultSize(0);
                    }
                }
            }
        } else if (DrugConfig.GET_DRUG_FILTER_CONDITIONS.equals(tag)) {
            if (code == 1) {
                initFilterData((DrugFilterCondition) obj);
            }
        } else if (DrugConfig.ADDCARTITEM.equals(tag)) {
            if (code == 1) {
                ShowToast.showToast(this, "加入需求清单", 1000);
                DrugTotalQuantity drugTotalQuantity = (DrugTotalQuantity) obj;
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(userInfo == null ? "" : userInfo.getUserId()));
            }
        } else if (DrugConfig.GETDRUGSEARCH.equals(tag)) {
            if (refreshState == AutoListView.REFRESH) {
                if (code == 1) {
                    DrugSearchEntity searchEntity = (DrugSearchEntity) obj;
                    if (searchEntity != null) {
                        List<DrugSearchEntity.DataBean> mData = searchEntity.getData();
                        if (mData != null && mData.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onRefreshComplete();
                            mSearch_listview.setResultSize(mData.size());
                            mDrugListAdapter.setAdaptData2(mData, SORT_SEARCH_ACTIVITY);
                        } else {
                            mContentListView.setVisibility(View.GONE);
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mContentListView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                    }
                } else {
                    mContentListView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                }
            } else if (refreshState == AutoListView.LOAD) {
                if (code == 1) {
                    DrugSearchEntity searchEntity = (DrugSearchEntity) obj;
                    if (searchEntity != null) {
                        List<DrugSearchEntity.DataBean> mDsData = searchEntity.getData();
                        if (mDsData != null && mDsData.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(mDsData.size());
                            mDrugListAdapter.addAdaptData2(mDsData);
                        } else {
                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(0);
                        }
                    } else {
                        mSearch_listview.onLoadComplete();
                        mSearch_listview.setResultSize(0);
                    }
                }
            }
        } else if (DrugConfig.GET_DRUG_ACTIVITY_LIST.equals(tag)) {
            if (refreshState == AutoListView.REFRESH) {
                if (code == 1) {
                    DrugSearchEntity searchEntity = (DrugSearchEntity) obj;
                    if (searchEntity != null) {
                        List<DrugSearchEntity.DataBean> mData = searchEntity.getData();
                        if (mData != null && mData.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onRefreshComplete();
                            mSearch_listview.setResultSize(mData.size());
                            mDrugListAdapter.setAdaptData2(mData, SORT_SEARCH_ACTIVITY);
                        } else {
                            mContentListView.setVisibility(View.GONE);
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mContentListView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                    }
                } else {
                    mContentListView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                }
            } else if (refreshState == AutoListView.LOAD) {
                if (code == 1) {
                    DrugSearchEntity searchEntity = (DrugSearchEntity) obj;
                    if (searchEntity != null) {
                        List<DrugSearchEntity.DataBean> mDsData = searchEntity.getData();
                        if (mDsData != null && mDsData.size() > 0) {
                            mContentListView.setVisibility(View.VISIBLE);
                            mEmptyView.setVisibility(View.GONE);

                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(mDsData.size());
                            mDrugListAdapter.addAdaptData2(mDsData);
                        } else {
                            mSearch_listview.onLoadComplete();
                            mSearch_listview.setResultSize(0);
                        }
                    } else {
                        mSearch_listview.onLoadComplete();
                        mSearch_listview.setResultSize(0);
                    }
                }
            }

        }
    }

    @Override
    public void onClickFilterDoubleItem(int index, int parentPos, int childPos, SingleFilterObj obj) {
        Logger.d("onClickFilterDoubleItem" + obj.toString());
    }

    @Override
    public void onClickComplete() {
        if (mDoubleFilter != null) {
            mDoubleFilter.setFilterParamsValue(mFilterParams);
        }
        if (mFilterParams != null) {
            try {
                long min = Long.valueOf(mFilterParams.lowPrice);
                long max = Long.valueOf(mFilterParams.highPrice);
                if (min > max) {
                    UIUtils.showToast(mContext, "最低价格比最高价格高，请重新填写");
                    return;
                }
            } catch (Exception e) {
            }
        }
        if (mExpTabView != null) {
            mExpTabView.onPressBack();
        }
        initData(1, AutoListView.REFRESH);
    }

    @Override
    public void onClickReset() {

    }

    @Override
    public void onDismissDoubleView() {
        if (mExpTabView != null) {
            mExpTabView.onPressBack();
        }
    }

    private int bottomHeight;

    @Override
    public void countBottomHeight(int hei) {
        bottomHeight = hei;
    }

    @Override
    public void onRefresh() {
        if (mSearch_listview != null) {
            mSearch_listview.onRefreshComplete();
        }
    }

    @Override
    public void onClickFilterSingeItem(int index, int position, SingleFilterObj mObj) {
        Logger.d("onClickFilterSingeItem" + mObj.toString());
        if (mFilterParams == null) {
            mFilterParams = new FilterParams();
        }
        mFilterParams.totalSort = mObj.getId();
        if (mExpTabView != null) {
            mExpTabView.onPressBack();
        }
        initData(1, AutoListView.REFRESH);
    }

    @Override
    public void onDismissSingleView() {
        if (mExpTabView != null) {
            mExpTabView.onPressBack();
        }
    }

    @Override
    public void onClickSearchButton(String key) {
        hideSearchFragment();
        SoftInputUtils.closeInput(mContext, searchEt);
        if (searchEt != null) {
            searchEt.setText(key);
        }
        if (mExpTabView != null) {
            mExpTabView.onPressBack();
        }
        if (mSingleFilter != null) {
            mSingleFilter.clickReset();
        }
        if (mDoubleFilter != null) {
            mDoubleFilter.clickReset();
        }
        if (mFilterParams != null) {
            mFilterParams.setReset();
        }

        if (!TextUtils.isEmpty(key)) {
            mSearchKey = key;
            initData(1, AutoListView.REFRESH);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mSearchFragment != null && !mSearchFragment.isHidden()) {
                hideSearchFragment();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, mMoreImg.getWidth() / 3, -(mMoreImg.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }
}
