package com.ebaonet.pharmacy.ui.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.util.JsonUtil;

/**
 * @author yao.feng
 * 
 *         2015-9-8
 */
public class SharedHelper {


	/**
	 * 获取首页轮播图数据
	 * @param mContext
	 * @return
	 *//*
	public static IndexInfoEntity getIndexInfo(Context mContext) {
		SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
		String data = sp.getString("indexinfo", "");
		if (!TextUtils.isEmpty(data)) {//有数据
			return JsonUtil.toBean(data, IndexInfoEntity.class);
		}
		return null;
	}
	
	public static void saveIndexInfo(Context mContext,String info){
		SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString("indexinfo",info).commit();
	}*/

	/**
	 * 获取配置信息数据
	 * @param mContext
	 * @return
	 */
	public static ConfigInfo getConfigInfo(Context mContext){
		SharedPreferences sp = mContext.getSharedPreferences("config",Context.MODE_PRIVATE);
		String data = sp.getString("configinfo","");
		if(!TextUtils.isEmpty(data)){
			return JsonUtil.toBean(data,ConfigInfo.class);
		}
		return null;
	}

	/**
	 * 保存配置信息数据
	 * @param mContext
	 * @param info
	 */
	public static void saveConfigInfo(Context mContext,String info){
		SharedPreferences sp = mContext.getSharedPreferences("config",Context.MODE_PRIVATE);
		sp.edit().putString("configinfo",info).commit();
	}

}
