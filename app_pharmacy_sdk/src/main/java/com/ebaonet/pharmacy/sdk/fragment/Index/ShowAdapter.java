package com.ebaonet.pharmacy.sdk.fragment.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;

/**
 * 首页gridview通用适配器
 * Created by zhaojun.gao on 2016/10/11.
 */
public class ShowAdapter extends BaseAdapter {
    private Context mContext;
    private int which;
    private ArrayList<ShowBean> mDefaultShow = new ArrayList<ShowBean>();
    private LayoutInflater mInflater;
    private int mGvHeight;
    public ShowAdapter(Context mContext,int which) {
        this.mContext = mContext;
        this.which = which;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadDefaultShow();
        mGvHeight = (int) mContext.getResources().getDimension(R.dimen.show_grid_item_width);
    }

    private void loadDefaultShow(){
        switch (which){
            case ShowConfig.INDEX_TOP://首页轮播图下gridview
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "老人", ShowConfig.OLDER));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "女性", ShowConfig.WOMEN));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "男性", ShowConfig.MAM));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "儿童", ShowConfig.CHILD));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "老人2", ShowConfig.OLDER2));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "女性2", ShowConfig.WOMEN2));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "男性2", ShowConfig.MAM2));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "儿童2", ShowConfig.CHILD2));
                break;
            case ShowConfig.INDEX_MIDDLE://中间gridview
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "医保专区", ShowConfig.ONE));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "隐形眼镜", ShowConfig.TWO));
                mDefaultShow.add(new ShowBean(R.drawable.pharmacy_function_pension_normal, "医疗器械", ShowConfig.THREE));
                 
        }
        addRemainItem(mDefaultShow.size());
    }

    private void addRemainItem(int size) {
        if (which != ShowConfig.INDEX_MIDDLE) {
            int remain = size % 4;
            if (remain != 0) {
                int mRemain = 4 - remain;
                for (int i = 0; i < mRemain; i++) {
                    mDefaultShow.add(new ShowBean(0, "", -1));
                }
            }
        }
    }
    
    @Override
    public int getCount() {
        return mDefaultShow.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            if(which == ShowConfig.INDEX_TOP){
                convertView = mInflater.inflate(R.layout.pharmacy_item_show_home_top, null);
            }else if(which == ShowConfig.INDEX_MIDDLE){
                convertView = mInflater.inflate(R.layout.pharmacy_item_show_home_middle, null);
            }
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.item_hall_img);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.item_hall_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mDefaultShow.get(position).getImgPath() == 0) {
            viewHolder.mImg.setVisibility(View.INVISIBLE);
            viewHolder.mText.setVisibility(View.INVISIBLE);
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            viewHolder.mImg.setVisibility(View.VISIBLE);
            viewHolder.mText.setVisibility(View.VISIBLE);
            viewHolder.mImg.setImageResource(mDefaultShow.get(position).getImgPath());
            viewHolder.mText.setText(mDefaultShow.get(position).getTitle());
            convertView.setBackgroundResource(R.drawable.pharmacy_layout_click_bg_01);
        }
        convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, mGvHeight));
        convertView.setId(mDefaultShow.get(position).getId());
        return convertView;
    }

    class ViewHolder {
        ImageView mImg;
        TextView mText;
        TextView mDesc;
    }
}
