package com.petrpol.hearthstonecards.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarController {

    public static void showDefaultSnackBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
