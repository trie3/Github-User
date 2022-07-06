package com.suwarso.tri.githubuser.utils.core;

import com.suwarso.tri.githubuser.utils.api.ApiBuilder;
import com.suwarso.tri.githubuser.utils.api.ApiService;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter <T> {

    public T view;
    protected ApiService apiStores;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(T mView) {
        view = mView;
    }

    public void onViewAttached(T view) {
        this.view = view;
        apiStores = ApiBuilder.getRetrofit().create(ApiService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onViewDetached() {
        this.view = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

}
