package com.baikaleg.v3.baking.data;

import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.data.network.RecipeApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class Repository implements RecipeDataSource {
    private final RecipeApi recipeApi;

    @Inject
    public Repository(final RecipeApi recipeApi) {
        this.recipeApi = recipeApi;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return recipeApi.createService().getRecipes();
    }
}