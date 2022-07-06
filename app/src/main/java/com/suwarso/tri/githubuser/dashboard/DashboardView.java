package com.suwarso.tri.githubuser.dashboard;

import com.suwarso.tri.githubuser.utils.core.BaseView;

import java.util.List;

public interface DashboardView extends BaseView {

    void loadUserSuccess(List<ListUserResponse> response);

    void userNoData();

}
