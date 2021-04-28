package com.petrpol.hearthstonecards.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.ui.home.HomeViewModel;

public class FilterItemAdapter extends RecyclerView.Adapter<FilterItemViewHolder>{

    Context mContext;
    LiveData<Filter> mFilterData;
    LiveData<FilterType> mFilterTypeData;
    HomeViewModel homeViewModel;

    public FilterItemAdapter(Context mContext, LiveData<Filter> mFilterData, LiveData<FilterType> mFilterTypeData, HomeViewModel homeViewModel) {
        this.mContext = mContext;
        this.mFilterData = mFilterData;
        this.mFilterTypeData = mFilterTypeData;
        this.homeViewModel = homeViewModel;
    }


    @NonNull
    @Override
    public FilterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_filter,parent,false);
        return new FilterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterItemViewHolder holder, int position) {

        String itemText = "";

        if (mFilterTypeData.getValue()==FilterType.TYPE)
            itemText = mFilterData.getValue().getTypes()[position];

        else  if (mFilterTypeData.getValue()==FilterType.SET)
            itemText = mFilterData.getValue().getSets()[position];

        else  if (mFilterTypeData.getValue()==FilterType.CLASS)
            itemText = mFilterData.getValue().getClasses()[position];

        holder.itemName.setText(itemText);
        holder.itemView.setOnClickListener(v -> {
            homeViewModel.getFilteredCards(holder.itemName.getText().toString());
            homeViewModel.hideFilter();
        });
    }

    @Override
    public int getItemCount() {
        if (mFilterTypeData.getValue()==FilterType.TYPE && mFilterData.getValue() != null)
            return mFilterData.getValue().getTypes().length;

        else if (mFilterTypeData.getValue()==FilterType.SET && mFilterData.getValue() != null)
            return mFilterData.getValue().getSets().length;

        else if (mFilterTypeData.getValue()==FilterType.CLASS && mFilterData.getValue() != null)
            return mFilterData.getValue().getClasses().length;

        else
            return 0;
    }
}
