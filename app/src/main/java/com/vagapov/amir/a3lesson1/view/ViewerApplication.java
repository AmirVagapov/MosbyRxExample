package com.vagapov.amir.a3lesson1.view;

import android.app.Application;
import io.realm.Realm;


public class ViewerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
