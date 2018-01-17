package com.vagapov.amir.a3lesson1.model.api;


import com.vagapov.amir.a3lesson1.model.entity.GithubUser;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {

    /*@Headers({
            "Accept: application/vnd.github.v3+json",
            "User-Agent: ViewerForGitHub v.1"
    })*/
    @GET("users/{user}")
    Observable<GithubUser> getUser(@Path("user") String user);

}
