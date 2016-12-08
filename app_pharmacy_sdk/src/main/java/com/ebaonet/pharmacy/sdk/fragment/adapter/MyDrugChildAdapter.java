package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.order.orderlist.OrderDrugInfo;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理中药品的具体信息
 * Created by yao.feng on 2016/9/23.
 */
public class MyDrugChildAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private ArrayList<OrderDrugInfo> mDrugList = new ArrayList<>();

    public MyDrugChildAdapter(Context mCon) {
        this.mContext = mCon;
        this.mInflater = LayoutInflater.from(mCon);
    }

    public MyDrugChildAdapter(Context mCon, List<OrderDrugInfo> mdatas) {
        this.mContext = mCon;
        this.mInflater = LayoutInflater.from(mCon);
        setDrugData(mdatas);
    }

    public void setDrugData(List<OrderDrugInfo> mdatas) {
        this.mDrugList.clear();
        this.mDrugList.addAll(mdatas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDrugList.size();
    }

    @Override
    public OrderDrugInfo getItem(int i) {
        return mDrugList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (view == null) {
            mHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.pharmacy_child_drug_order_adapter, null);
            mHolder.mDrugIconImg = (ImageView) view.findViewById(R.id.drug_icon);
            mHolder.imgPic = (ImageView)view.findViewById(R.id.img_pic);
            mHolder.mDrugNameTv = (TextView) view.findViewById(R.id.drug_name);
            mHolder.mDrugStanTv = (TextView) view.findViewById(R.id.drug_standard);
            mHolder.mDrugPriceTv = (TextView) view.findViewById(R.id.drug_price_single);
            mHolder.mDrugCountTv = (TextView) view.findViewById(R.id.drug_count);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        OrderDrugInfo mDrugGood = getItem(i);
        if (mDrugGood.getDimImage() != null) {
            ImageLoader.getInstance().displayImage(mDrugGood.getDimImage().getThumbPath(),
                    mHolder.mDrugIconImg, ImageDisOpt.getDefaultImgDisOpt());
        }
        if(!TextUtils.isEmpty(mDrugGood.getMedicalInsuranceCode())){
            mHolder.imgPic.setVisibility(View.VISIBLE);
        }
        mHolder.mDrugNameTv.setText(mDrugGood.getDisplayName());
        if (mDrugGood.getDrDrugBase() != null) {
            mHolder.mDrugStanTv.setText(mDrugGood.getDrDrugBase().getNorms());
        }
        mHolder.mDrugPriceTv.setText("¥" +StringUtils.format(StringUtils.formatDouble(mDrugGood.getUpPrice())));
        mHolder.mDrugCountTv.setText("x" + mDrugGood.getDrugNum());

        return view;
    }


    class ViewHolder {
        ImageView mDrugIconImg,imgPic;
        TextView mDrugNameTv, mDrugStanTv, mDrugPriceTv, mDrugCountTv;
    }

}
