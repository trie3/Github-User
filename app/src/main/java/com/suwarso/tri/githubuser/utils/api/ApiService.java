package com.suwarso.tri.githubuser.utils.api;

import com.suwarso.tri.githubuser.dashboard.ListUserResponse;
import com.suwarso.tri.githubuser.detail.UserDetailResponse;
import com.suwarso.tri.githubuser.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constants.URLAPI.LIST_USER)
    Observable<List<ListUserResponse>> getListUser(
            @Query("since") int since
    );

    @GET(Constants.URLAPI.DETAIL_USER + "{username}")
    Observable<UserDetailResponse> getDetailUser(
            @Path("username") String username
    );

}
