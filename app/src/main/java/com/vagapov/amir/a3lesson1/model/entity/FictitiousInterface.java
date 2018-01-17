package com.vagapov.amir.a3lesson1.model.entity;

import android.support.annotation.Nullable;


public interface FictitiousInterface {


    @Nullable
    default String getId() {
        return null;
    }

    @Nullable
    default String getAvatar() {
        return null;
    }

    @Nullable
    default String getLogin() {
        return null;
    }

    @Nullable
    default String getWorkPlace() {
        return null;
    }

    @Nullable
    default String getUserName() {
        return null;
    }

    @Nullable
    default String getCity() {
        return null;
    }

    @Nullable
    default String getEmail() {
        return null;
    }

    @Nullable
    default String getBiography() {
        return null;
    }
}
