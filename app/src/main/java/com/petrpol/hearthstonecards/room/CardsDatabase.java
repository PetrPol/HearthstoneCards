package com.petrpol.hearthstonecards.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.dao.CardBackDao;
import com.petrpol.hearthstonecards.room.dao.CardDao;
import com.petrpol.hearthstonecards.room.dao.FilterDao;

/** Room database for cards
 *  Singleton */
@Database(entities = {Card.class, CardBack.class, Filter.class}, version = 3, exportSchema = false)
public abstract class CardsDatabase extends RoomDatabase {

    private static CardsDatabase instance;

    public static synchronized CardsDatabase getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), CardsDatabase.class, context.getString(R.string.database_cards))
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }

    /**
     * Returns interface to access Card data
     */
    public abstract CardDao getCardDao();

    /**
     * Returns interface to access Filter data
     */
    public abstract FilterDao getFilterDao();

    /**
     * Returns interface to access CardBack data
     */
    public abstract CardBackDao getCardBacksDao();
}
