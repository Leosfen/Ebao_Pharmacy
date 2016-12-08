package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.address.Address;
import com.ebaonet.pharmacy.entity.order.CreateOrderEntry;
import com.ebaonet.pharmacy.entity.order.DeliveryEntity;
import com.ebaonet.pharmacy.entity.order.SubmitOrderEntry;
import com.ebaonet.pharmacy.manager.AddressManager;
import com.ebaonet.pharmacy.manager.DrugManager;
import com.ebaonet.pharmacy.manager.OrderListManager;
import com.ebaonet.pharmacy.manager.ShoppingCarManager;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.manager.params.DrugParamsHelper;
import com.ebaonet.pharmacy.manager.params.OrderParamsHelper;
import com.ebaonet.pharmacy.manager.params.ShoppingCarParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.SquareImageView;
import com.ebaonet.pharmacy.sdk.file.ChaseInfo;
import com.ebaonet.pharmacy.sdk.file.SaveChaseInfoUtil;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.StringUtils;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.DeleteDialog;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 确认订单
 * Created by zhaojun.gao on 2016/9/13.
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTv_title;
    private ImageButton mRightBtn;
    private LinearLayout mLl_imges;
    private ImageLoader imageLoader;
    private List<String> imageurlList = new ArrayList<String>();
    private LinearLayout mLl_chasinginvoices;
    private ImageView mIv_drugstore;
    private ImageView mIv_byoneself;
    private TextView mTv_des;
    private TextView mTv_fare;
    private TextView mTv_total;
    private ImageButton mPharmacy_leftBtn;
    private DeleteDialog deleteDialog;
    private TextView mInvoice;
    private LinearLayout mLayout_manage_addr;
    private String mResult;
    private TextView mTv_commit;
    private TextView mCount;
    private TextView mTotal;
    private TextView tv_preferentialPrice;
    private AddressManager mManager = AddressManager.getInstance();
    private TextView mTv_name;
    private TextView mTv_phone;
    private TextView mTv_address;
    private String mAddr;
    private String mReceivePhone;
    private String mReceiveName;
    private TextView mTv_default;
    private LinearLayout mLl_default2;
    private String mOrderCode;
    private String mAddrId;
    private String mAddrId1;
    private TextView mTv_orderFreight;
    private TextView mTv_payment;
    private int mNum = 0;
    private String mDrug;
    private String mDrugStoreId;
    private String addressBeanAddrId;
    private String mChaseType;
    private String type;
    private String mHeader;
    private String deliveryType;
    public static final String ADDRESSID = "addressid";
    private RightTopActionPopWin popupWindow;
    private boolean fromLocal = true;
    private CreateOrderEntry createOrderEntry;
    private String mName_title;
    private String mName;
    private String mLogistics;
    private PharmcyUserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_confirm_order);
        imageLoader = ImageLoader.getInstance();
        initView();
        initData();
    }

    @Override
    protected void initTopbar() {
        super.initTopbar();
        mPharmacy_leftBtn = (ImageButton) findViewById(R.id.pharmacy_leftBtn);
        mPharmacy_leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteDialog == null) {
                    deleteDialog = new DeleteDialog(mContext);
                    deleteDialog.settext("良药苦口，思而后动", "去意己决", "思考一下");
                }
                deleteDialog.setClicklistener(new DeleteDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                    }

                    @Override
                    public void doCancel() {
                        finish();
                    }
                });
                deleteDialog.show();
            }
        });
    }

    private void initData() {
        mDrug = getIntent().getStringExtra("drug");
        mDrugStoreId = getIntent().getStringExtra("drugStoreId");
        mUserInfo  = PharCacheInfoManager.getUserInfo(mContext);
        OrderListManager.getInstance().createOrder(OrderParamsHelper.createOrder("1", "2", mUserInfo == null ? "" : mUserInfo.getUserId(), mDrugStoreId, mDrug));
        //mManager.getAddressList(AddressParamsHelper.getAddressListParams(PharmacyUrlConst.PHARMACY_USER_ID));
    }

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mRightBtn = (ImageButton) findViewById(R.id.rightBtn);
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        mLayout_manage_addr = (LinearLayout) findViewById(R.id.layout_manage_addr);
        mTv_default = (TextView) findViewById(R.id.tv_default);
        mLl_default2 = (LinearLayout) findViewById(R.id.ll_default2);
        mTv_name = (TextView) findViewById(R.id.tv_name);
        mTv_phone = (TextView) findViewById(R.id.tv_phone);
        mTv_address = (TextView) findViewById(R.id.tv_address);
        mLl_imges = (LinearLayout) findViewById(R.id.ll_imges);//控件用来放置图片
        mCount = (TextView) findViewById(R.id.count);//商品件数
        mTv_fare = (TextView) findViewById(R.id.tv_fare);//配送费
        mTotal = (TextView) findViewById(R.id.total);//总额
