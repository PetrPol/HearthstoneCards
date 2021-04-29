package com.petrpol.hearthstonecards.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.dao.FilterDao;

/** Database to store filers info
 *  Singleton */
@Database(entities = Filter.class, version = 1, exportSchema = false)
@TypeConverters(RoomConverters.class)
public abstract class FilterDatabase extends RoomDatabase {

    private static FilterDatabase instance;

    public static synchronized FilterDatabase getInstance(Context context){
        if (instance==null)
            instance = Room.databaseBuilder(context.getApplicationContext(),FilterDatabase.class,"filters_database").allowMainThreadQueries().build();

        return instance;
    }

    public abstract FilterDao getFilterDao();
}
