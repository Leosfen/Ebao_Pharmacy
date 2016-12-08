package com.ebaonet.pharmacy.sdk.fragment.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.index.IndexInfoEntity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.DrugInfoActivity;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.UIUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页页面所有gridview的通用适配器
 * Created by zhaojun.gao on 2016/10/11.
 */
public class MyShowAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ShowBean> mDefaultList = new ArrayList();
    private List<IndexInfoEntity.DataBean.RecommendConfigBean> recommendList = new ArrayList<>();
    private List<IndexInfoEntity.DataBean.SpecailConfigBean> specialList = new ArrayList<>();
    private List<IndexInfoEntity.DataBean.SalesConfigBean.DrugDsBean> saleList = new ArrayList<>();//促销
    private List<IndexInfoEntity.DataBean.ClassesConfigBean> classesList = new ArrayList<>();//类别
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private int which;

    public MyShowAdapter(Context mContext, int which) {
        this.which = which;
        this.mContext = mContext;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = ImageLoader.getInstance();
    }

    /**
     * 设置类别商品数据
     */
    public void setClassesList(List<IndexInfoEntity.DataBean.ClassesConfigBean> list, int which) {
        this.which = which;
        classesList.clear();
        if (list != null) {
            classesList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置促销商品数据
     */
    public void setSaleList(List<IndexInfoEntity.DataBean.SalesConfigBean.DrugDsBean> list, int which) {
        this.which = which;
        saleList.clear();
        if (list != null) {
            saleList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /***
     * 设置专题区数据
     */
    public void setSpecialList(List<IndexInfoEntity.DataBean.SpecailConfigBean> list, int which) {
        this.which = which;
        specialList.clear();
        if (list != null) {
            specialList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /***
     * 设置推荐药品数据
     */
    public void setRecommendList(List<IndexInfoEntity.DataBean.RecommendConfigBean> list, int which) {
        this.which = which;
        recommendList.clear();
        if (list != null) {
            recommendList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (which == ShowConfig.INDEX_TOP) {
            if (classesList.size() % 4 != 0 && classesList.size() > 4 || classesList.size() > 4) {
                if (classesList.size() > 8) {
                    return 8;
                }
                return (classesList.size() / 4) * 4;
            }
            return classesList.size();
        } else if (which == ShowConfig.INDEX_MIDDLE) {
            if (specialList.size() % 3 != 0 && specialList.size() > 3) {
                return (specialList.size() / 3) * 3;
            }
            return specialList.size();
        } else if (which == ShowConfig.INDEX_SALE) {
            if (saleList.size() >= 3) {
                return 3;
            }
            return saleList.size();
        } else if (which == ShowConfig.INDEX_BOTTOM) {
            if (recommendList.size() % 3 != 0 && recommendList.size() > 3) {
                return (recommendList.size() / 3) * 3;
            }
            return recommendList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (which == ShowConfig.INDEX_BOTTOM || which == ShowConfig.INDEX_SALE) {
                convertView = mInflater.inflate(R.layout.pharmacy_item_show_home_bottom, null);
            } else if (which == ShowConfig.INDEX_TOP) {//分类
                convertView = mInflater.inflate(R.layout.pharmacy_item_show_home_top, null);
            } else if (which == ShowConfig.INDEX_MIDDLE) {//专题
                convertView = mInflater.inflate(R.layout.pharmacy_item_show_home_middle, null);
            }
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.item_hall_img);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.item_hall_text);
            viewHolder.mDesc = (TextView) convertView.findViewById(R.id.item_hall_describe);
            viewHolder.mDesc2 = (TextView) convertView.findViewById(R.id.item_hall_describe2);
            viewHolder.mImgPic = (ImageView) convertView.findViewById(R.id.img_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (which == ShowConfig.INDEX_TOP) {
            final IndexInfoEntity.DataBean.ClassesConfigBean classesConfigBean = classesList.get(position);
            final String name = classesConfigBean.getName();
            //接口字段待修改
            final String imagePath = classesConfigBean.getImagePath();
            viewHolder.mText.setText(name);
            if (imagePath != null && !imagePath.equals("")) {
                mImageLoader.displayImage(imagePath, viewHolder.mImg, ImageDisOpt.getDefaultImgDisOpt());
            }

        } else if (which == ShowConfig.INDEX_MIDDLE) {
            final IndexInfoEntity.DataBean.SpecailConfigBean specailConfigBean = specialList.get(position);
            mImageLoader.displayImage(specailConfigBean.getImagePath(), viewHolder.mImg, ImageDisOpt.getDefaultImgDisOpt());
        } else if (which == ShowConfig.INDEX_SALE) {
            final IndexInfoEntity.DataBean.SalesConfigBean.DrugDsBean drugDsBean = saleList.get(position);
            final String standardName = drugDsBean.getStandardName();
            final String normPrice = drugDsBean.getNormPrice();
            final String upPrice = drugDsBean.getUpPrice();
            final String medicalInsuranceCode = drugDsBean.getMedicalInsuranceCode();
            if (!TextUtils.isEmpty(medicalInsuranceCode)) {
                viewHolder.mImgPic.setVisibility(View.VISIBLE);
            }
            mImageLoader.displayImage(drugDsBean.getImagePath(), viewHolder.mImg, ImageDisOpt.getSortImgDisOpt());
            viewHolder.mText.setText(UIUtils.toDBC(standardName));
            viewHolder.mDesc.setText("¥" + upPrice);
            if (normPrice.equals(upPrice)) {
                viewHolder.mDesc2.setVisibility(View.INVISIBLE);
            }
            viewHolder.mDesc2.setText("¥" + normPrice);
            viewHolder.mDesc2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DrugInfoActivity.class);
                    intent.putExtra("drugid", drugDsBean.getDrugDsId());
                    mContext.startActivity(intent);
                }
            });
        } else if (which == ShowConfig.INDEX_BOTTOM) {
            final IndexInfoEntity.DataBean.RecommendConfigBean recommendConfigBean = recommendList.get(position);
            final String imagePath = recommendConfigBean.getImagePath();
            final String normPrice = recommendConfigBean.getNormPrice();
            final String upPrice = recommendConfigBean.getUpPrice();
            final String medicalInsuranceCode = recommendConfigBean.getMedicalInsuranceCode();
            if (!TextUtils.isEmpty(medicalInsuranceCode)) {
                viewHolder.mImgPic.setVisibility(View.VISIBLE);
            }
            mImageLoader.displayImage(imagePath, viewHolder.mImg, ImageDisOpt.getSortImgDisOpt());
            viewHolder.mText.setText(UIUtils.toDBC(recommendConfigBean.getStandardName()));
            viewHolder.mDesc.setText("¥" + upPrice);
            if (normPrice.equals(upPrice)) {
                viewHolder.mDesc2.setVisibility(View.INVISIBLE);
            }
            viewHolder.mDesc2.setText("¥" + normPrice);
            viewHolder.mDesc2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DrugInfoActivity.class);
                    intent.putExtra("drugid", recommendConfigBean.getDrugDsId());
                    mContext.startActivity(intent);
                }
            });
        }
        //设置item的高度和宽度
        if (which == ShowConfig.INDEX_MIDDLE) {
            convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                    UIUtils.getScreenWidth(mContext) / 3));
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mImg, mImgPic;
        TextView mText;
        TextView mDesc, mDesc2;
    }
}
