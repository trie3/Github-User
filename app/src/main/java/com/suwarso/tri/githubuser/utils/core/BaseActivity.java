package com.suwarso.tri.githubuser.utils.core;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;
    public boolean isError;
    protected final BetterActivityResult<Intent, ActivityResult> activityLauncher =
            BetterActivityResult.registerActivityForResult(this);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isError = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isError = false;
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

}
