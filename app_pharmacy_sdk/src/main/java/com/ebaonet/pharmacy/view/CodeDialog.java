package com.ebaonet.pharmacy.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebaonet.pharmacy.sdk.R;

/**
 * 二维码放大dialog
 * Created by peng.dong on 2016/8/17.
 */
public class CodeDialog extends Dialog{
    private Context mContext;
    private Bitmap mBitmap;
    public CodeDialog(Context context,Bitmap bitmap) {
        super(context, R.style.CustomDialog);
        this.mContext = context;
        mBitmap=bitmap;
        init();
    }
    
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pharmacy_dialog_qrcode, null);
        setContentView(view);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels); // 高度设置为屏幕的0.6
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.layout);
        ImageView imgview =new ImageView(mContext);
        imgview.setLayoutParams(new RelativeLayout.LayoutParams(lp.width,lp.width));
        imgview.setImageBitmap(mBitmap);
        imgview.setScaleType(ImageView.ScaleType.FIT_XY);
        rl.addView(imgview);
        dialogWindow.setAttributes(lp);
    }
    


}
