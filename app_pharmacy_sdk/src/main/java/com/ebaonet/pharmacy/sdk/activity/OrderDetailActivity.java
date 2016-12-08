package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderDrugInfo;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderManagerInfo;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.OrderListManager;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.manager.params.OrderParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.adapter.MyDrugChildAdapter;
import com.ebaonet.pharmacy.util.StringUtils;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.CodeDialog;
import com.ebaonet.pharmacy.view.NoScrollListView;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;
import com.ebaonet.pharmacy.view.RoundDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;
import java.util.List;

/**
 * 订单详情界面
 * Created by peng.dong on 2016/9/22.
 */
public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String ORDER_DETAIL_INFO = "orderDetail";
    OrderManagerInfo info;
    private TextView tvShopName, tvName, tvAddress, tvTotalPrice, tvOrderStatus,
            tvPhone, tvOrderId, tvCreateTime, tvDeliveryType, tvBillType, tvCount, tvOrderPrice, tvDeliveryPrice, tvPreferentialPrice;
    private ImageView imgQRcode;
    private LinearLayout layoutContent, layoutDelete;
    public static final String ORDER_NUM = "orderNum";
    private Bitmap mBitmap;
    private CodeDialog mDialog;

    @Override
    public void onClick(View v) {
        if (info != null) {
            Intent intent = new Intent(this, LogisticsDetailActivity.class);
            intent.putExtra(ORDER_NUM, info.getOrderId());
            startActivity(intent);
        }

    }

    private NoScrollListView mListView;
    private LinearLayout mCheck_logistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_order_detail);
        info = (OrderManagerInfo) getIntent().getSerializableExtra(ORDER_DETAIL_INFO);
        if (info != null) {
            initview();
        }

    }

    private void initview() {
        btnRight.setVisibility(View.VISIBLE);
        tvTitle.setText("订单详情");
        tvShopName = (TextView) findViewById(R.id.tv_shop_name);
        tvAddress = (TextView) findViewById(R.id.order_detail_bottomview_address);
        tvName = (TextView) findViewById(R.id.order_detail_bottomview_person);
        tvPhone = (TextView) findViewById(R.id.order_detail_bottomview_phone);
        tvOrderId = (TextView) findViewById(R.id.order_detail_orderid);
        tvCreateTime = (TextView) findViewById(R.id.order_detail_createtime);
        tvDeliveryType = (TextView) findViewById(R.id.order_detail_delivery_type);
        tvDeliveryPrice = (TextView) findViewById(R.id.order_detail_deliveryprice);
        tvBillType = (TextView) findViewById(R.id.order_detail_bill_type);
        tvTotalPrice = (TextView) findViewById(R.id.order_detail_totalprice);
        tvOrderPrice = (TextView) findViewById(R.id.order_detail_price);
        tvCount = (TextView) findViewById(R.id.order_detail_count);
        tvOrderStatus = (TextView) findViewById(R.id.order_status);
        layoutContent = (LinearLayout) findViewById(R.id.layout_content);
        layoutDelete = (LinearLayout) findViewById(R.id.layout_delete);
        tvPreferentialPrice = (TextView) findViewById(R.id.order_detail_preferentialprice);
        imgQRcode = (ImageView) findViewById(R.id.order_detail_qrcode);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(info.getCsPhone())) {
                    callPhone(info.getCsPhone());
                } else {
                    UIUtils.showToast(mContext, "暂无电话信息！");
                }
            }
        });
        layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanDelete(info.getOrderStatus())) {
                    View mView = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_dialog_delete_order, null);
                    final RoundDialog mRd = new RoundDialog(mContext, mView);
                    mView.findViewById(R.id.click_cancel_delete_order).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRd.dismiss();
                        }
                    });
                    mView.findViewById(R.id.click_confirm_delete_order).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRd.dismiss();
                            OrderListManager.getInstance().deleteOrder(OrderParamsHelper.getDeleteOrderParams(info.getOrderId(), "23", "345"));
                        }
                    });
                    mRd.show();
                }

            }
        });

        tvOrderStatus.setText(getOrderTypeString(info.getOrderStatus()));
        tvOrderPrice.setText(StringUtils.formatDouble(info.getNormalPrice()));
        tvDeliveryPrice.setText(StringUtils.formatDouble(info.getOrderFreight()));
        tvTotalPrice.setText(StringUtils.formatDouble(info.getOrderPrice()));
        tvPreferentialPrice.setText(StringUtils.formatDouble(info.getPreferentialPrice()));

        List<OrderDrugInfo> druginfo = info.getDrug();
        int count = 0;
        for (OrderDrugInfo info : druginfo) {
            count = count + info.getDrugNum();
        }
        tvCount.setText(String.valueOf(count));
        tvShopName.setText(StringUtils.formatString(info.getDrug().get(0).getDrugStoreName()));

        tvAddress.setText(StringUtils.formatString(info.getReceiveAddr()));
        tvPhone.setText(StringUtils.formatString(info.getReceivePhone()));
        tvName.setText(StringUtils.formatString(info.getReceiveName()));

        tvOrderId.setText(StringUtils.formatString(info.getOrderCode()));
        tvCreateTime.setText(StringUtils.formatString(info.getCreTime()));
        if (info.getQrCode() != null) {
            Logger.d("orderididid:" + info.getQrCode());
            mBitmap = createQRCodeBitmap(info.getQrCode());
        }
        //imgQRcode.setBackground(mDrawable);
        imgQRcode.setImageBitmap(mBitmap);
        imgQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog == null) {
                    mDialog = new CodeDialog(mContext, mBitmap);
                }
                mDialog.show();
            }
        });
        if (info.getDeliveryType().equals("1")) {
            tvDeliveryType.setText("药店快递");
        } else {
            tvDeliveryType.setText("上门自提");
        }
        if (info.getInvoice() != null) {
            if (info.getInvoice().getInvoiceType() == 1) {
                tvBillType.setText("个人");
            } else if (info.getInvoice().getInvoiceType() == 0) {
                tvBillType.setText("不需要发票");
            } else {
                tvBillType.setText("公司");
            }
        }
        //tvBillType.setText(String.valueOf(info.getInvoice().getInvoiceType()));
        mCheck_logistics = (LinearLayout) findViewById(R.id.check_logistics);
        mCheck_logistics.setOnClickListener(this);
        mListView = (NoScrollListView) findViewById(R.id.order_detail_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, DrugInfoActivity.class);
                intent.putExtra("drugid", info.getDrug().get(position).getDrugDsId());
                startActivity(intent);
            }
        });
        MyDrugChildAdapter adapter = new MyDrugChildAdapter(mContext);
        mListView.setAdapter(adapter);
        adapter.setDrugData(info.getDrug());
    }

    public String getOrderTypeString(String orderStatus) {
        if (orderStatus.equals("3")) {
            tvOrderStatus.setTextColor(getResources().getColor(R.color.color_green_82c631));
            layoutDelete.setVisibility(View.GONE);
            layoutContent.setVisibility(View.VISIBLE);
            return "进行中";
        } else if (orderStatus.equals("5")) {
            layoutDelete.setVisibility(View.VISIBLE);
            layoutContent.setVisibility(View.GONE);
            return "已完成";
        } else if (orderStatus.equals("6")) {
            layoutDelete.setVisibility(View.VISIBLE);
            layoutContent.setVisibility(View.GONE);
            return "已取消";
        }
        return "";
    }

    private RightTopActionPopWin popupWindow;

    /**
     * 设置popupwindow的状态
     *
     * @param v
     */
    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, btnRight.getWidth() / 3, -(btnRight.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }

    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        mContext.startActivity(intent);
    }

    private boolean isCanDelete(String orderStatus) {
        if (orderStatus.equals("5") || orderStatus.equals("6")) {
            return true;
        }
        return false;
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        if (tag.equals(OrderConfig.DELETE_ORDER)) {
            if (code == 1) {
                finish();
            }
        }
    }

    private Bitmap createQRCodeBitmap(String content) {
        // 用于设置QR二维码参数  
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别——这里选择最高M级别  
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置编码方式  
        qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, 300, 300, qrParam);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑白两色  
            int w = bitMatrix.getWidth();
            int h = bitMatrix.getHeight();
            int[] data = new int[w * h];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (bitMatrix.get(x, y))
                        data[y * w + x] = 0xff000000;// 黑色  
                    else
                        data[y * w + x] = -1;// -1 相当于0xffffffff 白色  
                }
            }

            // 创建一张bitmap图片，采用最高的效果显示  
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            // 将上面的二维码颜色数组传入，生成图片颜色  
            bitmap.setPixels(data, 0, w, 0, 0, w, h);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
