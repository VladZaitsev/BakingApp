package com.baikaleg.v3.baking.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {
    @SerializedName("id")
    @Expose
    private final int id = 0;
    @SerializedName("shortDescription")
    @Expose
    private final String shortDescription = null;
    @SerializedName("description")
    @Expose
    private final String description = null;
    @SerializedName("videoURL")
    @Expose
    private final String videoURL = null;
    @SerializedName("thumbnailURL")
    @Expose
    private final String thumbnailURL = null;
}