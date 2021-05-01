package com.petrpol.hearthstonecards.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import com.petrpol.hearthstonecards.utils.BackButtonInterface;

/** Abstract base activity - All activities extends this activity */
public abstract class ABaseActivity extends AppCompatActivity {

    public BackButtonInterface backButtonInterface;

    /** Handle on back pressed by fragment callbacks */
    @Override
    public void onBackPressed() {
        if (backButtonInterface == null || !backButtonInterface.onBackPressed())
            super.onBackPressed();
    }
}
