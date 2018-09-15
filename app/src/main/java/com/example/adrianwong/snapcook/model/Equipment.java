package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {

    @Json(name = "id")
    private int id;

    @Json(name = "name")
    private String name;

    @Json(name = "image")
    private String imageUrl;

}
