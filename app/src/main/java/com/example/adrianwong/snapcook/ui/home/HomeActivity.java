package com.example.adrianwong.snapcook.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.ui.camera.CameraActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeView{

    @Inject HomePresenter homePresenter;
    @BindView(R.id.snap_button) Button snapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);

        homePresenter.attachView(this);
    }

    @OnClick(R.id.snap_button)
    public void startCamera() {
        homePresenter.onButtonClick();
    }

    @Override
    public void startCameraActivity() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
}
