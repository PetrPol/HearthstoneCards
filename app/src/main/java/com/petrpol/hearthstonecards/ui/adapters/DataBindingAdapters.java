package com.petrpol.hearthstonecards.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.squareup.picasso.Picasso;

public class DataBindingAdapters {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        if (view !=null && value != null){
            view.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("android:maxHeight")
    public static void setLayoutHeight(View view, FilterType filterType) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (filterType != null && filterType != FilterType.NONE)
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        else
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(layoutParams);
    }
}
