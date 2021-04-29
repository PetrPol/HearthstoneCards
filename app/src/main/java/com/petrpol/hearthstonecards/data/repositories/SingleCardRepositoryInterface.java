package com.petrpol.hearthstonecards.data.repositories;

/** Interface for SingleCardsRepository */
public interface SingleCardRepositoryInterface {

    void onCardDataGetSuccess();
    void onCardDataGetFail(String message);
}
