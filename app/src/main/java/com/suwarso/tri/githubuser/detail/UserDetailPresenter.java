package com.suwarso.tri.githubuser.detail;

import com.suwarso.tri.githubuser.utils.api.ApiBuilder;
import com.suwarso.tri.githubuser.utils.api.ApiService;
import com.suwarso.tri.githubuser.utils.core.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserDetailPresenter extends BasePresenter<UserDetailView> {

    public UserDetailPresenter(UserDetailView mView) {
        super(mView);
    }

    public void loadDataUser(String username){
        view.showProgress();
        getCompositeDisposable().add(ApiBuilder.getRetrofit().create(ApiService.class)
                .getDetailUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    view.hideProgress();
                    view.loadDetailUserSuccess(response);
                }, throwable -> {
                    view.hideProgress();
                    view.error();
                })
        );
    }

}
