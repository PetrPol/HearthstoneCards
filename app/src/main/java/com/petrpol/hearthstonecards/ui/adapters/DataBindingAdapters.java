package com.petrpol.hearthstonecards.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.ui.cardBacks.CardBacksViewModel;
import com.petrpol.hearthstonecards.ui.home.HomeViewModel;
import com.petrpol.hearthstonecards.utils.SnackBarController;
import com.petrpol.hearthstonecards.utils.StringFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

/** Binding adapters for data binding */
public class DataBindingAdapters {

    /** Sets visibility based on boolean value */
    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        if (view !=null && value != null){
            view.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    }

    /** Loads image to image view
     * @param imageUrl - url of image to load
     * @param imageFullSize - if true image is loaded in full size - if false image is resized and showed as thumbnail */
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

    /** Loads image to image view - Allows to load golden version of card
     *  @param card - card with images to show
     *  @param showGolden - if true is loaded golden version of card*/
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

    /** Snack bar binder - shows snackbar in CoordinatorLayout
     * @param message - message of snackbar to show */
    @BindingAdapter("snackBar")
    public static void showSnackBar(CoordinatorLayout view, String message){
        if (message!=null) {
            SnackBarController.showDefaultSnackBar(view, message);
        }
    }

    /** Shows text of actual filter settings
     * @param filterType - actual type of filter
     * @param filterValue - value of actual filter */
    @BindingAdapter({"filterType","filterValue"})
    public static void setText(TextView view, FilterType filterType, String filterValue){

        if (filterType == null || filterValue == null ||filterType == FilterType.NONE) {
            view.setText(view.getContext().getString(R.string.list_title_all_cards));
            return;
        }

        String text = view.getContext().getString(R.string.list_title_filter, filterType, filterValue);
        view.setText(StringFormatter.firstLetterToUppercase(text));
    }

    /** Set patch text of newest filter */
    @BindingAdapter("android:text")
    public static void setText(TextView view, List<Filter> filters){
        if (filters != null && filters.size() > 0)
            view.setText(view.getContext().getString(R.string.patch_text,filters.get(0).getPatch()));
        else
            view.setText("");
    }

    /** Set text to number of found card backs */
    @BindingAdapter("android:text")
    public static void setCardBackText(TextView view, List<CardBack> cardBacks){
        if (cardBacks != null && cardBacks.size() > 0)
            view.setText(view.getContext().getString(R.string.card_back_title,cardBacks.size()));
        else
            view.setText("");
    }

    /** Set text to no card found splash screen description
     *  @param viewType - Filter type used when no cards found
     *  @param connectionProblems - if true show connection problems text */
    @BindingAdapter({"noCardsText","connectionProblems"})
    public static void setNoCardsText(TextView view, FilterType viewType, boolean connectionProblems){
        if (connectionProblems)
            view.setText(view.getContext().getString(R.string.connection_problems_description));
        else
            view.setText(view.getContext().getString(R.string.no_cards_description,viewType.toString()));
    }

    /** Set text to no card found splash screen title
     *  @param connectionProblems - if true show connection problems text */
    @BindingAdapter("connectionProblems")
    public static void setNoCardsText(TextView view, boolean connectionProblems){
        if (connectionProblems)
            view.setText(view.getContext().getString(R.string.connection_problems_title));
        else
            view.setText(view.getContext().getString(R.string.no_cards_title));
    }

    /** Sets motion state based on given filter type and filter showed value */
    @BindingAdapter({"filterType","filterShowed"})
    public static void setFilterMotion(MotionLayout motionLayout, FilterType filterType, Boolean filterShowed) {
        if (filterType == null || !filterShowed)
            motionLayout.transitionToState(R.id.start);
        else if (filterType==FilterType.NONE)
            motionLayout.transitionToState(R.id.filter);
        else
            motionLayout.transitionToState(R.id.end);
    }

    /** Set lore title text based on boolean value
     *  @param containsLore - true if card contains lore info */
    @BindingAdapter({"containsLore"})
    public static void setText(TextView view, Boolean containsLore) {
        if (containsLore)
            view.setText(R.string.card_detail_flavor_title);
        else
            view.setText(R.string.card_detail_flavor_title_empty);

    }

    /** Shows or hide button icon based on boolean value
     * @param showIcon - if true icon is showed */
    @BindingAdapter({"showFilterIcon"})
    public static void showFilterIcon(FloatingActionButton view, boolean showIcon) {
        if(showIcon)
            view.setImageResource(R.drawable.ic_baseline_filter_list_24);
        else
            view.setImageDrawable(null);
    }

    /** Creates on refresh data listener for card back view model */
    @BindingAdapter({"refreshListener"})
    public static void setOnRefreshListener(SwipeRefreshLayout view, CardBacksViewModel cardBacksViewModel) {
        view.setOnRefreshListener(cardBacksViewModel::refreshData);
    }

    /** Creates on refresh data listener for home view model */
    @BindingAdapter({"refreshListener"})
    public static void setOnRefreshListener(SwipeRefreshLayout view, HomeViewModel homeViewModel) {
        view.setOnRefreshListener(homeViewModel::updateCardData);
    }

}
