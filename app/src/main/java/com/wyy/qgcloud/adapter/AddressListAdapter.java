package com.wyy.qgcloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

public class AddressListAdapter extends BaseExpandableListAdapter {


    //获得父项的数量
    @Override
    public int getGroupCount() {
        return 0;
    }

    //获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    //获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    //获得某个父项的子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    //获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    //获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    //是否具有稳定的id
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if(view == null){
        }
        return view;
    }

    //获得子项显示的view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    //子项是否可选中，若需要设置子项的点击事件，需返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
