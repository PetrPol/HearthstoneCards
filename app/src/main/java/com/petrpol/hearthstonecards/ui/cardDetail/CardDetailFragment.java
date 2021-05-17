package com.petrpol.hearthstonecards.ui.cardDetail;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardDetailBinding;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.ui.base.ABaseFragment;


/** Fragment to show detail info of single card */
public class CardDetailFragment extends ABaseFragment {

    private CardDetailViewModel cardDetailViewModel;
    private FragmentCardDetailBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Data binding create view
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_detail, container, false);
        setHasOptionsMenu(true);

        //Setup animation
        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));

        //Get cardID from bundle
        String CardID = null;
        if (getArguments() != null)
            CardID = CardDetailFragmentArgs.fromBundle(getArguments()).getCardId();

        //Get view model from provider
        cardDetailViewModel = new ViewModelProvider(this).get(CardDetailViewModel.class);

        //Bind view
        mBinding.setCardDetailViewModel(cardDetailViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        //Load data based on Card id
        cardDetailViewModel.loadCard(CardID);

        return mBinding.getRoot();
    }

    /** Inflate own menu with additional button to show golden version of card
     *  Shows button only if card has golden version of card */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.card_detail_menu, menu);

        //Observe to show golden menu button
        cardDetailViewModel.getCard().observe(getViewLifecycleOwner(), card -> {
            if(card.getImgGold()!=null && !card.getImgGold().isEmpty())
                menu.findItem(R.id.card_detail_show_golden).setVisible(true);
        });
    }

    /** Handles back arrow in Options menu - pops back stack */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.card_detail_show_golden)
            cardDetailViewModel.changeGolden();
        else
            Navigation.findNavController(mBinding.getRoot()).popBackStack();
        return false;
    }
}
