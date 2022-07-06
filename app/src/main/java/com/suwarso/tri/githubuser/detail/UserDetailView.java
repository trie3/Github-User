package com.suwarso.tri.githubuser.detail;

import com.suwarso.tri.githubuser.utils.core.BaseView;

import java.util.List;

public interface UserDetailView extends BaseView {

    void loadDetailUserSuccess(UserDetailResponse response);

}
