package com.wyy.qgcloud.ui.changePassword;

public class ChangePasswordPresent implements ChangePasswordContract.ChangePasswordPresent {

    private ChangePasswordContract.ChangePasswordModel changePasswordModel;
    private ChangePasswordContract.ChangePasswordView changePasswordView;
    @Override
    public void bindView(ChangePasswordContract.ChangePasswordView view) {
        this.changePasswordView = view;
        changePasswordModel = new ChangePasswordModel();
    }

    @Override
    public void unbindView() {
        changePasswordModel = null;
        changePasswordView = null;
    }


}
