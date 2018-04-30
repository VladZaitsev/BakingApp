package com.baikaleg.v3.baking.ui.recipelist.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.ItemRecipeViewBinding;
import com.baikaleg.v3.baking.ui.recipelist.RecipeNavigator;

import java.util.ArrayList;
import java.util.List;

public class RecipesViewAdapter extends RecyclerView.Adapter<RecipesViewAdapter.RecipesViewHolder> {

    private List<Recipe> recipesList = new ArrayList<>();
    @Nullable
    private final RecipeNavigator callback;
    private final int viewHeight, viewWidth;

    public RecipesViewAdapter(@Nullable RecipeNavigator callback, int viewWidth, int viewHeight) {
        this.callback = callback;
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeViewBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recipe_view,
                        parent, false);
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(viewWidth, viewHeight));
        binding.setCallback(callback);
        return new RecipesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        holder.binding.setRecipe(recipesList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return recipesList == null ? 0 : recipesList.size();
    }

    public void refreshAdapter(List<Recipe> list) {
        recipesList.clear();
        recipesList.addAll(list);
        notifyDataSetChanged();
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder {

        final ItemRecipeViewBinding binding;

        RecipesViewHolder(ItemRecipeViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}