package com.petrpol.hearthstonecards.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.repositories.CardsRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Card>> mCards;
    private CardsRepository mCardsRepository;

    public HomeViewModel() {
        mCardsRepository = CardsRepository.getInstance();
        mCards = mCardsRepository.getCards();
    }

    public LiveData<List<Card>> getCards() {
        return mCards;
    }
}