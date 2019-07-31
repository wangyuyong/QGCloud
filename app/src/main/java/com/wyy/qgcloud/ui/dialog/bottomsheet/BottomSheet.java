package com.wyy.qgcloud.ui.dialog.bottomsheet;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BottomSheet extends DialogFragment {

    @BindView(R.id.ll_download)
    LinearLayout downloadLl;
    @BindView(R.id.ll_detail)
    LinearLayout detailLl;
    @BindView(R.id.ll_power_manager)
    LinearLayout powerManagerLl;
    @BindView(R.id.ll_rename)
    LinearLayout renameLl;
    @BindView(R.id.ll_delete)
    LinearLayout deleteLl;
    Unbinder unbinder;

    private OnMultiClickListener downloadListener;
    private OnMultiClickListener detailListener;
    private OnMultiClickListener powerListener;
    private OnMultiClickListener renameListener;
    private OnMultiClickListener deleteListener;

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //隐藏title
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置进出动画
        getDialog().getWindow().setWindowAnimations(R.style.bottom_sheet);
        //设置弹窗在底部
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawable(new ColorDrawable(0xffffff));
        window.setAttributes(lp);

        View bootomView = inflater.inflate(R.layout.dialog_fragment_bottom_sheet, null);

        unbinder = ButterKnife.bind(this, bootomView);
        return bootomView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_download, R.id.ll_detail, R.id.ll_power_manager, R.id.ll_rename, R.id.ll_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_download:
                if (downloadListener != null) {
                    downloadListener.onClick(view);
                }
                dismiss();
                break;
            case R.id.ll_detail:
                if (detailListener != null){
                    detailListener.onClick(view);
                }
                dismiss();
                break;
            case R.id.ll_power_manager:
                if (powerListener != null){
                    powerListener.onClick(view);
                }
                dismiss();
                break;
            case R.id.ll_rename:
                if (renameListener != null){
                    renameListener.onClick(view);
                }
                dismiss();
                break;
            case R.id.ll_delete:
                if (deleteListener != null){
                    deleteListener.onClick(view);
                }
                dismiss();
                break;
            default:

        }
    }

    public void setDownloadListener(OnMultiClickListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public void setDetailListener(OnMultiClickListener detailListener) {
        this.detailListener = detailListener;
    }

    public void setPowerListener(OnMultiClickListener powerListener) {
        this.powerListener = powerListener;
    }

    public void setRenameListener(OnMultiClickListener renameListener) {
        this.renameListener = renameListener;
    }

    public void setDeleteListener(OnMultiClickListener deleteListener) {
        this.deleteListener = deleteListener;
    }
}
