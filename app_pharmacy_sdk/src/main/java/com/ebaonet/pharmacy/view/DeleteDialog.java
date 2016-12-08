package com.ebaonet.pharmacy.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ebaonet.pharmacy.sdk.R;

/**
 * Created by peng.dong on 2016/8/17.
 */
public class DeleteDialog extends Dialog{
    Context mContext;
    private TextView tvConfirm,tvCancel,tvtext;
    private ClickListenerInterface clickListenerInterface;
    public DeleteDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.mContext = context;
        init();
    }

    public interface ClickListenerInterface {

        public void doConfirm();

        public void doCancel();
    }
    public void settext(String text){
        tvtext.setText(text);
    }
    public void settext(String text,String text2,String text3){
        tvtext.setText(text);
        tvCancel.setText(text2);
        tvConfirm.setText(text3);
    }
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pharmacy_dialog_delete_addr, null);
        tvConfirm= (TextView) view.findViewById(R.id.dialog_tv_confirm);
        tvCancel= (TextView) view.findViewById(R.id.dialog_tv_cancel);
        tvtext =(TextView) view.findViewById(R.id.dialog_tv_text);
        setContentView(view);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListenerInterface.doConfirm();
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListenerInterface.doCancel();
                dismiss();
            }
        });
    }
    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }


}
