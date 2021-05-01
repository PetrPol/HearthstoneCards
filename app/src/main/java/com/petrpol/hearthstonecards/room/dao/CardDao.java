package com.petrpol.hearthstonecards.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petrpol.hearthstonecards.data.model.Card;

import java.util.List;

/** Interface for Card Database */
@Dao
public interface CardDao {

    /** Stores Card to database */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCard(Card card);

    /** Gets all cards from database*/
    @Query("SELECT * FROM cards_table")
    LiveData<List<Card>> getAllCards();

    /** Gets all cards of given type from database*/
    @Query("SELECT * FROM cards_table where type = :typeValue")
    LiveData<List<Card>> getCardsByType(String typeValue);

    /** Gets all cards of given set from database*/
    @Query("SELECT * FROM cards_table where cardSet = :setValue")
    LiveData<List<Card>> getCardsBySet(String setValue);

    /** Gets all cards of given class  from database*/
    @Query("SELECT * FROM cards_table where playerClass = :classValue")
    LiveData<List<Card>> getCardsByClass(String classValue);

    /** Gets card of given id from database */
    @Query("SELECT * FROM cards_table where cardId = :cardId")
    LiveData<Card> getCardById(String cardId);

}
