package com.petrpol.hearthstonecards.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Model class object represents Filter info */
@Entity(tableName = "filter_table")
public class Filter {

    @PrimaryKey
    @NonNull
    private String patch = "";
    private String[] classes;
    private String[] sets;
    private String[] types;

    public @NonNull String getPatch() {
        return patch;
    }

    @SuppressWarnings({"unused"}) //Set by Retrofit - used by Room
    public void setPatch(@NonNull String patch) {
        this.patch = patch;
    }

    public String[] getClasses() {
        return classes;
    }

    @SuppressWarnings({"unused"}) //Set by Retrofit - used by Application
    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public String[] getSets() {
        return sets;
    }

    @SuppressWarnings({"unused"}) //Set by Retrofit - used by Application
    public void setSets(String[] sets) {
        this.sets = sets;
    }

    public String[] getTypes() {
        return types;
    }

    @SuppressWarnings({"unused"}) //Set by Retrofit - used by Application
    public void setTypes(String[] types) {
        this.types = types;
    }
}
