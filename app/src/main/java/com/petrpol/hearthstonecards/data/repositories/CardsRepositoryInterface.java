package com.petrpol.hearthstonecards.data.repositories;

/** Interface for CardsRepository */
public interface CardsRepositoryInterface {

    /** Called when data successfully got from server */
    void onCardDataGetSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onCardDataGetFail(String message);

    /** Called when empty data got from server*/
    void onCardDataGetFailNoCards();
}
