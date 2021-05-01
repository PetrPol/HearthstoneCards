package com.petrpol.hearthstonecards.data.repositories;

/** Interface for SingleCardsRepository */
public interface SingleCardRepositoryInterface {

    /** Called when data successfully got from server */
    void onCardDataGetSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onCardDataGetFail(String message);
}
