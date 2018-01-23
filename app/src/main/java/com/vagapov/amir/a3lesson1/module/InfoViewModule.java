package com.vagapov.amir.a3lesson1.module;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vagapov.amir.a3lesson1.model.image.ImageLoader;
import com.vagapov.amir.a3lesson1.model.image.PicassoImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class InfoViewModule {

    @Provides
    public ImageLoader<ImageView> providesAvatarLoader(Context context){
        return new PicassoImageLoader(Picasso.with(context));
    }

}
