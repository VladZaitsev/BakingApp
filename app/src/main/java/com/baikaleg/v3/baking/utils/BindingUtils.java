package com.baikaleg.v3.baking.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.ui.recipedetails.adapter.IngredientsViewAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

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
    public static void showIngredients(ListView listView, List<Ingredient> ingredients) {
        IngredientsViewAdapter adapter = new IngredientsViewAdapter(ingredients);
        listView.setAdapter(adapter);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:steps")
    public static void showSteps(LinearLayout layout, List<Step> steps) {

    }
}