package com.ebaonet.pharmacy.sdk.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.ActivityHelper;
import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.entity.drug.sort.DrugDetailEntry;
import com.ebaonet.pharmacy.entity.drug.sort.DrugDispEntry;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugQuantity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.ConfigInfoManager;
import com.ebaonet.pharmacy.manager.DrugManager;
import com.ebaonet.pharmacy.manager.ShoppingCarManager;
import com.ebaonet.pharmacy.manager.config.ConfigInfoConfig;
import com.ebaonet.pharmacy.manager.config.DrugConfig;
import com.ebaonet.pharmacy.manager.params.ConfigParamsHelper;
import com.ebaonet.pharmacy.manager.params.DrugParamsHelper;
import com.ebaonet.pharmacy.manager.params.ShoppingCarParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.image.ImageDisOpt;
import com.ebaonet.pharmacy.util.JsonUtil;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.MyViewPager;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 药品详情界面
 * Created by peng.dong on 2016/8/30.
 */
public class DrugInfoActivity extends BaseActivity {
    private RightTopActionPopWin popupWindow;
    private LayoutInflater inflater;
    private ViewPager infoViewPager;
    private MyViewPager itemViewPager;//商品信息中的viewpager
    private List<View> infoViewList = new ArrayList<View>();//存放infoViewPager的view集合
    private List<String> imageUrlList = new ArrayList<String>();//存放itemViewPager的图片集合
    private View itemView, detailView;//商品页面，详情页面
    private LinearLayout titleItem, titleDetail;// 头部文字控件,根据不同页面显示
    private DisplayImageOptions options;
    private LinearLayout pointLayout;//圆点指示图
    private LinearLayout layoutBottom;
    private LinearLayout layoutLimit, layoutDiscountType, mDispEmptyView;
    private View discountLine;
    private ImageView[] mImageViews = null;
    private ImageView mImageView = null;
    private ArrayList<ImageView> mImageViewCacheList = new ArrayList<ImageView>();
    private ArrayList<RelativeLayout> layoutCacheList = new ArrayList<RelativeLayout>();
    private TextView drugName, drugOrg, nowPrice, normalPrice, discountName;
    private ImageView drugOtc;
    private DrugInfoPagerAdapter adapter;
    private ItemPagerAdapter itemAdapter;
    private TextView tvCommit;
    private LinearLayout tv_shoppingcar;
    private DrugDetailEntry entry;
    private String IsShoppingcar, drugid, decode;
    private TextView tvHeadItem, tvHeadDetail;
    private int addnum = 0;
    private RelativeLayout mlayoutQuantity;
    private DrugQuantity drugQuantity;
    private TextView tvQuantity;
    private Button serviceBtn;
    private TextView tvRemark, tvTitle, tvNomalName, tvProductName, tvEngName, tvChnSpell, tvElement, tvCharacter, tvElemChar, tvFunctionType, tvRadiate, tvRadioactive, tvInoculate, tvIndication, tvFunction, tvSpec, tvEffect, tvImmune, tvDosage, tvAbsorb, tvAdverse, tvTaboo, tvNotes, tvGravidaUse, tvChildUse, tvAgedUse, tvInteraction, tvExcessive, tvClinical, tvPharmaco, tvKinetics, tvStore, tvPack, tvValidity, tvExecutiveStad, tvApprovalNo, tvImportNo, tvPackNo, tvCompany, tvPackCompany, tvComAddress;
    //private TextView tvCaution, tvWarning,tvComCode, tvComPhone, tvComUrl, tvOrgSpec, tvOrgSpec_url;
    private String phoneNo;
    private String mFromSortDetail;
    private ScrollView mDrugDispLayout;
    private DrugDispEntry mEntry;
    private boolean showWaterImg = false;//是否有水印
    private PharmcyUserInfo mUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_drug_info);
        initView();
        drugid = getIntent().getStringExtra("drugid");
        IsShoppingcar = getIntent().getStringExtra("IsShoppingcar");//判断是否是 从需求清单跳转过来
        mFromSortDetail = getIntent().getStringExtra("fromSortDetail");//判断是从药品列表还是需求清单跳转过来
        decode = getIntent().getStringExtra("decode");
        DrugManager.getInstance().getDrugDetail(DrugParamsHelper.getDrugDetail("1010011", "1.01", decode, drugid, "1"));
