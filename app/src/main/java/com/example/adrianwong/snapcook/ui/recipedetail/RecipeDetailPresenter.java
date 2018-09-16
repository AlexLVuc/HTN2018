package com.example.adrianwong.snapcook.ui.recipedetail;

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

    public void getRecipeInfo() {
        disposable.add(restAdapter.getRecipeInstructions(RestConstants.X_MASHAPE_KEY, RestConstants.ACCEPT, 2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<RecipeInstructions>() {
                @Override
                public void onNext(RecipeInstructions recipeInstructions) {
                    List<Step> stepList = recipeInstructions.getStepList();
                    StringBuffer ingredients = new StringBuffer();
                    StringBuffer instructions = new StringBuffer();

                    for (Step step : stepList) {
                        List<Ingredient> ingredientList = step.getIngredientList();
                        for (Ingredient ingredient : ingredientList) {
                            ingredients.append(ingredient.getName()).append(", ");
                        }

                        instructions.append(step.getNumber()).append(". ").append(step.getInstruction()).append("\n\n");
                    }


                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }));
    }

}
