package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.address.Address;
import com.ebaonet.pharmacy.manager.AddressManager;
import com.ebaonet.pharmacy.manager.params.AddressParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.CreateAddrActivity;
import com.ebaonet.pharmacy.util.Utils;
import com.ebaonet.pharmacy.view.DeleteDialog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by peng.dong on 2016/8/12.
 */
public class ManageAddrAdapt extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    Intent intent;
    private ArrayList<Address> list = new ArrayList<Address>();
    private DeleteDialog mDialog;

    public ManageAddrAdapt(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setAddressList(ArrayList<Address> mlist) {
        list = mlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pharmacy_item_manage_addr_list, null);
            holder.tvEdit = (TextView) convertView.findViewById(R.id.item_addr_list_edit);
            holder.tvDelete = (TextView) convertView.findViewById(R.id.item_addr_list_delete);
            holder.tvDefalut = (TextView) convertView.findViewById(R.id.item_addr_list_default);
            holder.tvReceiveName = (TextView) convertView.findViewById(R.id.item_addr_list_receivename);
            holder.tvAddr = (TextView) convertView.findViewById(R.id.item_addr);
            holder.tvMobile = (TextView) convertView.findViewById(R.id.item_addr_list_mobile);
            holder.line = convertView.findViewById(R.id.item_addr_line_head);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Address mAddress = list.get(position);
        final String mId = mAddress.getAddrId();
        if (position == 0) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }
        if (mAddress.isStatusDefault()) {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.pharmacy_address_icon_selected);
            holder.tvDefalut.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            holder.tvDefalut.setText("默认地址");
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.pharmacy_address_icon_unselected);
            holder.tvDefalut.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            holder.tvDefalut.setText("设为默认");
        }
        holder.tvAddr.setText(Utils.replaceDefaultString(mAddress.getAddr()));
        holder.tvMobile.setText(Utils.replaceDefaultString(mAddress.getReceivePhone()));
        holder.tvReceiveName.setText(Utils.replaceDefaultString(mAddress.getReceiveName()));
        holder.tvDefalut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                AddressManager.getInstance().saveDefaultAddress(AddressParamsHelper.getSaveDefaultAddressParams(mId, userInfo == null ? "" : userInfo.getUserId()
                ));
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, CreateAddrActivity.class);
                intent.putExtra("isedit", true);
                intent.putExtra("data", (Serializable) mAddress);
                mContext.startActivity(intent);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new DeleteDialog(mContext);
                mDialog.setClicklistener(new DeleteDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        AddressManager.getInstance().delAddress(AddressParamsHelper.getDeleteAddressParams(mId));
                    }

                    @Override
                    public void doCancel() {

                    }
                });
                mDialog.show();
            }
        });
        return convertView;
    }


    class ViewHolder {
        TextView tvEdit;
        TextView tvDelete;
        TextView tvDefalut;
        TextView tvAddr;
        TextView tvReceiveName;
        TextView tvMobile;
        View line;
    }
}
