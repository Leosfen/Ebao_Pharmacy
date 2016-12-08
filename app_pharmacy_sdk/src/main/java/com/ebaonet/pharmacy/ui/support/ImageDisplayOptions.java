package com.ebaonet.pharmacy.ui.support;

import android.text.TextUtils;

import com.ebaonet.pharmacy.sdk.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

//import cn.ebaonet.base.request.config.KFRequestConfig;

public class ImageDisplayOptions {

    /**
     * 缩略图的管理器
     */
    public DisplayImageOptions defaultImageDisplayOptions; // 默认图片参数
    public DisplayImageOptions selectImageDisplayOptions; // 发送信息选择图片参数
    public DisplayImageOptions photoOptions; // 发送信息选择图片参数
    public DisplayImageOptions mVerifyCodeOp;

    private ImageDisplayOptions() {
        initDisplayImageOptions();
    }

    public final static ImageDisplayOptions options = new ImageDisplayOptions();

    public static ImageDisplayOptions getInstance() {
        return options;
    }

    /**
     * 显示时的参数设置
     *
     * @param context
     * @param screenWidth
     * @param screenHeight
     */
    protected void initDisplayImageOptions() {
        defaultImageDisplayOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.pharmacy_find_hospital)
                .showImageForEmptyUri(R.drawable.pharmacy_find_hospital)
                .showImageOnFail(R.drawable.pharmacy_find_hospital).cacheInMemory(true).cacheOnDisk(true)
                .build();
        selectImageDisplayOptions = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.color.default_color)
                // .showImageForEmptyUri(R.drawable.message_add_pic)
                // .showImageOnFail(R.color.default_color)
                .cacheInMemory(true).considerExifParams(true).cacheOnDisk(true).build();
        photoOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                .showImageOnLoading(R.drawable.pharmacy_touxiang).showImageForEmptyUri(R.drawable.pharmacy_touxiang)
                .showImageOnFail(R.drawable.pharmacy_touxiang).considerExifParams(true).cacheOnDisk(true)
                .build();

        mVerifyCodeOp = new DisplayImageOptions.Builder()
                .showImageOnLoading(0).showImageForEmptyUri(R.drawable.pharmacy_verifycode_load_fail)
                .showImageOnFail(R.drawable.pharmacy_verifycode_load_fail).considerExifParams(true)
                .build();
    }

    /**
     * 获取原图url
     *
     * @param image_id
     * @return
     */
    public static String getImageUrl(String image_id) {
        if (TextUtils.isEmpty(image_id) || "null".equalsIgnoreCase(image_id)) {
            return "";
        }
//        return KFRequestConfig.IMAGE.replace("{image_id}", image_id);
        return image_id;
    }
//
//    /**
//     * 获取缩略图url
//     *
//     * @param image_id
//     * @return
//     */
//    public static String getThumbUrl(String image_id) {
//        if (TextUtils.isEmpty(image_id) || "null".equalsIgnoreCase(image_id)) {
//            return "";
//        }
//        return KFRequestConfig.THUMBIMAGE.replace("{image_id}", image_id);
//    }

}
