package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.CardBack;

import java.util.List;

/** Interface for Card back Database */
@Dao
public interface CardBackDao {

    /** Stores Card back to database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCardBack(CardBack card);

    /** Gets all card back from database */
    @Query("SELECT * FROM card_backs_table")
    LiveData<List<CardBack>> getAllCardBacks();

}
