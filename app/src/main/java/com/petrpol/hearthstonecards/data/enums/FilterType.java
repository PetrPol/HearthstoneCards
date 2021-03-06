package com.petrpol.hearthstonecards.data.enums;

/** Enum to define filter type */
public enum FilterType {
    TYPE,
    SET,
    CLASS,
    NONE;

    /** Returns string representation of Filter type */
    public String toString(){
        switch (this){
            case TYPE:
                return "type";
            case SET:
                return "set";
            case CLASS:
                return "class";
            default:
                return "none";
        }
    }
}
