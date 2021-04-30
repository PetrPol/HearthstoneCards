package com.petrpol.hearthstonecards.ui.home;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.databinding.FragmentHomeBinding;
import com.petrpol.hearthstonecards.ui.adapters.cards.CardItemCallback;
import com.petrpol.hearthstonecards.ui.adapters.cards.CardsAdapter;
import com.petrpol.hearthstonecards.ui.adapters.filter.FilterItemAdapter;
import com.petrpol.hearthstonecards.ui.adapters.filter.FilterItemCallback;
import com.petrpol.hearthstonecards.utils.SnackBarController;

import java.util.List;

/** Default Home fragment
 *  Contains list of cards an allows to filter cards by type,class or set*/
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private MotionLayout motionLayout;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Data binding create view
        FragmentHomeBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        root = mBinding.getRoot();

        //Create new model only if is null
        if (homeViewModel==null)
            homeViewModel = new HomeViewModel(getContext());

        homeViewModel.clean();
        mBinding.setHomeModelView(homeViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerViews();

        return root;
    }


    /** Setups recycler views */
    private void setupRecyclerViews(){

        //Card Recycler
        RecyclerView cardsRecyclerView = root.findViewById(R.id.home_recycler_view);

        //Observe dara view changed - create new adapter
        homeViewModel.getDataViewType().observe(getViewLifecycleOwner(), filterType -> {
            CardsAdapter cardsAdapter = new CardsAdapter(getContext(), homeViewModel.getCards(), this::showDetail);
            cardsRecyclerView.setAdapter(cardsAdapter);
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        cardsRecyclerView.setLayoutManager(layoutManager);

        //Filter Recycler
        FilterItemCallback filterItemCallback = filter -> {
            homeViewModel.getFilteredCards(filter);
            homeViewModel.hideFilter(null);
        };

        RecyclerView filterRecyclerView = root.findViewById(R.id.home_filter_recycler_view);
        FilterItemAdapter filterAdapter = new FilterItemAdapter(getContext(), homeViewModel.getFilterData(), homeViewModel.getFilterType(), filterItemCallback);
        layoutManager = new LinearLayoutManager(getContext());
        filterRecyclerView.setLayoutManager(layoutManager);
        filterRecyclerView.setAdapter(filterAdapter);
    }

    /** Navigates to CardDetailFragment - passes CardId as argument */
    public void showDetail(String cardId, View imageView){
        HomeFragmentDirections.ActionNavigationHomeToCardDetailFragment action = HomeFragmentDirections.actionNavigationHomeToCardDetailFragment(cardId);
        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(imageView,"card_detail_image").build();
        Navigation.findNavController(imageView).navigate(action,extras);
    }
}