package com.wyy.qgcloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.GroupInfo;

import java.util.List;

public class LimitAdapter extends BaseExpandableListAdapter {

    private List<GroupInfo.DataBean> groupMember;
    private List<String> groupList;
    private Context mContext;

    public LimitAdapter(List<GroupInfo.DataBean> groupMember, List<String> groupList, Context mContext) {
        this.groupMember = groupMember;
        this.groupList = groupList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (groupPosition) {
            case 0:
                return groupMember.get(0).getBackground().size();
            case 1:
                return groupMember.get(0).getFrontend().size();
            case 2:
                return groupMember.get(0).getMobile().size();
            case 3:
                return groupMember.get(0).getDataMining().size();
            case 4:
                return groupMember.get(0).getEmbedded().size();
            case 5:
                return groupMember.get(0).getDesign().size();
            case 6:
                return groupMember.get(0).getGraphics().size();
            case 7:
                return groupMember.get(0).getUngrouped().size(); }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        switch (groupPosition) {
            case 0:
                return groupMember.get(0).getBackground().get(childPosition);
            case 1:
                return groupMember.get(0).getFrontend().get(childPosition);
            case 2:
                return groupMember.get(0).getMobile().get(childPosition);
            case 3:
                return groupMember.get(0).getDataMining().get(childPosition);
            case 4:
                return groupMember.get(0).getEmbedded().get(childPosition);
            case 5:
                return groupMember.get(0).getDesign().get(childPosition);
            case 6:
                return groupMember.get(0).getGraphics().get(childPosition);
            case 7:
                return groupMember.get(0).getUngrouped().get(childPosition);
        }
        return 0;
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address_parent,null);
            groupHolder = new GroupHolder();
            groupHolder.groupCheckBox = convertView.findViewById(R.id.ckb_item_limit_parent);
            groupHolder.groupName = convertView.findViewById(R.id.tv_item_limit_parent);
            groupHolder.groupIndicator = convertView.findViewById(R.id.imv_item_limit_indicator);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder)convertView.getTag();
        }
        if(isExpanded){
            //父项展开
            groupHolder.groupIndicator.setImageResource(R.mipmap.ic_group_close);
        }else {
            groupHolder.groupIndicator.setImageResource(R.mipmap.ic_group_open);
        }
        groupHolder.groupName.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address_child,null);
            childHolder = new ChildHolder();
            childHolder.memberCheckBox = convertView.findViewById(R.id.ckb_item_limit_child);
            childHolder.memberIcon = convertView.findViewById(R.id.group_member_icon);
            childHolder.memberName = convertView.findViewById(R.id.group_member_name);
            convertView.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        switch (groupPosition) {
            case 0:
                childHolder.memberName.setText(groupMember.get(0).getBackground().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getBackground().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 1:
                childHolder.memberName.setText(groupMember.get(0).getFrontend().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getFrontend().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 2:
                childHolder.memberName.setText(groupMember.get(0).getMobile().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getMobile().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 3:
                childHolder.memberName.setText(groupMember.get(0).getDataMining().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDataMining().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 4:
                childHolder.memberName.setText(groupMember.get(0).getEmbedded().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getEmbedded().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 5:
                childHolder.memberName.setText(groupMember.get(0).getDesign().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDesign().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 6:
                childHolder.memberName.setText(groupMember.get(0).getGraphics().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getGraphics().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
            case 7:
                childHolder.memberName.setText(groupMember.get(0).getUngrouped().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getUngrouped().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);

                break;
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder{
        CheckBox groupCheckBox;
        TextView groupName;
        ImageView groupIndicator;
    }

    private class ChildHolder{
        CheckBox memberCheckBox;
        ImageView memberIcon;
        TextView memberName;
    }
}
