package com.baikaleg.v3.baking.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.ui.recipedetails.adapter.StepsViewAdapter;
import com.baikaleg.v3.baking.ui.recipedetails.subview.IngredientView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BindingUtils {

    @BindingAdapter({"app:image"})
    public static void showImage(@NonNull ImageView imageView, @NonNull String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .error(imageView.getContext().getResources().getDrawable(R.drawable.ic_default_image))
                .placeholder(imageView.getContext().getResources().getDrawable(R.drawable.ic_default_image))
                .into(imageView);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:ingredients")
    public static void showIngredients(LinearLayout layout, List<Ingredient> ingredients) {
       /* StepsViewAdapter adapter = new StepsViewAdapter(ingredients);
        listView.setAdapter(adapter);*/
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientView ingredientView = new IngredientView(layout.getContext());
            ingredientView.setIngredient(ingredients.get(i));
            ((LinearLayout) layout).addView(ingredientView);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:steps")
    public static void showSteps(RecyclerView recyclerView, List<Step> steps) {
        StepsViewAdapter adapter = (StepsViewAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.refreshAdapter(steps);
        }
    }
}