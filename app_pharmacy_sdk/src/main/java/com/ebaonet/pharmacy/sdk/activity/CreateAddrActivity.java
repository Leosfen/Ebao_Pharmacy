package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.search.core.PoiInfo;
import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.address.Address;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.AddressManager;
import com.ebaonet.pharmacy.manager.config.AddressConfig;
import com.ebaonet.pharmacy.manager.params.AddressParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.baidu.location.MyBDLocationListener;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.util.Utils;
import com.ebaonet.pharmacy.util.permission.MPermissions;

/**
 * Created by peng.dong on 2016/8/8.
 * 新增地址和编辑地址activity
 */
public class CreateAddrActivity extends BaseActivity implements TextWatcher, MyBDLocationListener {

    public static final int REQUEST_CODE_BAIDU_SEARCH = 1000;
    public static final int RESPONSE_SUCCESS = 1234;
    public static final String RESULT_BEAN = "bean";

    private TextView tvTitle, tvArea;
    private Boolean tvAreaStatus = false;
    private EditText mEditReceiver, mEditPhone, mEditDetail;
    private Button mBtnSave;
    private LinearLayout mLayoutArea;
    private Address mAddress;
    private String addrId;
    private Boolean isEdit;

    private String lng, lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //根据tag判断是编辑页面还是新增页面,默认为新增界面
        isEdit = getIntent().getBooleanExtra("isedit", false);
        setContentView(R.layout.pharmacy_activity_create_addr);
        initView();
        //2016年11月25日11:49:00，进入编辑地址后，不定位
        // MyBaiduLocationClient.getInstance(this).startLoc(this);
        //添加地图定位权限
        MPermissions.requestLocationPermission(this, this);
    }


    private void initView() {
        tvTitle = (TextView) findViewById(R.id.pharmacy_tv_title);
        mBtnSave = (Button) findViewById(R.id.create_addr_btn_save);
        mEditReceiver = (EditText) findViewById(R.id.create_addr_et_receiver);
        mEditPhone = (EditText) findViewById(R.id.create_addr_et_phone);
        tvArea = (TextView) findViewById(R.id.create_addr_et_area);
        mEditDetail = (EditText) findViewById(R.id.create_addr_et_detail);
        //设置禁止回车换行
//        mEditDetail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);
//            }
//        });
        mLayoutArea = (LinearLayout) findViewById(R.id.create_addr_layout_area);
        if (isEdit == false) {
            tvTitle.setText("新增收货地址");
            addrId = null;
        } else {
            mAddress = (Address) getIntent().getSerializableExtra("data");
            addrId = mAddress.getAddrId();
            tvTitle.setText("编辑收货地址");
            tvAreaStatus = true;
            mEditReceiver.setText(Utils.replaceDefaultString(mAddress.getReceiveName()));
            mEditPhone.setText(Utils.replaceDefaultString(mAddress.getReceivePhone()));
            mEditDetail.setText(Utils.replaceDefaultString(mAddress.getAddr()));
            tvArea.setText(Utils.replaceDefaultString(mAddress.getBiotopeName()));
            tvArea.setTextColor(mContext.getResources().getColor(R.color.color_black_666666));
            //添加经纬度
            lng = mAddress.getLongitude();
            lat = mAddress.getLatitude();
        }
        mLayoutArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, BaiduPoiSearchActivity.class);
                startActivityForResult(mIntent, REQUEST_CODE_BAIDU_SEARCH);
            }
        });
        mEditReceiver.addTextChangedListener(this);
        mEditPhone.addTextChangedListener(this);
        mEditDetail.addTextChangedListener(this);
        checkBtnStatus();
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Utils.isMobile(mEditPhone.getText().toString().trim())) {
                    UIUtils.showToast(mContext, "手机号输入错误");
                    return;
                }
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                AddressManager.getInstance().saveAddress(AddressParamsHelper.getSaveAddressParams(addrId, userInfo == null ? "" : userInfo.getUserId(), tvArea.getText().toString(),
                        mEditDetail.getText().toString(), lng + "", lat + "", mEditReceiver.getText().toString(), mEditPhone.getText().toString()));
            }
        });

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkBtnStatus();
    }

    private void checkBtnStatus() {
        if (TextUtils.isEmpty(mEditReceiver.getText().toString().trim())
                || TextUtils.isEmpty(mEditPhone.getText().toString().trim())
                || TextUtils.isEmpty(tvArea.getText().toString().trim())
                || TextUtils.isEmpty(mEditDetail.getText().toString().trim()) || tvAreaStatus == false) {
            mBtnSave.setEnabled(false);
        } else {
            mBtnSave.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BAIDU_SEARCH && data != null) {
            if (resultCode == RESPONSE_SUCCESS) {
                PoiInfo mPi = data.getParcelableExtra(RESULT_BEAN);
                if (mPi != null) {
                    if (mPi.location != null) {
                        lng = mPi.location.longitude + "";
                        lat = mPi.location.latitude + "";
                    }
                    tvArea.setText(mPi.name.toString());
                    tvArea.setTextColor(mContext.getResources().getColor(R.color.color_black_666666));
                    tvAreaStatus = true;
                    Logger.d("create address poi name" + mPi.name);
                }
                checkBtnStatus();
            }
        }

    }


    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        if (AddressConfig.SAVE_ADDRESS.equals(tag)) {
            if (code == 1) {
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                AddressManager.getInstance().getAddressList(AddressParamsHelper.getAddressListParams(userInfo == null ? "" : userInfo.getUserId()));
                UIUtils.showSaveSuccessToast(this, "保存成功");
                finish();
            } else {
                UIUtils.showSaveFailToast(this);
            }
        }
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null) {
            lng = bdLocation.getLongitude() + "";
            lat = bdLocation.getLatitude() + "";
        }
    }
}
