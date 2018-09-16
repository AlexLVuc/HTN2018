package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import java.util.List;

public class RecipeInstructions {

    @Json(name = "name")
    private String name;

    @Json(name = "steps")
    private List<Step> stepList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public RecipeInstructions(String name, List<Step> stepList) {

        this.name = name;
        this.stepList = stepList;
    }
}
