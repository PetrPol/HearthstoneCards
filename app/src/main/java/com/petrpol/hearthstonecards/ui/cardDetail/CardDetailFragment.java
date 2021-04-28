package com.petrpol.hearthstonecards.ui.cardDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.databinding.FragmentCardDetailBinding;

public class CardDetailFragment extends Fragment {

    private CardDetailViewModel cardDetailViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Data binding create view
        FragmentCardDetailBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_detail, container, false);
        View root = mBinding.getRoot();

        //Get cardID from bundle
        String CardID = CardDetailFragmentArgs.fromBundle(getArguments()).getCardId();

        cardDetailViewModel = new CardDetailViewModel();
        mBinding.setCardDetailViewModel(cardDetailViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        cardDetailViewModel.loadCard(CardID);

        return root;
    }
}
