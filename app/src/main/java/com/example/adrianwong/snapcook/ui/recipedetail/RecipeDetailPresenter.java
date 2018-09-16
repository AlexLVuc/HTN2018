package com.example.adrianwong.snapcook.ui.recipedetail;

import android.util.Log;

import com.example.adrianwong.snapcook.model.Ingredient;
import com.example.adrianwong.snapcook.model.RecipeInstructions;
import com.example.adrianwong.snapcook.model.Step;
import com.example.adrianwong.snapcook.network.RestConstants;
import com.example.adrianwong.snapcook.network.SnapcookRestAdapter;
import com.example.adrianwong.snapcook.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecipeDetailPresenter extends BasePresenter<RecipeDetailActivity> {

    private SnapcookRestAdapter restAdapter;
    private CompositeDisposable disposable;

    @Inject
    public RecipeDetailPresenter(SnapcookRestAdapter restAdapter) {
        this.restAdapter = restAdapter;
        this.disposable = new CompositeDisposable();
    }

    public void getRecipeInfo(int recipeId) {
        disposable.add(restAdapter.getRecipeInstructions(RestConstants.X_MASHAPE_KEY, RestConstants.ACCEPT, recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<RecipeInstructions>>() {
                @Override
                public void onNext(List<RecipeInstructions> recipeInstructions) {
                    Log.d("inside", "inside onNext");

                    List<Step> stepList = recipeInstructions.get(0).getStepList();
                    StringBuilder ingredients = new StringBuilder();
                    StringBuilder instructions = new StringBuilder();

                    for (Step step : stepList) {
                        List<Ingredient> ingredientList = step.getIngredientList();
                        for (Ingredient ingredient : ingredientList) {
                            ingredients.append(ingredient.getName()).append(", ");
                            Log.d("inside", "ingredients: " + ingredients.toString());
                        }

                        Log.d("inside", "instructions: " + instructions.toString());


                        instructions.append(step.getNumber()).append(". ").append(step.getInstruction()).append("\n\n");
                    }

                    mView.setupUi(ingredients.toString(), instructions.toString());
                }

                @Override
                public void onError(Throwable e) {


                    Log.d("inside", e.getLocalizedMessage());

                }

                @Override
                public void onComplete() {
                    Log.d("inside", "inside onComplete");

                }
            }));
    }

}
