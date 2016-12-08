package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.address.Address;
import com.ebaonet.pharmacy.entity.address.AddressList;
import com.ebaonet.pharmacy.manager.AddressManager;
import com.ebaonet.pharmacy.manager.config.AddressConfig;
import com.ebaonet.pharmacy.manager.params.AddressParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.ManageAddrAdapter;

import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/8/8.
 */
public class ManageAddrActivity extends BaseActivity {
    ListView mListView;
    Button mBtnCreate;
    Intent intent;
    ManageAddrAdapter adapt;
    View emptyview;
    private AddressManager mManager = AddressManager.getInstance();
    public static final String EXTRA_NAME_ADDRESSINFO = "addressinfo";
    public static final String ADDRESSID = "addressid";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String NAME = "name";
    public static final String DEFAULT = "default";
    private String selectAddr = "false";
    private ImageButton mPharmacy_leftBtn;
    private String mAddrId;
    private String mAddr;
    private String mReceiveName;
    private String mReceivePhone;

    private int fromWhere;
    public static final String EXTRA_NAME_FROM = "from";
    public static final int FROM_CONFIRM_ORDER_ACTIVITY = 10;

    private ArrayList<Address> mAddressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_manage_addr);
        initView();
    }


    private void initView() {
        mAddrId = getIntent().getStringExtra(ConfirmOrderActivity.ADDRESSID);

        fromWhere = getIntent().getIntExtra(EXTRA_NAME_FROM, -1);
        tvTitle.setText("地址管理");
        mListView = (ListView) findViewById(R.id.manageraddr_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (fromWhere == FROM_CONFIRM_ORDER_ACTIVITY) {
                    mAddrId = adapt.getItem(position).getAddrId();
                    setResultAndFinish();
                }
            }
        });
        adapt = new ManageAddrAdapter(mContext);
        mListView.setAdapter(adapt);
        mBtnCreate = (Button) findViewById(R.id.manager_addr_create_newaddr);
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, CreateAddrActivity.class);
                intent.putExtra("isedit", false);
                startActivity(intent);
            }
        });
        PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
        mManager.getAddressList(AddressParamsHelper.getAddressListParams(userInfo == null ? "" : userInfo.getUserId()
        ));
    }

    private Address getAddressByAddressId(String addrId) {
        if (!TextUtils.isEmpty(addrId)) {
            for (Address mAddress : mAddressList) {
                if (mAddress.getAddrId().equals(addrId)) {
                    return mAddress;
                }
            }
        }
        return null;
    }

    public void setResultAndFinish() {
        Address mAdd = getAddressByAddressId(mAddrId);
        Intent mIntent = new Intent();
        mIntent.putExtra(EXTRA_NAME_ADDRESSINFO, mAdd);
        setResult(RESULT_OK, mIntent);
        finish();
    }

    /**
     * 点击返回键
     */
    @Override
    public void onBackPressed() {
        setResultAndFinish();
        super.onBackPressed();
    }

    @Override
    protected void initTopbar() {
        super.initTopbar();
        mPharmacy_leftBtn = (ImageButton) findViewById(R.id.pharmacy_leftBtn);
        mPharmacy_leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultAndFinish();

            }
        });

    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        if (tag.equals(AddressConfig.GET_ADDRESS_LIST)) {
            if (code == 1) {
                AddressList list = (AddressList) obj;
                mAddressList.clear();
                mAddressList.addAll(list.getData());
                if (adapt != null) {
                    adapt.setAddressList(mAddressList);
                }
            }
            if (emptyview == null) {
                emptyview = findViewById(R.id.empty_view);
                emptyview.setVisibility(View.VISIBLE);
                mListView.setEmptyView(emptyview);
            }
        } else if (tag.equals(AddressConfig.DEL_ADDRESS)) {
            if (code == 1) {
                if (adapt != null) {
                    final Address address = adapt.getAddress();
                    if (address.getAddrId().equals(mAddrId)) {
                        mAddrId = "";
                    }
                }
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                mManager.getAddressList(AddressParamsHelper.getAddressListParams(userInfo == null ? "" : userInfo.getUserId()));
            }
        } else if (tag.equals(AddressConfig.SAVE_DEFAULT_ADDRESS)) {
            if (code == 1) {
                if (adapt != null) {
                    mAddrId = adapt.getAddress().getAddrId();
                    mAddr = adapt.getAddress().getAddr();
                    mReceiveName = adapt.getAddress().getReceiveName();
                    mReceivePhone = adapt.getAddress().getReceivePhone();
                }
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                mManager.getAddressList(AddressParamsHelper.getAddressListParams(userInfo == null ? "" : userInfo.getUserId()));

            }
        }

    }


    @Override
    public boolean isNetDataTransmission() {
        return true;
    }


}
