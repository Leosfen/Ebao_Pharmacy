package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class InsertWebView extends FrameLayout {

    private WebView webView;
    private Context mContext;
    private CustomProgressDialog mProgressDialog;
    private onTitleChangedListener onTitleChangedListener;

    public InsertWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InsertWebView(Context context) {
        super(context);
        init(context);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(mContext);
            mProgressDialog.setMessage("请稍候...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing()) {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            Window mWindow = mProgressDialog.getWindow();
            if (mWindow == null) {
                return;
            }
            if (mWindow.getDecorView() == null) {
                return;
            }
            if (!mWindow.getDecorView().isAttachedToWindow()) {
                return;
            }
            mProgressDialog.dismiss();
        }
    }

    private void init(Context context) {
        this.mContext = context;
        setWebView(new WebView(getContext()));
        getWebView().setLayoutParams(
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        WebSettings settings = getWebView().getSettings();
        // webView.addJavascriptInterface();
        settings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        settings.setAllowFileAccess(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);

        getWebView().setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissProgressDialog();
                /*if (onTitleChangedListener != null) {
                    onTitleChangedListener.getCurrentUrl(url);
                }*/
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgressDialog();
                if (onTitleChangedListener != null) {
                    onTitleChangedListener.getCurrentUrl(url);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

        });

        getWebView().setWebChromeClient(new WebChromeClient() {

            @Override
            // 设置网页加载的进度条
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            // 设置应用程序的标题title
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (onTitleChangedListener != null) {
                    onTitleChangedListener.onTitleChanged(title);
                }
            }

            public void onExceededDatabaseQuota(String url, String databaseIdentifier,
                                                long currentQuota, long estimatedSize, long totalUsedQuota,
                                                WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(estimatedSize * 2);
            }

            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota,
                                                 WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(spaceNeeded * 2);
            }

        });
        // 覆盖默认后退按钮的作用，替换成WebView里的查看历史页面
        getWebView().setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_BACK) && getWebView().canGoBack()) {
                        getWebView().goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        addView(getWebView(),
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public void load(String url) {
        webView.loadUrl(url);
    }

    public void setOnTitleChangedListner(onTitleChangedListener onTitleChangedListener) {
        this.onTitleChangedListener = onTitleChangedListener;
    }

    public interface onTitleChangedListener {
        void onTitleChanged(String title);

        void getCurrentUrl(String url);
    }

}
