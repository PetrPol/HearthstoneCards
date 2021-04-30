package com.petrpol.hearthstonecards.ui.cardDetail;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.databinding.FragmentCardDetailBinding;

public class CardDetailFragment extends Fragment {

    private CardDetailViewModel cardDetailViewModel;
    private View root;
    private Menu menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Data binding create view
        FragmentCardDetailBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_detail, container, false);
        root = mBinding.getRoot();
        setHasOptionsMenu(true);

        //Setup animation
        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));

        //Get cardID from bundle
        String CardID = null;
        if (getArguments() != null)
            CardID = CardDetailFragmentArgs.fromBundle(getArguments()).getCardId();

        //Create new model only if is null
        if (cardDetailViewModel == null)
            cardDetailViewModel = new CardDetailViewModel(getContext());

        mBinding.setCardDetailViewModel(cardDetailViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        cardDetailViewModel.loadCard(CardID);



        return root;
    }

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
            Navigation.findNavController(root).popBackStack();
        return false;
    }
}
