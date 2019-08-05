package com.wyy.qgcloud.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.ui.addressList.AddressListFragment;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressListAdapter extends BaseExpandableListAdapter {

    private List<GroupInfo.DataBean> groupMember;
    private List<String> groupList;
    private Context mContext;
    protected BottomSheetOnClickListener bottomSheetOnClickListener;

    public AddressListAdapter(List<GroupInfo.DataBean> groupMember, List<String> groupList, Context mContext) {
        this.groupMember = groupMember;
        this.groupList = groupList;
        this.mContext = mContext;
    }

    //获得父项的数量
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    //获得某个父项的子项数目
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

    //获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    //获得某个父项的某个子项
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

    //获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //是否具有稳定的id
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_address_parent,null);
            groupHolder = new GroupHolder();
            groupHolder.groupName = view.findViewById(R.id.group_name);
            groupHolder.groupIndicator = view.findViewById(R.id.group_indicator);
            view.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder)view.getTag();
        }
        if(isExpanded){
            //父项展开
            groupHolder.groupIndicator.setImageResource(R.mipmap.ic_group_close);
        }else {
            groupHolder.groupIndicator.setImageResource(R.mipmap.ic_group_open);
        }
        groupHolder.groupName.setText(groupList.get(groupPosition));
        return view;
    }

    //获得子项显示的view
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address_child,null);
            childHolder = new ChildHolder();
            childHolder.memberIcon = convertView.findViewById(R.id.group_member_icon);
            childHolder.memberName = convertView.findViewById(R.id.group_member_name);
            childHolder.more = convertView.findViewById(R.id.group_member_more);
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
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getBackground().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 1:
                childHolder.memberName.setText(groupMember.get(0).getFrontend().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getFrontend().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getFrontend().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 2:
                childHolder.memberName.setText(groupMember.get(0).getMobile().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getMobile().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getMobile().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 3:
                childHolder.memberName.setText(groupMember.get(0).getDataMining().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDataMining().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int userId = groupMember.get(0).getDataMining().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 4:
                childHolder.memberName.setText(groupMember.get(0).getEmbedded().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getEmbedded().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getEmbedded().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 5:
                childHolder.memberName.setText(groupMember.get(0).getDesign().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getDesign().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getDesign().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 6:
                childHolder.memberName.setText(groupMember.get(0).getGraphics().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getGraphics().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getGraphics().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            case 7:
                childHolder.memberName.setText(groupMember.get(0).getUngrouped().get(childPosition).getUserName());
                Glide.with(mContext)
                        .load(groupMember.get(0).getUngrouped().get(childPosition).getIcon())
                        .into(childHolder.memberIcon);
                childHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wx",v.getId()+"");
                        int userId = groupMember.get(0).getUngrouped().get(childPosition).getUserId();
                        showBottomSheetList(userId);
                    }
                });
                break;
            default:
        }
        return convertView;
    }

    //子项是否可选中，若需要设置子项的点击事件，需返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder{
        TextView groupName;
        ImageView groupIndicator;
    }

    private class ChildHolder{
        ImageView memberIcon;
        TextView memberName;
        ImageView more;
    }

    //传入参数为被操作者的id
    private void showBottomSheetList(final int toUserId){
            new QMUIBottomSheet.BottomListSheetBuilder(mContext)
                    .addItem(mContext.getDrawable(R.mipmap.ic_user_grouping), "用户分组")
                    .addItem(mContext.getDrawable(R.mipmap.ic_give_position), "身份赋予")
                    .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                        @Override
                        public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                            dialog.dismiss();
                            switch (position) {
                                case 0:
                                    //用户分组
                                    if (bottomSheetOnClickListener != null) {
                                        bottomSheetOnClickListener.bottomSheetOnClick(0, toUserId);
                                    }
                                    break;
                                case 1:
                                    //身份赋予
                                    if(bottomSheetOnClickListener != null) {
                                        bottomSheetOnClickListener.bottomSheetOnClick(1, toUserId);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .build()
                    .show();
    }

    public void setBottomSheetOnClickListener(BottomSheetOnClickListener bottomSheetOnClickListener){
        this.bottomSheetOnClickListener = bottomSheetOnClickListener;
    }
}
