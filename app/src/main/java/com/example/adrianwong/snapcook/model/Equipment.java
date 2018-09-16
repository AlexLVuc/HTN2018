package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

public class Equipment {

    @Json(name = "id")
    private int id;

    @Json(name = "name")
    private String name;

    @Json(name = "image")
    private String imageUrl;

    public Equipment(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
