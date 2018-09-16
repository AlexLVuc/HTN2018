package com.example.adrianwong.snapcook.dagger;

import android.app.ListActivity;

import com.example.adrianwong.snapcook.ui.camera.CameraActivity;
import com.example.adrianwong.snapcook.ui.recipe.RecipeActivity;

import dagger.Component;

@Component
public interface AppComponent {

    void inject(ListActivity listActivity);
    void inject(CameraActivity cameraActivity);
    void inject(RecipeActivity recipeActivity);
}
