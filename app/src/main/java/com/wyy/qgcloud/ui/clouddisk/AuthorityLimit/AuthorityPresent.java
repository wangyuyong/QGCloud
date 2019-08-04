package com.wyy.qgcloud.ui.clouddisk.AuthorityLimit;

import android.content.Context;

import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.GroupInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AuthorityPresent implements AuthorityContract.AuthorityPresent {

    private AuthorityContract.AuthorityModel authorityModel;
    private AuthorityContract.AuthorityView authorityView;

    @Override
    public void getUpdateAuthorityInfo(Context context, int userId, int fileId, int authority, int told) {
        authorityModel.getUpdateAuthorityInfo(context, userId, fileId, authority, told).subscribe(new Observer<ChangePasswordInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChangePasswordInfo changePasswordInfo) {
                if(changePasswordInfo.getStatus()){
                    authorityView.showSuccess("权限修改成功！");
                }else {
                    authorityView.showError(changePasswordInfo.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getGroupInfo() {
        authorityModel.getGroupInfo().subscribe(new Observer<GroupInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupInfo groupInfo) {
                authorityView.initList(groupInfo.getData());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void bindView(AuthorityContract.AuthorityView view) {
        authorityModel = new AuthorityModel();
        this.authorityView = view;
    }

    @Override
    public void unbindView() {
        authorityView = null;
        authorityModel = null;
    }
}
