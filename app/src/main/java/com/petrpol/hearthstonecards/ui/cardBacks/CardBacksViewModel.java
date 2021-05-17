package com.petrpol.hearthstonecards.ui.cardBacks;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.repositories.CardBackRepository;
import com.petrpol.hearthstonecards.data.repositories.CardBacksRepositoryInterface;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.ui.base.ABaseViewModel;

import java.util.List;

/**
 * View model for Card backs fragment
 */
public class CardBacksViewModel extends ABaseViewModel implements CardBacksRepositoryInterface {

    private LiveData<List<CardBack>> mCardBacks;

    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    private CardBackRepository mCardBacksRepository;

    /** Default constructor
     *  Creates repository and gets list data
     *  @param application - Application for repository */
    public CardBacksViewModel(@NonNull Application application) {
        super(application);

        //Default values
        isDataLoading.setValue(true);

        //Create repository and get data
        mCardBacksRepository = new CardBackRepository(application);
        mCardBacks = mCardBacksRepository.getCardBacks(this);
    }

    public void refreshData(){
        isConnectionProblems.postValue(false);
        mCardBacksRepository.getCardBacks(this);
    }

    //Repository interface methods
    @Override
    public void onCardBackDataGetSuccess() {
        isDataLoading.postValue(false);
    }

    @Override
    public void onCardBackDataGetFail(String message) {
        isDataLoading.postValue(false);
        if (mCardBacks.getValue()==null || mCardBacks.getValue().size()==0)
            isConnectionProblems.postValue(true);
        mErrorMessage.postValue(message);
    }

    //Getters
    public LiveData<List<CardBack>> getCardBacks() {
        return mCardBacks;
    }

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }
}