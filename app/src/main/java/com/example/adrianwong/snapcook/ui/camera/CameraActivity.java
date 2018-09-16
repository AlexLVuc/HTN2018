package com.example.adrianwong.snapcook.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.google.android.gms.common.util.CrashUtils;
import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.File;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CameraActivity extends AppCompatActivity implements CameraView {

    private CameraHelper helper;

    @BindView(R.id.search_recipe_button)
    Button searchRecipeButton;

    @BindView(R.id.imageView)
    ImageView imageHolder;

    Bitmap photo = null;

    File photoFile = null;

    Single<ClassifiedImages> observable;

    VisualRecognition visualRecognition;

    ClassifyOptions classifyOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);
        ButterKnife.bind(this);

        initViews();

        observable = Single.create(new SingleOnSubscribe<ClassifiedImages>() {
            @Override
            public void subscribe(SingleEmitter<ClassifiedImages> emitter) throws Exception {
                IamOptions options = new IamOptions.Builder()
                        .apiKey(getString(R.string.api_key))
                        .build();

                visualRecognition = new VisualRecognition("2018-03-19", options);
                classifyOptions = new ClassifyOptions.Builder()
                        .imagesFile(photoFile)
                        .classifierIds(Collections.singletonList("Ingredients_1267642770"))
                        .owners(Collections.singletonList("me"))
                        .build();

                Log.d("CameraActivity", "photo: " + photoFile.getName());

                ClassifiedImages classifiedImages = visualRecognition.classify(classifyOptions).execute();
                emitter.onSuccess(classifiedImages);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        // Initialize camera helper
        helper = new CameraHelper(this);
        helper.dispatchTakePictureIntent();
    }

    // Get the bitmap and image path onActivityResult of an activity or fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = helper.getBitmap(resultCode);
        photoFile = helper.getFile(resultCode);
        imageHolder.setImageBitmap(photo);
    }

    private void initViews() {
        searchRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CameraActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                observable.subscribe(new SingleObserver<ClassifiedImages>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("CameraActivity", "hello");
                    }

                    @Override
                    public void onSuccess(ClassifiedImages classifiedImages) {
                        List<ClassResult> resultList = classifiedImages.getImages().get(0).getClassifiers().get(0).getClasses();
                        Log.d("CameraActivity", "size: " + resultList.size());
                        for (ClassResult classResult : resultList) {
                            Log.d("CameraActivity", classResult.getClassName());
                        }
                        Toast.makeText(CameraActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("CameraActivity", e.getLocalizedMessage());
                    }
                });
            }
        });
    }


    @Override
    public void startRecipeActivity() {

    }
}
