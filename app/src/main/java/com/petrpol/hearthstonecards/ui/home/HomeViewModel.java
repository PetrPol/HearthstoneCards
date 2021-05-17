package com.petrpol.hearthstonecards.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.data.repositories.CardsRepository;
import com.petrpol.hearthstonecards.data.repositories.CardsRepositoryInterface;
import com.petrpol.hearthstonecards.data.repositories.FilterRepository;
import com.petrpol.hearthstonecards.data.repositories.FilterRepositoryInterface;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.ui.base.ABaseViewModel;

import java.util.List;

/**
 * View model for Home fragment
 */
public class HomeViewModel extends ABaseViewModel implements CardsRepositoryInterface, FilterRepositoryInterface {

    private LiveData<List<Card>> mCards;
    private LiveData<List<Filter>> mFilterInfo;

    private MutableLiveData<Boolean> filterViewShowed = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFilterLoading = new MutableLiveData<>();
    private MutableLiveData<FilterType> filterType = new MutableLiveData<>();
    private MutableLiveData<FilterType> dataViewType = new MutableLiveData<>();
    private MutableLiveData<String> filterValue = new MutableLiveData<>();
    private MutableLiveData<Boolean> noDataFound = new MutableLiveData<>();

    private boolean showFilterAfterSuccess = false;

    private CardsRepository mCardsRepository;
    private FilterRepository mFilterRepository;

    /**
     * Default constructor
     * Sets default values
     * Creates repository and gets default data
     *
     * @param application - Application for repository
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);

        //Default values
        filterType.setValue(FilterType.NONE);
        dataViewType.setValue(FilterType.NONE);
        filterViewShowed.setValue(false);
        isDataLoading.setValue(true);
        isFilterLoading.setValue(true);
        filterValue.setValue(null);
        noDataFound.setValue(false);

        //Create repositories
        mCardsRepository = new CardsRepository(application);
        mFilterRepository = new FilterRepository(application);

        //Get cards and filter
        mCards = mCardsRepository.getCards(this);
        mFilterInfo = mFilterRepository.getFilter(this);
    }

    /** Updates filterViewShowed to true */
    public void showFilter(){
        if (mFilterInfo.getValue()==null || mFilterInfo.getValue().size()==0){
            isFilterLoading.setValue(true);
            mFilterRepository.updateFilter(this);
            showFilterAfterSuccess = true;
        }
        else
            filterViewShowed.postValue(true);
    }

    /** Updates filterViewShowed to false */
    public void hideFilter(){
        filterViewShowed.postValue(false);
        filterType.postValue(FilterType.NONE);
    }

    /** Updates FilterType */
    public void setFilterType(FilterType filterType){
        this.filterType.postValue(filterType);
    }

    /** Updates card list with filter data */
    public void getFilteredCards(String filterText){
        isDataLoading.postValue(true);
        noDataFound.postValue(false);
        isConnectionProblems.postValue(false);

        if (filterType.getValue()!= null)
            mCards = mCardsRepository.getFilteredCards(filterType.getValue(), filterText,this);

        filterValue.postValue(filterText);
        dataViewType.postValue(filterType.getValue());
    }

    /** Updates data for actual dataViewType and FilterValue  */
    public void updateCardData(){
        isConnectionProblems.postValue(false);

        if (dataViewType.getValue()== null || filterValue.getValue()== null || dataViewType.getValue()==FilterType.NONE)
            mCardsRepository.getCards( this);
        else
            mCardsRepository.getFilteredCards(dataViewType.getValue(),filterValue.getValue(),this);

    }

    //Repositories interface
    @Override
    public void onCardDataGetSuccess() {
        isDataLoading.postValue(false);
    }

    @Override
    public void onCardDataGetFail(String message) {
        isDataLoading.postValue(false);

        if (mCards.getValue()==null || mCards.getValue().size()==0)
            isConnectionProblems.postValue(true);

        mErrorMessage.postValue(message);
    }

    @Override
    public void onCardDataGetFailNoCards() {
        noDataFound.postValue(true);
        isDataLoading.postValue(false);
    }

    @Override
    public void onFilterDataGetSuccess() {
        isFilterLoading.postValue(false);

        //Show filter if was opened before call
        if (showFilterAfterSuccess){
            filterViewShowed.postValue(true);
            showFilterAfterSuccess = false;
        }
    }

    @Override
    public void onFilterDataGetFail(String message) {
        isFilterLoading.postValue(false);
        mErrorMessage.postValue(message);
    }


    //Getters
    public LiveData<List<Card>> getCards() {
        return mCards;
    }

    public LiveData<Boolean> getFilterViewShowed() {
        return filterViewShowed;
    }

    public LiveData<List<Filter>> getFilterData(){return mFilterInfo;}

    public LiveData<FilterType> getFilterType(){return filterType;}

    public LiveData<String> getFilterValue(){return filterValue;}

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public LiveData<Boolean> getIsFilterLoading() {
        return isFilterLoading;
    }

    public LiveData<FilterType> getDataViewType() {
        return dataViewType;
    }

    public LiveData<Boolean> getNoDataFound() {
        return noDataFound;
    }


}