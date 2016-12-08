package com.ebaonet.pharmacy.sdk.image;

import android.graphics.Bitmap;

import com.ebaonet.pharmacy.sdk.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageDisOpt {

    private static DisplayImageOptions mDefaultImgDisOpt;//默认的图片展示参数

    private static DisplayImageOptions mSortImgDisOpt; // 购药分类展示图片参数

    private static DisplayImageOptions mDetailImgDisOpt; // 详情页轮播图展示图片参数

    public final static ImageDisOpt options = new ImageDisOpt();


    /**
     * 获取分类图片显示参数
     *
     * @return
     */
    public static DisplayImageOptions getSortImgDisOpt() {
        if (mSortImgDisOpt == null) {
            mSortImgDisOpt = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.pharmacy_list_pic_default_02)
                    .showImageForEmptyUri(R.drawable.pharmacy_list_pic_default_02)
                    .showImageOnFail(R.drawable.pharmacy_list_pic_default_02)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return mSortImgDisOpt;
    }

    /**
     * 默认的图片展示参数，有缓存
     *
     * @return
     */
    public static DisplayImageOptions getDefaultImgDisOpt() {
        if (mDefaultImgDisOpt == null) {
            mDefaultImgDisOpt = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return mDefaultImgDisOpt;
    }

    /**
     * 详情页的图片展示参数，有缓存
     *
     * @return
     */
    public static DisplayImageOptions getDetailImgDisOpt() {
        if (mDetailImgDisOpt == null) {
            mDetailImgDisOpt = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.pharmacy_goods_pic_default)
                    .showImageForEmptyUri(R.drawable.pharmacy_goods_pic_default)
                    .showImageOnFail(R.drawable.pharmacy_goods_pic_default)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return mDefaultImgDisOpt;
    }


}
