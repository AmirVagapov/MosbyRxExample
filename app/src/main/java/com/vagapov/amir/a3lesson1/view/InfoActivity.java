package com.vagapov.amir.a3lesson1.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import com.vagapov.amir.a3lesson1.R;

import com.vagapov.amir.a3lesson1.model.entity.FictitiousInterface;
import com.vagapov.amir.a3lesson1.model.image.ImageLoader;

import com.vagapov.amir.a3lesson1.module.ActivityModule;
import com.vagapov.amir.a3lesson1.module.DaggerInfoComponent;
import com.vagapov.amir.a3lesson1.module.InfoComponent;
import com.vagapov.amir.a3lesson1.presenter.InfoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InfoActivity extends MvpLceViewStateActivity<LinearLayout, FictitiousInterface,
        InfoView, InfoPresenter>
        implements InfoView {

    private static final String user = "octocat";

    private static final String ERROR = "Unknown Error";

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.user_image)
    ImageView userImage;

    @BindView(R.id.user_login_text_view)
    TextView loginTextView;

    @BindView(R.id.user_id_text_view)
    TextView idTextView;

    @BindView(R.id.user_name_text_view)
    TextView nameTextView;

    @BindView(R.id.user_workplace_text_view)
    TextView workplaceTextView;

    @BindView(R.id.user_city_text_view)
    TextView cityTextView;

    @BindView(R.id.user_site_text_view)
    TextView siteTextView;

    @BindView(R.id.user_bio_text_view)
    TextView bioTextView;


    @Inject
    ImageLoader<ImageView> imageLoader;
    private InfoComponent component;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        component = DaggerInfoComponent.builder().activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                loadData(false);
            }
        });
    }


    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return component.presenter();
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMsg = e.getMessage();
        return errorMsg == null ? ERROR : errorMsg;
    }

    @Override
    public void setData(@NonNull FictitiousInterface data) {

        runOnUiThread(() -> fillViews(data));
        loadingView.setVisibility(View.INVISIBLE);
    }

    private void fillViews(@NonNull FictitiousInterface data) {
        loginTextView.setText(data.getLogin());
        idTextView.setText(data.getId());
        nameTextView.setText(data.getUserName());
        workplaceTextView.setText(data.getWorkPlace());
        cityTextView.setText(data.getCity());
        siteTextView.setText(data.getEmail());
        bioTextView.setText(data.getBiography());

        if(data.getAvatar() != null) {
            imageLoader.downloadInfo(data.getAvatar(), userImage);
        } else{
            userImage.setImageResource(R.drawable.ic_portrait_black_24dp);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        userImage.setImageResource(R.drawable.ic_portrait_black_24dp);
        presenter.loadInformation(pullToRefresh);
        loadingView.setVisibility(View.VISIBLE);
    }


    @NonNull
    @Override
    public LceViewState<FictitiousInterface, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @NonNull
    @Override
    public FictitiousInterface getData() {
        return new FictitiousInterface() {};
    }

}
