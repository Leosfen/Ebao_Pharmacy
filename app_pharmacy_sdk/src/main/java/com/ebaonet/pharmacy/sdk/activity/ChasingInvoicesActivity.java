package com.ebaonet.pharmacy.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.file.ChaseInfo;
import com.ebaonet.pharmacy.sdk.file.SaveChaseInfoUtil;
import com.ebaonet.pharmacy.view.EditTextWithDelete;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;

/**
 * 索要发票界面
 * Created by zhaojun.gao on 2016/9/19.
 */
public class ChasingInvoicesActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private CharSequence temp;
    private Button mBt_finish;
    private ImageButton mIb_selected1;
    private boolean isChasing = true;//默认不开发票（标蓝）
    private String perorcom = "";//否则报空指针
    private final int charMaxNumPerson = 10;
    private final int charMaxNumCompany = 25;
    private TextView mTv_title;
    private ImageButton mRightBtn;
    private ImageButton mIb_selected;
    private TextView mTv_person;
    private TextView mTv_company;
    private EditTextWithDelete mEt_name;
    private String personName = "";
    private String companyName = "";
    public static final String LOGISTICS = "logistics";
    public static final String NAME = "name";
    public static final String NAME_TITLE = "name_title";
    private RightTopActionPopWin popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_chasing_invoices);
        initView();
