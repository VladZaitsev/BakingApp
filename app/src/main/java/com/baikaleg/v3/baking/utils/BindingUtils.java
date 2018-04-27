package com.baikaleg.v3.baking.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.baikaleg.v3.baking.R;
import com.squareup.picasso.Picasso;

import java.io.File;

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
}