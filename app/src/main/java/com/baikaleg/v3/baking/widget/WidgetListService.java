package com.baikaleg.v3.baking.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetFactory(getApplicationContext(), intent);
    }
}