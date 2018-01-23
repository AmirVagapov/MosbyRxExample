package com.vagapov.amir.a3lesson1.model;


import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;

import com.vagapov.amir.a3lesson1.model.entity.FictitiousInterface;

import java.util.List;

import rx.Observable;

public interface InfoModel  {

    String ERROR_SUBSCRIBE_TWICE = "You can't subscribe on lifecycle twice";
    String REALM_CLOSED = "Realm is already closed";
    String ERROR_LIFECYCLE_SUBSCRIBE = "You should subscribe on lifecycle() first";

    @NonNull
    Observable<? extends List<? extends FictitiousInterface>> lifecycle(); //Открываем закрываем DB

    @NonNull
    Observable<? extends List<? extends FictitiousInterface>> observeInfo(); //обновляем View данными из БД

    @NonNull
    Observable<? extends List<? extends FictitiousInterface>> updateInfo();// обновляем инфу в DB

}
