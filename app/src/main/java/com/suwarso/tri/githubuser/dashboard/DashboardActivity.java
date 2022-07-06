package com.suwarso.tri.githubuser.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.suwarso.tri.githubuser.R;
import com.suwarso.tri.githubuser.detail.UserDetailActivity;
import com.suwarso.tri.githubuser.utils.ConnectivityReceiver;
import com.suwarso.tri.githubuser.utils.core.BaseActivity;
import com.suwarso.tri.githubuser.utils.core.BaseApp;
import com.suwarso.tri.githubuser.utils.offline.GithubUser;
import com.suwarso.tri.githubuser.utils.offline.GithubUserAdapter;
import com.suwarso.tri.githubuser.utils.offline.GithubUserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements DashboardView,
        ConnectivityReceiver.ConnectivityReceiverListener{

    @BindView(R.id.dashboard_et_search) EditText etSearch;
    @BindView(R.id.dashboard_btn_search) ImageButton btnSearch;
    @BindView(R.id.dashboard_progressBar) ProgressBar pBar;
    @BindView(R.id.dashboard_layoutData) NestedScrollView layoutData;
    @BindView(R.id.dashboard_recyclerView) RecyclerView recyclerView;
    @BindView(R.id.dashboard_pBarLoadMore) ProgressBar pBarLoadMore;
    @BindView(R.id.dashboard_placeholder) ConstraintLayout layoutPlaceholder;
    @BindView(R.id.imageView) ImageView imgPlaceHolder;
    @BindView(R.id.textView) TextView tvTitlePlaceholder;
    @BindView(R.id.textView3) TextView tvDescriptionPlaceholder;
    @BindView(R.id.profile_switchtheme) Switch switchTheme;
    DashboardPresenter presenter;
    int since = 0;
    //    List<GithubUser> postDataList = new ArrayList<>();
//    UserViewModel postViewModel;
//    ListUserAdapter listUserAdapter;
//    //    List<ListUserResponse> listUser;
//    List<GithubUser> users = new ArrayList<>();
    ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();
    boolean isConnected;
    boolean tryLoadUrl = false;
    boolean search = false;

//    UserAdapter adapter;
    List<GithubUser> listUser = new ArrayList<>();
    GithubUserAdapter adapter;
    GithubUserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        presenter = new DashboardPresenter(this);
        presenter.onViewAttached(this);

        adapter = new GithubUserAdapter(this, listUser);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(onItemClickListener);

        viewModel = new ViewModelProvider(this).get(GithubUserViewModel.class);
        viewModel.getListUser().observe(this, list -> {
            if(list.size() == 0){
                tryLoadUrl = true;
                presenter.loadDataUser(since);
            }else{
                since = list.get(list.size() - 1).getId();
            }
            listUser = list;
            adapter.setList(listUser);
        });

        layoutData.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                if(isConnected && !search){
                    tryLoadUrl = true;
                    presenter.loadDataUser(since);
                }
            }
        });

        etSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUser(etSearch.getText().toString());
                return true;
            }
            return false;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(etSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        checkConnection();

        switchTheme.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityReceiver, intentFilter);
        BaseApp.getInstance().setConnectivityListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void loadUserSuccess(List<ListUserResponse> response) {
        hideProgress();
        for (ListUserResponse m: response) {
            GithubUser model = new GithubUser(m.getId(), m.getLogin(), m.getNodeId(), m.getAvatarUrl(), m.getGravatarId(),
                    m.getUrl(), m.getHtmlUrl(), m.getFollowersUrl(), m.getFollowingUrl(), m.getGistsUrl(),
                    m.getStarredUrl(), m.getSubscriptionsUrl(), m.getOrganizationsUrl(), m.getReposUrl(),
                    m.getEventsUrl(), m.getReceivedEventsUrl(), m.getType(), m.getSiteAdmin(), "", false);
            viewModel.insert(model);
        }
        setContentPlaceHolder(1);
    }

    @Override
    public void userNoData() {
        setContentPlaceHolder(4);
    }

    @Override
    public void error() {
        setContentPlaceHolder(5);
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
            case 5:
                // View Home when data user is not found
                layoutPlaceholder.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
                imgPlaceHolder.setImageResource(R.drawable.ic_error);
                tvTitlePlaceholder.setText("Error");
                tvDescriptionPlaceholder.setText("No Internet Connection");
                break;
        }
    }

    private View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra("github_user", new Gson().toJson(listUser.get(position)));
        activityLauncher.launch(intent, result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                viewModel.getListUser().observe(this, list -> {
                    if(list.size() == 0){
                        tryLoadUrl = true;
                        presenter.loadDataUser(since);
                    }else{
                        since = list.get(list.size() - 1).getId();
                    }
                    listUser = list;
                    adapter.setList(listUser);
                });
            }
        });
    };

    void checkConnection(){
        isConnected = ConnectivityReceiver.isConnected();
        onNetworkConnectionChanged(isConnected);
    }

    @Override
    public void onNetworkConnectionChanged(boolean connect) {
        if(connect){
            pBarLoadMore.setVisibility(View.VISIBLE);
            setContentPlaceHolder(1);
            if(tryLoadUrl){
                presenter.loadDataUser(since);
            }
        }else{
            pBarLoadMore.setVisibility(View.GONE);
            setContentPlaceHolder(1);
        }
    }

    void searchUser(String search){
        String searchQuery = "%" + search +"%";
        if(!search.isEmpty()){
            viewModel.searchData(search);
            viewModel.searchData(searchQuery).observe(this, list -> {
                listUser = list;
                adapter.setList(listUser);
            });
        }else{
            viewModel.getListUser().observe(this, list -> {
                if(list.size() == 0){
                    tryLoadUrl = true;
                    presenter.loadDataUser(since);
                }else{
                    since = list.get(list.size() - 1).getId();
                }
                listUser = list;
                adapter.setList(listUser);
            });
        }
    }
}