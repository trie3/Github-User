package com.suwarso.tri.githubuser.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.suwarso.tri.githubuser.R;
import com.suwarso.tri.githubuser.dashboard.DashboardPresenter;
import com.suwarso.tri.githubuser.utils.core.BaseActivity;
import com.suwarso.tri.githubuser.utils.offline.GithubUser;
import com.suwarso.tri.githubuser.utils.offline.GithubUserViewModel;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends BaseActivity implements UserDetailView{

    @BindView(R.id.userDetail_btn_back) ImageView btnBack;
    @BindView(R.id.userDetail_toolbar_title) TextView tvToolbarTitle;
    @BindView(R.id.userDetail_progress_bar) ProgressBar pBar;
    @BindView(R.id.userDetail_placeholder) ConstraintLayout layoutPlaceholder;
    @BindView(R.id.userDetail_layoutData) NestedScrollView layoutData;
    @BindView(R.id.userDetail_img_userDetail) CircleImageView imgProfile;
    @BindView(R.id.userDetail_tv_followers) TextView tvFollowers;
    @BindView(R.id.userDetail_tv_following) TextView tvFollowing;
    @BindView(R.id.userDetail_tv_name) TextView tvName;
    @BindView(R.id.userDetail_tv_company) TextView tvCompany;
    @BindView(R.id.userDetail_tv_location) TextView tvLocation;
    @BindView(R.id.userDetail_tv_blog) TextView tvBlog;
    @BindView(R.id.userDetail_et_notes) EditText etNotes;
    @BindView(R.id.userDetail_btn_save) Button btnSave;
    @BindView(R.id.imageView) ImageView imgPlaceHolder;
    @BindView(R.id.textView) TextView tvTitlePlaceholder;
    @BindView(R.id.textView3) TextView tvDescriptionPlaceholder;
    UserDetailPresenter presenter;
    GithubUser githubUser;
    GithubUserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        presenter = new UserDetailPresenter(this);
        presenter.onViewAttached(this);
        showProgress();
        viewModel = new ViewModelProvider(this).get(GithubUserViewModel.class);
        githubUser = new Gson().fromJson(getIntent().getStringExtra("github_user"), GithubUser.class);
        presenter.loadDataUser(githubUser.getLogin());

        if(githubUser.getNote().equals("default")){
            etNotes.setText("");
        }else{
            etNotes.setText(githubUser.getNote());
        }

        githubUser.setSeen(true);

        btnBack.setOnClickListener(view -> {
            viewModel.insert(githubUser);
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        });

        btnSave.setOnClickListener(view -> {
            String note = etNotes.getText().toString();
            githubUser.setNote(note);
            viewModel.insert(githubUser);
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        viewModel.insert(githubUser);
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }

    @Override
    public void loadDetailUserSuccess(UserDetailResponse response) {
        setContentPlaceHolder(1);
        tvToolbarTitle.setText(response.getName());
        Glide.with(this)
                .load(response.getAvatarUrl())
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);
        if (response.getFollowers() != 0) {
            tvFollowers.setText(String.valueOf(response.getFollowers()));
        } else {
            tvFollowers.setText("-");
        }

        if (response.getFollowers() != 0) {
            tvFollowing.setText(String.valueOf(response.getFollowing()));
        } else {
            tvFollowing.setText("-");
        }

        tvName.setText(response.getName());
        tvCompany.setText(response.getCompany());
        tvLocation.setText(response.getLocation());
        tvBlog.setText(response.getBlog());
    }

    @Override
    public void error() {
        setContentPlaceHolder(2);
    }

    @Override
    public void showProgress() {
        pBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pBar.setVisibility(View.GONE);
    }

    void setContentPlaceHolder(int condition){
        switch (condition){
            case 1:
                // View All when data is success load
                layoutPlaceholder.setVisibility(View.GONE);
                layoutData.setVisibility(View.VISIBLE);
                break;
            case 2:
                // View All when data is error load
                layoutPlaceholder.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
                imgPlaceHolder.setImageResource(R.drawable.ic_error);
                tvTitlePlaceholder.setText("Error");
                tvDescriptionPlaceholder.setText("Something went wrong, please try again");
                break;
            case 3:
                // View Home when waiting to search
                layoutPlaceholder.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
                imgPlaceHolder.setImageResource(R.drawable.ic_search);
                tvTitlePlaceholder.setText("Waiting to search!");
                tvDescriptionPlaceholder.setText("Search results will appear here");
                break;
            case 4:
                // View Home when data user is not found
                layoutPlaceholder.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
                imgPlaceHolder.setImageResource(R.drawable.ic_error);
                tvTitlePlaceholder.setText("Error");
                tvDescriptionPlaceholder.setText("We could not find search results");
                break;
        }
    }
}