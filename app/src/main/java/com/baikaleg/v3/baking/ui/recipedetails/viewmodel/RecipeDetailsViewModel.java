package com.baikaleg.v3.baking.ui.recipedetails.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.data.model.Step;

public class RecipeDetailsViewModel extends ViewModel {

    public final ObservableField<Recipe> recipeObservable = new ObservableField<>();

    public final ObservableList<Step> steps = new ObservableArrayList<>();

    public final ObservableList<Ingredient> ingredients = new ObservableArrayList<>();

    public final ObservableField<Integer> selected = new ObservableField<>();

    public void setRecipe(Recipe recipe) {
        recipeObservable.set(recipe);

        steps.clear();
        steps.addAll(recipe.getSteps());

        ingredients.clear();
        ingredients.addAll(recipe.getIngredients());
    }

    public void setSelected(int selected) {
        this.selected.set(selected);
    }
}