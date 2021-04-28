package com.petrpol.hearthstonecards.data.model;

import java.util.List;

public class Filter {

    private String patch;
    private String[] classes;
    private String[] sets;
    private String[] types;

    public Filter(String patch, String[] classes, String[] sets, String[] types) {
        this.patch = patch;
        this.classes = classes;
        this.sets = sets;
        this.types = types;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public String[] getSets() {
        return sets;
    }

    public void setSets(String[] sets) {
        this.sets = sets;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
