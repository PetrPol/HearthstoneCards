package com.petrpol.hearthstonecards.ui.adapters.cards;

import android.view.View;

/** Interface for card list item  */
public interface CardItemCallback {
    void onClick(String cardId, View imageView);
}
