package com.example.adrianwong.snapcook.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Provides;

public class NetworkModule {

    Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }
}
