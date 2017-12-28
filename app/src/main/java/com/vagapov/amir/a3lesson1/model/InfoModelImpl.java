package com.vagapov.amir.a3lesson1.model;


import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class InfoModelImpl implements InfoModel {

    private Random mRandom = new Random();
    private Throwable mThrowable;
    private static final String FUBAR = "FUBAR";
    private static final String ERROR = "ERRORRRRRRRR";


    @Override
    public Observable<String> retrieveInfo() {
       Observable observable1 =  Observable.range(0, 10)
               .observeOn(Schedulers.computation())
               .flatMap(new Func1<Integer, Observable<String>>() {
           @Override
           public Observable<String> call(Integer i) {
               Observable<String> result;

               try {
                   TimeUnit.MILLISECONDS.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               result = Observable.just(String.valueOf("num is " + i));

               return result;
           }
       });

       Observable<String> observable = Observable
               .from(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"})
               .observeOn(Schedulers.computation()).map(new Func1<String, String>() {
           @Override
           public String call(String s) {
               try {
                   TimeUnit.MILLISECONDS.sleep(1100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               return s;
           }
       });

        return observable1.mergeWith(observable);
    }

    @Override
    public Throwable getThrowable() {
        return mThrowable;
    }
}
