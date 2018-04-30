package com.baikaleg.v3.baking.ui.recipedetails.subview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Ingredient;

public class IngredientView extends RelativeLayout {
    private TextView ingredientTxt, quantityTxt, measureTxt;

    public IngredientView(Context context) {
        super(context);
        init();
    }

    public IngredientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.item_ingredient_view, this);
        ingredientTxt = findViewById(R.id.ingredient_txt);
        quantityTxt = findViewById(R.id.quantity_txt);
        measureTxt = findViewById(R.id.measure_txt);
    }

    public void setIngredient(Ingredient ingredient) {
        ingredientTxt.setText(ingredient.getIngredient());
        quantityTxt.setText(String.valueOf(ingredient.getQuantity()));
        measureTxt.setText(ingredient.getMeasure());
    }
}