package com.vagapov.amir.a3lesson1.module;

import com.vagapov.amir.a3lesson1.model.InfoModel;
import com.vagapov.amir.a3lesson1.presenter.InfoPresenter;
import com.vagapov.amir.a3lesson1.presenter.InfoPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class InfoPresenterModule {

    @Provides
    public InfoPresenter providePresenter(InfoModel model){
        return new InfoPresenterImpl(model);
    }
}
