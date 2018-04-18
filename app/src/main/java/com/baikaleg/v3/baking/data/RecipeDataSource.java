package com.baikaleg.v3.baking.data;

import com.baikaleg.v3.baking.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Access point for managing data.
 */
public interface RecipeDataSource {

    /**
     * Gets the recipes from the data source.
     *
     * @return the recipes from the data source.
     */
    Observable<List<Recipe>> getRecipes();
}