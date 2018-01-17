package com.vagapov.amir.a3lesson1.model.image;


import android.support.annotation.NonNull;
import android.view.View;


public interface ImageLoader<T extends View> {

    void downloadInfo(@NonNull String url,@NonNull T placeHolder);
}
