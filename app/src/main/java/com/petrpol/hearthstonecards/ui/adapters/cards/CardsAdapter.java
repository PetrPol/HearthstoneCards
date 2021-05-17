package com.petrpol.hearthstonecards.ui.adapters.cards;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.ui.adapters.ARecyclerViewAdapter;

import java.util.List;

/**
 * Recycler adapter for list of cards
 * Extends ARecyclerViewAdapter - can be easily added another type of item
 */
public class CardsAdapter extends ARecyclerViewAdapter {

    Context mContext;
    LiveData<List<Card>> mCardsDataSet;
    CardItemCallback mCardItemCallback;

    /** Default constructor */
    public CardsAdapter(Context mContext, LiveData<List<Card>> mCardsDataSet, CardItemCallback mCardItemCallback) {
        this.mContext = mContext;
        this.mCardsDataSet = mCardsDataSet;
        this.mCardItemCallback = mCardItemCallback;

        mCardsDataSet.observe((LifecycleOwner) mContext, cardList -> notifyDataSetChanged());
    }

    /** Returns card of given position from data set */
    @Override
    protected Object getItemForPosition(int position) {
        if (mCardsDataSet.getValue()!=null)
            return mCardsDataSet.getValue().get(position);
        return null;
    }

    /** Returns callback listener to given position */
    @Override
    protected Object getListenerForPosition(int position) {
        return mCardItemCallback;
    }

    /** Returns Layout for card item */
    @Override
    protected int getLayoutForPosition(int position) {
        return R.layout.list_item_card;
    }

    /** Returns count of item in data set */
    @Override
    public int getItemCount() {
        if (mCardsDataSet.getValue()!=null)
            return mCardsDataSet.getValue().size();
        else
            return 0;
    }
}
