package com.example.adrianwong.snapcook.ui.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.adapter.RecipeAdapter;
import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.network.RestConstants;
import com.example.adrianwong.snapcook.ui.recipedetail.RecipeDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity implements RecipeView, RecipeAdapter.InteractionListener {

    @BindView(R.id.recipe_toolbar) Toolbar toolbar;
    @BindView(R.id.recipe_recycler_view) RecyclerView recyclerView;

    @Inject RecipePresenter recipePresenter;
    @Inject RecipeAdapter recipeAdapter;

    String ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);

        recipePresenter.attachView(this);

        Intent intent = getIntent();
        ingredients = intent.getStringExtra("INGREDIENTS");
        Log.d("RecipeActivity", ingredients);

        setupGui();
        fetchRequests();
    }

    private void fetchRequests() {
        recipePresenter.getRecipeList(ingredients);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipePresenter.onDestroy();
        recipePresenter.detachView();
    }

    private void setupGui() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Available Recipes");
        toolbar.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);
        recipeAdapter.setListInteractionListener(this);
    }

    @Override
    public void updateRecipeList(List<Recipe> recipes) {
        recipeAdapter.setRecipeList(recipes);
        recipeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void onListClick(int id) {
        Recipe recipe = recipeAdapter.getRecipe(id);
        String title = recipe.getTitle();
        String imageUrl = recipe.getImageUrl();
        int recipeId = recipe.getId();

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra("RECIPE_TITLE", title);
        intent.putExtra("IMAGE_URL", imageUrl);
        intent.putExtra("RECIPE_ID", recipeId);

        startActivity(intent);
    }
}
