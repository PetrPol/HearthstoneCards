package com.petrpol.hearthstonecards.ui.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.utils.SnackBarController;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    @BindingAdapter("snackBar")
    public static void showSnackBar(CoordinatorLayout view, String message){
        if (message!=null) {
            SnackBarController.showDefaultSnackBar(view, message);
        }
    }

    @BindingAdapter({"filterType","filterValue"})
    public static void setText(TextView view, FilterType filterType, String filterValue){

        if (filterType == null || filterValue == null ||filterType == FilterType.NONE) {
            view.setText("All Cards");
            return;
        }

        Log.i("TEST ",filterType.name());

        String text = "";
        switch (filterType){//TODO
            case TYPE:
                text+= "Type: ";
                break;
            case CLASS:
                text+= "Class: ";
                break;
            case SET:
                text+= "Set:  ";
        }

        text+=filterValue;
        view.setText(text);
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, List<Filter> filters){
        if (filters != null && filters.size() > 0)
            view.setText(filters.get(0).getPatch());
        else
            view.setText("");
    }

    @BindingAdapter({"filterType","filterShowed"})
    public static void setFilterMotion(MotionLayout motionLayout, FilterType filterType, Boolean filterShowed) {
        if (filterType == null || !filterShowed)
            motionLayout.transitionToState(R.id.start);
        else if (filterType==FilterType.NONE)
            motionLayout.transitionToState(R.id.filter);
        else
            motionLayout.transitionToState(R.id.end);
    }

    @BindingAdapter({"containsLore"})
    public static void setText(TextView view, Boolean containsLore) {
        if (containsLore)
            view.setText(R.string.card_detail_flavor_title);
        else
            view.setText(R.string.card_detail_flavor_title_empty);

    }

}
