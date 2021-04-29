package com.petrpol.hearthstonecards.ui.adapters.cardBack;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.ui.adapters.ARecyclerViewAdapter;
import com.petrpol.hearthstonecards.ui.adapters.cards.CardItemCallback;

import java.util.List;

/** Recycler adapter for list of cards backs
 *  Extends ARecyclerViewAdapter - can be easily added another type of item */
public class CardBacksAdapter extends ARecyclerViewAdapter {

    Context mContext;
    LiveData<List<CardBack>> mCardBacksDataSet;

    /** Default constructor */
    public CardBacksAdapter(Context mContext, LiveData<List<CardBack>> mCardBacksDataSet) {
        this.mContext = mContext;
        this.mCardBacksDataSet = mCardBacksDataSet;

        mCardBacksDataSet.observe((LifecycleOwner) mContext, cardList -> notifyDataSetChanged());
    }

    /** Returns card of given position from data set */
    @Override
    protected Object getItemForPosition(int position) {
        if (mCardBacksDataSet.getValue()!=null)
            return mCardBacksDataSet.getValue().get(position);
        return null;
    }

    /** Returns callback listener to given position */
    @Override
    protected Object getListenerForPosition(int position) {
        return null;
    }

    /** Returns Layout for card item */
    @Override
    protected int getLayoutForPosition(int position) {
        return R.layout.list_item_card_back;
    }

    /** Returns count of item in data set */
    @Override
    public int getItemCount() {
        if (mCardBacksDataSet.getValue()!=null)
            return mCardBacksDataSet.getValue().size();
        else
            return 0;
    }
}
