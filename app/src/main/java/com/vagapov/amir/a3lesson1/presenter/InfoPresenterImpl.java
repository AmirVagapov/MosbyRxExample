package com.vagapov.amir.a3lesson1.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Pair;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vagapov.amir.a3lesson1.model.InfoModel;
import com.vagapov.amir.a3lesson1.model.entity.FictitiousInterface;
import com.vagapov.amir.a3lesson1.view.InfoView;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;


public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    @NonNull
    private final InfoModel model;

    @Nullable
    private Subscription subscription;

    @Nullable
    private Subscription updating;

    public InfoPresenterImpl(@NonNull InfoModel model) {
        this.model = model;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(retainInstance){
            tryToUnsubscribe(subscription);
            tryToUnsubscribe(updating);
        }
    }

    @Override
    public void attachView(InfoView view) {
        super.attachView(view);
        if(!isSubscribed(subscription)){
            subscription = model.lifecycle().filter(githubUsers -> !githubUsers.isEmpty())
                    .map(githubUsers -> githubUsers.get(0))
                    .subscribe(this::setDataOnView, this::setDataOnView);
        }
    }

    private void tryToUnsubscribe(@Nullable Subscription subscription){
        if(isSubscribed(subscription)){
            subscription.unsubscribe();
        }
    }

    private boolean isSubscribed(@Nullable Subscription subscription){
        return subscription != null && !subscription.isUnsubscribed();
    }



    @Override
    public void loadInformation(boolean pullToRefresh) {
        tryToUnsubscribe(updating);
        final Throwable[] newThrowable = new Throwable[1];


        updating = model.observeInfo()
                .map((Func1<List<? extends FictitiousInterface>, Boolean>) List::isEmpty)
                .doOnNext(aBoolean -> getView().showLoading(!aBoolean))
                .zipWith(model.updateInfo()
                        .doOnNext((Action1<List<? extends FictitiousInterface>>) fictitiousInterfaces -> {
                    if(!fictitiousInterfaces.isEmpty()){
                        setDataOnView(fictitiousInterfaces.get(0));
                    }
                }).isEmpty().onErrorReturn(throwable -> {
                    newThrowable[0] = throwable;
                    return true;
                }), Pair::create)
                .subscribe(booleanBooleanPair -> {
                    boolean isViewEmpty = booleanBooleanPair.first;
                    boolean isErrorCaused = booleanBooleanPair.second;
                    if(isErrorCaused && isViewAttached()){
                        getView().showError(newThrowable[0], !isViewEmpty);
                    }
                });

    }

    private void setDataOnView(@NonNull FictitiousInterface s) {
        InfoView view = getView();
        if (isViewAttached()) {
            view.setData(s);
            view.showContent();
        }
    }

    private void setDataOnView(@NonNull Throwable s) {
        InfoView view = getView();
        if (isViewAttached()) {
            view.showError(s, false);
            view.showContent();
        }
    }
}
