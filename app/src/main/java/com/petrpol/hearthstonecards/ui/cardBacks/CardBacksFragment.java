package com.petrpol.hearthstonecards.ui.cardBacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardBacksBinding;
import com.petrpol.hearthstonecards.databinding.FragmentHomeBinding;
import com.petrpol.hearthstonecards.ui.adapters.cardBack.CardBacksAdapter;
import com.petrpol.hearthstonecards.ui.adapters.filter.FilterItemAdapter;
import com.petrpol.hearthstonecards.ui.home.HomeViewModel;

public class CardBacksFragment extends Fragment {

    private CardBacksViewModel cardBacksViewModel;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Data binding create view
        FragmentCardBacksBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_backs, container, false);
        root = mBinding.getRoot();

        //Create new model only if is null
        if (cardBacksViewModel==null)
            cardBacksViewModel = new CardBacksViewModel(getContext());

        mBinding.setCardBackModelView(cardBacksViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView(){
        RecyclerView cardBackRecyclerView = root.findViewById(R.id.card_back_recycler_view);
        CardBacksAdapter cardBacksAdapter = new CardBacksAdapter(getContext(), cardBacksViewModel.getCardBacks());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        cardBackRecyclerView.setLayoutManager(layoutManager);
        cardBackRecyclerView.setAdapter(cardBacksAdapter);
    }

}