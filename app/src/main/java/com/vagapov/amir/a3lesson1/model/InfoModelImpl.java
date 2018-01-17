package com.vagapov.amir.a3lesson1.model;



import android.support.annotation.NonNull;
import android.util.Log;

import com.vagapov.amir.a3lesson1.model.api.GithubService;
import com.vagapov.amir.a3lesson1.model.entity.FictitiousInterface;
import com.vagapov.amir.a3lesson1.model.entity.GithubUser;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public class InfoModelImpl implements InfoModel {

    private final String user;
    private final GithubService api;
    private final RealmConfiguration configuration;
    private final Scheduler scheduler;

    private Realm realm;



    public InfoModelImpl(@NonNull String user,@NonNull GithubService api
            ,@NonNull RealmConfiguration configuration,@NonNull Scheduler scheduler) {
        this.user = user;
        this.api = api;
        this.configuration = configuration;
        this.scheduler = scheduler;
    }


    @NonNull
    @Override
    public Observable<? extends List<? extends FictitiousInterface>> lifecycle() {
        return Observable.defer(() -> {
            if(checkRealmIsValid()){
                throw new IllegalStateException(ERROR_SUBSCRIBE_TWICE);
            }
            realm = Realm.getInstance(configuration);
            return (Observable<List<GithubUser>>) observeInfo();
        }).doOnUnsubscribe(() -> {
            if(!checkRealmIsValid()){
                throw new IllegalStateException(REALM_CLOSED);
            }
            realm.close();
            realm = null;
        });
    }

    @NonNull
    @Override
    public Observable<? extends List<? extends FictitiousInterface>> observeInfo() {
        return Observable.defer(() -> {
            if(!checkRealmIsValid()){
                throw new IllegalStateException(ERROR_LIFECYCLE_SUBSCRIBE);
            }
            return realm.where(GithubUser.class)   //RealmQuery<>
                    .findAllAsync()
                    .<RealmResults<GithubUser>>asObservable()
                    .filter(RealmResults::isLoaded);
        }).subscribeOn(scheduler);
    }

    @NonNull
    @Override
    public Observable<? extends List<? extends FictitiousInterface>> updateInfo() {
        return api.getUser(user).observeOn(scheduler).doOnNext(githubUser -> {
            if(!checkRealmIsValid()){
                throw new IllegalStateException(ERROR_LIFECYCLE_SUBSCRIBE);
            }
            realm.executeTransactionAsync(realm -> {
                GithubUser realmObject = realm.where(GithubUser.class).findFirst();
                if(realmObject == null){
                    realm.copyToRealmOrUpdate(githubUser);
                }else {
                    fillUserDescription(realmObject, githubUser);
                }
            });
        }).map(Collections::singletonList);
    }

    private void fillUserDescription(GithubUser realmObject, GithubUser githubUser) {
        String userAvatarUrl = githubUser.getAvatar();
        if(userAvatarUrl != null && !userAvatarUrl.equals(realmObject.getAvatar())){
            realmObject.setAvatar(userAvatarUrl);
        }
        String userName = githubUser.getUserName();
        if(userName != null && !userName.equals(realmObject.getUserName())){
            realmObject.setUserName(userName);
        }
        String id = githubUser.getId();
        if(id != null && !id.equals(realmObject.getId())){
            realmObject.setId(id);
        }
        String company = githubUser.getWorkPlace();
        if(company != null && !company.equals(realmObject.getWorkPlace())){
            realmObject.setWorkPlace(company);
        }
        String location = githubUser.getCity();
        if(location != null && !location.equals(realmObject.getCity())){
            realmObject.setCity(location);
        }
        String email = githubUser.getEmail();
        if(email != null && !email.equals(realmObject.getEmail())){
            realmObject.setEmail(email);
        }
        String bio = githubUser.getBiography();
        if(bio != null && !bio.equals(realmObject.getBiography())){
            realmObject.setBiography(bio);
        }
    }

    private boolean checkRealmIsValid(){
        return realm != null && !realm.isClosed();
    }
}
