package com.wyy.qgcloud.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneDialog extends Dialog {

    @BindView(R.id.edt_change_phone)
    EditText edtChangePhone;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_sure)
    Button btnSure;
    private ConfigOnClickedListener listener;

    public ChangePhoneDialog(Context context) {
        super(context);
    }

    public ChangePhoneDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                cancel();
                break;
            case R.id.btn_sure:
                if(listener != null){
                    listener.onClick(edtChangePhone.getText().toString());
                }
                break;
                default:
                    break;
        }
    }

    public void setListener(ConfigOnClickedListener listener) {

        this.listener = listener;
    }
}
