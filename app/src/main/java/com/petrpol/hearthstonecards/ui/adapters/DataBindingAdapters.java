package com.petrpol.hearthstonecards.ui.adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
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

    @BindingAdapter({"imageUrl","imageFullSize"})
    public static void loadImage(ImageView view, String imageUrl, boolean imageFullSize){

        if (imageFullSize)
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.card_placeholder)
                .into(view);
        else
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.card_back_placeholder)
                .resize(125,173)
                .into(view);
    }

    @BindingAdapter({"card","showGolden"})
    public static void loadImageDetail(ImageView view, Card card, boolean showGolden){

        if (card!=null) {
            String imageUrl;
            if (showGolden)
                imageUrl = card.getImgGold();
            else
                imageUrl = card.getImg();

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.card_back_placeholder)
                    .into(view);

            //Fetch golden image to quick show
            if (!showGolden && card.getImgGold()!= null && !card.getImgGold().isEmpty())
                Picasso.get().load(card.getImgGold()).fetch();
        }
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
            view.setText(view.getContext().getString(R.string.patch_text,filters.get(0).getPatch()));
        else
            view.setText("");
    }

    @BindingAdapter("android:text")
    public static void setCardBackText(TextView view, List<CardBack> cardBacks){
        if (cardBacks != null && cardBacks.size() > 0)
            view.setText(view.getContext().getString(R.string.card_back_title,cardBacks.size()));
        else
            view.setText("");
    }

    @BindingAdapter("noCardsText")
    public static void setNoCardsText(TextView view, FilterType viewType){
        view.setText(view.getContext().getString(R.string.no_cards_description,viewType.toString()));
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
    @BindingAdapter({"showFilterIcon"})
    public static void showFilterIcon(FloatingActionButton view, boolean showIcon) {
        if(showIcon)
            view.setImageResource(R.drawable.ic_baseline_filter_list_24);
        else
            view.setImageDrawable(null);
    }

}
