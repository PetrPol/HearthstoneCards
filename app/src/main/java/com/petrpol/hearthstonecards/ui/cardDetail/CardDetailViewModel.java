package com.petrpol.hearthstonecards.ui.cardDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepository;

/** ViewModel for card detail fragment */
public class CardDetailViewModel extends ViewModel {

    private MutableLiveData<Card> mCard;

    private SingleCardRepository mSingleCardRepository;

    public CardDetailViewModel() {
        mSingleCardRepository = SingleCardRepository.getInstance();
        mCard = new MutableLiveData<>();
    }

    //Getters
    public void loadCard(String cardId) {
        mSingleCardRepository.getCard(mCard, cardId);
    }

    public LiveData<Card> getCard(){
        return mCard;
    }
}
