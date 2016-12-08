package com.ebaonet.pharmacy.sdk.fragment.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.SortDetailActivity;
import com.ebaonet.pharmacy.sdk.adapt.SearchHistoryAdapter;
import com.ebaonet.pharmacy.util.SoftInputUtils;
import com.ebaonet.pharmacy.view.EditTextWithDelete;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;
import com.ebaonet.pharmacy.view.model.SearchAutoData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by yao.feng on 2016/9/19.
 */
public class SearchFragment extends BaseFragment {

    private OnSearchActionListener mListener;

    private View mSearchView;

    public static final String SEARCH_HISTORY = "search_history";
    public static final int MAX = 5;
    private ImageView mMoreImg;
    private EditTextWithDelete mSearchEt;
    private ListView mHistory_listview;
    private View mFooter, mHeader;
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private RightTopActionPopWin popupWindow;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof OnSearchActionListener) {
            mListener = (OnSearchActionListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mSearchView == null) {
            mSearchView = inflater.inflate(R.layout.pharmacy_sortsearch_fragment, container, false);
            initView();
        }
        return mSearchView;
    }


    private void initView() {
        mMoreImg = (ImageView) mSearchView.findViewById(R.id.moreImg);
        mMoreImg.setVisibility(View.VISIBLE);
        mMoreImg.setImageResource(R.drawable.pharmacy_titlebar_icon_point_normal);
        mMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        mSearchView.findViewById(R.id.pharmacy_leftBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    SoftInputUtils.closeInput(mContext, mSearchEt);
                    FragmentTransaction mFt = getActivity().getSupportFragmentManager().beginTransaction();
                    mFt.hide(SearchFragment.this);
                    mFt.commit();
                } else {
                    getActivity().finish();
                }
            }
        });
        mSearchEt = (EditTextWithDelete) mSearchView.findViewById(R.id.searchEt);
        mSearchEt.requestFocus();
        mSearchEt.setHint("商品名、品牌、厂商、症状");
        mHistory_listview = (ListView) mSearchView.findViewById(R.id.history_listview);
        mFooter = View.inflate(mContext, R.layout.pharmacy_footer, null);
        mHeader = View.inflate(mContext, R.layout.pharmacy_header, null);
        mHistory_listview.addFooterView(mFooter);
        mHistory_listview.addHeaderView(mHeader);
        mSearchHistoryAdapter = new SearchHistoryAdapter(mContext, 5);//最多显示5条数据
        refreshSavaKeyShowData();
        mHistory_listview.setAdapter(mSearchHistoryAdapter);
        //条目点击事件~
        mHistory_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int size = mHistory_listview.getHeaderViewsCount();//1
                if (mSearchHistoryAdapter.getSize() == (position - size)) {
                    mFooter.setVisibility(View.GONE);
                    mHeader.setVisibility(View.GONE);
                    mSearchHistoryAdapter.clear();
                    mHistory_listview.setVisibility(View.GONE);
                } else if (position == 0) {
                    return;
                } else {
                    SearchAutoData data = (SearchAutoData) mSearchHistoryAdapter.getItem(position - size);
                    mSearchEt.setText(data.getContent());
                    mSearchEt.setSelection(data.getContent().length());//光标位置移至末尾

                    clickSearchAction(data.getContent());
                }
            }
        });
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String str = mSearchEt.getText().toString().trim();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (str.length() == 0) {
                        Toast.makeText(mContext, "请输入关键字", Toast.LENGTH_SHORT).show();
                    } else {
                        //1.保存记录到sp   2.发送网络请求（这个请求使要带参数string的） 3.移除该activity
                        saveSearchHistory();

                        //点击搜索后进行刷新
                        refreshSavaKeyShowData();

                        mSearchHistoryAdapter.notifyDataSetChanged();//无效
                        clickSearchAction(str);
//                        finish();//
                    }
                    return true;

                } else {
                    return false;
                }

            }
        });

//        mSearchEt.setOnDeleteListener(new EditTextWithDelete.OnDeleteAllListener() {
//            @Override
//            public void deleteAll() {
//                //点击全部删除关键字后进行刷新
//                refreshSavaKeyShowData();
//            }
//        });

    }


    /**
     * 设置popupwindow的状态
     *
     * @param v
     */
    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, mMoreImg.getWidth() / 3, -(mMoreImg.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }

    private void clickSearchAction(String key) {
        if (mListener == null) {
            getActivity().finish();
            Intent intent = new Intent(mContext, SortDetailActivity.class);
            intent.putExtra(SortDetailActivity.KEY_WORD, key);
            intent.putExtra(SortDetailActivity.FROM_WHERE, SortDetailActivity.SORT_SEARCH_ACTIVITY);
            startActivity(intent);
        } else {
            mListener.onClickSearchButton(key);
        }
    }

    private void refreshSavaKeyShowData() {
        if (haveHistory()) {
            mSearchHistoryAdapter.setData();
            mHistory_listview.setVisibility(View.VISIBLE);//多余
            mHeader.setVisibility(View.VISIBLE);
            mFooter.setVisibility(View.VISIBLE);

        }
    }


    /**
     * 有无历史记录（sp中有无数据，需要在）
     *
     * @return
     */
    private boolean haveHistory() {
        SharedPreferences sp = mContext.getSharedPreferences(SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SEARCH_HISTORY, "");
        if (longhistory.equals("")) {
            Logger.e("haveHistory-----------false");
            return false;
        } else {
            Logger.e("haveHistory----------true");
            return true;
        }
    }

    /***
     * 保存搜索历史（点击软键盘搜索键时，如果重复了就保存新的）
     */
    private void saveSearchHistory() {
        String text = mSearchEt.getText().toString().trim();
        if (text.length() < 1) {
            return;
        }
        //1.实例化sp对象
        SharedPreferences sp = mContext.getSharedPreferences(SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SEARCH_HISTORY, " ");
        String[] tmpHistory = longhistory.split(",");
        ArrayList<String> history = new ArrayList<String>(
                Arrays.asList(tmpHistory));//Arrays.asList(tmpHistory)返回只读的list集合，即不可增删相当于数组

        Iterator<String> sListIterator = history.iterator();//bug解决  
        while (sListIterator.hasNext()) {
            String e = sListIterator.next();
            if (e.equals(" ")) {
                sListIterator.remove();
            }
        }

        if (history.size() > 0 && history.size() < MAX) {
//        if (history.size() > 0) {//处理数据相同时，保存新数据
            int i;
            for (i = 0; i < history.size(); i++) {
                if (text.equals(history.get(i))) {
                    history.remove(i);
                    break;
                }
            }
            history.add(0, text);
        } else if (history.size() == MAX) {

            int i;
            for (i = 0; i < history.size(); i++) {
                if (text.equals(history.get(i))) {
                    history.remove(i);
                    break;
                }
            }
            if (history.size() == MAX) {//代表就没有相同的元素
                history.remove(history.size() - 1);
            }
            history.add(0, text);
        }

        if (history.size() > 0 && history.size() <= MAX) {
//        if (history.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < history.size(); i++) {
                sb.append(history.get(i) + ",");
            }

            sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
        } else {
            sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
            return;//
        }

    }

    public interface OnSearchActionListener {

        void onClickSearchButton(String key);

    }
}
