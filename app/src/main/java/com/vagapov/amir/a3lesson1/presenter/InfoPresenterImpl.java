package com.vagapov.amir.a3lesson1.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vagapov.amir.a3lesson1.model.InfoModel;
import com.vagapov.amir.a3lesson1.view.InfoView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel model;
    private Subscription subscription;
    private ArrayList<String> list = new ArrayList<>();

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    public void loadInformation() {
        getView().showLoading(true);

        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

                setDataOnView(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                setDataOnView("");
            }
        });

    }


    private void setDataOnView(String s) {
        InfoView view = getView();
        list.add(s);
        if (isViewAttached()) {
            view.setData(list);
            view.showContent();
        }
    }
}
