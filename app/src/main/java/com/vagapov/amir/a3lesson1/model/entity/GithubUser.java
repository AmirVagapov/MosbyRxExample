package com.vagapov.amir.a3lesson1.model.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class GithubUser extends RealmObject implements FictitiousInterface {

    private static final String NULL_INFO = "Information is absent";

    @PrimaryKey
    @Nullable
    @SerializedName("login")
    @Expose
    private String login;

    @Nullable
    @SerializedName("avatar_url")
    @Expose
    private String avatar;


    @Nullable
    @SerializedName("id")
    @Expose
    private String id;


    @Nullable
    @SerializedName("company")
    @Expose
    private String workPlace;


    @Nullable
    @SerializedName("name")
    @Expose
    private String userName;


    @Nullable
    @SerializedName("location")
    @Expose
    private String city;


    @Nullable
    @SerializedName("email")
    @Expose
    private String email;


    @Nullable
    @SerializedName("bio")
    @Expose
    private String biography;

    @Nullable
    public String getId() {
        if(id != null) {
            return id;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getAvatar() {

        return avatar;
    }

    @Nullable
    public String getLogin() {
        if(login != null) {
            return login;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getWorkPlace() {
        if(workPlace != null) {
            return workPlace;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getUserName() {
        if(userName != null) {
            return userName;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getCity() {
        if(city != null) {
            return city;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getEmail() {
        if(email != null) {
            return email;
        }
        return NULL_INFO;
    }

    @Nullable
    public String getBiography() {
        if(biography != null) {
            return biography;
        }
        return NULL_INFO;
    }

    public void setLogin(@Nullable String login) {
        this.login = login;
    }

    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    public void setWorkPlace(@Nullable String workPlace) {
        this.workPlace = workPlace;
    }

    public void setUserName(@Nullable String userName) {
        this.userName = userName;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setBiography(@Nullable String biography) {
        this.biography = biography;
    }
}
