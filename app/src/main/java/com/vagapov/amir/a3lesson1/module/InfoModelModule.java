package com.vagapov.amir.a3lesson1.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vagapov.amir.a3lesson1.model.InfoModel;
import com.vagapov.amir.a3lesson1.model.InfoModelImpl;
import com.vagapov.amir.a3lesson1.model.api.GithubService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class InfoModelModule {

    public static final String USERNAME = "username";
    public static final String ENDPOINT = "endpoint";

    @Provides
    public RealmConfiguration provideRealmConfiguration(){
        return new RealmConfiguration.Builder().build();
    }

    @Provides
    public Scheduler provideScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(ENDPOINT)
    public String provideEndpoint(){
        return "https://api.github.com/";
    }

    @Provides
    public GithubService provideRestApi(@Named(ENDPOINT) String endpoint, OkHttpClient client,
                                        Gson gson){
        return new Retrofit.Builder().baseUrl(endpoint).addCallAdapterFactory(RxJavaCallAdapterFactory
        .createWithScheduler(Schedulers.io())).addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build().create(GithubService.class);
    }

    @Provides
    public OkHttpClient provideClient(){
        return new OkHttpClient.Builder().build();
    }

    @Provides
    public Gson provideGson(){
        return new GsonBuilder().create();
    }

    @Provides
    @Named(USERNAME)
    public String provideUsername(){
        return "octocat";
    }

    @Provides
    public InfoModel provideInfoModel(@Named(USERNAME) String user, RealmConfiguration configuration,
                                      Scheduler scheduler, GithubService restApi){
        return new InfoModelImpl(user, restApi,configuration, scheduler);
    }

}
