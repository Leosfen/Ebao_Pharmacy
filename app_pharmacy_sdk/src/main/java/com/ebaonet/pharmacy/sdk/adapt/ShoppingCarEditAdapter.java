package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugStoreInfo;
import com.ebaonet.pharmacy.entity.shoppingcar.ShoppingCarData;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.StringUtils;
import com.ebaonet.pharmacy.view.DeleteDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peng.dong on 2016/8/12.
 */
public class ShoppingCarEditAdapter extends BaseExpandableListAdapter implements FragmentSelectListener {
    Context mContext;
    LayoutInflater mInflater;
    float totalprice = 0;
    private ShoppingCarData data = new ShoppingCarData();
    private HashMap<Integer, Boolean> groupstatus = new HashMap<Integer, Boolean>();//全选状态
    private ArrayList<HashMap<Integer, Boolean>> status = new ArrayList<HashMap<Integer, Boolean>>();//item选中状态
    private DeleteDialog mDialog;
    private ShoppingCarEditListener mlistener;
    private ImageLoader imageLoader;
    private ConfigInfo.DataBean mConfiginfo;
    public ShoppingCarEditAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=ImageLoader.getInstance();
    }

    public void setListener(ShoppingCarEditListener listener) {
        mlistener = listener;
    }

    public void setData(ConfigInfo.DataBean configinfo){
        mConfiginfo = configinfo;
    }
    public void setShoppingList(ShoppingCarData mData) {
        data = mData;
        status.clear();
        groupstatus.clear();
        for (int i = 0; i < data.getData().size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
            groupstatus.put(i, false);
            ArrayList<DrugStoreInfo> drugstoreinfo = data.getData();
            for (int j = 0; j < drugstoreinfo.get(i).getDrugList().size(); j++) {
                map.put(j, false);
            }
            status.add(map);
        }

        this.notifyDataSetChanged();
    }
    //清除选中状态，把item选中状态设为false，全选状态设为false
    public void clearStatus() {
        status.clear();
        groupstatus.clear();
        for (int i = 0; i < data.getData().size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
            ArrayList<DrugStoreInfo> drugstoreinfo = data.getData();
            groupstatus.put(i, false);
            for (int j = 0; j < drugstoreinfo.get(i).getDrugList().size(); j++) {
                map.put(j, false);
            }
            status.add(map);
        }

        this.notifyDataSetChanged();
    }
    
    @Override
    public int getGroupCount() {
        return data.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.getData().get(groupPosition).getDrugList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolderParent holder;
        if (convertView == null) {
            holder = new ViewHolderParent();
            convertView = mInflater.inflate(R.layout.pharmacy_shopping_car_list_parent, null);
            holder.btnSelectAll = (ImageButton) convertView.findViewById(R.id.btn_select_all);
            holder.tvShopName = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tvFreight =(TextView) convertView.findViewById(R.id.tv_freight);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderParent) convertView.getTag();
        }
        ArrayList<DrugStoreInfo> drugStoreInfo = data.getData();
        holder.tvShopName.setText(drugStoreInfo.get(groupPosition).getDrugStoreName());
        final String freightFreePrice = mConfiginfo.getFreightFreePrice();
        if(!TextUtils.isEmpty(freightFreePrice)){
            final int i = Integer.parseInt(freightFreePrice.trim());
            if(i<=0){
                holder.tvFreight.setVisibility(View.INVISIBLE);
            }
            final String s = StringUtils.format2(freightFreePrice);
            holder.tvFreight.setText("满"+s+"元免配送费");
        }else{
            holder.tvFreight.setVisibility(View.INVISIBLE);
        }
        
        if (groupstatus.get(groupPosition) == false) {
            holder.btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);
        } else {
            holder.btnSelectAll.setImageResource(R.drawable.pharmacy_shopping_icon_selected);
        }
        holder.btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectAll(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolderChild holder;
        if (convertView == null) {
            holder = new ViewHolderChild();
            convertView = mInflater.inflate(R.layout.pharmacy_shopping_car_list_child, null);
            holder.itemcount = (EditText) convertView.findViewById(R.id.et_total);
            holder.minus = (ImageButton) convertView.findViewById(R.id.btn_minus);
            holder.plus = (ImageButton) convertView.findViewById(R.id.btn_plus);
            holder.btnSelect = (ImageButton) convertView.findViewById(R.id.btn_item_select);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tvSpec = (TextView) convertView.findViewById(R.id.tv_item_spec);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_item_price);
            holder.dividerLine = (View) convertView.findViewById(R.id.divider_line);
            holder.lastLine = (View) convertView.findViewById(R.id.last_line);
            holder.icon =(ImageView) convertView.findViewById(R.id.image_item_pic);
            holder.imgPic =(ImageView) convertView.findViewById(R.id.img_pic);
                    
            //holder.tvCount = (TextView) convertView.findViewById(R.id.tv_item_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderChild) convertView.getTag();
        }
        final ArrayList<DrugInfo> li=data.getData().get(groupPosition).getDrugList();
        //判断item是否为最后一个，如果为最后一个则显示没有边距的分割线
        if (childPosition != li.size() - 1) {
            holder.dividerLine.setVisibility(View.VISIBLE);
            holder.lastLine.setVisibility(View.GONE);
        } else {
            holder.dividerLine.setVisibility(View.GONE);
            holder.lastLine.setVisibility(View.VISIBLE);
        }

        if (status.get(groupPosition).get(childPosition) == true) {
            holder.btnSelect.setImageResource(R.drawable.pharmacy_shopping_icon_selected);
        } else {
            holder.btnSelect.setImageResource(R.drawable.pharmacy_shopping_icon_unselected);
        }
        holder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSingleSelect(groupPosition, childPosition);

            }
        });
        holder.itemcount.setText(li.get(childPosition).getQuantity());
        if (holder.itemcount.getText().toString().equals("1")) {
            holder.minus.setImageResource(R.drawable.pharmacy_shopping_minus_disabled);
        } else {
            holder.minus.setImageResource(R.drawable.pharmacy_shopping_minus);

        }
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.EditItemChange(groupPosition, childPosition,"add");
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.EditItemChange(groupPosition, childPosition,"minus");
            }
        });
        holder.tvName.setText(li.get(childPosition).getDisplayName());
        holder.tvPrice.setText(StringUtils.format(li.get(childPosition).getCurPrice()));
        holder.tvSpec.setText(li.get(childPosition).getNorms());
        if(!TextUtils.isEmpty(li.get(childPosition).getMedicalInsuranceCode())){
            holder.imgPic.setVisibility(View.VISIBLE);
        }else{
            holder.imgPic.setVisibility(View.GONE);
        }
        imageLoader.displayImage(li.get(childPosition).getPicUrl(), holder.icon,
                ImageDisOpt.getDefaultImgDisOpt());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //设置全选状态
    private void setSelectAll(int groupPosition) {

        Boolean groupselected = groupstatus.get(groupPosition);
        groupstatus.put(groupPosition, !groupselected);
        int size = status.get(groupPosition).size();
        for (int i = 0; i < size; i++) {
            status.get(groupPosition).put(i, !groupselected);
        }
        mlistener.EditSelectChange(status);
        notifyDataSetChanged();
    }

    //设置item选中状态
    private void setSingleSelect(int groupPosition, int childPosition) {
        Boolean childselected = status.get(groupPosition).get(childPosition);
        status.get(groupPosition).put(childPosition, !childselected);
        HashMap<Integer, Boolean> AllChildStatus = status.get(groupPosition);
        //循环遍历，当有一个item没选择中，则不是全选状态，反之为全选状态
        for (int i = 0; i < AllChildStatus.size(); i++) {
            if (AllChildStatus.get(i) == false) {
                groupstatus.put(groupPosition, false);
                break;
            }
            groupstatus.put(groupPosition, true);
        }
        mlistener.EditSelectChange(status);
        notifyDataSetChanged();

    }
    //fragment中的全选所有item按钮操作
    @Override
    public void SelectAll(boolean isselectall) {
        status.clear();
        groupstatus.clear();
        for (int i = 0; i < data.getData().size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
            groupstatus.put(i, isselectall);
            for (int j = 0; j < data.getData().get(i).getDrugList().size(); j++) {
                map.put(j, isselectall);
            }
            status.add(map);
        }
        this.notifyDataSetChanged();
        mlistener.EditSelectChange(status);
    }

    public FragmentSelectListener getAdapterListener() {
        return this;
    }


    class ViewHolderChild {
        ImageButton btnSelect;
        TextView tvName;
        TextView tvSpec;
        TextView tvPrice;
        View dividerLine;
        View lastLine;
        EditText itemcount;
        ImageButton minus;
        ImageButton plus;
        ImageView icon,imgPic;
    }

    class ViewHolderParent {
        ImageButton btnSelectAll;
        TextView tvShopName,tvFreight;

    }
}