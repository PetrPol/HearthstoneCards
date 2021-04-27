package com.petrpol.hearthstonecards.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;

public class CardViewHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView text;
    ImageView image;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.list_item_card_name);
        text = itemView.findViewById(R.id.list_item_card_text);
        image = itemView.findViewById(R.id.list_item_card_image);
    }
}
