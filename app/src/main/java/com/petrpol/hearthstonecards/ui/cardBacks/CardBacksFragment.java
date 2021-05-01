package com.petrpol.hearthstonecards.ui.cardBacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardBacksBinding;
import com.petrpol.hearthstonecards.ui.adapters.cardBack.CardBacksAdapter;
import com.petrpol.hearthstonecards.ui.base.ABaseFragment;

/** Fragment to show list of card backs */
public class CardBacksFragment extends ABaseFragment {

    private CardBacksViewModel cardBacksViewModel;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Data binding create view
        FragmentCardBacksBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_backs, container, false);
        root = mBinding.getRoot();

        //Create new model only if is null
        if (cardBacksViewModel==null)
            cardBacksViewModel = new CardBacksViewModel(getContext());

        //Set binding
        mBinding.setCardBackModelView(cardBacksViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerView();

        return root;
    }

    /** Setups recycler view and adapter */
    private void setupRecyclerView(){
        RecyclerView cardBackRecyclerView = root.findViewById(R.id.card_back_recycler_view);
        CardBacksAdapter cardBacksAdapter = new CardBacksAdapter(getContext(), cardBacksViewModel.getCardBacks());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        cardBackRecyclerView.setLayoutManager(layoutManager);
        cardBackRecyclerView.setAdapter(cardBacksAdapter);
    }

}