//        initChaseFileInfo();
    }

    private void initChaseFileInfo() {
        ChaseInfo mChase = SaveChaseInfoUtil.getChaseInfo(mContext);
        if (mChase != null) {
            perorcom = mChase.getChaseType();
            personName = mChase.getPersonInfo();
            companyName = mChase.getCompanyInfo();

            if (!TextUtils.isEmpty(perorcom)) {
                
                if (perorcom.equals("个人")) {
                    mEt_name.setEnabled(true);
                    isChasing = false;
                    mIb_selected.setImageResource
                            (R.drawable.pharmacy_address_icon_unselected);//修改发票状态
                    mTv_person.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                    mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
                    mTv_company.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                    mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices);
                    mEt_name.setText(personName);
                } else if (perorcom.equals("公司")) {
                    mEt_name.setEnabled(true);
                    isChasing = false;
                    mIb_selected.setImageResource
                            (R.drawable.pharmacy_address_icon_unselected);//修改发票状态
                    mTv_company.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                    mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
                    mTv_person.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                    mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices);
                    mEt_name.setText(companyName);
                }
            } else {
                mEt_name.setEnabled(false);
                mIb_selected.setImageResource(R.drawable.pharmacy_address_icon_selected);
               
            }
        } else {
            mEt_name.setEnabled(false);
            mIb_selected.setImageResource(R.drawable.pharmacy_address_icon_selected);
        }
    }

    private void initView() {
        final String name_title = getIntent().getStringExtra(NAME_TITLE);
        final String name = getIntent().getStringExtra(NAME);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mRightBtn = (ImageButton) findViewById(R.id.rightBtn);
        mTv_title.setText("索要发票");
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setImageResource(R.drawable.pharmacy_titlebar_icon_point_normal);
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        mIb_selected = (ImageButton) findViewById(R.id.ib_selected);
        mTv_person = (TextView) findViewById(R.id.tv_person);
        mTv_company = (TextView) findViewById(R.id.tv_company);
         
        mBt_finish = (Button) findViewById(R.id.bt_finish);
        mBt_finish.setEnabled(true);
        mTv_person.setOnClickListener(this);
        mTv_company.setOnClickListener(this);
        mIb_selected .setOnClickListener(this);
        mBt_finish.setOnClickListener(this);
       // mBt_finish.setEnabled(false);
        mEt_name = (EditTextWithDelete) findViewById(R.id.et_name);
        mEt_name.setEnabled(false);
        mEt_name.addTextChangedListener(this);
        findViewById(R.id.not_have_chase).setOnClickListener(this);
        if(!TextUtils.isEmpty(name_title)){
            isChasing = false;
            if("个人".equals(name_title)){
                perorcom = "个人";
                mIb_selected.setImageResource
                        (R.drawable.pharmacy_address_icon_unselected);//修改发票状态
                mEt_name.setText(name);
                mEt_name.setSelection(temp.length());
                mTv_person.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
                mTv_company.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices);
            }else if("公司".equals(name_title)){
                perorcom = "公司";
                mIb_selected.setImageResource
                        (R.drawable.pharmacy_address_icon_unselected);//修改发票状态
                mEt_name.setText(name);
                mEt_name.setSelection(temp.length());
                mTv_person.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices);
                mTv_company.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
            }
        }
        
    }

    private void setInvoices() {
        perorcom = "";
        personName = "";
        companyName = "";
        mEt_name.setText("");

        mTv_company.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
        mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices);
        mTv_person.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
        mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        temp = s;
    }

    @Override
    public void afterTextChanged(Editable s) {
        int start = mEt_name.getSelectionStart();
        int end = mEt_name.getSelectionEnd();
        if (perorcom.equals("个人")) {
            if (temp.length() > charMaxNumPerson) {
                Toast.makeText(getApplicationContext(), "最多不超过10个字符！", Toast.LENGTH_LONG).show();
                s.delete(start - 1, end);
                mEt_name.setText(s);
                mEt_name.setSelection(temp.length());
            }
            personName = mEt_name.getText().toString();
            Log.e("personName======", "==========" + personName);

        } else if (perorcom.equals("公司")) {
            if (temp.length() > charMaxNumCompany) {
                Toast.makeText(getApplicationContext(), "最多不超过25个字符！", Toast.LENGTH_LONG).show();
                s.delete(start - 1, end);
                mEt_name.setText(s);
                mEt_name.setSelection(temp.length());
            }

            companyName = mEt_name.getText().toString();
            Log.e("companyName======", "==========" + companyName);

        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_person || i == R.id.tv_company) {
            initChaseFileInfo();
            mEt_name.setEnabled(true);
            mBt_finish.setEnabled(true);
            isChasing = false;//开发票（标灰）
            mIb_selected.setImageResource(R.drawable.pharmacy_address_icon_unselected);//修改发票状态
            //修改个人/公司状态
            if (i == R.id.tv_person) {
                perorcom = "个人";
                mEt_name.setText(personName);
                mEt_name.setSelection(temp.length());

                mTv_person.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
                mTv_company.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices);
            } else {
                perorcom = "公司";
                mEt_name.setText(companyName);
                mEt_name.setSelection(temp.length());

                mTv_company.setTextColor(this.getResources().getColor(R.color.color_blue_00a5f9));
                mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices_blue);
                mTv_person.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
                mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices);
            }

        } else if (i == R.id.not_have_chase) {
//            SaveChaseInfoUtil.setChaseInfo(mContext,null);
//            initChaseFileInfo();
            if (isChasing) {//标蓝
                isChasing = false;
                mIb_selected.setImageResource(R.drawable.pharmacy_address_icon_unselected);
                mBt_finish.setEnabled(true);
            } else {//标灰
                
                isChasing = true;
                mIb_selected.setImageResource(R.drawable.pharmacy_address_icon_selected);
               // mBt_finish.setEnabled(false);
                setInvoices();
//                mTv_company.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
//                mTv_company.setBackgroundResource(R.drawable.pharmacy_invoices);
//                mTv_person.setTextColor(this.getResources().getColor(R.color.color_gray_999999));
//                mTv_person.setBackgroundResource(R.drawable.pharmacy_invoices);
            }

        } else if (i == R.id.bt_finish) {
            Intent intent = new Intent();
            if (isChasing) {//
                
                intent.putExtra(LOGISTICS, "不开发票");
                setResult(RESULT_OK, intent);
                finish();
            } else {

                String s = mEt_name.getText().toString().trim();
                if (s.equals("")) {
                    Toast.makeText(ChasingInvoicesActivity.this, perorcom + "名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(NAME_TITLE, perorcom);
                    intent.putExtra(NAME, mEt_name.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       
        ChaseInfo mcha = new ChaseInfo();
        mcha.setChaseType(perorcom);
        mcha.setPersonInfo(personName);
        mcha.setCompanyInfo(companyName);
        SaveChaseInfoUtil.setChaseInfo(mContext, mcha);
    }

    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, mRightBtn.getWidth() / 3, -(mRightBtn.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }
}
