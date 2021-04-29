package com.petrpol.hearthstonecards.ui.cardDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardDetailBinding;

public class CardDetailFragment extends Fragment {

    private CardDetailViewModel cardDetailViewModel;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Data binding create view
        FragmentCardDetailBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_detail, container, false);
        root = mBinding.getRoot();
        setHasOptionsMenu(true);

        //Get cardID from bundle
        String CardID = null;
        if (getArguments() != null)
            CardID = CardDetailFragmentArgs.fromBundle(getArguments()).getCardId();

        //Create new model only if is null
        if (cardDetailViewModel == null)
            cardDetailViewModel = new CardDetailViewModel();

        mBinding.setCardDetailViewModel(cardDetailViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        cardDetailViewModel.loadCard(CardID);

        return root;
    }

    /** Handles back arrow in Options menu - pops back stack */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Navigation.findNavController(root).popBackStack();
        return false;
    }
}
