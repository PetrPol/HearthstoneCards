package com.petrpol.hearthstonecards.ui.cardBacks;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.repositories.CardBackRepository;
import com.petrpol.hearthstonecards.data.repositories.CardBacksRepositoryInterface;
import com.petrpol.hearthstonecards.data.repositories.CardsRepositoryInterface;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepository;
import com.petrpol.hearthstonecards.room.CardBacksDatabase;
import com.petrpol.hearthstonecards.room.CardsDatabase;

import java.util.List;

public class CardBacksViewModel extends ViewModel implements CardBacksRepositoryInterface {


    private LiveData<List<CardBack>> mCardBacks;
    private MutableLiveData<Boolean> isDataLoading= new MutableLiveData<>();
    private MutableLiveData<String> mErrorMessage= new MutableLiveData<>();

    private CardBackRepository mCardBacksRepository;

    public CardBacksViewModel(Context context) {
        isDataLoading.setValue(true);

        mCardBacksRepository = CardBackRepository.getInstance(CardBacksDatabase.getInstance(context));
        mCardBacks = mCardBacksRepository.getCardBacks(this);
    }

    //Repository interface methods
    @Override
    public void onCardBackDataGetSuccess() {
        isDataLoading.postValue(false);}

    @Override
    public void onCardBackDataGetFail(String message) {
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
}