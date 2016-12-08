package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;

/**
 * 带删除编辑框
 *
 * @author geely
 */
public class EditTextWithDelete extends EditText {
    private Drawable imgEnable;
    private Context context;
    private OnDeleteAllListener onDeleteListener;

    public EditTextWithDelete(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        // 获取图片资源
        imgEnable = context.getResources().getDrawable(R.drawable.pharmacy_ic_search_delete);
        //imgEnable = getCompoundDrawables()[2];
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d("afterTextChanged" + getId());
                if (hasFocus()) {
                    Logger.d("afterTextChanged  is has focus" + getId());
                    setDrawable();
                }
            }
        });
        //setDrawable();
    }

    /**
     * 设置删除图片
     */
    private void setDrawable() {
        if (hasFocusable() && length() != 0) {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], imgEnable,
                    getCompoundDrawables()[3]);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        }
    }

    /**
     * event.getX() 获取相对应自身左上角的X坐标 event.getY() 获取相对应自身左上角的Y坐标 getWidth()
     * 获取控件的宽度 getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离 getPaddingRight()
     * 获取删除图标右边缘到控件右边缘的距离 getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
     * getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgEnable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            // 判断触摸点是否在水平范围内
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight()))
                    && (x < (getWidth() - getPaddingRight()));
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = imgEnable.getBounds();
            // 获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerWidth && isInnerHeight) {
                setText("");
                //判定接口不为空再调用里面的方法
                if (onDeleteListener != null) {
                    onDeleteListener.deleteAll();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            Logger.d("onFocusChanged has focus ");
            setDrawable();
        } else {
            Logger.d("onFocusChanged has no focus");
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * 自定义删除list监听接口
     */
    public interface OnDeleteAllListener {
        void deleteAll();
    }

    public void setOnDeleteListener(OnDeleteAllListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public OnDeleteAllListener getOnDeleteListener() {
        return onDeleteListener;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        if (!focusable) {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        }
    }
}
