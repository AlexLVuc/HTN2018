package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipeInstructions {

    @Json(name = "name")
    private String name;

    @Json(name = "steps")
    private List<Step> stepList;

}
