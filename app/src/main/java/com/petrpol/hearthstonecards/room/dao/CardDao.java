package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCard(Card card);

    @Query("SELECT * FROM cards_table ORDER BY cost ASC")
    LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM cards_table where type = :typeValue ORDER BY cost ASC")
    LiveData<List<Card>> getCardsByType(String typeValue);

    @Query("SELECT * FROM cards_table where cardSet = :setValue ORDER BY cost ASC")
    LiveData<List<Card>> getCardsBySet(String setValue);

    @Query("SELECT * FROM cards_table where playerClass = :classValue ORDER BY cost ASC")
    LiveData<List<Card>> getCardsByClass(String classValue);

}
