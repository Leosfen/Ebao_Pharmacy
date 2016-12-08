package com.ebaonet.online.pharmacy;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.ebaonet.pharmacy.sdk.PharmacySDKInit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by yao.feng on 2016/8/22.
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        PharmacySDKInit.init(getApplicationContext(), BuildConfig.DEBUG);
        crash();
    }

    private void crash() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @SuppressWarnings("deprecation")
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                FileOutputStream out = null;
                try {
                    // Logger.e(TAG, ex);
                    File file = new File(PharmacySDKInit.getCachePath(getApplicationContext(),
                            PharmacySDKInit.SDK_CACHE), "pharmacy_sdk.txt");
                    out = new FileOutputStream(file, true);
                    out.write((new Date().toLocaleString() + "\n").getBytes());
                    out.write(Log.getStackTraceString(ex).getBytes());
                    out.write("\n\n".getBytes());
                    out.flush();
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, ex);
                } catch (IOException e) {
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        });
    }
}
