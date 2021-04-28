package com.petrpol.hearthstonecards.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.data.repositories.CardsRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Card>> mCards;
    private MutableLiveData<Boolean> filterViewShowed = new MutableLiveData<>();
    private MutableLiveData<Filter> filterInfo;
    private MutableLiveData<FilterType> filterType = new MutableLiveData<>();

    private CardsRepository mCardsRepository;

    public HomeViewModel() {
        mCardsRepository = CardsRepository.getInstance();
        mCards = mCardsRepository.getCards();
        filterInfo = mCardsRepository.getFilter();
        filterType.postValue(FilterType.NONE);
    }

    public void showFilter(){
        filterViewShowed.postValue(true);
    }

    public void hideFilter(){
        filterViewShowed.postValue(false);
        filterType.postValue(FilterType.NONE);
    }

    public void setFilterType(FilterType filterType){
        this.filterType.postValue(filterType);
    }

    public void getFilteredCards(String filterText){
        mCards = mCardsRepository.getFilteredCards(mCards, filterType.getValue(), filterText);
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
}