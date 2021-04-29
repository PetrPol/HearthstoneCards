package com.petrpol.hearthstonecards.data.repositories;

/** Interface for FilterRepository */
public interface FilterRepositoryInterface {

    void onFilterDataGetSuccess();
    void onFilterDataGetFail(String message);
}
