package com.ebaonet.pharmacy.sdk.adapt;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peng.dong on 2016/8/31.
 */
public interface ShoppingCarEditListener {
    void EditSelectChange(ArrayList<HashMap<Integer, Boolean>> status);//编辑模式，改变选中状态
    void EditItemChange(int GroupPosition, int ChildPosition,String tag);//编辑模式，修改商品数量
   
}