//        DrugManager.getInstance().getDrugDisp(DrugParamsHelper.getDrugDisp(drugid));
        mUserInfo  = PharCacheInfoManager.getUserInfo(mContext);
        DrugManager.getInstance().getQuantity(DrugParamsHelper.getQuantity(mUserInfo == null ? "" : mUserInfo.getUserId()));

    }

    private void initView() {
        inflater = LayoutInflater.from(mContext);
        options = ImageDisOpt.getDetailImgDisOpt();
        layoutBottom = (LinearLayout) findViewById(R.id.drug_detail_bottom_view);
        final View headView = findViewById(R.id.drug_detail_head_view);//include的头布局文件
        tvCommit = (TextView) findViewById(R.id.drug_info_commit);
        tvHeadItem = (TextView) findViewById(R.id.head_item);
        tvHeadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoViewPager.setCurrentItem(0);
            }
        });
        tvHeadDetail = (TextView) findViewById(R.id.head_detail);
        tvHeadDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoViewPager.setCurrentItem(1);
            }
        });
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entry != null) {
                    RequestParams params = DrugParamsHelper.addCartitem(PharCacheInfoManager.getUserInfo(mContext).getUserId(),
                            entry.getData().getDrugDsId(), "1", String.valueOf(entry.getData().getNormPrice()));
                    DrugManager.getInstance().addCartitem(params);
                }
            }
        });
        tv_shoppingcar = (LinearLayout) findViewById(R.id.tv_go_to_shoppingcar);
        tv_shoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("yes".equals(mFromSortDetail)) {//列表跳转过来
                    Log.e("1=================", "列表到详情");
                    Intent intent = new Intent(DrugInfoActivity.this, ShoppCarActivity.class);
                    startActivity(intent);

                } else {
                    if ("yes".equals(getIntent().getStringExtra("fromShoppCarActivity"))) {
//                        Intent intent = new Intent(DrugInfoActivity.this, ShoppCarActivity.class);
//                        startActivity(intent);
                        finish();
                    } else {
                        StartActivity activity = (StartActivity) ActivityHelper.getInstance().getActivity((Class) StartActivity.class);
                        if (activity != null) {
                            activity.pageToFragmentByPosition(2);
                            ActivityHelper.getInstance().popAllActivityExceptOne(StartActivity.class);
                        }
                    }
                }

            }
        });
        serviceBtn = (Button) findViewById(R.id.service_btn);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo));
                startActivity(intent);
            }
        });
        tvQuantity = (TextView) findViewById(R.id.tv_quantity);
        mlayoutQuantity = (RelativeLayout) findViewById(R.id.layout_quantity);
        btnRight = (ImageButton) findViewById(R.id.rightBtn);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {
                    popupWindow = new RightTopActionPopWin(mContext, false);
                }
                popupWindow.showAsDropDown(headView, headView.getWidth() / 3 * 2 + 15, -(btnRight.getHeight() / 3));
            }
        });
        titleItem = (LinearLayout) headView.findViewById(R.id.title_item);
        titleDetail = (LinearLayout) headView.findViewById(R.id.title_detail);
        itemView = inflater.inflate(R.layout.pharmacy_layout_drug_info_item, null);

        drugName = (TextView) itemView.findViewById(R.id.drug_info_name);//drug_info_normalprice
        drugOrg = (TextView) itemView.findViewById(R.id.drug_info_org);
        drugOtc = (ImageView) itemView.findViewById(R.id.drug_info_otc);
        normalPrice = (TextView) itemView.findViewById(R.id.drug_info_normalprice);
        nowPrice = (TextView) itemView.findViewById(R.id.drug_info_nowprice);
        layoutLimit = (LinearLayout) itemView.findViewById(R.id.layout_count_limit);
        layoutDiscountType = (LinearLayout) itemView.findViewById(R.id.layout_discount_type);
        discountName = (TextView) itemView.findViewById(R.id.discount_name);
        discountLine = itemView.findViewById(R.id.discount_line);
        normalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //详情页
        detailView = inflater.inflate(R.layout.pharmacy_layout_drug_info_detail, null);
        mDrugDispLayout = (ScrollView) detailView.findViewById(R.id.drugdisp_info);
        mDispEmptyView = (LinearLayout) detailView.findViewById(R.id.empty_view);
        tvTitle = (TextView) detailView.findViewById(R.id.tv_title);
        tvRemark = (TextView) detailView.findViewById(R.id.tv_remark);
        //tvCaution = (TextView) detailView.findViewById(R.id.tv_caution);
        //tvWarning = (TextView) detailView.findViewById(R.id.tv_warning);
        tvProductName = (TextView) detailView.findViewById(R.id.tv_product_name);
        tvNomalName = (TextView) detailView.findViewById(R.id.tv_nomal_name);
        tvEngName = (TextView) detailView.findViewById(R.id.tv_eng_name);
        tvChnSpell = (TextView) detailView.findViewById(R.id.tv_chn_spell);
        tvElement = (TextView) detailView.findViewById(R.id.tv_element);
        tvCharacter = (TextView) detailView.findViewById(R.id.tv_character);
        tvElemChar = (TextView) detailView.findViewById(R.id.tv_elem_char);
        tvFunctionType = (TextView) detailView.findViewById(R.id.tv_function_type);
        tvRadiate = (TextView) detailView.findViewById(R.id.tv_radiate);
        tvRadioactive = (TextView) detailView.findViewById(R.id.tv_radioactive);
        tvInoculate = (TextView) detailView.findViewById(R.id.tv_inoculate);
        tvIndication = (TextView) detailView.findViewById(R.id.tv_indication);
        tvFunction = (TextView) detailView.findViewById(R.id.tv_function);
        tvSpec = (TextView) detailView.findViewById(R.id.tv_spec);
        tvEffect = (TextView) detailView.findViewById(R.id.tv_effect);
        tvImmune = (TextView) detailView.findViewById(R.id.tv_immune);
        tvDosage = (TextView) detailView.findViewById(R.id.tv_dosage);
        tvAbsorb = (TextView) detailView.findViewById(R.id.tv_absorb);
        tvAdverse = (TextView) detailView.findViewById(R.id.tv_adverse);
        tvTaboo = (TextView) detailView.findViewById(R.id.tv_taboo);
        tvNotes = (TextView) detailView.findViewById(R.id.tv_notes);
        tvGravidaUse = (TextView) detailView.findViewById(R.id.tv_gravida_use);
        tvChildUse = (TextView) detailView.findViewById(R.id.tv_child_use);
        tvAgedUse = (TextView) detailView.findViewById(R.id.tv_aged_use);
        tvInteraction = (TextView) detailView.findViewById(R.id.tv_interaction);
        tvExcessive = (TextView) detailView.findViewById(R.id.tv_excessive);
        tvClinical = (TextView) detailView.findViewById(R.id.tv_clinical);
        tvPharmaco = (TextView) detailView.findViewById(R.id.tv_pharmaco);
        tvKinetics = (TextView) detailView.findViewById(R.id.tv_kinetics);
        tvStore = (TextView) detailView.findViewById(R.id.tv_store);
        tvPack = (TextView) detailView.findViewById(R.id.tv_pack);
        tvValidity = (TextView) detailView.findViewById(R.id.tv_validity);
        tvExecutiveStad = (TextView) detailView.findViewById(R.id.tv_executive_stad);
        tvApprovalNo = (TextView) detailView.findViewById(R.id.tv_approval_no);
        tvImportNo = (TextView) detailView.findViewById(R.id.tv_import_no);
        tvPackNo = (TextView) detailView.findViewById(R.id.tv_pack_no);
        tvCompany = (TextView) detailView.findViewById(R.id.tv_company);
        tvPackCompany = (TextView) detailView.findViewById(R.id.tv_pack_company);
        tvComAddress = (TextView) detailView.findViewById(R.id.tv_com_address);
        //tvComCode = (TextView) detailView.findViewById(R.id.tv_com_code);
        //tvComPhone = (TextView) detailView.findViewById(R.id.tv_com_phone);
        //tvComUrl = (TextView) detailView.findViewById(R.id.tv_com_url);
        //tvOrgSpec = (TextView) detailView.findViewById(R.id.tv_org_spec);
        //tvOrgSpec_url = (TextView) detailView.findViewById(R.id.tv_org_spec_url);

        infoViewList.add(itemView);
        infoViewList.add(detailView);
        infoViewPager = (ViewPager) findViewById(R.id.drug_info_viewpager);
        infoViewPager.setOnPageChangeListener(new InfoPageChangeListener());
        adapter = new DrugInfoPagerAdapter(mContext);
        infoViewPager.setAdapter(adapter);

        itemViewPager = (MyViewPager) itemView.findViewById(R.id.drug_info_item_viewpager);
        itemViewPager.setOnPageChangeListener(new ItemPageChangeListener());
        pointLayout = (LinearLayout) itemView.findViewById(R.id.layout_view_point);
        itemAdapter = new ItemPagerAdapter();
        itemViewPager.setAdapter(itemAdapter);


    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        if (DrugConfig.GET_DRUG_DETAIL.equals(tag)) {
            if (code == 1) {
                layoutBottom.setVisibility(View.VISIBLE);
                infoViewPager.setVisibility(View.VISIBLE);
                imageUrlList.clear();
                entry = (DrugDetailEntry) obj;
                drugName.setText(entry.getData().getDisplayName());
                drugOrg.setText(entry.getData().getManufacturer());
                //判断otc标识
                if (entry.getData().getOtcId().equals("1")) {
                    drugOtc.setImageResource(R.drawable.pharmacy_otc_red);
                } else if (entry.getData().getOtcId().equals("2")) {
                    drugOtc.setImageResource(R.drawable.pharmacy_otc_green);
                } else {
                    drugOtc.setVisibility(View.GONE);
                }

                if (entry.getData().getNormPrice().equals(entry.getData().getUpPrice())) {
                    normalPrice.setVisibility(View.GONE);
                } else {
                    normalPrice.setText("¥" + entry.getData().getNormPrice());
                    layoutDiscountType.setVisibility(View.VISIBLE);
                    //layoutLimit.setVisibility(View.VISIBLE); 限购接口不通，暂时隐藏
                    if (entry.getData().getSalesName().equals("")) {
                        discountName.setText("接口没返回打折名称");
                    } else {
                        discountName.setText(entry.getData().getSalesName());
                    }
                    discountLine.setVisibility(View.VISIBLE);

                }
                nowPrice.setText(entry.getData().getUpPrice());
                List<DrugDetailEntry.DataBean.ImagesBean> images = entry.getData().getImages();
                final String medicalInsuranceCode = entry.getData().getMedicalInsuranceCode();
                if (!TextUtils.isEmpty(medicalInsuranceCode)) {
                    showWaterImg = true;
                }
                for (DrugDetailEntry.DataBean.ImagesBean bean : images) {
                    imageUrlList.add(bean.getImagePath());
                }
                if (imageUrlList.size() == 0) {
                    imageUrlList.add("");
                }
                setLayoutPoint();
                itemAdapter.notifyDataSetChanged();
                ConfigInfoManager.getInstance().getConfigList(ConfigParamsHelper.configList("1.01", "1010011"));
            } else {
                tvHeadDetail.setEnabled(false);
                layoutBottom.setVisibility(View.GONE);
                findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }
        } else if (DrugConfig.ADDCARTITEM.equals(tag)) {
            //如果activity onpause 不刷新数据
            if (mResumed != false) {
                addnum++;
                if (code == 1) {
                    UIUtils.showToast(this, "加入需求清单");
                    if (IsShoppingcar == null || IsShoppingcar == " ") {
                        PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                        ShoppingCarManager.getInstance().getShoppingCarList(ShoppingCarParamsHelper.getCartInfoParams(userInfo == null ? "" : userInfo.getUserId()));
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("drugid", drugid);
                        intent.putExtra("addnum", addnum);
                        setResult(RESULT_OK, intent);
                    }
                    PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
                    DrugManager.getInstance().getQuantity(DrugParamsHelper.getQuantity(userInfo == null ? "" : userInfo.getUserId()));
                }
            }

        } else if (DrugConfig.GET_DRUG_DISP.equals(tag)) {
            if (code == 1) {
                mEntry = (DrugDispEntry) obj;
                showDrugDisp(mEntry);
            } else {
                mDispEmptyView.setVisibility(View.VISIBLE);
                mDrugDispLayout.setVisibility(View.GONE);
            }
        } else if (DrugConfig.GET_QUANTITY.equals(tag)) {
            if (code == 1) {
                drugQuantity = (DrugQuantity) obj;
                int quantity = drugQuantity.getData().getQuantity();
                if (quantity > 0) {
                    tvQuantity.setText("" + quantity);
                    mlayoutQuantity.setVisibility(View.VISIBLE);
                } else {
                    mlayoutQuantity.setVisibility(View.GONE);
                }

            }
        } else if (tag == ConfigInfoConfig.CONFIGLISTINFO) {
            if (code == 1) {
                ConfigInfo.DataBean data = ((ConfigInfo) obj).getData();
                List<ConfigInfo.DataBean.ServiceTelBean> getServiceTel = data.getServiceTel();
                phoneNo = getServiceTel.get(0).getLabel();
                if (phoneNo != null) {
                    serviceBtn.setVisibility(View.VISIBLE);
                } else {
                    serviceBtn.setVisibility(View.GONE);
                }
            }
        }
    }

    //infoViewPager的 adapter
    private class DrugInfoPagerAdapter extends PagerAdapter {
        Context Context;

        public DrugInfoPagerAdapter(Context context) {
            Context = context;
        }

        @Override
        public int getCount() {
            return infoViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(infoViewList.get(position));
            return infoViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(infoViewList.get(position));
        }
    }

    //itemViewPager 的 adapter
    private class ItemPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ImageView view = (ImageView) object;
