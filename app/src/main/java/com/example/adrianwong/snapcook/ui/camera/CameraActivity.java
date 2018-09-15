package com.example.adrianwong.snapcook.ui.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adrianwong.snapcook.R;
import com.google.firebase.FirebaseApp;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        FirebaseApp.initializeApp(this);
    }
}
