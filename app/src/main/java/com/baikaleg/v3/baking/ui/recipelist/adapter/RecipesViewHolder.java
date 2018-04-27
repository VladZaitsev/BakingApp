package com.baikaleg.v3.baking.ui.recipelist.adapter;

import android.support.v7.widget.RecyclerView;

import com.baikaleg.v3.baking.databinding.ItemRecipesBinding;

class RecipesViewHolder extends RecyclerView.ViewHolder {

    final ItemRecipesBinding binding;

    RecipesViewHolder(ItemRecipesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}