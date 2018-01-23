package com.vagapov.amir.a3lesson1.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Context context;

    public ActivityModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }


}