//        tv_preferentialPrice = (TextView) findViewById(R.id.tv_preferentialPrice);//优惠    
        mTv_orderFreight = (TextView) findViewById(R.id.tv_orderFreight);
        mTv_total = (TextView) findViewById(R.id.tv_total);//合计
        mLl_chasinginvoices = (LinearLayout) findViewById(R.id.ll_chasinginvoices);
        mInvoice = (TextView) findViewById(R.id.invoice);//发票信息
        mTv_commit = (TextView) findViewById(R.id.drug_info_commit);
        mIv_drugstore = (ImageView) findViewById(R.id.iv_drugstore);
        mIv_byoneself = (ImageView) findViewById(R.id.iv_byoneself);
        mTv_des = (TextView) findViewById(R.id.tv_des);
        mTv_payment = (TextView) findViewById(R.id.tv_payment);
        mLayout_manage_addr.setOnClickListener(this);
        mIv_drugstore.setOnClickListener(this);
        mIv_byoneself.setOnClickListener(this);
        mLl_chasinginvoices.setOnClickListener(this);
        mTv_commit.setOnClickListener(this);
        mLl_imges.setOnClickListener(this);
        mTv_title.setText("确认订单");
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setImageResource(R.drawable.pharmacy_titlebar_icon_point_normal);
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        if (tag.equals(OrderConfig.CREATE_ORDER)) {
            if (code == 1) {//成功
                createOrderEntry = (CreateOrderEntry) obj;
                CreateOrderEntry.DataBean data = createOrderEntry.getData();
                CreateOrderEntry.DataBean.AddressBean addressBean = data.getAddress();
                if (addressBean != null) {
                    mLl_default2.setVisibility(View.VISIBLE);
                    mTv_default.setVisibility(View.GONE);
                    addressBeanAddrId = addressBean.getAddrId();
                    addressBean.getProvName();
                    String receiveName = addressBean.getReceiveName();//收货人
                    String receivePhone = addressBean.getReceivePhone();//手机号
                    mTv_name.setText(receiveName);
                    mTv_phone.setText(receivePhone);
                    mTv_address.setText(addressBean.getProvName() + addressBean.getCityName() + addressBean.getBiotopeName() + addressBean.getAddr());
                } else {
                    mLl_default2.setVisibility(View.GONE);
                    mTv_default.setVisibility(View.VISIBLE);
                }
                List<CreateOrderEntry.DataBean.DrugBean> drug = data.getDrug();
                int size = drug.size();//药品数量 2

                for (int x = 0; x < drug.size(); x++) {
                    CreateOrderEntry.DataBean.DrugBean.DimImageBean dimImageBean = drug.get(x).getDimImage();
                    if (dimImageBean == null) {
                        imageurlList.add("http://ebao-zsyd.ebaonet.cn/uploadimages/746.jpg");
                    } else {
                        String thumbPath = dimImageBean.getThumbPath();
                        imageurlList.add(thumbPath);
                    }
                    mNum = mNum + drug.get(x).getDrugNum();

                }
                if (imageurlList.size() > 0) {
                    int imagecount = 0;
                    if (imageurlList.size() >= 5) {
                        imagecount = 4;
                    } else {
                        imagecount = imageurlList.size();
                    }
                    for (int i = 0; i < imagecount; i++) {
                        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        RelativeLayout relativeLayout = new RelativeLayout(this);
                        RelativeLayout.LayoutParams mParams2 = new RelativeLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        SquareImageView squareImageView = new SquareImageView(this);
                        squareImageView.setImageResource(R.drawable.pharmacy_send_pic_default);
                        squareImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        ImageView imageView = new ImageView(this);
                        //判断是否加水印
                        if (!TextUtils.isEmpty(drug.get(i).getMedicalInsuranceCode())) {
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            imageView.setVisibility(View.GONE);
                        }
                        imageView.setImageResource(R.drawable.pharmacy_watermark);
                        mParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        imageView.setLayoutParams(mParams2);
                        int i2 = UIUtils.dip2px(this, 8);
                        if(i!=0){
                            mParams.leftMargin = i2;
                        }else{
                            Log.e("leftMargin", "=============================");
                        }
                        int i1 = UIUtils.dip2px(this, 72);//将dp转化成px设置给控件宽高
                        mParams.height = i1;
                        mParams.width = i1;
                        imageLoader.displayImage(imageurlList.get(i), squareImageView, ImageDisOpt.getDefaultImgDisOpt());
                        relativeLayout.addView(squareImageView);
                        relativeLayout.addView(imageView);
                        mLl_imges.addView(relativeLayout, mParams);

                    }
                    mLl_imges.setOnClickListener(this);
                }
                String normalPrice = StringUtils.formatDouble(data.getNormalPrice());//总额
                String preferentialPrice = StringUtils.formatDouble(data.getPreferentialPrice());//优惠
                String orderPrice = StringUtils.formatDouble(data.getOrderPrice());//合计
                String deliveryType = data.getDeliveryType();//配送方式
                String orderFreight = StringUtils.formatDouble(data.getOrderFreight());//配送费（运费）
                mOrderCode = data.getOrderCode();
                //mAddrId = data.getAddrId();
                mCount.setText("共" + mNum + "件");//数量
                if (StringUtils.format(orderFreight).equals("0.00")) {
                    mTv_fare.setText("免配送费");
                } else {
                    mTv_fare.setText("配送费: ¥" + StringUtils.format(orderFreight));
                }
                mTv_orderFreight.setText("+¥" + StringUtils.format(orderFreight));
                mTotal.setText("¥" + normalPrice);
//                tv_preferentialPrice.setText("-￥" + preferentialPrice);//优惠
                mTv_total.setText("¥" + orderPrice);
                mTv_payment.setText("¥" + orderPrice);
            }
        }
        if (tag.equals(OrderConfig.SUBMIT_ORDER)) {
            if (code == 1) {
                setResult(RESULT_OK);
                UIUtils.showSaveSuccessToast(mContext, "订单提交成功");
                SubmitOrderEntry.DataBean data = ((SubmitOrderEntry) obj).getData();
                String orderCode2 = data.getOrderCode();
              
                ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(mUserInfo == null ? "" : mUserInfo.getUserId()));
//                OrderListManager.getInstance().getOnDoingOrderNum(OrderParamsHelper.getOnDoingOrderNum(
//                        PharCacheInfoManager.getmUserInfo(mContext).getUserId(), "23", "33"
//                ));                                                         
                DrugManager.getInstance().getQuantity(DrugParamsHelper.getQuantity( mUserInfo == null ? "" : mUserInfo.getUserId()));
                Intent intent = new Intent(this, MyOrdersListActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (tag.equals(OrderConfig.CHANGE_DELIVERY)) {
            if (code == 1) {
                DeliveryEntity.DataBean data = ((DeliveryEntity) obj).getData();
                String orderPrice = data.getOrderPrice();
                String orderFreight = data.getOrderFreight();
                if (deliveryType.equals("2")) {
                    mTv_fare.setText("免配送费");
                } else if (deliveryType.equals("1")) {
                    if (StringUtils.format(orderFreight).equals("0.00")) {
                        mTv_fare.setText("免配送费");
                    } else {
                        mTv_fare.setText("配送费:￥" + StringUtils.format(orderFreight));
                    }
                }
                mTv_total.setText("￥" + StringUtils.format(orderPrice));//合计
                mTv_orderFreight.setText("+￥" + StringUtils.format(orderFreight));//配送费
                mTv_payment.setText("￥" + orderPrice);
            }
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_chasinginvoices) {//索要发票
            Intent intent = new Intent(this, ChasingInvoicesActivity.class);
            if (TextUtils.isEmpty(mLogistics)) {//索要发票
                intent.putExtra(ChasingInvoicesActivity.NAME_TITLE, mName_title);
                intent.putExtra(ChasingInvoicesActivity.NAME, mName);
            }
            startActivityForResult(intent, 0);
        }
        if (i == R.id.iv_drugstore) {//点击药店配送
            deliveryType = "1";
            mIv_drugstore.setSelected(true);
            mIv_byoneself.setSelected(false);
            mTv_des.setText(UIUtils.toDBC("每天8:00-17:00间用户下的药店配送订单，当天送达，超过17:00的订单将第二天配送； 上门自提可刷医保卡支付."));
            mIv_drugstore.setImageResource(R.drawable.pharmacy_order_btn_send_seclected);
            mIv_byoneself.setImageResource(R.drawable.pharmacy_order_btn_pick_unseclected);
            OrderListManager.getInstance().changeDelivery(OrderParamsHelper.changeDelivery("1", "10", mOrderCode, deliveryType));
        }
        if (i == R.id.iv_byoneself) {
            deliveryType = "2";
            mIv_byoneself.setSelected(true);
            mIv_drugstore.setSelected(false);
            mTv_des.setText(UIUtils.toDBC("门店的工作时间为8:00-20:30，请在营业时间内到门店自提；上门自提可刷医保卡支付。"));
            mIv_drugstore.setImageResource(R.drawable.pharmacy_order_btn_send_unseclected);
            mIv_byoneself.setImageResource(R.drawable.pharmacy_order_btn_pick_seclected);
            OrderListManager.getInstance().changeDelivery(OrderParamsHelper.changeDelivery("1", "10", mOrderCode, deliveryType));
        }
        if (i == R.id.ll_imges) {
            Intent intent = new Intent(this, ConfirmOrderListActivity.class);
            intent.putExtra("createOrderEntry", this.createOrderEntry);
            startActivity(intent);
        }
        if (i == R.id.layout_manage_addr) {
            Intent intent = new Intent(this, ManageAddrActivity.class);
            intent.putExtra(ADDRESSID, addressBeanAddrId);
            intent.putExtra(ManageAddrActivity.EXTRA_NAME_FROM, ManageAddrActivity.FROM_CONFIRM_ORDER_ACTIVITY);
            startActivityForResult(intent, 1);
        }
        if (i == R.id.drug_info_commit) {
            //1:索要发票 0：不要发票
            if (mLl_default2.getVisibility() == View.GONE) {
                Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            } else {
                if (mInvoice.getText().equals("不需要发票")) {
                    OrderListManager.getInstance().submitOrder(OrderParamsHelper.submitOrders( mUserInfo == null ? "" : mUserInfo.getName(),  mUserInfo == null ? "" : mUserInfo.getIdCard(), "1", "1", mOrderCode, addressBeanAddrId, "0", "", ""));
                } else {
                    final ChaseInfo chaseInfo = SaveChaseInfoUtil.getChaseInfo(mContext);
                    mChaseType = chaseInfo.getChaseType();
                    if (mChaseType.equals("公司")) {
                        type = "2";
                        mHeader = chaseInfo.getCompanyInfo();
                    } else if (mChaseType.equals("个人")) {
                        type = "1";
                        mHeader = chaseInfo.getPersonInfo();
                    }
                    // 订单号     地址id   是否需要发票  发票类型
                    OrderListManager.getInstance().submitOrder(OrderParamsHelper.submitOrders( mUserInfo == null ? "" : mUserInfo.getName(),  mUserInfo == null ? "" : mUserInfo.getIdCard(), "1", "1", mOrderCode, addressBeanAddrId, "1", type, mHeader));
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == 0) {
                    Bundle extras = data.getExtras();
                    mLogistics = extras.getString(ChasingInvoicesActivity.LOGISTICS);
                    if (TextUtils.isEmpty(mLogistics)) {
                        mName_title = extras.getString(ChasingInvoicesActivity.NAME_TITLE);
                        mName = extras.getString(ChasingInvoicesActivity.NAME);
                        mResult = mName_title + "-" + mName;
                        /**应测试要求修改*/
//                        if (mResult.length() > 11) {
//                            int i = mResult.length() / 11;
//                            String formatAddspace = StringUtils.connectString(mResult.substring(0, 11), "\n");
//                            if (i < 2) {
//                                mResult = StringUtils.connectString(formatAddspace, mResult.substring(11, mResult.length()));
//                            } else {
//                                mResult = StringUtils.connectString(formatAddspace, mResult.substring(11, 19));
//                                mResult = StringUtils.connectString(mResult, "...");
//                            }
//                        }
                        mInvoice.setText(mResult);
                    } else {
                        mInvoice.setText("不需要发票");
                    }
                } else if (requestCode == 1) {
                    final Address address = (Address) data.getSerializableExtra(ManageAddrActivity.EXTRA_NAME_ADDRESSINFO);
                    if (address != null) {
                        addressBeanAddrId = address.getAddrId();
                        mLl_default2.setVisibility(View.VISIBLE);
                        mTv_default.setVisibility(View.GONE);
                        mTv_name.setText(address.getReceiveName());
                        mTv_phone.setText(address.getReceivePhone());
                        mTv_address.setText(address.getProvName() + address.getCityName() + address.getBiotopeName());
                    } else {
                        addressBeanAddrId = "";
                        mLl_default2.setVisibility(View.GONE);
                        mTv_default.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }


    @Override
    public boolean isNetDataTransmission() {
        return true;
    }

    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, mRightBtn.getWidth() / 3, -(mRightBtn.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }
}
