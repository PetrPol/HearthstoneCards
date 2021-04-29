package com.petrpol.hearthstonecards.data.repositories;

/** Interface for CardBAcksRepository */
public interface CardBacksRepositoryInterface {

    void onCardBackDataGetSuccess();
    void onCardBackDataGetFail(String message);
}
