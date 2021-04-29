package com.petrpol.hearthstonecards.data.repositories;

/** Interface for CardsRepository */
public interface CardsRepositoryInterface {

    void onCardDataGetSuccess();
    void onCardDataGetFail(String message);
}
