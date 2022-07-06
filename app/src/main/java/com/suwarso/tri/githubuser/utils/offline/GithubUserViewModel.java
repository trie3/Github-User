package com.suwarso.tri.githubuser.utils.offline;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GithubUserViewModel extends AndroidViewModel {

    private GithubUserRepository mRepository;
    private final LiveData<List<GithubUser>> listLiveData;

    public GithubUserViewModel(Application application) {
        super(application);
        mRepository = new GithubUserRepository(application);
        listLiveData = mRepository.getAllUser();
    }

    public LiveData<List<GithubUser>> getListUser() {
        return listLiveData;
    }

    public void insert(GithubUser githubUser) {
        mRepository.insert(githubUser);
    }

    public LiveData<List<GithubUser>> searchData(String search){
        return (LiveData) mRepository.search(search);
    }
}
