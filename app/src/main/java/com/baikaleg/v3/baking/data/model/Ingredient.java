package com.baikaleg.v3.baking.data.model;

import com.baikaleg.v3.baking.data.MeasureType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("quantity")
    @Expose
    private final double quantity = 0;
    @SerializedName("measure")
    @Expose
    private final MeasureType measure = null;
    @SerializedName("ingredient")
    @Expose
    private final String ingredient = null;

    public double getQuantity() {
        return quantity;
    }

    public MeasureType getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}