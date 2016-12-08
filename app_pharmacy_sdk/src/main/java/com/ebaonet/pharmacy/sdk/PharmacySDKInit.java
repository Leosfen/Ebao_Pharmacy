package com.ebaonet.pharmacy.sdk;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.ebaonet.pharmacy.logger.LogLevel;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.logger.MyLogTool;
import com.ebaonet.pharmacy.util.Utils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by yao.feng on 2016/8/8.
 */
public class PharmacySDKInit {

    public static final String IMAGE_LOADER = "image";

    public static final String SDK_CACHE = "cache";

    private static final String LOGGER_TAG = "Pharmacy_SDK";

    public static String getCachePath(Context mContext, String folderName) {
        return Utils.getDiskFilesDir(mContext, folderName);
    }

    public static void init(Context mContext) {
        initLogger(true);
        //初始化百度地图SDK
        SDKInitializer.initialize(mContext.getApplicationContext());
        //初始化ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCache(new UnlimitedDiskCache(new File(getCachePath(mContext, IMAGE_LOADER))))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPoolSize(3)
                .memoryCache(new LRULimitedMemoryCache(1 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(1080, 1080)
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * @param mContext
     * @param debug    请用当前主应用工程下的BuildConfig.DEBUG
     */
    public static void init(Context mContext, boolean debug) {
        init(mContext);
        initLogger(debug);
    }

    private static void initLogger(boolean debug) {
        //初始化log日志管理工具
        Logger.init(LOGGER_TAG).logLevel(debug ? LogLevel.FULL : LogLevel.NONE)
                .hideThreadInfo().setMethodCount(0).logTool(new MyLogTool());
    }

}
