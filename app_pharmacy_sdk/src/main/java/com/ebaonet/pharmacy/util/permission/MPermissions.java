package com.ebaonet.pharmacy.util.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;


import java.util.ArrayList;
import java.util.List;


/**
 * 2016年11月22日16:28:32
 */
public class MPermissions {

    /**
     * 权限允许
     */
    public final static int GRANTED = 0xffff;
    /**
     * 权限被禁止
     */
    public final static int DENIED = 0xffff + 1;

    /**
     * 请求定位权限code码
     * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
     * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
     */
    public static final int REQUEST_LOCATION_CODE = 0xff;

    /**
     * 请求相机权限code码
     * <uses-permission android:name="android.permission.CAMERA"/>
     * <uses-permission android:name="android.permission.FLASHLIGHT"/>
     * <uses-permission android:name="android.permission.VIBRATE"/>
     */
    public static final int REQUEST_CAMERA_CODE = 0xff + 1;

    /**
     * 请求读写内存卡权限code码
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    public static final int REQUEST_STORAGE_CODE = 0xff + 2;

    /**
     * 请求获取手机信息权限code码
     * permission:android.permission.READ_CALL_LOG
     * permission:android.permission.READ_PHONE_STATE
     * permission:android.permission.CALL_PHONE
     * permission:android.permission.WRITE_CALL_LOG
     * permission:android.permission.USE_SIP
     * permission:android.permission.PROCESS_OUTGOING_CALLS
     * permission:com.android.voicemail.permission.ADD_VOICEMAIL
     */
    public static final int REQUEST_PHONE_CODE = 0xff + 3;

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permission)) {
            return true;
        }
        return false;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    private static void requestPermissions(Object object, int requestCode, PermissionProxy iPerPro,
                                           String... permissions) {
        if (!Utils.isOverMarshmallow()) {//小于23版本
            if (iPerPro != null) {
                iPerPro.OnPermissionProxyResult(GRANTED, requestCode);
            }
            return;
        }
        List<String> deniedPermissions = Utils.findDeniedPermissions(Utils.getActivity(object), permissions);

        if (deniedPermissions.size() > 0) {
            if (object instanceof Activity) {
                ((Activity) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else if (object instanceof Fragment) {
                ((Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                throw new IllegalArgumentException(object.getClass().getName() + " is not supported!");
            }
        } else {
            if (iPerPro != null) {
                iPerPro.OnPermissionProxyResult(GRANTED, requestCode);
            }
        }
    }


    public static void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                  int[] grantResults, PermissionProxy iPerPro) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.size() > 0) {
            if (iPerPro != null) {
                iPerPro.OnPermissionProxyResult(DENIED, requestCode);
            }
        } else {
            if (iPerPro != null) {
                iPerPro.OnPermissionProxyResult(GRANTED, requestCode);
            }
        }
    }


    /**
     * 请求定位权限
     *
     * @param object
     * @param iPerPro
     */
    public static void requestLocationPermission(Object object, PermissionProxy iPerPro) {
        requestPermissions(object, REQUEST_LOCATION_CODE, iPerPro,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 请求相机权限
     *
     * @param object
     * @param iPerPro
     */
    public static void requestCameraPermission(Object object, PermissionProxy iPerPro) {
        requestPermissions(object, REQUEST_CAMERA_CODE, iPerPro, Manifest.permission.CAMERA,
                Manifest.permission.FLASHLIGHT, Manifest.permission.VIBRATE);
    }

    /**
     * 请求内存卡权限
     *
     * @param object
     * @param iPerPro
     */
    public static void requestStoragePermission(Object object, PermissionProxy iPerPro) {
        requestPermissions(object, REQUEST_STORAGE_CODE, iPerPro,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 请求获取手机IMEI等信息的权限
     *
     * @param object
     * @param iPerPro
     */
    public static void requestPhonePermission(Object object, PermissionProxy iPerPro) {
        requestPermissions(object, REQUEST_STORAGE_CODE, iPerPro,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE);
    }


}
