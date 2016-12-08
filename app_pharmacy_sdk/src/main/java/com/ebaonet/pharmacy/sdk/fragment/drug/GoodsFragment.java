package com.ebaonet.pharmacy.sdk.fragment.drug;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ebaonet.pharmacy.sdk.R;
import java.util.ArrayList;
import java.util.List;

/**
 * DrugDetailActivity中-----商品fragment
 * Created by zhaojun.gao on 2016/8/31.
 */
public class GoodsFragment extends LazyFragment {
    // 标志fragment是否初始化完成
    private boolean isPrepared;
    private View view;
    private ViewPager mGoods_viewpager_in;
    private static final int[] imagesId = {R.drawable.pharmacy_symptom1, R.drawable.pharmacy_symptom2, R.drawable.pharmacy_symptom3};
    private TextView mTv_des;
    private LinearLayout mLl_point;
    private List<ImageView> imageList;
    private ImageView image;
    private ImageView image2;
    private int lastposition = 0;//上一个位置


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.pharmacy_fragment_detail_goods, container, false);
            Log.e("TAG", "oneFragment--onCreateView");
            isPrepared = true;
            lazyLoad();
        }
        return view;
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        //否则初始化数据
        mGoods_viewpager_in = (ViewPager) view.findViewById(R.id.goods_viewpager_in);
        mTv_des = (TextView) view.findViewById(R.id.tv_des);
        mTv_des.setText("第一个图");
        mLl_point = (LinearLayout) view.findViewById(R.id.ll_point);
        mLl_point.removeAllViews();
        imageList = new ArrayList<ImageView>();
        for (int i = 0; i < imagesId.length; i++) {
            //图片依次添加到list集合中，最终在pageradapter的instantiateItem()方法中设置
            image = new ImageView(this.getActivity());
            image.setBackgroundResource(imagesId[i]);
            imageList.add(image);

            image2 = new ImageView(this.getActivity());
            image2.setBackgroundResource(R.drawable.pharmacy_select_point);

            //设置image2的margin
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 15;
            image2.setLayoutParams(lp);

            if (i != 0) {
                image2.setEnabled(false);
            }
            //把image2设置为ll的子节点，iv才会显示在界面上
            mLl_point.addView(image2);
            Log.e("mLl_point", "*************" + mLl_point.getChildCount());
        }
        mGoods_viewpager_in.setAdapter(new MyAdapter());
        //viewPager的滑动侦听
        mGoods_viewpager_in.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                //在界面切换时改变原点的颜色
                mLl_point.getChildAt(position).setEnabled(true);
                mLl_point.getChildAt(lastposition).setEnabled(false);
                lastposition = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Log.e("TAG", "oneFragment--lazyLoad");
    }

    class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);//一定不能少，将view加入到viewPager中
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
            //container.removeView(object);也可以
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
