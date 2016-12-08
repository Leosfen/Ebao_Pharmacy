package com.ebaonet.pharmacy.sdk.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ebaonet.pharmacy.base.activity.ActivityHelper;
import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugStoreInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.ShoppingCarData;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.ConfigInfoManager;
import com.ebaonet.pharmacy.manager.ShoppingCarManager;
import com.ebaonet.pharmacy.manager.config.ConfigInfoConfig;
import com.ebaonet.pharmacy.manager.config.ShoppingCartConfig;
import com.ebaonet.pharmacy.manager.params.ConfigParamsHelper;
import com.ebaonet.pharmacy.manager.params.ShoppingCarParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.ConfirmOrderActivity;
import com.ebaonet.pharmacy.sdk.activity.DrugInfoActivity;
import com.ebaonet.pharmacy.sdk.activity.ShoppCarActivity;
import com.ebaonet.pharmacy.sdk.activity.StartActivity;
import com.ebaonet.pharmacy.sdk.adapt.FragmentSelectListener;
import com.ebaonet.pharmacy.sdk.adapt.ShoppingCarAdapter;
import com.ebaonet.pharmacy.sdk.adapt.ShoppingCarChangeListener;
import com.ebaonet.pharmacy.sdk.adapt.ShoppingCarEditAdapter;
import com.ebaonet.pharmacy.sdk.adapt.ShoppingCarEditListener;
import com.ebaonet.pharmacy.util.ResourceUitls;
import com.ebaonet.pharmacy.util.StringUtils;
import com.ebaonet.pharmacy.view.DeleteDialog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yao.feng on 2016/8/11.
 */
public class ShoppingCarFragment extends BaseFragment implements ShoppingCarChangeListener, ShoppingCarEditListener {

    private TextView tvNormalTitle;
    private TextView tvEditTitle;
    private String DrugStoreID;
    private RelativeLayout layoutNormal;
    private RelativeLayout layoutEdit;
    private ExpandableListView normalListView;
    private ExpandableListView editListView;
    private ShoppingCarAdapter normaladapter;
    private ShoppingCarEditAdapter editadapter;
    private ShoppingCarData data = new ShoppingCarData();//返回数据的实体类
    private View emptyview;
    private Button btnRightNormal;
    private Button btnRightEdit;
    private boolean canCommit = false;
    private boolean canDelete = false;
    private ArrayList<String> deleteItem = new ArrayList<String>();
    private ArrayList<String> commitItem = new ArrayList<String>();
    private ArrayList<OrderItem> orderCommit = new ArrayList<OrderItem>();
    private TextView tvDelete;
    private TextView tvTotal;
    private TextView tvCommit;
    private ImageButton btnleftNormal;
    private ImageButton btnleftEdit;
    private Button btnEmpty;
    private StartActivity mActivity;
    private ImageButton btnSelectAll;
    private DeleteDialog mDialog;
    private boolean isselectall = false;
    private FragmentSelectListener mListener;
    private static int groupposition = 0;
    private static int childposition = 0;
    private static String quantity;
    private ArrayList<HashMap<Integer, Boolean>> mStatus = new ArrayList<HashMap<Integer, Boolean>>();
    private ImageButton mPharmacy_leftBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(ResourceUitls.getLayoutId(mContext, "pharmacy_fragment_car"), container, false);
            initView();
        }
        return mView;
    }

    @Override
    protected void lazyLoad() {
        PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
        ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(userInfo == null ? "" : userInfo.getUserId()));
        ConfigInfoManager.getInstance().getConfigList(ConfigParamsHelper.configList("10", "1"));
    }

    private void changeDrugCount() {
        data.getData().get(groupposition).getDrugList().get(childposition).setQuantity(quantity);
        normaladapter.notifyDataSetChanged();
        editadapter.notifyDataSetChanged();
        float price = 0;
        for (int i = 0; i < mStatus.size(); i++) {
            for (int j = 0; j < mStatus.get(i).size(); j++) {
                Boolean checked = mStatus.get(i).get(j);
                if (checked == true) {
                    float money =
                            Float.parseFloat(data.getData().get(i).getDrugList().get(j).getCurPrice().toString())
                                    * Integer.parseInt(data.getData().get(i).getDrugList().get(j).getQuantity().toString());
                    price = price + money;
                }
            }
        }
        tvTotal.setText(StringUtils.formatDouble(price));
    }


    private void initView() {
        layoutNormal = (RelativeLayout) mView.findViewById(R.id.layout_normal);
        layoutEdit = (RelativeLayout) mView.findViewById(R.id.layout_edit);
        //提交頁面
        tvNormalTitle = (TextView) layoutNormal.findViewById(R.id.tv_title);
        tvNormalTitle.setText("需求清单");
        normalListView = (ExpandableListView) mView.findViewById(R.id.shoppingcar_listview_normal);
        normaladapter = new ShoppingCarAdapter(mContext);
        normaladapter.setListener(this);
        normalListView.setAdapter(normaladapter);
        normalListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final FragmentActivity activity = getActivity();
                Intent intent = new Intent(mContext, DrugInfoActivity.class);
                if (activity instanceof ShoppCarActivity) {
                    intent.putExtra("fromShoppCarActivity", "yes");
                }
                intent.putExtra("drugid", data.getData().get(groupPosition).getDrugList().get(childPosition).getDrugDsId());
                intent.putExtra("IsShoppingcar", "shoppingcar");
                startActivityForResult(intent, 1);
                return true;
            }
        });
        //编辑按钮，初始化不可用、颜色为灰
        btnRightNormal = (Button) layoutNormal.findViewById(R.id.rightBtn);
        btnRightNormal.setText("编辑");
        btnRightNormal.setTextColor(mContext.getResources().getColor(R.color.color_gray_9c9c9c));
        btnleftNormal = (ImageButton) layoutNormal.findViewById(R.id.pharmacy_leftBtn);
