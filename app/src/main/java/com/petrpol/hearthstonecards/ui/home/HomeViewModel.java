package com.petrpol.hearthstonecards.ui.home;

import android.content.Context;

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
import com.petrpol.hearthstonecards.room.FilterDatabase;

import java.util.List;

/** View model for Home fragment */
public class HomeViewModel extends ViewModel implements CardsRepositoryInterface, FilterRepositoryInterface {

    private LiveData<List<Card>> mCards;
    private LiveData<List<Filter>> mFilterInfo;

    private MutableLiveData<Boolean> filterViewShowed = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFilterLoading = new MutableLiveData<>();
    private MutableLiveData<FilterType> filterType = new MutableLiveData<>();
    private MutableLiveData<FilterType> dataViewType = new MutableLiveData<>();
    private MutableLiveData<String> mErrorMessage = new MutableLiveData<>();
    private MutableLiveData<String> filterValue = new MutableLiveData<>();
    private MutableLiveData<Boolean> noDataFound = new MutableLiveData<>();

    private CardsRepository mCardsRepository;

    /** Default constructor
     *  Sets default values
     *  Creates data repositories and gets default data */
    public HomeViewModel(Context context) {

        //Default values
        filterType.setValue(FilterType.NONE);
        dataViewType.setValue(FilterType.NONE);
        filterViewShowed.setValue(false);
        isDataLoading.setValue(true);
        isFilterLoading.setValue(true);
        mErrorMessage.setValue(null);
        filterValue.setValue(null);
        noDataFound.setValue(false);

        //Create repositories
        mCardsRepository = CardsRepository.getInstance(CardsDatabase.getInstance(context));
        FilterRepository mFilterRepository = FilterRepository.getInstance(FilterDatabase.getInstance(context));

        //Get cards and filter
        mCards = mCardsRepository.getCards( this);
        mFilterInfo = mFilterRepository.getFilter(this);
    }

    /** Cleans data before inflate to new view */
    public void clean(){
        mErrorMessage.setValue(null);
    }

    /** Updates filterViewShowed to true */
    public void showFilter(){
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

        if (filterType.getValue()!= null)
            mCards = mCardsRepository.getFilteredCards(filterType.getValue(), filterText,this);

        filterValue.postValue(filterText);
        dataViewType.postValue(filterType.getValue());
    }

    //Repositories interface
    @Override
    public void onCardDataGetSuccess() {
        isDataLoading.postValue(false);
    }

    @Override
    public void onCardDataGetFail(String message) {
        isDataLoading.postValue(false);
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

    public LiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public LiveData<FilterType> getDataViewType() {
        return dataViewType;
    }

    public LiveData<Boolean> getNoDataFound() {
        return noDataFound;
    }
}