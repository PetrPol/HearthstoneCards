package com.petrpol.hearthstonecards.data.repositories;

/** Interface for CardBacksRepository */
public interface CardBacksRepositoryInterface {

    /** Called when data successfully got from server */
    void onCardBackDataGetSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onCardBackDataGetFail(String message);
}
