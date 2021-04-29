package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.CardBack;

import java.util.List;

@Dao
public interface CardBackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCardBack(CardBack card);

    @Query("SELECT * FROM card_backs_table ORDER BY name ASC")
    LiveData<List<CardBack>> getAllCardBacks();

}
