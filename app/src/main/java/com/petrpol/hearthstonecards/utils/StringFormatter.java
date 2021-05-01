package com.petrpol.hearthstonecards.utils;

/** Class to format strings */
public class StringFormatter {

    /** Converts first letter of string to uppercase */
    public static String firstLetterToUppercase(String string){
        return string.substring(0,1).toUpperCase() + string.substring(2);
    }
}
