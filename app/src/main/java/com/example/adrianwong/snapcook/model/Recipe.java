package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(int missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Recipe(int id, String title, String imageUrl, int usedIngredientCount, int missedIngredientCount, int likes) {

        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.usedIngredientCount = usedIngredientCount;
        this.missedIngredientCount = missedIngredientCount;
        this.likes = likes;
    }
}