package com.ebaonet.pharmacy.view.infiniteviewpager.myadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebaonet.pharmacy.entity.index.IndexInfoEntity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.DrugInfoActivity;
import com.ebaonet.pharmacy.sdk.activity.SortDetailActivity;
import com.ebaonet.pharmacy.ui.support.ImageDisplayOptions;
import com.ebaonet.pharmacy.view.infiniteviewpager.viewpager.InfinitePagerAdapter;
import com.ebaonet.pharmacy.web.EbaoWebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RxRead on 2015/9/24.
 */
public class CarryPagerAdapter extends InfinitePagerAdapter {

    private final LayoutInflater mInflater;
    private final Context mContext;

    private List<IndexInfoEntity.DataBean.RollImageConfigBean> mList = new ArrayList<>();


    public void setDataList(List<IndexInfoEntity.DataBean.RollImageConfigBean > list) {
        this.mList.clear();
        this.mList.addAll(list);
        this.notifyDataSetChanged();
    }


    public CarryPagerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.pharmacy_item_infinite_viewpager, container, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        final IndexInfoEntity.DataBean.RollImageConfigBean item = mList.get(position);
        holder.position = position;
        
        ImageLoader.getInstance().displayImage(ImageDisplayOptions.getImageUrl(item.getImagePath()), holder.image,
                ImageDisplayOptions.getInstance().selectImageDisplayOptions);
       
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getJumpType().equals("2")){
                    Intent mIntent1 = new Intent(mContext, EbaoWebViewActivity.class);
                    mIntent1.putExtra(EbaoWebViewActivity.ExtraWebViewURL, item.getJumpTypeVal());
                    mContext.startActivity(mIntent1);
                }else if(item.getJumpType().equals("1")||item.getJumpType().equals("4")){//列表
                    Intent mIntent1 = new Intent(mContext, SortDetailActivity.class);
                    mIntent1.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.INDEX_ROLLIMAGE);
                    mIntent1.putExtra("configid",item.getConfigId());
                    Log.e("configid","==================="+item.getConfigId());
                    mContext.startActivity(mIntent1);
                }else if(item.getJumpType().equals("3")){
                    Intent mIntent1 = new Intent(mContext, DrugInfoActivity.class);
                    mIntent1.putExtra("drugid",item.getJumpTypeVal());
                    mContext.startActivity(mIntent1);
                }
            }
        });
        return view;
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    private static class ViewHolder {
        public int position;
        ImageView image;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.item_image);
        }
    }

}
