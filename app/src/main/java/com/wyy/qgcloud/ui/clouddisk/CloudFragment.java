package com.wyy.qgcloud.ui.clouddisk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.FileAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.adapter.OnItemMenuClickedListener;
import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;
import com.wyy.qgcloud.ui.dialog.DetailDialog;
import com.wyy.qgcloud.ui.dialog.TransferFileDialog;
import com.wyy.qgcloud.ui.dialog.bottomsheet.BottomSheet;
import com.wyy.qgcloud.util.MyToast;

import java.util.ArrayList;
import java.util.List;

public class CloudFragment extends Fragment implements CloudContract.CloudView {

    CloudContract.CloudPresent present;
    RecyclerView fileRv;
    ImageView makeDirIv;
    ImageView backIv;
    FileAdapter adapter;
    List<FileInfo.DataBean> fileInfos = new ArrayList<>();
    LinearLayoutManager manager;
    BottomSheet bottomSheet;

    /**
     * 获取从activity得到的用户信息
     */
    LoginInfo.DataBean user = new LoginInfo.DataBean();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);

        //待删
        user.setUserId(1);

        present = new CloudPresent();
        present.bindView(this);

        fileRv = view.findViewById(R.id.rv_file);
        backIv = view.findViewById(R.id.iv_back);
        makeDirIv = view.findViewById(R.id.iv_make_dir);
        bottomSheet = new BottomSheet();
        //返回按钮点击事件
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CloudPresent","点击");
                present.back(user.getUserId());
            }
        });
        makeDirIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TransferFileDialog dialog = new TransferFileDialog(getActivity(),R.style.dialog);
                dialog.setListener(new ConfigOnClickedListener() {
                    @Override
                    public void onClick(String info) {
                        dialog.cancel();
                        present.makeDir(user.getUserId(),info);
                    }
                });
                dialog.show();
            }
        });
        initFileList();
        initRecyclerView();

        return view;
    }

    @Override
    public void showError(String message) {
        MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_sad,message);
    }

    @Override
    public void updataDir(List<FileInfo.DataBean> fileList) {
        fileInfos.clear();
        fileInfos.addAll(fileList);
        for (FileInfo.DataBean temp: fileList){
            Log.d("CloudFragment",temp.getFileName());
        }
        Log.d("CloudFragment","success");
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        present.unbindView();
    }

    /**
     * 初始化滑动组件
     */
    private void initRecyclerView(){
        manager = new LinearLayoutManager(getActivity());
        adapter = new FileAdapter(fileInfos);
        fileRv.setLayoutManager(manager);
        fileRv.setAdapter(adapter);
        adapter.setItemListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                present.openDir(user.getUserId(),position);
            }
        });

        //设置菜单的点击事件
        adapter.setMenuListener(new OnItemMenuClickedListener() {
            @Override
            public void onClick(final int position) {
                //设置每一个菜单项的点击列表
                bottomSheet.setDetailListener(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        present.queryDetail(position);
                    }
                });
                bottomSheet.show(getActivity().getSupportFragmentManager(),"show");

            }
        });
    }

    private void initFileList(){
        present.displayRoot(user.getUserId());
    }

    @Override
    public void hideHomePage() {
        backIv.setVisibility(View.GONE);
    }

    @Override
    public void showHomePage() {
        backIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(FileInfo.DataBean dataBean) {
        DetailDialog detailDialog = new DetailDialog(getActivity(),R.style.dialog,dataBean);
        detailDialog.show();
    }
}
