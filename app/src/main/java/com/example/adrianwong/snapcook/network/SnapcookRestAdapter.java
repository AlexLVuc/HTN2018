package com.example.adrianwong.snapcook.network;

import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.model.RecipeInstructions;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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

    @Inject
    public SnapcookRestAdapter(Retrofit retrofit) {
        this.snapcookService = retrofit.create(SnapcookService.class);
    }

    public interface SnapcookService {

        // FIND RECIPES BASED ON INGREDIENTS
        @GET("findByIngredients")
        Observable<List<Recipe>> getRecipeList(@Header("X-Mashape-Key") String key,
                                               @Header("Accept") String accept,
                                               @Query("ingredients") String ingredients,
                                               @Query("number") int number,
                                               @Query("ranking") int ranking);

        // RETRIEVE THE ACTUAL RECIPE INSTRUCTIONS
        @GET("{id}/analyzedInstructions")
        Observable<List<RecipeInstructions>> getRecipeInstructions(@Header("X-Mashape-Key") String key,
                                                                   @Header("Accept") String accept,
                                                                   @Path("id") int id);

    }

    public Observable<List<Recipe>> getRecipeList(String key, String accept, String ingredients, int number, int ranking) {
        return snapcookService.getRecipeList(key, accept, ingredients, number, ranking);
    }

    public Observable<List<RecipeInstructions>> getRecipeInstructions(String key, String accept, int id) {
        return snapcookService.getRecipeInstructions(key, accept, id);
    }
}