//        btnleftNormal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((StartActivity) mContext).pageToFragmentByPosition(0);
//
//            }
//        });
        //初始化合计金额为0
        tvTotal = (TextView) mView.findViewById(R.id.fragment_car_total);
        tvTotal.setText("0.00");
        canCommit = false;
        //编辑按钮点击事件
        btnRightNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //没有数据，不能点击编辑按钮
                if (data.getData().size() == 0) {
                    return;
                }
                layoutEdit.setVisibility(View.VISIBLE);
                layoutNormal.setVisibility(View.GONE);
                normaladapter.clearStatus();//将提交页面里的商品，选中状态设为false
                tvTotal.setText("0.00");//合计金额设为0
                commitItem.clear();//清空提交商品集合
                canCommit = false;//设置为不能提交状态
                tvCommit.setBackgroundResource(R.color.color_gray_d1d1d1);//提交按钮设为灰色
            }
        });
        //编辑页面
        canDelete = false;
        isselectall = false;
        tvEditTitle = (TextView) layoutEdit.findViewById(R.id.tv_title);
        tvEditTitle.setText("需求清单");
        //完成按钮
        btnRightEdit = (Button) layoutEdit.findViewById(R.id.rightBtn);
        btnRightEdit.setText("完成");
        btnleftEdit = (ImageButton) layoutEdit.findViewById(R.id.pharmacy_leftBtn);
