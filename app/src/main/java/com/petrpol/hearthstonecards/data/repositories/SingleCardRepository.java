package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.webApi.RetrofitCards;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleCardRepository {

    public static SingleCardRepository instance;

    private RetrofitCards retrofitCards;

    public SingleCardRepository() {
        retrofitCards = RetrofitCards.getInstance();
    }

    public static synchronized SingleCardRepository getInstance(){
        if (instance == null) {
            instance = new SingleCardRepository();
        }

        return instance;
    }

    public void getCard(MutableLiveData<Card> card, String cardId){

        retrofitCards.getCardDetail(cardId, new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    return;
                }

                if (response.body().size()>0)
                    card.postValue(response.body().get(0));

            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("RetroFailure",t.getMessage());
            }
        });
    }

}
