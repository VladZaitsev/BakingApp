package com.baikaleg.v3.baking.data.network;

import com.baikaleg.v3.baking.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("baking.json")
    Observable <List<Recipe>> getRecipes();
}