package com.example.adrianwong.snapcook.ui.recipedetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeView {

    @BindView(R.id.recipe_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.recipe_image)
    ImageView imageHolder;

    @BindView(R.id.ingredient_text)
    TextView ingredientText;

    @BindView(R.id.recipe_text)
    TextView recipeText;

    private String title;

    private String imageUrl;

    private int recipeId;

    @Inject RecipeDetailPresenter recipeDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        MyApplication.getApp().getAppComponent().inject(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("RECIPE_TITLE");
        imageUrl = intent.getStringExtra("IMAGE_URL");
        recipeId = intent.getIntExtra("RECIPE_ID", -1);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setVisibility(View.VISIBLE);
        recipeText.setMovementMethod(new ScrollingMovementMethod());

        recipeDetailPresenter.attachView(this);
        fetchRequests();
    }

    private void fetchRequests() {
        Log.d("inside", "fetching request");

        recipeDetailPresenter.getRecipeInfo(recipeId);
    }

    @Override
    public void setupUi(String ingredients, String instructions) {
        Picasso.with(this).load(imageUrl).into(imageHolder);
        ingredientText.setText(ingredients);
        recipeText.setText(instructions);


    }
}
