package com.petrpol.hearthstonecards.ui.cardDetail;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepository;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepositoryInterface;
import com.petrpol.hearthstonecards.room.CardsDatabase;

/** ViewModel for card detail fragment */
public class CardDetailViewModel extends ViewModel implements SingleCardRepositoryInterface {

    private LiveData<Card> mCard;
    private MutableLiveData<String> mErrorMessage= new MutableLiveData<>();

    private SingleCardRepository mSingleCardRepository;

    public CardDetailViewModel(Context context) {
        mSingleCardRepository = SingleCardRepository.getInstance(CardsDatabase.getInstance(context));
    }

    public void loadCard(String cardId) {
        mCard = mSingleCardRepository.getCard(cardId, this);
    }

    //Repository interface methods
    @Override
    public void onCardDataGetSuccess() {}

    @Override
    public void onCardDataGetFail(String message) {
        mErrorMessage.postValue(message);
    }

    //Getters
    public LiveData<Card> getCard(){
        return mCard;
    }

    public LiveData<String> getErrorMessage() {
        return mErrorMessage;
    }
}
