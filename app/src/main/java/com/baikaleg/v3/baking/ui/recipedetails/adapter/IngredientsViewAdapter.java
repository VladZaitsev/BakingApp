package com.baikaleg.v3.baking.ui.recipedetails.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.databinding.ItemIngredientBinding;

import java.util.ArrayList;
import java.util.List;

public class IngredientsViewAdapter extends BaseAdapter {

    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientsViewAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemIngredientBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ingredient,
                        parent, false);

        Ingredient ingredient = getIngredient(position);
        binding.ingredientTxt.setText(ingredient.getIngredient());
        binding.measureTxt.setText(ingredient.getMeasure());
        binding.quantityTxt.setText(String.valueOf(ingredient.getQuantity()));
        return binding.getRoot();
    }

    private Ingredient getIngredient(int position) {
        return ((Ingredient) getItem(position));
    }
}