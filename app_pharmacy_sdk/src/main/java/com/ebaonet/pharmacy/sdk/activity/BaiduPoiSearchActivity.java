package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.PoiResultAdapter;
import com.ebaonet.pharmacy.sdk.baidu.location.MyBDLocationListener;
import com.ebaonet.pharmacy.sdk.baidu.location.MyBaiduLocationClient;
import com.ebaonet.pharmacy.util.SoftInputUtils;
import com.ebaonet.pharmacy.view.EditTextWithDelete;

import java.util.List;

/**
 * 百度地图的POI搜索
 * Created by yao.feng on 2016/8/16.
 */
public class BaiduPoiSearchActivity extends BaseActivity implements MyBDLocationListener, View.OnClickListener,
        AdapterView.OnItemClickListener {

    private MapView mPoiMapView;
    private BaiduMap mBaiduMap;

    private EditTextWithDelete searchEt;
    private PoiSearch mPoiSearch = PoiSearch.newInstance();

    private PoiResultAdapter mPoiResultAdapter;
    private ListView mPoiLv;
    private TextView mPoiTv;
    private LinearLayout mPoiLL;

    private View mCurrPosView;
    private InfoWindow mInfoWindow;
    private TextView mCurTv;

    private static final String NEARBY_KEY = "小区";
    private static final String CURRENT_CITY = "北京";
    private boolean isInputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_baidu_poi_search);
        initView();
        startLocation();
    }

    private void startLocation() {
        MyBaiduLocationClient.getInstance(this).startLoc(this);
    }

    private void initView() {
        mPoiMapView = (MapView) findViewById(R.id.baidu_poi_mapview);

        mPoiMapView.showZoomControls(false);
        mPoiMapView.showScaleControl(false);

        mBaiduMap = mPoiMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMaxAndMinZoomLevel(21, 3);
//        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                if (searchEt != null) {
//                    searchEt.setText("");
//                }
//                searchNeabyByLatLng(latLng, NEARBY_KEY);
//            }
//
//            @Override
//            public boolean onMapPoiClick(MapPoi mapPoi) {
//                return false;
//            }
//        });

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                if (mapStatus != null) {
                    if (mapStatus.target != null) {
                        if (searchEt != null) {
                            searchEt.setText("");
                        }
                        isInputSearch = false;
                        searchNeabyByLatLng(mapStatus.target, NEARBY_KEY);
                    }
                }
            }
        });

        searchEt = (EditTextWithDelete) findViewById(R.id.searchEt);
        searchEt.setHint("请输入写字楼、小区等");
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    isInputSearch = true;
                    searchPoiByKey(searchEt.getText().toString().trim());
                    SoftInputUtils.closeInput(mContext, searchEt);
                    return true;
                }
                return false;
            }
        });

        mPoiTv = (TextView) findViewById(R.id.search_result_show_tip);
        mPoiLL = (LinearLayout) findViewById(R.id.baidu_ll_search_result);

        mPoiLv = (ListView) findViewById(R.id.listview_poi_result);
        mPoiResultAdapter = new PoiResultAdapter(mContext);
        mPoiLv.setAdapter(mPoiResultAdapter);
        mPoiLv.setOnItemClickListener(this);

        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (mPoiResultAdapter != null && mPoiLv != null && mPoiLL != null && mPoiTv != null) {
                    if (poiResult != null) {
                        List<PoiInfo> mLpi = poiResult.getAllPoi();
                        if (mLpi != null && mLpi.size() > 0) {
                            mPoiResultAdapter.setPoiInfos(mLpi);
                            mPoiLv.setVisibility(View.VISIBLE);
                            mPoiLL.setVisibility(View.GONE);
                        } else {
                            mPoiLv.setVisibility(View.GONE);
                            mPoiLL.setVisibility(View.VISIBLE);
                            if (isInputSearch) {
                                mPoiTv.setText("您输入的地址无法识别");
                            } else {
                                mPoiTv.setText("您选择的地址无法识别");
                            }
                        }
                    } else {
                        mPoiLv.setVisibility(View.GONE);
                        mPoiLL.setVisibility(View.VISIBLE);
                        if (isInputSearch) {
                            mPoiTv.setText("您输入的地址无法识别");
                        } else {
                            mPoiTv.setText("您选择的地址无法识别");
                        }
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            }
        });

        findViewById(R.id.position_to_current_img).setOnClickListener(this);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null && mBaiduMap != null) {
            Logger.d("BaiduPoiSearchActivity lat=" + bdLocation.getLatitude() + "...lng=" + bdLocation.getLongitude());
            MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            moveCurrentPosition(ll);
            Logger.d("addrStr=" + bdLocation.getAddrStr() + "bulidName==" + bdLocation.getBuildingName() +
                            "getCity" + bdLocation.getCity() + "getStreet" + bdLocation.getStreet() +
                            "getSemaAptag" + bdLocation.getSemaAptag() + "getSatelliteNumber" + bdLocation.getSatelliteNumber()
            );
            showCurrentPosWin(ll, bdLocation.getSemaAptag());
            //搜索附近小区
            searchNeabyByLatLng(ll, NEARBY_KEY);
        }
    }

    /**
     * 以动画的形式移动到指定的经纬度
     *
     * @param latLng
     */
    private void moveCurrentPosition(LatLng latLng) {
        if (latLng != null && mBaiduMap != null) {
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 18.0f);
            if (mapStatusUpdate != null) {
                try {
                    mBaiduMap.animateMapStatus(mapStatusUpdate);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 显示当前位置所在坐标的小窗口
     *
     * @param latLng
     */
    private void showCurrentPosWin(LatLng latLng, String posDetail) {
        if (mCurrPosView == null) {
            mCurrPosView = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_view_layout_current_pos, null);
            mCurTv = (TextView) mCurrPosView.findViewById(R.id.current_pos);
            mInfoWindow = new InfoWindow(mCurrPosView, latLng, -47);
        }

        if (mInfoWindow != null && mCurTv != null && mBaiduMap != null && posDetail != null) {
            if (!TextUtils.isEmpty(posDetail)) {
                //去除第一个字“在”和最后两个字“附近”
                int len = posDetail.length();
                if (len > 2) {
                    String sampleName = posDetail.substring(1, posDetail.length() - 2);
                    mCurTv.setText(sampleName);
                    mCurTv.setVisibility(View.VISIBLE);
                } else {
                    mCurTv.setVisibility(View.GONE);
                }
            } else {
                mCurTv.setVisibility(View.GONE);
            }
            mBaiduMap.showInfoWindow(mInfoWindow);
        }
    }

    /**
     * 全市关键字搜索
     *
     * @param key
     */
    private void searchPoiByKey(String key) {
        if (!TextUtils.isEmpty(key) && mPoiSearch != null) {
            Logger.d("......keywords...." + key.trim());
            mPoiSearch.searchInCity(new PoiCitySearchOption().city(CURRENT_CITY).keyword(key.trim()));
        }
    }


    /**
     * 根据经纬度搜索附近小区、写字楼
     *
     * @param latLng
     */
    private void searchNeabyByLatLng(LatLng latLng, String key) {
        if (latLng != null && mPoiSearch != null && key != null) {
            Logger.d("searchNeabyByLatLng lat=" + latLng.latitude + "...lng=" + latLng.longitude);
            mPoiSearch.searchNearby(new PoiNearbySearchOption().location(latLng).keyword(key).radius(1000).
                    sortType(PoiSortType.distance_from_near_to_far));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mPoiMapView != null) {
            mPoiMapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPoiMapView != null) {
            mPoiMapView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPoiMapView != null) {
            mPoiMapView.onDestroy();
        }
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.position_to_current_img) {
            if (searchEt != null) {
                searchEt.setText("");
            }
            startLocation();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent mIntent = new Intent();
        mIntent.putExtra(CreateAddrActivity.RESULT_BEAN, mPoiResultAdapter.getItem(position));
        setResult(CreateAddrActivity.RESPONSE_SUCCESS, mIntent);
        finish();
    }
}
