package com.petrpol.hearthstonecards.ui.adapters.filter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.ui.adapters.ARecyclerViewAdapter;

/** Adapter for Filter options of given type */
public class FilterItemAdapter extends ARecyclerViewAdapter {

    Context mContext;
    LiveData<Filter> mFilterData;
    LiveData<FilterType> mFilterTypeData;
    FilterItemCallback filterItemCallback;

    /** Default constructor */
    public FilterItemAdapter(Context mContext, LiveData<Filter> mFilterData, LiveData<FilterType> mFilterTypeData, FilterItemCallback filterItemCallback) {
        this.mContext = mContext;
        this.mFilterData = mFilterData;
        this.mFilterTypeData = mFilterTypeData;
        this.filterItemCallback = filterItemCallback;

        mFilterTypeData.observe((LifecycleOwner) mContext, (Observer<FilterType>) filterType -> notifyDataSetChanged());
    }

    /** Returns object based on position and filter type */
    @Override
    protected Object getItemForPosition(int position) {

        if (mFilterData.getValue()==null || mFilterTypeData.getValue() == null)
            return null;

        switch (mFilterTypeData.getValue()){
            case SET:
                return mFilterData.getValue().getSets()[position];
            case TYPE:
                return mFilterData.getValue().getTypes()[position];
            case CLASS:
                return mFilterData.getValue().getClasses()[position];
            default:
                return null;
        }
    }

    /** Returns listener for given position */
    @Override
    protected Object getListenerForPosition(int position) {
        return filterItemCallback;
    }

    /** Returns layout for filter item */
    @Override
    protected int getLayoutForPosition(int position) {
        return R.layout.list_item_filter;
    }

    /** Returns item count based on filter type */
    @Override
    public int getItemCount() {

        if (mFilterData.getValue()==null || mFilterTypeData.getValue() == null)
            return 0;

        switch (mFilterTypeData.getValue()){
            case SET:
                return mFilterData.getValue().getSets().length;
            case TYPE:
                return mFilterData.getValue().getTypes().length;
            case CLASS:
                return mFilterData.getValue().getClasses().length;
            default:
                return 0;
        }
    }
}
