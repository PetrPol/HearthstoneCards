package com.petrpol.hearthstonecards.ui.cardBacks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CardBacksViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CardBacksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Card back fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}