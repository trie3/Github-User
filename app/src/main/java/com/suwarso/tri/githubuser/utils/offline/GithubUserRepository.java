package com.suwarso.tri.githubuser.utils.offline;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GithubUserRepository {

    private GithubUserDao githubUserDao;
    private LiveData<List<GithubUser>> listGithubUser;

    GithubUserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        githubUserDao = db.githubUserDao();
        listGithubUser = githubUserDao.getListUser();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<GithubUser>> getAllUser() {
        return listGithubUser;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(GithubUser githubUser) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            githubUserDao.insert(githubUser);
        });
    }

    LiveData<List<GithubUser>> search(String search) {
        return githubUserDao.searchUser(search);
    }

//    List<GithubUser> search(String search){
//
//    }

}
