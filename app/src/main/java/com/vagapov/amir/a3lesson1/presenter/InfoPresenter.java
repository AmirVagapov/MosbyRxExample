package com.vagapov.amir.a3lesson1.presenter;



import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.vagapov.amir.a3lesson1.view.InfoView;



public interface InfoPresenter extends MvpPresenter<InfoView>{
    void loadInformation();
}
