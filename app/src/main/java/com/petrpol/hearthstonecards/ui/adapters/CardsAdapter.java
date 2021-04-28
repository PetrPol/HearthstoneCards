package com.petrpol.hearthstonecards.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class CardsAdapter extends RecyclerView.Adapter<CardViewHolder> {

    HomeFragment mHomeFragment;
    LiveData<List<Card>> mCardsDataSet;

    public CardsAdapter(HomeFragment mHomeFragment, LiveData<List<Card>> mCardsDataSet) {
        this.mHomeFragment = mHomeFragment;
        this.mCardsDataSet = mCardsDataSet;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mHomeFragment.getContext());
        View view = inflater.inflate(R.layout.list_item_card,parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        Card card = Objects.requireNonNull(mCardsDataSet.getValue()).get(position);

        //Load image
        Picasso.get().load(card.getImg()).into(holder.image);

        holder.name.setText(card.getName());
        holder.text.setText(card.getText());

        holder.itemView.setOnClickListener(v -> mHomeFragment.showDetail(v,card.getCardId()));
    }

    @Override
    public int getItemCount() {
        if (mCardsDataSet.getValue()!=null)
            return mCardsDataSet.getValue().size();
        else
            return 0;
    }
}
