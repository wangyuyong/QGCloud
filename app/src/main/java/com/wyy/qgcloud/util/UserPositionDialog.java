package com.wyy.qgcloud.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import com.wyy.qgcloud.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserPositionDialog extends Dialog {

    @BindView(R.id.rg_user_position)
    RadioGroup rgUserPosition;
    @BindView(R.id.btn_user_position_cancel)
    Button btnUserPositionCancel;
    @BindView(R.id.btn_user_position_sure)
    Button btnUserPositionSure;
    private UserOnClickListener listener;

    public UserPositionDialog(Context context) {
        super(context);
    }

    public UserPositionDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_user_position);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_user_position_cancel, R.id.btn_user_position_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_user_position_cancel:
                cancel();
                break;
            case R.id.btn_user_position_sure:
                if(listener != null) {
                    int chooseId = rgUserPosition.getCheckedRadioButtonId();
                    switch (chooseId) {
                        case R.id.rb_user_position_one:
                            //小组组长1
                            listener.onClick(1);
                            break;
                        case R.id.rb_user_position_two:
                            //工作室负责人2
                            listener.onClick(2);
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
    }

    public void setListener(UserOnClickListener listener) {
        this.listener = listener;
    }
}
