package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.room.dao.CardDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Data repository for single card
 *  Singleton pattern */
public class SingleCardRepository {

    public static SingleCardRepository instance;

    private RetrofitController retrofitController;
    private CardDao cardDao;

    public SingleCardRepository(CardsDatabase cardsDatabase) {
        retrofitController = RetrofitController.getInstance();
        cardDao = cardsDatabase.getCardDao();
    }

    /** Returns instance (create if null) */
    public static synchronized SingleCardRepository getInstance(CardsDatabase cardsDatabase){
        if (instance == null)
            instance = new SingleCardRepository(cardsDatabase);

        return instance;
    }

    /** Updates card object of given cardId */
    public LiveData<Card> getCard(String cardId , SingleCardRepositoryInterface callback){

        LiveData<Card> data = cardDao.getCardById(cardId);

        retrofitController.getCardDetail(cardId, new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onCardDataGetFail(response.message());
                    return;
                }

                if (response.body()!=null && response.body().size()>0) {
                    //cardDao.addCard(response.body().get(0));
                    callback.onCardDataGetSuccess();
                }
                else
                    callback.onCardDataGetFail("Card not found");

            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                if (t.getMessage()!=null) {
                    Log.e("RetroFailure", t.getMessage());
                    callback.onCardDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }

}
