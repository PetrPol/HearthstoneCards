package com.petrpol.hearthstonecards.ui.cardBacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.petrpol.hearthstonecards.R;

public class CardBacksFragment extends Fragment {

    private CardBacksViewModel cardBacksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Create View model and layout
        cardBacksViewModel = ViewModelProviders.of(this).get(CardBacksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_card_backs, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard);

        cardBacksViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}