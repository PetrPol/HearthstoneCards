package com.petrpol.hearthstonecards.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Abstract binding adapter for recycler view
 */
public abstract class ARecyclerViewAdapter extends RecyclerView.Adapter<BindViewHolder>{

    /** Returns object to bind to view of given position */
    protected abstract Object getItemForPosition(int position);

    /** Returns object to bind to view of given position */
    protected abstract Object getListenerForPosition(int position);

    /** Returns layout (viewType) for given position */
    protected abstract int getLayoutForPosition(int position);

    /** Creates layout based on view type */
    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent,false);
        return new BindViewHolder(binding);
    }

    /** Binds object and listener to item view */
    @Override
    public void onBindViewHolder(@NonNull BindViewHolder holder, int position) {
        Object item = getItemForPosition(position);
        holder.bindObject(item);

        Object listener = getListenerForPosition(position);
        if (listener!= null)
            holder.bindOListener(listener);
    }

    /** Gets view type for given position for create view holder */
    @Override
    public int getItemViewType(int position) {
        return getLayoutForPosition(position);
    }
}
