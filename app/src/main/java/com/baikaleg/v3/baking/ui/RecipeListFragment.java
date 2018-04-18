package com.baikaleg.v3.baking.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipeListFragment extends DaggerFragment {

    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }
}
