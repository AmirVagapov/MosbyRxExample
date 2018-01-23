package com.vagapov.amir.a3lesson1.module;

import com.vagapov.amir.a3lesson1.presenter.InfoPresenter;
import com.vagapov.amir.a3lesson1.view.InfoActivity;

import dagger.Component;

@Component(modules = {InfoModelModule.class, InfoPresenterModule.class, InfoViewModule.class, ActivityModule.class})
public interface InfoComponent {

    void inject(InfoActivity activity);

    InfoPresenter presenter();
}
