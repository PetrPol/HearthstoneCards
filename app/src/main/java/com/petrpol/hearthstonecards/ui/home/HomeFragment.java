package com.petrpol.hearthstonecards.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.ui.adapters.CardsAdapter;
import com.petrpol.hearthstonecards.ui.adapters.FilterItemAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView mFilterRecyclerView;
    private FloatingActionButton mFilterFab;
    private ConstraintLayout mFilterView;
    private TextView mFilterPatchText;
    private LinearLayout mButtonLayout;
    private ImageView mFilterBackButton;

    private CardsAdapter mAdapter;
    private FilterItemAdapter mFilterAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Create View model and layout
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Get and setup Views
        mRecyclerView = root.findViewById(R.id.home_recycler_view);
        mFilterFab = root.findViewById(R.id.home_filter_fab);
        mFilterView = root.findViewById(R.id.home_filter_constraint);
        mFilterPatchText = root.findViewById(R.id.home_filter_patch_text);
        mButtonLayout = root.findViewById(R.id.home_filter_buttons_layout);
        mFilterRecyclerView = root.findViewById(R.id.home_filter_recycler_view);
        mFilterBackButton = root.findViewById(R.id.home_filter_back);
        setupRecyclerViews();

        //Observers
        homeViewModel.getCards().observe(getViewLifecycleOwner(), cards -> mAdapter.notifyDataSetChanged());
        homeViewModel.getFilterViewShowed().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean){
                mFilterFab.setVisibility(View.GONE);
                mFilterView.setVisibility(View.VISIBLE);
            }
            else {
                mFilterFab.setVisibility(View.VISIBLE);
                mFilterView.setVisibility(View.GONE);
            }
        });
        homeViewModel.getFilterData().observe(getViewLifecycleOwner(), filter -> mFilterPatchText.setText(filter.getPatch()));
        homeViewModel.getFilterType().observe(getViewLifecycleOwner(), filterType -> {
            if (filterType==FilterType.NONE){
                mButtonLayout.setVisibility(View.VISIBLE);
                mFilterRecyclerView.setVisibility(View.GONE);
                mFilterBackButton.setVisibility(View.GONE);

                ViewGroup.LayoutParams layoutParams = mFilterView.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mFilterView.setLayoutParams(layoutParams);
            }
            else{
                mButtonLayout.setVisibility(View.GONE);
                mFilterRecyclerView.setVisibility(View.VISIBLE);
                mFilterBackButton.setVisibility(View.VISIBLE);

                ViewGroup.LayoutParams layoutParams = mFilterView.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                mFilterView.setLayoutParams(layoutParams);
            }

            mFilterAdapter.notifyDataSetChanged();
        });

        //On Clicks
        mFilterFab.setOnClickListener(v -> homeViewModel.showFilter());
        root.findViewById(R.id.home_filter_close).setOnClickListener(v -> homeViewModel.hideFilter());
        mFilterBackButton.setOnClickListener(v -> homeViewModel.setFilterType(FilterType.NONE));
        root.findViewById(R.id.home_filter_button_type).setOnClickListener(v -> homeViewModel.setFilterType(FilterType.TYPE));
        root.findViewById(R.id.home_filter_button_set).setOnClickListener(v -> homeViewModel.setFilterType(FilterType.SET));
        root.findViewById(R.id.home_filter_button_class).setOnClickListener(v -> homeViewModel.setFilterType(FilterType.CLASS));

        return root;
    }

    private void setupRecyclerViews(){
        //Card Recycler
        mAdapter = new CardsAdapter(getContext(),homeViewModel.getCards());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Filter Recycler
        mFilterAdapter = new FilterItemAdapter(getContext(),homeViewModel.getFilterData(),homeViewModel.getFilterType(), homeViewModel);
        layoutManager = new LinearLayoutManager(getContext());
        mFilterRecyclerView.setLayoutManager(layoutManager);
        mFilterRecyclerView.setAdapter(mFilterAdapter);
    }


}