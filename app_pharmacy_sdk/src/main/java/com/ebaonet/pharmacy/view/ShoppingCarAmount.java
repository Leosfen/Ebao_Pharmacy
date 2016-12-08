package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ebaonet.pharmacy.sdk.R;

/**
 * Created by peng.dong on 2016/8/26.
 */
public class ShoppingCarAmount extends LinearLayout{
    private Context mContext;
    private ImageButton btn_plus;
    private ImageButton btn_minus;
    private EditText et_total;
    private int total;
    private int groupposition;
    private int childposition;

    public ShoppingCarAmount(Context context) {
        super(context);
        mContext = context;
        initview();
    }

    public ShoppingCarAmount(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initview();
    }

    public ShoppingCarAmount(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initview();
    }
    public void getPostion(int x,int y){
        groupposition=x;
        childposition=y;
    }
    private void initview() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pharmacy_shopping_car_amount, null);
        addView(view);
        btn_plus = (ImageButton) view.findViewById(R.id.btn_plus);
        btn_minus = (ImageButton) view.findViewById(R.id.btn_minus);
        et_total = (EditText) view.findViewById(R.id.et_total);
//        et_total.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Logger.d("group:"+groupposition+"child:"+childposition+"total"+et_total.getText().toString());
//            }
//        });
        total=Integer.parseInt(et_total.getText().toString());//购买数量
        btn_plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                total++;
                et_total.setText(String.valueOf(total));
                checkstatus();
            }
        });
        btn_minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total == 1) {
                    
                } else {
                    total--;
                    et_total.setText(String.valueOf(total));
                }
                checkstatus();
            }
        });

    }
    private void checkstatus(){
        if(total==1){
            btn_minus.setImageResource(R.drawable.pharmacy_shopping_minus_disabled);
        }else{
            btn_minus.setImageResource(R.drawable.pharmacy_shopping_minus);
        }
    }
}
