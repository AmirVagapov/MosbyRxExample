package com.vagapov.amir.a3lesson1.model.api;


import okhttp3.OkHttpClient;
import okhttp3.Request;

public  class InterceptorsClient {

    private static OkHttpClient.Builder client = new OkHttpClient.Builder();


    private static void addRequest() {
        client.interceptors().add(chain -> {
            Request request = chain.request();

            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .addHeader("User-Agent", "ViewerForGitHub v.1")
                    .method(request.method(), request.body());

            return chain.proceed(requestBuilder.build());
        });
    }

    public static OkHttpClient.Builder getClient() {
        addRequest();
        return client;
    }
}
