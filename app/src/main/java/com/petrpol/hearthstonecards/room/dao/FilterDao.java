package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.Filter;

import java.util.List;

/** Interface for Filter Database */
@Dao
public interface FilterDao {

    /** Stores filter to database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFilter(Filter filter);

    /** Gets all filters from database */
    @Query("SELECT * FROM filter_table ORDER BY patch DESC")
    LiveData<List<Filter>> getFilters();
}
