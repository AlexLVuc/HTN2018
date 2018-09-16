package com.example.adrianwong.snapcook.ui.home;

import com.example.adrianwong.snapcook.ui.base.BasePresenter;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeActivity> {

    @Inject
    public HomePresenter() {
    }

    public void onButtonClick() {
        mView.startCameraActivity();
    }
}
