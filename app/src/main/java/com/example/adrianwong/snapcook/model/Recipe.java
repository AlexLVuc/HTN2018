package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {

    @Json(name = "id")
    private int id;

    @Json(name = "title")
    private String title;

    @Json(name = "image")
    private String imageUrl;

    @Json(name = "usedIngredientCount")
    private int usedIngredientCount;

    @Json(name = "missedIngredientCount")
    private int missedIngredientCount;

    @Json(name = "likes")
    private int likes;
}
