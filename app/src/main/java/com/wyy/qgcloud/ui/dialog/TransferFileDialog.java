package com.wyy.qgcloud.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TransferFileDialog extends Dialog {

    @BindView(R.id.dialog_et_newfile)
    EditText newFileEd;
    @BindView(R.id.btn_makedir_cancel)
    Button cancelBtn;
    @BindView(R.id.btn_config)
    Button configBtn;
    /**
     * 确认按钮点击事件的回调接口
     */
    private ConfigOnClickedListener listener;

    public TransferFileDialog(Context context) {
        super(context);
    }

    public TransferFileDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_transfer_file);
        ButterKnife.bind(this);
    }

    public void setListener(ConfigOnClickedListener listener) {
        this.listener = listener;
    }

    @OnClick({R.id.btn_makedir_cancel, R.id.btn_config})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_makedir_cancel:
                cancel();
                break;
            case R.id.btn_config:
                if (listener != null) {
                    listener.onClick(newFileEd.getText().toString());
                }
                break;
            default:
        }
    }
}
