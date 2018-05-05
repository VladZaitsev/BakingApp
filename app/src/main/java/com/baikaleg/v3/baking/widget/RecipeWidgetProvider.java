package com.baikaleg.v3.baking.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsActivity;
import com.baikaleg.v3.baking.utils.Constants;
import com.google.gson.Gson;

import java.util.Arrays;

public class RecipeWidgetProvider extends AppWidgetProvider {
    public final static String WIDGET_RECIPE_EXTRA = "widget_recipe_extra";
    final String LOG_TAG = "myLogs";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        SharedPreferences sp = context.getSharedPreferences(
                Constants.SP, context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            updateWidget(context, appWidgetManager, sp, id);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }


    public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                                    SharedPreferences sp, int appWidgetId) {
        Gson gson = new Gson();
        String jsonRecipe = sp.getString(Constants.RECIPE_PREF, null);
        Recipe recipe = gson.fromJson(jsonRecipe, Recipe.class);

        RemoteViews rv = new RemoteViews(context.getPackageName(),
                R.layout.recipe_widget);
        rv.setTextViewText(R.id.recipe_widget_name, recipe.getName());

        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra(RecipeDetailsActivity.EXTRA_RECIPE, recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        rv.setOnClickPendingIntent(R.id.recipe_widget_name, pendingIntent);

        Intent adapter = new Intent(context, WidgetListService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        adapter.putExtra(WIDGET_RECIPE_EXTRA, jsonRecipe);
        Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
        adapter.setData(data);
        rv.setRemoteAdapter(R.id.recipe_widget_list, adapter);

        appWidgetManager.updateAppWidget(new ComponentName(context, RecipeWidgetProvider.class), rv);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.recipe_widget_list);
    }
}