//            container.removeView(view);
//            mImageViewCacheList.add(view);
            RelativeLayout view = (RelativeLayout) object;
            ImageView imgView = (ImageView) view.getTag();
            imgView.setImageResource(0);
            container.removeView(view);
            layoutCacheList.add(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = imageUrlList.get(position);
            Logger.d("imageUrl" + imageUrl);
            ImageView imageView = null;
            ImageView backImg = null;
            ImageView waterImg = null;
            RelativeLayout view = null;
            if (layoutCacheList.isEmpty()) {
                view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pharmacy_activity_druginfo_lb, null);
                backImg = (ImageView) view.findViewById(R.id.background_img);
                waterImg = (ImageView) view.findViewById(R.id.water_img);
                view.setTag(backImg);
                if (showWaterImg == true) {
                    waterImg.setBackground(mContext.getResources().getDrawable(R.drawable.pharmacy_details_watermark));
                } else {
                    waterImg.setVisibility(View.GONE);
                }
            } else {
                view = layoutCacheList.remove(0);
                backImg = (ImageView) view.getTag();
            }
            container.addView(view);
            imageLoader.displayImage(imageUrl, backImg, options);
            return view;
        }
    }
    //设置商品图底下的point指示图

    private void setLayoutPoint() {
        pointLayout.removeAllViews();
        final int imageCount = imageUrlList.size();
        mImageViews = new ImageView[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 5, 5, 5);//4个参数按顺序分别是左上右下
            mImageView.setLayoutParams(layoutParams);
            mImageViews[i] = mImageView;
            if (i == 0) {
                if (imageCount < 2) {
                    mImageViews[i].setVisibility(View.GONE);
                } else {
                    mImageViews[i].setBackgroundResource(R.drawable.pharmacy_detail_point_red);
                }
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.pharmacy_detail_point_gray);
            }

            pointLayout.addView(mImageViews[i]);
        }
    }

    private class InfoPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (i == 0) {
                titleItem.setVisibility(View.VISIBLE);
                titleDetail.setVisibility(View.GONE);
                serviceBtn.setVisibility(View.VISIBLE);
            } else {
                if (mEntry == null) {
                    DrugManager.getInstance().getDrugDisp(DrugParamsHelper.getDrugDisp(drugid));
                }

                titleItem.setVisibility(View.GONE);
                titleDetail.setVisibility(View.VISIBLE);
                serviceBtn.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private String isEmpty(String string) {
        if (TextUtils.isEmpty(string)) {
            string = "无";
        }
        return Html.fromHtml(string).toString();
    }

    ;

    /**
     * 显示药品说明书
     */
    private void showDrugDisp(DrugDispEntry mEntry) {
        //判断字符串是否为空
        if (mEntry != null && !TextUtils.isEmpty(mEntry.getData())) {
            DrugDispEntry.Drugbackbean drugbackbean = JsonUtil.toBean(mEntry.getData().toString(), DrugDispEntry.Drugbackbean.class);
            //判断状态是否为1
            if (drugbackbean.getStatus().equals("1")) {

                final List<DrugDispEntry.Drugbackbean.DatasBean> datas = drugbackbean.getDatas();
                if (datas != null && datas.size() > 0) {
                    mDispEmptyView.setVisibility(View.GONE);
                    mDrugDispLayout.setVisibility(View.VISIBLE);
                    DrugDispEntry.Drugbackbean.DatasBean datasBean = drugbackbean.getDatas().get(0);
                    tvTitle.setText(isEmpty(datasBean.getSTAD_NAME()) + "  说明书");
                    tvNomalName.setText("【通用名称】  " + isEmpty(datasBean.getSTAD_NAME()));
                    tvProductName.setText("【商品名称】  " + isEmpty(datasBean.getPRODUCT_NAME()));
                    tvEngName.setText("【英文名称】  " + isEmpty(datasBean.getENG_NAME()));
                    tvChnSpell.setText("【汉语拼音】  " + isEmpty(datasBean.getCHN_SPELL()));
                    tvElement.setText("【成份】  " + isEmpty(datasBean.getELEMENT()));
                    tvCharacter.setText("【性状】  " + isEmpty(datasBean.getCHARACTER()));
                    tvElemChar.setText("【成分和性状】  " + isEmpty(datasBean.getELEM_CHAR()));
                    tvFunctionType.setText("【作用类别】  " + isEmpty(datasBean.getFUNCTION_TYPE()));
                    tvRadiate.setText("【放射性核素半衰期】  " + isEmpty(datasBean.getRADIATE()));
                    tvRadioactive.setText("【放射性活度和标示时间】  " + isEmpty(datasBean.getRADIOACTIVE()));
                    tvInoculate.setText("【接种对象】  " + isEmpty(datasBean.getINOCULATE()));
                    tvIndication.setText("【适应症】  " + isEmpty(datasBean.getINDICATION()));
                    tvFunction.setText("【功能主治】  " + isEmpty(datasBean.getFUNCTION()));
                    tvSpec.setText("【规格】  " + isEmpty(datasBean.getSPEC()));
                    tvEffect.setText("【作用与用途】  " + isEmpty(datasBean.getEFFECT()));
                    tvImmune.setText("【免疫程序和剂量】  " + isEmpty(datasBean.getIMMUNE()));
                    tvDosage.setText("【用法用量】  " + isEmpty(datasBean.getDOSAGE()));
                    tvAbsorb.setText("【内辐射吸收剂量】  " + isEmpty(datasBean.getABSORB()));
                    tvAdverse.setText("【不良反应】  " + isEmpty(datasBean.getADVERSE()));
                    tvTaboo.setText("【禁忌】  " + isEmpty(datasBean.getTABOO()));
                    tvNotes.setText("【注意事项】  " + isEmpty(datasBean.getNOTES()));
                    tvGravidaUse.setText("【孕妇及哺乳期妇女用药】  " + isEmpty(datasBean.getGRAVIDA_USE()));
                    tvChildUse.setText("【儿童用药】  " + isEmpty(datasBean.getCHILD_USE()));
                    tvAgedUse.setText("【老年用药】  " + isEmpty(datasBean.getAGED_USE()));
                    tvInteraction.setText("【药物相互作用】  " + isEmpty(datasBean.getINTERACTION()));
                    tvExcessive.setText("【药物过量】  " + isEmpty(datasBean.getEXCESSIVE()));
                    tvClinical.setText("【临床试验】  " + isEmpty(datasBean.getCLINICAL()));
                    tvPharmaco.setText("【药理毒理】  " + isEmpty(datasBean.getPHARMACO()));
                    tvKinetics.setText("【药代动力学】  " + isEmpty(datasBean.getKINETICS()));
                    tvStore.setText("【贮藏】  " + isEmpty(datasBean.getSTORE()));
                    tvPack.setText("【包装】  " + isEmpty(datasBean.getPACK()));
                    tvValidity.setText("【有效期】  " + isEmpty(datasBean.getVALIDITY()));
                    tvExecutiveStad.setText("【执行标准】  " + isEmpty(datasBean.getEXECUTIVE_STAD()));
                    tvApprovalNo.setText("【批准文号】  " + isEmpty(datasBean.getAPPROVAL_NO()));
                    tvImportNo.setText("【进口药品注册证号】  " + isEmpty(datasBean.getIMPORT_NO()));
                    tvPackNo.setText("【分包装批准文号】  " + isEmpty(datasBean.getPACK_NO()));
                    tvCompany.setText("【生产企业名称】  " + isEmpty(datasBean.getCOMPANY()));
                    tvPackCompany.setText("【分包装企业名称】  " + isEmpty(datasBean.getPACK_COMPANY()));
                    tvComAddress.setText("【生产企业地址】  " + isEmpty(datasBean.getCOM_ADDRESS()));
                } else {
                    mDispEmptyView.setVisibility(View.VISIBLE);
                    mDrugDispLayout.setVisibility(View.GONE);
                }

            } else {
                mDispEmptyView.setVisibility(View.VISIBLE);
                mDrugDispLayout.setVisibility(View.GONE);
            }

        } else {
            mDispEmptyView.setVisibility(View.VISIBLE);
            mDrugDispLayout.setVisibility(View.GONE);
        }
    }

    private class ItemPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            for (int x = 0; x < mImageViews.length; x++) {
                if (i == x) {
                    // 设置图片滚动指示器背景
                    mImageViews[x].setBackgroundResource(R.drawable.pharmacy_detail_point_red);
                } else {
                    mImageViews[x].setBackgroundResource(R.drawable.pharmacy_detail_point_gray);
                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    public interface MyClickListener {
        public void PageToIndex();

        public void PageToOnLine();

    }

    @Override
    public boolean isNetDataTransmission() {
        return true;
    }
}
 