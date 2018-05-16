package com.baikaleg.v3.baking.ui;

import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.data.model.Step;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Recipe createRecipe() {
        int id = 0;
        String name = "test_recipe_name";
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(0, null, "ingredient_1"));
        ingredients.add(new Ingredient(0, null, "ingredient_2"));
        ingredients.add(new Ingredient(0, null, "ingredient_3"));

        List<Step> steps = new ArrayList<>();
        steps.add(new Step(0, "shortDescription_0", "description_0", null, null));
        steps.add(new Step(1, "shortDescription_1", "description_1", null, null));
        steps.add(new Step(2, "shortDescription_2", "description_2", null, null));

        return new Recipe(id, name, ingredients, steps, 8, null);
    }
}