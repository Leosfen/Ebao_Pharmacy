package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.drug.sort.DrugSearchEntity;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugThreeListInfo;
import com.ebaonet.pharmacy.entity.order.CreateOrderEntry;
import com.ebaonet.pharmacy.manager.DrugManager;
import com.ebaonet.pharmacy.manager.params.DrugParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.SortDetailActivity;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/26.
 */
public class DrugListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DrugThreeListInfo> mList = new ArrayList<DrugThreeListInfo>();
    private List<DrugSearchEntity.DataBean> mList2 = new ArrayList<DrugSearchEntity.DataBean>();//搜索
    private List<CreateOrderEntry.DataBean.DrugBean> mList3 = new ArrayList<>();//确认订单商品列表
    private ImageLoader mImageLoader;
    private int flag;

    public DrugListAdapter(Context context) {
        mContext = context;
        mImageLoader = ImageLoader.getInstance();
    }

    public void setAdaptData(List<DrugThreeListInfo> list, int tag) {
        flag = tag;
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setAdaptData2(List<DrugSearchEntity.DataBean> list, int tag) {
        flag = tag;
        mList2.clear();
        if (list != null) {
            mList2.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setAdaptData3(List<CreateOrderEntry.DataBean.DrugBean> list, int tag) {
        flag = tag;
        mList3.clear();
        if (list != null) {
            mList3.addAll(list);
        }
        notifyDataSetChanged();
    }


    public void addAdaptData(List<DrugThreeListInfo> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAdaptData2(List<DrugSearchEntity.DataBean> list) {
        mList2.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (flag == SortDetailActivity.TWO_LEVEL_SORT) {
            return mList.size();
        } else if (flag == SortDetailActivity.CONFIRM_ORDER_LIST) {
            return mList3.size();
        }

        return mList2.size();
    }

    @Override
    public Object getItem(int position) {
        if (flag == SortDetailActivity.TWO_LEVEL_SORT) {
            return mList.get(position);
        } else if (flag == SortDetailActivity.CONFIRM_ORDER_LIST) {
            return mList3.get(position);
        }
        return mList2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.pharmacy_item_druglist, null);
            convertView.setTag(viewHolder);
            viewHolder.name = (TextView) convertView.findViewById(R.id.drug_name);
            viewHolder.des = (TextView) convertView.findViewById(R.id.drug_des);
            viewHolder.discount = (TextView) convertView.findViewById(R.id.drug_discount);
            viewHolder.price = (TextView) convertView.findViewById(R.id.drug_price);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.drug_img);
            viewHolder.imgdiscount = (ImageView) convertView.findViewById(R.id.img_discount);
            viewHolder.commit = (ImageView) convertView.findViewById(R.id.commit);
            viewHolder.imaPic = (ImageView) convertView.findViewById(R.id.img_pic);
            viewHolder.itemnum = (TextView) convertView.findViewById(R.id.tv_item_num);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//       final DrugThreeListInfo mDtli = getItem(position);
        if (flag == SortDetailActivity.TWO_LEVEL_SORT) {
            final DrugThreeListInfo mDtli = mList.get(position);
            if (!TextUtils.isEmpty(mDtli.medicalInsuranceCode)) {
                viewHolder.imaPic.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imaPic.setVisibility(View.GONE);
            }
            viewHolder.name.setText(mDtli.displayName);
            viewHolder.des.setText(mDtli.drDrugBase.indications);
            viewHolder.discount.setText("¥" + StringUtils.format(mDtli.upPrice));
            //判断是否显示原价
            if (!TextUtils.isEmpty(mDtli.upPrice) && !TextUtils.isEmpty(mDtli.normPrice)) {
                viewHolder.discount.setVisibility(View.VISIBLE);
                viewHolder.discount.setText("¥" + StringUtils.format(mDtli.upPrice));

                if (Double.parseDouble(mDtli.upPrice) == Double.parseDouble(mDtli.normPrice)) {
                    viewHolder.imgdiscount.setVisibility(View.GONE);
                    viewHolder.price.setVisibility(View.GONE);
                } else {
                    viewHolder.imgdiscount.setVisibility(View.VISIBLE);
                    viewHolder.price.setVisibility(View.VISIBLE);
                    viewHolder.price.setText("¥" + StringUtils.format(mDtli.normPrice));
                    viewHolder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            } else {
                //如果，原价和现价有一个为空，价格都不显示
                viewHolder.imgdiscount.setVisibility(View.GONE);
                viewHolder.price.setVisibility(View.GONE);
                viewHolder.discount.setVisibility(View.GONE);
            }
            
            mImageLoader.displayImage(mDtli.dimImage == null ? "" : mDtli.dimImage.imagepath, viewHolder.image, ImageDisOpt.getSortImgDisOpt());
            viewHolder.commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                    RequestParams params = DrugParamsHelper.addCartitem(userInfo == null ? "" : userInfo.getUserId(),
                            mDtli.drugDsId, "1", mDtli.normPrice);
                    DrugManager.getInstance().addCartitem(params);
                }
            });
        } else if (flag == SortDetailActivity.SORT_SEARCH_ACTIVITY) {
            final DrugSearchEntity.DataBean mDtli2 = mList2.get(position);
            viewHolder.name.setText(mDtli2.getStandardName());
            viewHolder.des.setText(mDtli2.getIndications());
            if (!TextUtils.isEmpty(mDtli2.getMedicalInsuranceCode())) {
                viewHolder.imaPic.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imaPic.setVisibility(View.GONE);
            }
            //判断是否显示原价
            if (!TextUtils.isEmpty(mDtli2.getUpPrice()) && !TextUtils.isEmpty(mDtli2.getNormPrice())) {
                viewHolder.discount.setVisibility(View.VISIBLE);
                viewHolder.discount.setText("¥" + StringUtils.format(mDtli2.getUpPrice()));

                if (Double.parseDouble(mDtli2.getUpPrice()) == Double.parseDouble(mDtli2.getNormPrice())) {
                    viewHolder.imgdiscount.setVisibility(View.GONE);
                    viewHolder.price.setVisibility(View.GONE);
                } else {
                    viewHolder.imgdiscount.setVisibility(View.VISIBLE);
                    viewHolder.price.setVisibility(View.VISIBLE);
                    viewHolder.price.setText("¥" + StringUtils.format(mDtli2.getNormPrice()));
                    viewHolder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            } else {
                //如果，原价和现价有一个为空，价格都不显示
                viewHolder.imgdiscount.setVisibility(View.GONE);
                viewHolder.price.setVisibility(View.GONE);
                viewHolder.discount.setVisibility(View.GONE);
            }


            mImageLoader.displayImage(mDtli2.getImagePath() == null ? "" : mDtli2.getImagePath(), viewHolder.image, ImageDisOpt.getSortImgDisOpt());
            viewHolder.commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                    RequestParams params = DrugParamsHelper.addCartitem(userInfo == null ? "" : userInfo.getUserId(),
                            mDtli2.getDrugDsId(), "1", String.valueOf(mDtli2.getUpPrice()));
                    DrugManager.getInstance().addCartitem(params);
                }
            });
        } else if (flag == SortDetailActivity.CONFIRM_ORDER_LIST) {
            CreateOrderEntry.DataBean.DrugBean mDtli3 = mList3.get(position);
            viewHolder.name.setText(mDtli3.getDisplayName());
            //viewHolder.des.setVisibility(View.GONE);
            viewHolder.des.setText(mDtli3.getDrDrugBase().getIndications());
            viewHolder.discount.setText("¥" + StringUtils.formatDouble(mDtli3.getUpPrice()));
            viewHolder.commit.setVisibility(View.GONE);
            viewHolder.itemnum.setVisibility(View.VISIBLE);
            viewHolder.itemnum.setText("x" + mDtli3.getDrugNum());
            if (!TextUtils.isEmpty(mDtli3.getMedicalInsuranceCode())) {
                viewHolder.imaPic.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imaPic.setVisibility(View.GONE);
            }
            if (mDtli3.getUpPrice() == mDtli3.getNormPrice()) {
                viewHolder.imgdiscount.setVisibility(View.GONE);
                viewHolder.price.setVisibility(View.GONE);
            } else {
                viewHolder.imgdiscount.setVisibility(View.VISIBLE);
                viewHolder.price.setVisibility(View.VISIBLE);
                viewHolder.price.setText("¥" + StringUtils.formatDouble(mDtli3.getNormPrice()));
                viewHolder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (mDtli3.getDimImage()!=null){
                mImageLoader.displayImage(mDtli3.getDimImage().getThumbPath() == null ? "" :
                        mDtli3.getDimImage().getThumbPath(), viewHolder.image, ImageDisOpt.getSortImgDisOpt());
            }else {
                mImageLoader.displayImage("", viewHolder.image, ImageDisOpt.getSortImgDisOpt());
            }
        }


        return convertView;
    }


    class ViewHolder {
        TextView name, des, discount, price, itemnum;
        ImageView image, commit, imgdiscount, imaPic;

    }

    public interface onCommitListener {
        public void commit(String userId, String drugId, String quantity, String price);
    }

    public void setOnCommitListener(onCommitListener onDeleteListener) {
        this.onCommitListener = onDeleteListener;
    }

    public onCommitListener getOnDeleteListener() {
        return onCommitListener;
    }

    private onCommitListener onCommitListener;
}
