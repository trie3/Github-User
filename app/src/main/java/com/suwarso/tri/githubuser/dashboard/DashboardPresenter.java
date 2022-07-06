package com.suwarso.tri.githubuser.dashboard;

import com.suwarso.tri.githubuser.utils.api.ApiBuilder;
import com.suwarso.tri.githubuser.utils.api.ApiService;
import com.suwarso.tri.githubuser.utils.core.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DashboardPresenter extends BasePresenter<DashboardView> {

    public DashboardPresenter(DashboardView mView) {
        super(mView);
    }

    public void loadDataUser(int since){
        getCompositeDisposable().add(ApiBuilder.getRetrofit().create(ApiService.class)
                        .getListUser(since)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if(response.size() == 0){
                                view.userNoData();
                            }else {
                                view.loadUserSuccess(response);
                            }
                        }, throwable -> {
                            view.error();
                        })
        );
    }
}
