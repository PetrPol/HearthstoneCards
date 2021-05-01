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
import retrofit2.internal.EverythingIsNonNull;

/** Data repository for Single card
 *  Gets data from database and updates them from server
 *  Singleton pattern */
public class SingleCardRepository {

    public static SingleCardRepository instance;

    private RetrofitController retrofitController;
    private CardDao cardDao;

    /** Default constructor
     *  @param cardsDatabase - requires database to access data */
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

    /** Gets card object from DB
     *  Calls get card od given card id from server
     *  Calls callback when success or fail */
    public LiveData<Card> getCard(String cardId , SingleCardRepositoryInterface callback){

        LiveData<Card> data = cardDao.getCardById(cardId);

        retrofitController.getCardDetail(cardId, new Callback<List<Card>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                // When card not found
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onCardDataGetFail(response.message());
                    return;
                }

                //Update card in database
                if (response.body()!=null && response.body().size()>0) {
                    new Thread(() -> {
                        cardDao.addCard(response.body().get(0));
                        callback.onCardDataGetSuccess();
                    }).start();
                }
                else
                    callback.onCardDataGetFail("Card not found");

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Card>> call, Throwable t) {
                //Print error and call callback with error message
                if (t.getMessage()!=null) {
                    Log.e("RetroFailure", t.getMessage());
                    callback.onCardDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }

}
