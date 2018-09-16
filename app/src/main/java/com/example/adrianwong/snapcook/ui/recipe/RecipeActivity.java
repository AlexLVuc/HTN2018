package com.example.adrianwong.snapcook.ui.recipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.adapter.RecipeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity implements RecipeView {

    @BindView(R.id.recipe_toolbar) Toolbar toolbar;
    @BindView(R.id.recipe_recycler_view) RecyclerView recyclerView;

    RecipePresenter recipePresenter;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ButterKnife.bind(this);

        recipeAdapter = new RecipeAdapter();
        recipePresenter = new RecipePresenter();
        recipePresenter.attachView(this);

        setupGui();
    }

    private void setupGui() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Available Recipes");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
    }
}
