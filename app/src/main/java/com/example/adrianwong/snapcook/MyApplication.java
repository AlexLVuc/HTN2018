package com.example.adrianwong.snapcook;

import android.app.Application;

import com.example.adrianwong.snapcook.dagger.AppComponent;
import com.example.adrianwong.snapcook.dagger.DaggerAppComponent;
import com.example.adrianwong.snapcook.dagger.NetworkModule;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {

    private static MyApplication app;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        app = this;

        appComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static MyApplication getApp () {
        return app;
    }
}
