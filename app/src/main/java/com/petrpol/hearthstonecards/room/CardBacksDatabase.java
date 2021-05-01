package com.petrpol.hearthstonecards.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.dao.CardBackDao;
import com.petrpol.hearthstonecards.room.dao.FilterDao;

/** Database to store Card backs
 *  Singleton */
@Database(entities = CardBack.class, version = 1, exportSchema = false)
public abstract class CardBacksDatabase extends RoomDatabase {

    private static CardBacksDatabase instance;

    public static synchronized CardBacksDatabase getInstance(Context context){
        if (instance==null)
            instance = Room.databaseBuilder(context.getApplicationContext(), CardBacksDatabase.class,context.getString(R.string.database_card_backs)).build();

        return instance;
    }

    /** Returns interface to access data */
    public abstract CardBackDao getCardBacksDao();
}
