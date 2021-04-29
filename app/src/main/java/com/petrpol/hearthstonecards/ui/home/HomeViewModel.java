package com.petrpol.hearthstonecards.ui.home;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.data.repositories.CardsRepository;
import com.petrpol.hearthstonecards.data.repositories.CardsRepositoryInterface;

import java.util.List;

public class HomeViewModel extends ViewModel implements CardsRepositoryInterface {

    private MutableLiveData<List<Card>> mCards = new MutableLiveData<>();
    private MutableLiveData<Boolean> filterViewShowed = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFilterLoading = new MutableLiveData<>();
    private MutableLiveData<Filter> filterInfo = new MutableLiveData<>();
    private MutableLiveData<FilterType> filterType = new MutableLiveData<>();

    private CardsRepository mCardsRepository;

    public HomeViewModel() {

        filterType.setValue(FilterType.NONE);
        filterViewShowed.setValue(false);
        isDataLoading.setValue(true);
        isFilterLoading.setValue(true);

        mCardsRepository = CardsRepository.getInstance();
        mCardsRepository.getCards(mCards, this);
        mCardsRepository.getFilter(filterInfo,this);
    }


    /** Updates filterViewShowed to true */
    public void showFilter(View view){
        filterViewShowed.postValue(true);
    }

    /** Updates filterViewShowed to false */
    public void hideFilter(View view){
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
        mCardsRepository.getFilteredCards(mCards, filterType.getValue(), filterText,this);
    }


    //Repository interface
    @Override
    public void onCardDataGetSuccess() {
        isDataLoading.postValue(false);

    }

    @Override
    public void onCardDataGetFail(String message) {
        isDataLoading.postValue(false);

    }

    @Override
    public void onFilterDataGetSuccess() {
        isFilterLoading.postValue(false);

    }

    @Override
    public void onFilterDataGetFail(String message) {
        isFilterLoading.postValue(false);

    }


    //Getters
    public LiveData<List<Card>> getCards() {
        return mCards;
    }

    public LiveData<Boolean> getFilterViewShowed() {
        return filterViewShowed;
    }

    public LiveData<Filter> getFilterData(){return filterInfo;}

    public LiveData<FilterType> getFilterType(){return filterType;}

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public LiveData<Boolean> getIsFilterLoading() {
        return isFilterLoading;
    }
}