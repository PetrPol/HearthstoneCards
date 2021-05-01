package com.petrpol.hearthstonecards.ui.adapters.cards;

import android.view.View;

/** Interface for card list item  */
public interface CardItemCallback {

    /** Called when clicked on item
     *  @param cardId Selected card id
     *  @param imageView Image view of item */
    void onClick(String cardId, View imageView);
}
