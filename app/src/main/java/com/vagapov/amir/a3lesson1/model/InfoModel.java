package com.vagapov.amir.a3lesson1.model;


import rx.Observable;

public interface InfoModel  {

    Observable retrieveInfo();

    Throwable getThrowable();

}
