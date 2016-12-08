package com.ebaonet.pharmacy.view.tabview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;

/**
 * 菜单控件头部，封装了下拉动画，动态生成头部按钮个数
 *
 * @author yueyueniao
 */

public class ExpandTabView extends LinearLayout implements OnDismissListener {

    private ToggleButton selectedButton;
    private ArrayList<String> mTextArray = new ArrayList<String>();
    private ArrayList<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
    private ArrayList<ToggleButton> mToggleButton = new ArrayList<ToggleButton>();
    private Context mContext;
    private int displayWidth;
    private int displayHeight;
    private PopupWindow popupWindow;
    private int selectPosition;
    private boolean isChange = false;

    private int defalutColor, selectColor;

    public ExpandTabView(Context context) {
        super(context);
        init(context);
    }

    public ExpandTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 根据选择的位置设置tabitem显示的值
     */
    public void setTitle(String valueText, int position) {
        if (position < mToggleButton.size()) {
            mToggleButton.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置获取tabitem显示的值
     */
    public String getTitle(int position) {
        if (position < mToggleButton.size() && mToggleButton.get(position).getText() != null) {
            return mToggleButton.get(position).getText().toString();
        }
        return "";
    }

    /**
     * 设置tabitem的个数和初始值
     */
    public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
        // 避免重复添加内容
        mTextArray.clear();
        mViewArray.clear();
        if (mContext == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mTextArray = textArray;
        for (int i = 0; i < viewArray.size(); i++) {
            final RelativeLayout r = new RelativeLayout(mContext);
            int maxHeight = (int) (displayHeight * 0.6);
            // RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
            // RelativeLayout.LayoutParams.MATCH_PARENT, maxHeight);
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            // rl.leftMargin = 10;
            // rl.rightMargin = 10;
            r.addView(viewArray.get(i), rl);
            mViewArray.add(r);
            RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.pharmacy_toggle_button, this,
                    false);
            ToggleButton tButton = (ToggleButton) view.findViewById(R.id.tBtn);
            ImageView line = new ImageView(mContext);
            line.setImageResource(R.drawable.pharmacy_choosebar_line);
            if (i < viewArray.size() - 1) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                // lp.addRule(RelativeLayout.ALIGN_RIGHT,R.id.tBtn);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                view.addView(line, lp);
            }
            addView(view);
            mToggleButton.add(tButton);
            tButton.setTag(i);
            tButton.setText(mTextArray.get(i));

            r.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressBack();
                }
            });

            r.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));

