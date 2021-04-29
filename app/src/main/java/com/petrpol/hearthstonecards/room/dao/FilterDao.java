package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.Filter;

import java.util.List;

@Dao
public interface FilterDao {

    //Filter
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFilter(Filter filter);

    @Query("SELECT * FROM filter_table ORDER BY patch DESC")
    LiveData<List<Filter>> getFilters();
}
