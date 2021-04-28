package com.petrpol.hearthstonecards.ui.cardDetail;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.repositories.SingleCardRepository;
import com.squareup.picasso.Picasso;

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

    public String getImageUrl(){
        if (mCard.getValue()!= null)
            return mCard.getValue().getImg();
        return null;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }
}
