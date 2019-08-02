package com.wyy.qgcloud.util;

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

public class JoinGroupDialog extends Dialog {
    @BindView(R.id.rg_user_position)
    RadioGroup rgUserPosition;
    @BindView(R.id.btn_join_group_cancel)
    Button btnJoinGroupCancel;
    @BindView(R.id.btn_join_group_sure)
    Button btnJoinGroupSure;
    private UserOnClickListener listener;

    public JoinGroupDialog(Context context) {
        super(context);
    }

    public JoinGroupDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_join_group);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_join_group_cancel, R.id.btn_join_group_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_join_group_cancel:
                cancel();
                break;
            case R.id.btn_join_group_sure:
                if(listener != null) {
                    int chooseId = rgUserPosition.getCheckedRadioButtonId();
                    switch (chooseId) {
                        case R.id.rb_user_position_one:
                            listener.onClick(1);
                            break;
                        case R.id.rb_user_position_two:
                            listener.onClick(2);
                            break;
                        case R.id.rb_user_position_three:
                            listener.onClick(3);
                            break;
                        case R.id.rb_user_position_four:
                            listener.onClick(4);
                            break;
                        case R.id.rb_user_position_five:
                            listener.onClick(5);
                            break;
                        case R.id.rb_user_position_six:
                            listener.onClick(6);
                            break;
                        case R.id.rb_user_position_seven:
                            listener.onClick(7);
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
