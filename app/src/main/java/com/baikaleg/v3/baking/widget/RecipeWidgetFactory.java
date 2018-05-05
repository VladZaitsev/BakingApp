package com.baikaleg.v3.baking.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Ingredient;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeWidgetFactory implements RemoteViewsFactory {

    private List<Ingredient> data;
    private Recipe recipe;
    private Context context;

    public RecipeWidgetFactory(Context context, Intent intent) {
        String jsonRecipe = intent.getStringExtra(RecipeWidgetProvider.WIDGET_RECIPE_EXTRA);
        Gson gson = new Gson();
        this.context = context;
        this.recipe = gson.fromJson(jsonRecipe, Recipe.class);
    }

    @Override
    public void onCreate() {
        data = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        data.clear();
        data = recipe.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rView = new RemoteViews(context.getPackageName(), R.layout.recipe_wiget_item);
        rView.setTextViewText(R.id.widget_item_ingredient, data.get(i).getIngredient());
        return rView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}