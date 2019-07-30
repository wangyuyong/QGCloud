package com.wyy.qgcloud.ui.addressList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.app.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListFragment extends Fragment {

//    @BindView(R.id.address_list)
//    QMUIStickySectionLayout addressList;
//    private RecyclerView.LayoutManager mLayoutManager;
//    protected QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    protected void initStickyLayout() {
//        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
//        addressList.setLayoutManager(mLayoutManager);
//    }
//
//    private void initData(){
//        mAdapter
//        mAdapter.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem>() {
//            @Override
//            public void loadMore(QMUISection<SectionHeader, SectionItem> section, boolean loadMoreBefore) {
//
//            }
//
//            @Override
//            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
//                return false;
//            }
//        });
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
