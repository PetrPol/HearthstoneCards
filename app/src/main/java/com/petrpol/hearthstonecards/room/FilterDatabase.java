package com.petrpol.hearthstonecards.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.dao.FilterDao;

/** Database to store filers info
 *  Uses type converters to store data of filter
 *  Singleton */
@Database(entities = Filter.class, version = 1, exportSchema = false)
@TypeConverters(RoomConverters.class)
public abstract class FilterDatabase extends RoomDatabase {

    private static FilterDatabase instance;

    public static synchronized FilterDatabase getInstance(Context context){
        if (instance==null)
            instance = Room.databaseBuilder(context.getApplicationContext(),FilterDatabase.class,context.getString(R.string.database_filters)).build();

        return instance;
    }

    /** Returns interface to access data */
    public abstract FilterDao getFilterDao();
}
