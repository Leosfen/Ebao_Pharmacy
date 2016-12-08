package com.ebaonet.pharmacy.request;

import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;

import java.io.File;

public class CustomVolley {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public CustomVolley() {
    }

    /**
     * 无缓存的网络请求
     *
     * @param stack
     * @return
     */
    public static RequestQueue newRequestQueue(HttpStack stack) {
        String userAgent = "volley/0";

        if (stack == null) {
            if (VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        BasicNetwork network1 = new BasicNetwork(stack);
        RequestQueue queue1 = new RequestQueue(new NoCache(), network1);
        queue1.start();
        return queue1;
    }

    public static RequestQueue newRequestQueueInDisk(String dir, HttpStack stack) {
        File cacheDir = new File(dir, "volley");
        String userAgent = "volley/0";

        if (stack == null) {
            if (VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        BasicNetwork network1 = new BasicNetwork(stack);
        RequestQueue queue1 = new RequestQueue(new DiskBasedCache(cacheDir), network1);
        queue1.start();
        return queue1;
    }

    public static RequestQueue newRequestQueue() {
        return newRequestQueue(null);
    }
}
