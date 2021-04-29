package com.petrpol.hearthstonecards.ui.cardDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepository;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepositoryInterface;

/** ViewModel for card detail fragment */
public class CardDetailViewModel extends ViewModel implements SingleCardRepositoryInterface {

    private MutableLiveData<Card> mCard= new MutableLiveData<>();
    private MutableLiveData<String> mErrorMessage= new MutableLiveData<>();

    private SingleCardRepository mSingleCardRepository;

    public CardDetailViewModel() {
        mSingleCardRepository = SingleCardRepository.getInstance();
    }

    public void loadCard(String cardId) {
        mSingleCardRepository.getCard(mCard, cardId, this);
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
