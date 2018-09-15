package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Step {

    @Json(name = "number")
    private int number;

    @Json(name = "step")
    private String instruction;

    @Json(name = "ingredients")
    private List<Ingredient> ingredientList;

    @Json(name = "equipment")
    private List<Equipment> equipmentList;

}
