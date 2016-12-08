package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.drug.sort.level2.CateleveltwoInfo;
import com.ebaonet.pharmacy.entity.drug.sort.level2.CateleveltwoInfo.SonCateInfos;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.SortDetailActivity;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/12.
 */
public class MyGridviewAdapter extends BaseAdapter {
    private Context mContext;

    public MyGridviewAdapter(Context context) {
        mContext = context;
    }

    private List<CateleveltwoInfo.SonCateInfos> mlist = new ArrayList<CateleveltwoInfo.SonCateInfos>();
    private ImageLoader mImageLoader;

    public void setDefault(List<CateleveltwoInfo.SonCateInfos> list) {
        mlist.clear();
        if (list != null) {
            mlist.addAll(list);
        }
        mImageLoader = ImageLoader.getInstance();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public SonCateInfos getItem(int position) {
        return mlist.get(position);
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
            convertView = View.inflate(mContext, R.layout.pharmcy_item_module, null);
            viewHolder.img = (CircleImageView) convertView.findViewById(R.id.pharmcy_img_icon);
            viewHolder.textview = (TextView) convertView.findViewById(R.id.pharmcy_tv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        //给控件设置数据这里默认显示xml中的
        SonCateInfos mSci = mlist.get(position);
        if (!TextUtils.isEmpty(mSci.getCateName())) {
            String mCn = mSci.getCateName();
            if (mCn.length() > 5) {
                viewHolder.textview.setText(mCn.substring(0, 4) + "...");
            } else {
                viewHolder.textview.setText(mCn);
            }
        } else {
            viewHolder.textview.setText("");
        }
        String thumbPath = mSci.getThumbPath();
        //图片
        mImageLoader.displayImage(mSci.getThumbPath(), viewHolder.img, ImageDisOpt.getSortImgDisOpt());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SortDetailActivity.class);
                intent.putExtra(SortDetailActivity.THIRD_LEVEL_OBJ, getItem(position));//三级分类id，因目前无数据可获取，暂定1
                intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.TWO_LEVEL_SORT);
                intent.putExtra("name",getItem(position).getCateName());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        /*ImageView img;*/
        CircleImageView img;
        TextView textview;
    }
}