//			tButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//					ToggleButton tButton = (ToggleButton) buttonView;
//					if (isChecked) {
//						// initPopupWindow();
//						tButton.setTextColor(selectColor);
//						if (selectedButton != null && selectedButton != tButton) {
//							selectedButton.setChecked(false);
//							if (popupWindow != null && popupWindow.isShowing()) {
//								isChange = true;
//							}
//						}
//						selectedButton = tButton;
//						selectPosition = (Integer) selectedButton.getTag();
//						selectedButton.setChecked(true);
//						startAnimation();
//						if (mOnButtonClickListener != null && tButton.isChecked()) {
//							mOnButtonClickListener.onClick(selectPosition);
//						}
//					} else {
//						tButton.setTextColor(defalutColor);
//					}
//
//				}
//			});

            tButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    // initPopupWindow();
                    ToggleButton tButton = (ToggleButton) view;

                    if (selectedButton != null && selectedButton != tButton) {
                        selectedButton.setChecked(false);
                        if (popupWindow != null && popupWindow.isShowing()) {
                            isChange = true;
                        }
                    }
                    selectedButton = tButton;
                    selectPosition = (Integer) selectedButton.getTag();
                    selectedButton.setChecked(true);
                    startAnimation();
                    if (mOnButtonClickListener != null && tButton.isChecked()) {
                        mOnButtonClickListener.onClick(selectPosition);
                    }
                }
            });
        }
    }

    /**
     * 设置tabitem的个数和初始值
     */
    // public void setValue(ArrayList<String> textArray, ArrayList<View>
    // viewArray, double scale) {
    // if (mContext == null) {
    // return;
    // }
    // LayoutInflater inflater = (LayoutInflater) mContext
    // .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //
    // mTextArray = textArray;
    // for (int i = 0; i < viewArray.size(); i++) {
    // final RelativeLayout r = new RelativeLayout(mContext);
    // int maxHeight = (int) (displayHeight * scale);
    // RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
    // RelativeLayout.LayoutParams.MATCH_PARENT, maxHeight);
    // // rl.leftMargin = 10;
    // // rl.rightMargin = 10;
    // r.addView(viewArray.get(i), rl);
    // mViewArray.add(r);
    // RelativeLayout view = (RelativeLayout)
    // inflater.inflate(R.layout.toggle_button, this,
    // false);
    // ToggleButton tButton = (ToggleButton) view.findViewById(R.id.tBtn);
    // ImageView line = new ImageView(mContext);
    // line.setImageResource(R.drawable.choosebar_line);
    // if (i < viewArray.size() - 1) {
    // RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
    // RelativeLayout.LayoutParams.WRAP_CONTENT,
    // RelativeLayout.LayoutParams.WRAP_CONTENT);
    // // lp.addRule(RelativeLayout.ALIGN_RIGHT,R.id.tBtn);
    // lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    // lp.addRule(RelativeLayout.CENTER_VERTICAL);
    // view.addView(line, lp);
    // }
    // addView(view);
    // mToggleButton.add(tButton);
    // tButton.setTag(i);
    // tButton.setText(mTextArray.get(i));
    //
    // r.setOnClickListener(new OnClickListener() {
    // @Override
    // public void onClick(View v) {
    // onPressBack();
    // }
    // });
    //
    // r.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));
    // tButton.setOnClickListener(new OnClickListener() {
    // @Override
    // public void onClick(View view) {
    // // initPopupWindow();
    // ToggleButton tButton = (ToggleButton) view;
    //
    // if (selectedButton != null && selectedButton != tButton) {
    // selectedButton.setChecked(false);
    // }
    // selectedButton = tButton;
    // selectPosition = (Integer) selectedButton.getTag();
    // selectedButton.setChecked(true);
    // startAnimation();
    // if (mOnButtonClickListener != null && tButton.isChecked()) {
    // mOnButtonClickListener.onClick(selectPosition);
    // }
    // }
    // });
    // }
    // }
    private void startAnimation() {

        if (popupWindow == null) {
            popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth,
                    displayHeight);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.setFocusable(true);
            popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			popupWindow.setOutsideTouchable(true);//屏蔽避免点击其他地方popu收起，tab状态错误bug
        }

        if (selectedButton.isChecked()) {
            Log.i("tab", "ischecked.true");
            if (!popupWindow.isShowing()) {
                Log.i("tab", "pop.isNotShow");
                showPopup(selectPosition);
                selectedButton.setChecked(true);
            } else {
                Log.i("tab", "pop.isShow");
                if (isChange) {
                    isChange = false;
                    popupWindow.dismiss();
                    hideView();
                    showPopup(selectPosition);
                    return;
                }
                selectedButton.setChecked(false);
                // popupWindow.setOnDismissListener(this);
                popupWindow.dismiss();
                hideView();
            }
        } else {
            Log.i("tab", "ischecked.false");
            if (popupWindow.isShowing()) {
                Log.i("tab", "pop.isShow");
                popupWindow.dismiss();
                hideView();
            }
        }
    }

    private void showPopup(int position) {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.show();
        }
        if (popupWindow.getContentView() != mViewArray.get(position)) {
            popupWindow.setContentView(mViewArray.get(position));
        }
        // 这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(this, 0, 0);
    }

    /**
     * 如果菜单成展开状态，则让菜单收回去
     */
    public boolean onPressBack() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            hideView();
            if (selectedButton != null) {
                selectedButton.setChecked(false);
            }
            return true;
        } else {
            return false;
        }

    }

    private void hideView() {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.hide();
        }
    }

    private void init(Context context) {
        mContext = context;
        displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        defalutColor = mContext.getResources().getColor(R.color.default_filter_color);
        selectColor = mContext.getResources().getColor(R.color.select_filter_color);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    @Override
    public void onDismiss() {
        showPopup(selectPosition);
        popupWindow.setOnDismissListener(null);
    }

    private OnButtonClickListener mOnButtonClickListener;

    /**
     * 设置tabitem的点击监听事件
     */
    public void setOnButtonClickListener(OnButtonClickListener l) {
        mOnButtonClickListener = l;
    }

    /**
     * 自定义tabitem点击回调接口
     */
    public interface OnButtonClickListener {
        public void onClick(int selectPosition);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.d("dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logger.d("onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }
}