//        btnleftEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((StartActivity) mContext).pageToFragmentByPosition(0);
//            }
//        });
        editListView = (ExpandableListView) mView.findViewById(R.id.shoppingcar_listview_edit);
        editadapter = new ShoppingCarEditAdapter(mContext);
        editListView.setAdapter(editadapter);
        editadapter.setListener(this);
        mListener = editadapter.getAdapterListener();

        btnRightEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutEdit.setVisibility(View.GONE);
                layoutNormal.setVisibility(View.VISIBLE);
                editadapter.clearStatus();//将删除页面里的商品，选中状态设为false
                canDelete = false;//设置删除状态为false,不能删除
                deleteItem.clear();//清空删除商品集合
                tvDelete.setBackgroundResource(R.color.color_gray_d1d1d1);//提交按钮设为灰色
                isselectall = false;//全部选中状态设置为false
                btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);//全选按钮设为灰色

                // ConfigInfoManager.getInstance().getConfigList(ConfigParamsHelper.configList("10", "1"));
            }
        });
        btnSelectAll = (ImageButton) mView.findViewById(R.id.btn_edit_select);//全选按钮
        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isselectall == false) {
                    btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_selected);
                } else {
                    btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);
                }
                isselectall = !isselectall;
                mListener.SelectAll(isselectall);//调用adapter里的“全选”方法

            }
        });
        tvDelete = (TextView) mView.findViewById(R.id.fragment_car_delete);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (canDelete == true) {

                    if (mDialog == null) {
                        mDialog = new DeleteDialog(mContext);
                        mDialog.settext("确定删除选中的商品吗?");
                    }
                    mDialog.setClicklistener(new DeleteDialog.ClickListenerInterface() {
                        @Override
                        public void doConfirm() {
                            String id = formatString(deleteItem);
                            PharmcyUserInfo mUser = PharCacheInfoManager.getUserInfo(mContext);
                            ShoppingCarManager.getInstance().DeleteShoppingCarItem(ShoppingCarParamsHelper.getDeleteItemParams(
                                    mUser == null ? "" : mUser.getUserId(), id));
                            mDialog.dismiss();
                        }

                        @Override
                        public void doCancel() {
                            mDialog.dismiss();
                        }
                    });
                    mDialog.show();
                }


            }
        });

        tvCommit = (TextView) mView.findViewById(R.id.fragment_car_commit);//提交需求按钮
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canCommit == true) {
                    orderCommit.clear();
                    String id = formatString(commitItem);
                    Logger.d("commitfomat:" + id);
                    ArrayList<DrugStoreInfo> datainfo = ShoppingCarFragment.this.data.getData();
                    for (int x = 0; x < commitItem.size(); x++) {
                        String drugid = commitItem.get(x);
                        for (DrugStoreInfo info : datainfo) {
                            ArrayList<DrugInfo> drugList = info.getDrugList();//药店药品集合
                            for (DrugInfo drugInfo : drugList) {
                                if (drugid.equals(drugInfo.getDrugDsId())) {
                                    OrderItem item = new OrderItem();
                                    item.setDrugDsId(drugid);
                                    item.setDrugNum(drugInfo.getQuantity());
                                    orderCommit.add(item);
                                    DrugStoreID = drugInfo.getDrugStoreId();
                                }
                            }
                        }
                    }
                    String drug = JSON.toJSONString(orderCommit);

                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    intent.putExtra("drugStoreId", DrugStoreID);
                    intent.putExtra("drug", drug);
                    startActivityForResult(intent, 0);

                }

            }
        });
        btnEmpty = (Button) mView.findViewById(R.id.empty_view_button);
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof StartActivity) {
                    mActivity = (StartActivity) mContext;
                    if (mActivity != null) {
                        mActivity.pageToFragmentByPosition(0);
                    }
                } else if (mContext instanceof ShoppCarActivity) {
                    ActivityHelper.getInstance().popAllActivity();
                    startActivity(new Intent(mContext, StartActivity.class));

                }
            }
        });

        if (getActivity() instanceof StartActivity) {
            btnleftNormal.setVisibility(View.GONE);
            btnleftEdit.setVisibility(View.GONE);
        } else if (getActivity() instanceof ShoppCarActivity) {
            btnleftNormal.setVisibility(View.VISIBLE);
            btnleftEdit.setVisibility(View.VISIBLE);
            btnleftNormal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            });
            btnleftEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }

    private void checkCommit(float price) {
        tvTotal.setText(StringUtils.formatDouble(price));
        if (commitItem.size() == 0) {
            canCommit = false;
            tvCommit.setBackgroundResource(R.color.color_gray_d1d1d1);
        } else {
            canCommit = true;
            tvCommit.setBackgroundResource(R.color.color_red_f66f6b);
        }
    }

    private void checkDelete() {
        if (deleteItem.size() == 0) {
            canDelete = false;
            tvDelete.setBackgroundResource(R.color.color_gray_d1d1d1);
        } else {
            canDelete = true;
            tvDelete.setBackgroundResource(R.color.color_red_f66f6b);
        }
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        Logger.d("tag:" + tag + "code:" + code + "object:" + obj.toString());
        if (ShoppingCartConfig.GET_CART.equals(tag)) {
            if (code == 1) {
                data = (ShoppingCarData) obj;
                //  判断编辑按钮是否为灰色
                if (data.getData().size() == 0) {
                    btnRightNormal.setTextColor(mContext.getResources().getColor(R.color.color_gray_9c9c9c));
                } else {
                    btnRightNormal.setTextColor(mContext.getResources().getColor(R.color.color_blue_00a5f9));
                }
                normaladapter.setShoppingList(data);
                editadapter.setShoppingList(data);
                normalListView.setGroupIndicator(null);
                for (int i = 0; i < normaladapter.getGroupCount(); i++) {
                    normalListView.expandGroup(i);
                }
                editListView.setGroupIndicator(null);
                for (int i = 0; i < normaladapter.getGroupCount(); i++) {
                    editListView.expandGroup(i);
                }
                commitItem.clear();
                deleteItem.clear();
                tvTotal.setText("0.00");
                canCommit = false;
                canDelete = false;
                tvDelete.setBackgroundResource(R.color.color_gray_d1d1d1);
                tvCommit.setBackgroundResource(R.color.color_gray_d1d1d1);
            }
            if (emptyview == null) {
                emptyview = mView.findViewById(R.id.normal_empty_view);
            }
            emptyview.setVisibility(View.VISIBLE);
            normalListView.setEmptyView(emptyview);
            layoutEdit.setVisibility(View.GONE);
            layoutNormal.setVisibility(View.VISIBLE);

        } else if (ShoppingCartConfig.CHANGE_CART_ITEM.equals(tag)) {
            if (code == 1) {
                changeDrugCount();
            }
        } else if (ShoppingCartConfig.DELETE_CART_ITEM.equals(tag)) {
            if (code == 1) {
                canDelete = false;
                deleteItem.clear();//清空删除商品集合
                tvDelete.setBackgroundResource(R.color.color_gray_d1d1d1);
                isselectall = false;
                btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(userInfo == null ? "" : userInfo.getUserId()));
                //normaladapter.setShoppingList(data);
                //editadapter.setShoppingList(data);
                //判断！！删除页面商品为空，跳转到提交页面
                if (data.getData().size() == 0) {
                    layoutEdit.setVisibility(View.GONE);
                    layoutNormal.setVisibility(View.VISIBLE);
                }
            }

        } else if (ShoppingCartConfig.COMMIT_CART.equals(tag)) {
            if (code == 1) {
                canCommit = false;
                commitItem.clear();
                tvTotal.setText("0.00");
                tvCommit.setBackgroundResource(R.color.color_gray_d1d1d1);
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(userInfo == null ? "" : userInfo.getUserId()));
                //  判断编辑按钮是否为灰色
                if (data.getData().size() == 0) {
                    btnRightNormal.setTextColor(mContext.getResources().getColor(R.color.color_gray_9c9c9c));
                } else {
                    btnRightNormal.setTextColor(mContext.getResources().getColor(R.color.color_blue_00a5f9));
                }
//                normaladapter.setShoppingList(data);
//                editadapter.setShoppingList(data);
            }

        } else if (ConfigInfoConfig.CONFIGLISTINFO.equals(tag)) {
            if (code == 1) {
                ConfigInfo.DataBean data = ((ConfigInfo) obj).getData();
                normaladapter.setData(data);
                editadapter.setData(data);
            }

        }

    }

    //改变商品数量
    @Override
    public void NormalItemChange(int GroupPosition, int ChildPosition, String tag, ArrayList<HashMap<Integer, Boolean>> status) {
        mStatus = status;
        groupposition = GroupPosition;
        childposition = ChildPosition;
        String count = data.getData().get(GroupPosition).getDrugList().get(ChildPosition).getQuantity();
        String id = data.getData().get(GroupPosition).getDrugList().get(ChildPosition).getDrugDsId();
        if (tag == "add") {
            int x = Integer.parseInt(count) + 1;
            quantity = String.valueOf(x);
            PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
            ShoppingCarManager.getInstance().ChangeShoppingCarItem(ShoppingCarParamsHelper.getChangeCartParams(
                    userInfo == null ? "" : userInfo.getUserId(), id, String.valueOf(x)));
        } else {
            if (!count.equals("1")) {
                int x = Integer.parseInt(count) - 1;
                quantity = String.valueOf(x);
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                ShoppingCarManager.getInstance().ChangeShoppingCarItem(ShoppingCarParamsHelper.getChangeCartParams(
                        userInfo == null ? "" : userInfo.getUserId(), id, String.valueOf(x)));

            }
        }

    }

    //提交需求页面选中判断
    @Override
    public void NormalSelectChange(ArrayList<HashMap<Integer, Boolean>> status) {
        mStatus = status;
        float price = 0;
        commitItem.clear();
        for (int i = 0; i < status.size(); i++) {
            for (int j = 0; j < status.get(i).size(); j++) {
                Boolean checked = status.get(i).get(j);
                if (checked == true) {
                    commitItem.add(data.getData().get(i).getDrugList().get(j).getDrugDsId());
                    float money =
                            Float.parseFloat(data.getData().get(i).getDrugList().get(j).getCurPrice().toString())
                                    * Integer.parseInt(data.getData().get(i).getDrugList().get(j).getQuantity().toString());
                    price = price + money;
                }
            }
        }
        checkCommit(price);

    }

    //编辑需求页面选中判断
    @Override
    public void EditSelectChange(ArrayList<HashMap<Integer, Boolean>> status) {
        deleteItem.clear();
        for (int i = 0; i < status.size(); i++) {
            for (int j = 0; j < status.get(i).size(); j++) {
                Boolean checked = status.get(i).get(j);
                if (checked == true) {
                    String id = data.getData().get(i).getDrugList().get(j).getDrugDsId();
                    deleteItem.add(id);
                }
            }
        }
        checkDelete();

        for (int i = 0; i < status.size(); i++) {
            for (int j = 0; j < status.get(i).size(); j++) {
                Boolean checked = status.get(i).get(j);
                if (checked == false) {
                    isselectall = false;
                    btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);
                    return;
                }
            }
        }
        isselectall = true;
        btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_selected);


    }

    @Override
    public void EditItemChange(int GroupPosition, int ChildPosition, String tag) {
        groupposition = GroupPosition;
        childposition = ChildPosition;
        String count = data.getData().get(GroupPosition).getDrugList().get(ChildPosition).getQuantity();
        String id = data.getData().get(GroupPosition).getDrugList().get(ChildPosition).getDrugDsId();
        if (tag == "add") {
            int x = Integer.parseInt(count) + 1;
            quantity = String.valueOf(x);
            PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
            ShoppingCarManager.getInstance().ChangeShoppingCarItem(ShoppingCarParamsHelper.getChangeCartParams(
                    userInfo == null ? "" : userInfo.getUserId(), id, String.valueOf(x)));
            Logger.d("function:添加商品");
        } else {
            if (!count.equals("1")) {
                int x = Integer.parseInt(count) - 1;
                quantity = String.valueOf(x);
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                ShoppingCarManager.getInstance().ChangeShoppingCarItem(ShoppingCarParamsHelper.getChangeCartParams(
                        userInfo == null ? "" : userInfo.getUserId(), id, String.valueOf(x)));
            }
        }

    }


    //格式化数组，用“，”分割
    private String formatString(ArrayList<String> list) {
        StringBuffer sb = new StringBuffer();
        for (int x = 0; x < list.size(); x++) {
            sb.append(list.get(x).trim());
            sb.append(",");

        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean isNetDataTransmission() {
        return true;
    }

    private class OrderItem {
        private String drugDsId;
        private String drugNum;

        public String getDrugDsId() {
            return drugDsId;
        }

        public void setDrugDsId(String drugDsId) {
            this.drugDsId = drugDsId;
        }

        public String getDrugNum() {
            return drugNum;
        }

        public void setDrugNum(String drugNum) {
            this.drugNum = drugNum;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    orderCommit.clear();
                    canCommit = false;
                    commitItem.clear();
                    tvTotal.setText("0.00");
                    tvCommit.setBackgroundResource(R.color.color_gray_d1d1d1);
                    normaladapter.setShoppingList(this.data);
                    editadapter.setShoppingList(this.data);
                    break;
            }
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("drugid");
                int addnum = data.getIntExtra("addnum", 0);
                for (int i = 0; i < this.data.getData().size(); i++) {
                    DrugStoreInfo drugStoreInfo = this.data.getData().get(i);
                    for (int j = 0; j < drugStoreInfo.getDrugList().size(); j++) {
                        if (id.equals(drugStoreInfo.getDrugList().get(j).getDrugDsId())) {
                            int count = Integer.parseInt(this.data.getData().get(i).getDrugList().get(j).getQuantity()) + addnum;
                            this.data.getData().get(i).getDrugList().get(j).setQuantity(String.valueOf(count));
                            normaladapter.notifyDataSetChanged();
                            editadapter.notifyDataSetChanged();
                            break;
                        }
                    }

                }
                float price = 0;
                for (int i = 0; i < mStatus.size(); i++) {
                    for (int j = 0; j < mStatus.get(i).size(); j++) {
                        Boolean checked = mStatus.get(i).get(j);
                        if (checked == true) {
                            float money =
                                    Float.parseFloat(this.data.getData().get(i).getDrugList().get(j).getCurPrice().toString())
                                            * Integer.parseInt(this.data.getData().get(i).getDrugList().get(j).getQuantity().toString());
                            price = price + money;
                        }
                    }
                }
                Logger.d("allprice:" + StringUtils.formatDouble(price) + "mStatus" + mStatus.size());
                tvTotal.setText(StringUtils.formatDouble(price));
            }

        }

    }
}
