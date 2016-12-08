package com.ebaonet.pharmacy.web;

import android.os.Bundle;
import android.text.TextUtils;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.InsertWebView;


public class EbaoWebViewActivity extends BaseActivity {

    public static String ExtraWebViewURL = "url";

    public String mCurUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_ebao_webview);
        // setTitle(titleId);
        mCurUrl = getIntent().getStringExtra(ExtraWebViewURL);
        InsertWebView mEbaoWebView = (InsertWebView) findViewById(R.id.iw_eb_webview);

        if (!TextUtils.isEmpty(mCurUrl)) {
            // url = "http://www.ebaonet.cn";
            Logger.d("EbaoWebViewActivity" + "url=" + mCurUrl);
            mEbaoWebView.load(mCurUrl);
        }

//        // 设置网页返回的标题
//        mEbaoWebView.setOnTitleChangedListner(new InsertWebView.onTitleChangedListener() {
//            @Override
//            public void getCurrentUrl(String url) {
//                System.out.println("===============getCurrentUrl========" + url);
//                if (!TextUtils.isEmpty(mCurUrl) && !TextUtils.isEmpty(url) && !mCurUrl.equals(url)) {
//                    btnLeft.setVisibility(View.GONE);
//                } else {
//                    btnLeft.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onTitleChanged(String title) {
////                System.out.println("===============webview========" + title);
////                if (!TextUtils.isEmpty(title)) {
////                    setTitle(title);
////                }
//            }
//        });

    }

}
