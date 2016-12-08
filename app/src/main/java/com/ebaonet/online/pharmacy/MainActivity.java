package com.ebaonet.online.pharmacy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.sdk.activity.StartActivity;

public class MainActivity extends Activity {

    private Button mExit;
    private EditText mEt_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEt_userId = (EditText) findViewById(R.id.et_userId);
        mExit = (Button) findViewById(R.id.exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取开封人社传过来的数据：1.用户id，
                // 名字，身份证号，均不能为空，否则购药不可进
                /**  获取开封人社传过来的数据： 1.用户id，   2.名字     3.身份证号
                                               均不能为空，否则购药不可进
                 * */
                String userId = mEt_userId.getText().toString().trim();
                if(TextUtils.isEmpty(userId)){
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else{
                    PharmcyUserInfo pharmcyUserInfo = new PharmcyUserInfo();
                    pharmcyUserInfo.setName("haha");
                    pharmcyUserInfo.setIdCard("123456789009876543");
                    pharmcyUserInfo.setUserId(userId);
                    PharCacheInfoManager.setPharmcyUserInfo(MainActivity.this,pharmcyUserInfo);
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                    //finish();  
                }
               
            }
        });
    }
}
