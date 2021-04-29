package com.petrpol.hearthstonecards.data.repositories;

/** Interface for CardsRepository */
public interface CardsRepositoryInterface {

    void onCardDataGetSuccess();
    void onCardDataGetFail(String message);

    void onFilterDataGetSuccess();
    void onFilterDataGetFail(String message);
}
