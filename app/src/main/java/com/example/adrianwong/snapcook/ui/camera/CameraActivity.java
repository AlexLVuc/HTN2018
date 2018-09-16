package com.example.adrianwong.snapcook.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity implements CameraView {

    private VisualRecognition vrClient;
    private CameraHelper helper;

    @BindView(R.id.search_recipe_button) Button searchRecipeButton;
    @BindView(R.id.imageView) ImageView imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);
        ButterKnife.bind(this);

        vrClient = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20,
                getString(R.string.api_key)
        );

        // Initialize camera helper
        helper = new CameraHelper(this);
        helper.dispatchTakePictureIntent();
    }

    @OnClick(R.id.search_recipe_button)
    public void onSearchRecipeButton() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    // Get the bitmap and image path onActivityResult of an activity or fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            final Bitmap photo = helper.getBitmap(resultCode);
            final File photoFile = helper.getFile(resultCode);
            imageHolder.setImageBitmap(photo);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    VisualClassification response =
                            vrClient.classify(
                                    new ClassifyImagesOptions.Builder()
                                            .images(photoFile)
                                            .build()
                            ).execute();

                    ImageClassification classification = response.getImages().get(0);
                    VisualClassifier classifier = classification.getClassifiers().get(0);

                    final StringBuffer output = new StringBuffer();
                    for(VisualClassifier.VisualClass object: classifier.getClasses()) {
                        if(object.getScore() > 0.7f)
                            output.append("<").append(object.getName()).append("> ");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CameraActivity.this, output, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }



    @Override
    public void startRecipeActivity() {

    }
}
