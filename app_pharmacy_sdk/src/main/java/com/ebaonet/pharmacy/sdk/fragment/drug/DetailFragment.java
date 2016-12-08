package com.ebaonet.pharmacy.sdk.fragment.drug;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebaonet.pharmacy.sdk.R;

/**DrugDetailActivity中-----详情fragment
 * Created by zhaojun.gao on 2016/8/31.
 */
public class DetailFragment extends LazyFragment {
    // 标志fragment是否初始化完成
    private boolean isPrepared;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.pharmacy_fragment_detail_details, container , false);
            Log.e("TAG", "oneFragment--onCreateView");
            isPrepared = true;
            lazyLoad();
        }
        return view;
    }


    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        //否则初始化数据
        Log.e("TAG" , "oneFragment--lazyLoad");
    } 
}
