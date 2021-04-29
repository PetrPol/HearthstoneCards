package com.petrpol.hearthstonecards.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class RoomConverters {
    @TypeConverter
    public static String[] fromString(String value) {
        return new Gson().fromJson(value, String[].class);
    }

    @TypeConverter
    public static String fromStringArray(String[] list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
