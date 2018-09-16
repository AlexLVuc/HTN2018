package com.example.adrianwong.snapcook.dagger;

import android.app.ListActivity;

import com.example.adrianwong.snapcook.ui.camera.CameraActivity;
import com.example.adrianwong.snapcook.ui.home.HomeActivity;
import com.example.adrianwong.snapcook.ui.recipe.RecipeActivity;
import com.example.adrianwong.snapcook.ui.recipedetail.RecipeDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HomeModule.class, CameraModule.class, NetworkModule.class, RecipeModule.class})
public interface AppComponent {

    void inject(HomeActivity homeActivity);
    void inject(CameraActivity cameraActivity);
    void inject(RecipeActivity recipeActivity);
    void inject(RecipeDetailActivity recipeDetailActivity);
}
