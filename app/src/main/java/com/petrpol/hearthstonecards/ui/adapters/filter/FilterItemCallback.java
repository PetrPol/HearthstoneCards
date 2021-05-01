package com.petrpol.hearthstonecards.ui.adapters.filter;

/** Interface for filter list item  */
public interface FilterItemCallback {

    /** Called when item clicked
     * @param filter - string name of selected filter value */
    void onClick(String filter);
}
