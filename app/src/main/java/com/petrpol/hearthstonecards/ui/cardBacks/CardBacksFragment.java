package com.petrpol.hearthstonecards.ui.cardBacks;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardBacksBinding;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.ui.adapters.cardBack.CardBacksAdapter;
import com.petrpol.hearthstonecards.ui.base.ABaseFragment;

/** Fragment to show list of card backs */
public class CardBacksFragment extends ABaseFragment {

    private CardBacksViewModel cardBacksViewModel;
    private FragmentCardBacksBinding mBinding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Data binding create view
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_backs, container, false);

        //Get view model from provider
        cardBacksViewModel = new ViewModelProvider(this).get(CardBacksViewModel.class);

        //Set binding
        mBinding.setCardBackModelView(cardBacksViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerView();

        return mBinding.getRoot();
    }

    /** Setups recycler view and adapter */
    private void setupRecyclerView() {
        CardBacksAdapter cardBacksAdapter = new CardBacksAdapter(getContext(), cardBacksViewModel.getCardBacks());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mBinding.cardBackRecyclerView.setLayoutManager(layoutManager);
        mBinding.cardBackRecyclerView.setAdapter(cardBacksAdapter);
    }

}