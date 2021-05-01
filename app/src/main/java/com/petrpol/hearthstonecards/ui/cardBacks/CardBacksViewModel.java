package com.petrpol.hearthstonecards.ui.cardBacks;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.repositories.CardBackRepository;
import com.petrpol.hearthstonecards.data.repositories.CardBacksRepositoryInterface;
import com.petrpol.hearthstonecards.room.CardBacksDatabase;

import java.util.List;

/** View model for Card backs fragment */
public class CardBacksViewModel extends ViewModel implements CardBacksRepositoryInterface {

    private LiveData<List<CardBack>> mCardBacks;

    private MutableLiveData<Boolean> isDataLoading= new MutableLiveData<>();
    private MutableLiveData<String> mErrorMessage= new MutableLiveData<>();
    private MutableLiveData<Boolean> isConnectionProblems= new MutableLiveData<>();

    private CardBackRepository mCardBacksRepository;

    /** Default constructor
     *  Creates database for repository and gets card back list data */
    public CardBacksViewModel(Context context) {
        //Default values
        isDataLoading.setValue(true);
        isConnectionProblems.setValue(false);

        //Create repository and get data
        mCardBacksRepository = CardBackRepository.getInstance(CardBacksDatabase.getInstance(context));
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

    public LiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public LiveData<Boolean> getIsConnectionProblems() {
        return isConnectionProblems;
    }
}