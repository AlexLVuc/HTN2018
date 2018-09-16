package com.example.adrianwong.snapcook.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Step {

    @Json(name = "number")
    private int number;

    @Json(name = "step")
    private String instruction;

    @Json(name = "ingredients")
    private List<Ingredient> ingredientList;

    @Json(name = "equipment")
    private List<Equipment> equipmentList;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Step(int number, String instruction, List<Ingredient> ingredientList, List<Equipment> equipmentList) {

        this.number = number;
        this.instruction = instruction;
        this.ingredientList = ingredientList;
        this.equipmentList = equipmentList;
    }
}
