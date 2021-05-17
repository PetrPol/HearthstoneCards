package com.petrpol.hearthstonecards.ui.base;

import androidx.fragment.app.Fragment;

import com.petrpol.hearthstonecards.utils.BackButtonInterface;

/**
 * Abstract base fragment - All fragments extends this activity
 */
public class ABaseFragment extends Fragment implements BackButtonInterface {

    /** Register on back pressed interface to activity */
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity()!=null)
            ((ABaseActivity)getActivity()).backButtonInterface = this;
    }


    /** Unregister on back pressed interface to activity */
    @Override
    public void onPause() {
        super.onPause();
        if (getActivity()!=null)
            ((ABaseActivity)getActivity()).backButtonInterface = null;
    }

    /** Handles back button press in fragment */
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
