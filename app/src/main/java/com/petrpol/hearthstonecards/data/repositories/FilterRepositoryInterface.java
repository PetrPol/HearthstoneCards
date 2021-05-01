package com.petrpol.hearthstonecards.data.repositories;

/** Interface for FilterRepository */
public interface FilterRepositoryInterface {

    /** Called when data successfully got from server */
    void onFilterDataGetSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onFilterDataGetFail(String message);
}
