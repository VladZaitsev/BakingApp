package com.baikaleg.v3.baking.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    @SerializedName("id")
    @Expose
    private final int id = 0;
    @SerializedName("name")
    @Expose
    private final String name = null;
    @SerializedName("ingredients")
    @Expose
    private final List<Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private final List<Step> steps = null;
    @SerializedName("servings")
    @Expose
    private final int servings = 0;
    @SerializedName("image")
    @Expose
    private final String image = null;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}