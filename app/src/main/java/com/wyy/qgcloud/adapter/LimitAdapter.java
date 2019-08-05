package com.wyy.qgcloud.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.GroupInfo;

import java.util.ArrayList;
import java.util.List;

public class LimitAdapter extends BaseExpandableListAdapter {

    private List<GroupInfo.DataBean> groupMember;
    private List<String> groupList;
    private Context mContext;
    private List<List<CheckBox>> childCheckBox = new ArrayList<>();
    private List<CheckBox> groudCheckBox = new ArrayList<>();


    public LimitAdapter(List<GroupInfo.DataBean> groupMember, List<String> groupList, Context mContext) {
        for (int i = 0; i < groupList.size(); i++){
            childCheckBox.add(new ArrayList<CheckBox>());
        }
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
                return groupMember.get(0).getUngrouped().size();
            default:
                return 0;
        }

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
            default:
                return 0;
        }
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_limit_parent,null);
            groupHolder = new GroupHolder();
            groupHolder.groupCheckBox = convertView.findViewById(R.id.ckb_item_limit_parent);
            groupHolder.groupName = convertView.findViewById(R.id.tv_item_limit_parent);
            groupHolder.groupIndicator = convertView.findViewById(R.id.imv_item_limit_indicator);
            groupHolder.groupCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    List<CheckBox> powerBox = childCheckBox.get(groupPosition);
                    for (int i = 0; i < powerBox.size(); i++){
                        powerBox.get(i).setChecked(isChecked);
                    }
                }
            });
            groudCheckBox.add(groupHolder.groupCheckBox);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_limit_child,null);
            childHolder = new ChildHolder();
            childHolder.memberCheckBox = convertView.findViewById(R.id.ckb_item_limit_child);
            childHolder.memberCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            childCheckBox.get(groupPosition).add(childHolder.memberCheckBox);
            childHolder.memberIcon = convertView.findViewById(R.id.imv_item_limit_child);
            childHolder.memberName = convertView.findViewById(R.id.tv_item_limit_child);
            convertView.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        switch (groupPosition) {
            case 0:
                childHolder.memberName.setText(groupMember.get(0).getBackground().get(childPosition).getUserName());
                Log.d("LimitAdapter",groupMember.get(0).getBackground().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getBackground().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);
                break;
            case 1:
                childHolder.memberName.setText(groupMember.get(0).getFrontend().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getFrontend().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);
                break;
            case 2:
                childHolder.memberName.setText(groupMember.get(0).getMobile().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getMobile().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);
                break;
            case 3:
                childHolder.memberName.setText(groupMember.get(0).getDataMining().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDataMining().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);
                break;
            case 4:
                childHolder.memberName.setText(groupMember.get(0).getEmbedded().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getEmbedded().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);

                break;
            case 5:
                childHolder.memberName.setText(groupMember.get(0).getDesign().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDesign().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);

                break;
            case 6:
                childHolder.memberName.setText(groupMember.get(0).getGraphics().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getGraphics().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);

                break;
            case 7:
                childHolder.memberName.setText(groupMember.get(0).getUngrouped().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getUngrouped().get(childPosition).getIcon())
                        .error(R.mipmap.ic_head)
                        .into(childHolder.memberIcon);
                break;
            default:
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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

    public List<Integer> getToId(){
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < childCheckBox.size(); i++){
            List<CheckBox> boxList = childCheckBox.get(i);
            for (int j = 0; j < boxList.size(); j++){
                CheckBox box = boxList.get(j);
                if (box.isChecked()){
                    GroupInfo.DataBean data = groupMember.get(0);
                    Integer toId = null;
                    switch (i){
                        case 0:
                            toId = data.getBackground().get(j).getUserId();
                            break;
                        case 1:
                            toId = data.getFrontend().get(j).getUserId();
                            break;
                        case 2:
                            toId = data.getMobile().get(j).getUserId();
                            break;
                        case 3:
                            toId = data.getDataMining().get(j).getUserId();
                            break;
                        case 4:
                            toId = data.getEmbedded().get(j).getUserId();
                            break;
                        case 5:
                            toId = data.getDesign().get(j).getUserId();
                            break;
                        case 6:
                            toId = data.getGraphics().get(j).getUserId();
                            break;
                        case 7:
                            toId = data.getUngrouped().get(j).getUserId();
                            break;
                        default:
                    }
                integerList.add(toId);
                }
            }
        }
        return integerList;
    }
}
