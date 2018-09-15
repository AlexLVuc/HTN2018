package com.example.adrianwong.snapcook.network;

import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.model.RecipeInstructions;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class SnapcookRestAdapter {

    private interface SnapcookService {

        @GET("/findByIngredients")
        Observable<List<Recipe>> getRecipeList(@HeaderMap Map<String, String> headers,
                                              @QueryMap Map<String, String> options,
                                              @Body List<String> ingredientsList);

        @GET("/analyzedInstructions")
        Observable<List<RecipeInstructions>> getRecipeInstructions(@Path("id") int id,
                                                                   @HeaderMap Map<String, String> header);


    }
}
