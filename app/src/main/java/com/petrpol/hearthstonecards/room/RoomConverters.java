package com.petrpol.hearthstonecards.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

/**
 * Room converters data to store to database
 */
public class RoomConverters {

    /** Converts String array to Json object in string */
    @TypeConverter
    public static String fromStringArray(String[] list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /** Converts Json object in string back to String array */
    @TypeConverter
    public static String[] fromString(String value) {
        return new Gson().fromJson(value, String[].class);
    }
}
