package com.baikaleg.v3.baking.ui.recipelist.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.ItemRecipesBinding;
import com.baikaleg.v3.baking.ui.recipelist.RecipesNavigator;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private List<Recipe> recipesList = new ArrayList<>();
    private final RecipesNavigator callback;
    private final int viewHeight, viewWidth;

    public RecipesAdapter(RecipesNavigator callback, int viewWidth, int viewHeight) {
        this.callback = callback;
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipesBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recipes,
                        parent, false);
        binding.setCallback(callback);
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(viewWidth, viewHeight));
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
}