package com.example.adrianwong.snapcook.network;

import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.model.RecipeInstructions;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class SnapcookRestAdapter {

    private final SnapcookService snapcookService;

    public SnapcookRestAdapter(Retrofit retrofit) {
        this.snapcookService = retrofit.create(SnapcookService.class);
    }

    public interface SnapcookService {

        // FIND RECIPES BASED ON INGREDIENTS
        @GET("/findByIngredients")
        Observable<List<Recipe>> getRecipeList(@Header("X_Mashape_Keyheaders") String key,
                                               @Header("Accept") String accept,
                                               @Query("fillIngredients") boolean fillIngredients,
                                               @Query("ingredients") String ingredients,
                                               @Query("limitLicense") boolean limitLicense,
                                               @Query("number") int number,
                                               @Query("ranking") int ranking);

        // RETRIEVE THE ACTUAL RECIPE INSTRUCTIONS
        @GET("/analyzedInstructions")
        Observable<List<RecipeInstructions>> getRecipeInstructions(@Path("id") int id,
                                                                   @Header("X_Mashape_Keyheaders") String key,
                                                                   @Header("Accept") String accept);

    }
}
