package com.example.adrianwong.snapcook.ui.camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.mindorks.paracamera.Camera;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity implements CameraView {

    private Camera camera;
    private static int PERMISSION_REQUEST_CODE = 1;
    private CameraPresenter cameraPresenter;

    @BindView(R.id.take_picture_button) Button takePictureButton;
    @BindView(R.id.imageView) ImageView imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);

        cameraPresenter = new CameraPresenter();

        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)//1
                .setTakePhotoRequestCode(Camera.REQUEST_TAKE_PHOTO)//2
                .setDirectory("pics")//3
                .setName("delicious_${System.currentTimeMillis()}")//4
                .setImageFormat(Camera.IMAGE_JPEG)//5
                .setCompression(75)//6
                .build(this);

        try {
            camera.takePicture();
        }catch (Exception e){
            e.printStackTrace();
        }

        ButterKnife.bind(this);

    }

    @OnClick(R.id.take_picture_button)
    public void onTakePictureButtonClick() {
        try {
            camera.takePicture();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Get the bitmap and image path onActivityResult of an activity or fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Camera.REQUEST_TAKE_PHOTO){
            Bitmap bitmap = camera.getCameraBitmap();
            if(bitmap != null) {
                imageHolder.setImageBitmap(bitmap);
            }else{
                Toast.makeText(this.getApplicationContext(),"Picture not taken!",Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void startRecipeActivity() {

    }
}
