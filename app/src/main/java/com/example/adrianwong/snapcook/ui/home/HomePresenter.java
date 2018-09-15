package com.example.adrianwong.snapcook.ui.home;

import com.example.adrianwong.snapcook.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeActivity> {

    public HomePresenter() {
    }

    public void onButtonClick() {
        mView.startCameraActivity();
    }
}
