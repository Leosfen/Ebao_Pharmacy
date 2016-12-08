/**
 *
 */
package com.ebaonet.pharmacy.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


/**
 *
 */
public class NetworkUtils {

    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_2G = NETWORK_TYPE_NONE + 1;
    public static final int NETWORK_TYPE_3G = NETWORK_TYPE_2G + 1;
    public static final int NETWORK_TYPE_MOBILE = NETWORK_TYPE_3G + 1;
    public static final int NETWORK_TYPE_WIFI = NETWORK_TYPE_MOBILE + 1;
    public static final int NETWORK_TYPE_OTHER = NETWORK_TYPE_WIFI + 1;

    private static ConnectivityManager connectivityMgr;
    private static TelephonyManager telMgr;

    /**
     * 判断网络类型
     */
    public static int getNetWorkType(Context mContext) {

        if (connectivityMgr == null) {
            connectivityMgr = (ConnectivityManager) mContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
        }

        if (connectivityMgr == null) {
            return NETWORK_TYPE_NONE;
        }

        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return NETWORK_TYPE_NONE;
        }

        if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
            if (telMgr == null) {
                telMgr = (TelephonyManager) mContext
                        .getSystemService(Context.TELEPHONY_SERVICE);
            }

            if (telMgr == null) {
                return NETWORK_TYPE_MOBILE;
            }

            int mobileType = telMgr.getNetworkType();
            switch (mobileType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return NETWORK_TYPE_2G;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return NETWORK_TYPE_3G;
                default:
                    return NETWORK_TYPE_MOBILE;
            }
        } else if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
            return NETWORK_TYPE_WIFI;
        } else {
            return NETWORK_TYPE_OTHER;
        }
    }

    public static boolean isNetworkAvailable(Context mContext) {
        if (connectivityMgr == null) {
            connectivityMgr = (ConnectivityManager) mContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
        }

        if (connectivityMgr == null) {
            return false;
        }

        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()
                && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
