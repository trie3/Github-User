package com.suwarso.tri.githubuser.utils.offline;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GithubUser githubUser);

    @Query("DELETE FROM github_user")
    void deleteAllUsers();

    @Query("SELECT * FROM github_user ORDER BY id ASC")
    LiveData<List<GithubUser>> getListUser();

    @Query("SELECT * FROM github_user WHERE id = :id")
    GithubUser getUserById(int id);

    @Query("SELECT * FROM github_user WHERE login LIKE :search OR note LIKE :search")
    LiveData<List<GithubUser>> searchUser(String search);

}
