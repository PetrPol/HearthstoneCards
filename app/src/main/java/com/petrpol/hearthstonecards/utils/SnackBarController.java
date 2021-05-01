package com.petrpol.hearthstonecards.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

/** Controller to easily create predefined types of snack bars */
public class SnackBarController {

    /** Creates default snackbar
     * @param view - view where snackbar will be created
     * @param message - text of snack bar */
    public static void showDefaultSnackBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
