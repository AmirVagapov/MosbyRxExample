package com.vagapov.amir.a3lesson1.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.vagapov.amir.a3lesson1.R;
import com.vagapov.amir.a3lesson1.model.InfoModelImpl;
import com.vagapov.amir.a3lesson1.presenter.InfoPresenter;
import com.vagapov.amir.a3lesson1.presenter.InfoPresenterImpl;

import java.util.ArrayList;

public class InfoActivity extends MvpLceViewStateActivity<RecyclerView, ArrayList<String>,
        InfoView, InfoPresenter>
        implements InfoView {

    private static final String ERROR = "Unknown Error";
    private MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.activity_info);
        initUI();
    }

    private void initUI() {
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                updateAdapter();
                loadData(false);
            }
        });
        contentView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateAdapter();
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenterImpl(new InfoModelImpl());
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMsg = e.getMessage();
        return errorMsg == null ? ERROR : errorMsg;
    }

    @Override
    public void setData(ArrayList<String> arrayList) {
        adapter.setList(arrayList);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadInformation();
        loadingView.setVisibility(View.INVISIBLE);
    }


    @Override
    public LceViewState<ArrayList<String>, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public ArrayList<String> getData() {
        return ((MyRecyclerViewAdapter) contentView.getAdapter()).getList();
    }


    private void updateAdapter() {
        adapter = new MyRecyclerViewAdapter();
        contentView.setAdapter(adapter);
    }

}
