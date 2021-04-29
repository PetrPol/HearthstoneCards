package com.petrpol.hearthstonecards.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.room.dao.CardDao;
import com.petrpol.hearthstonecards.room.dao.FilterDao;

/** Room database for cards
 *  Singleton */
@Database(entities = Card.class, version = 2, exportSchema = false)
public abstract class CardsDatabase extends RoomDatabase {

    private static CardsDatabase instance;

    public static synchronized CardsDatabase getInstance(Context context){
        if (instance==null)
            instance = Room.databaseBuilder(context.getApplicationContext(),CardsDatabase.class,"cards_database").allowMainThreadQueries().build();

        return instance;
    }

    public abstract  CardDao getCardDao();


}
