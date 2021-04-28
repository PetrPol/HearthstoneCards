package com.petrpol.hearthstonecards.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;

public class FilterItemViewHolder extends RecyclerView.ViewHolder{

    TextView itemName;

    public FilterItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.list_item_filter_name);
    }
}
