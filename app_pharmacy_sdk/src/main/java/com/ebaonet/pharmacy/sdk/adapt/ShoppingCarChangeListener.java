package com.ebaonet.pharmacy.sdk.adapt;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peng.dong on 2016/8/31.
 */
public interface ShoppingCarChangeListener {
    void NormalSelectChange(ArrayList<HashMap<Integer, Boolean>> status);//正常模式，改变选中状态
    void NormalItemChange(int GroupPosition, int ChildPosition,
    String tag,ArrayList<HashMap<Integer, Boolean>> status);//正常模式,修改商品数量
}
