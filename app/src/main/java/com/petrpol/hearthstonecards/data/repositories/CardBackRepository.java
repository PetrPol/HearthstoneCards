package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.room.CardBacksDatabase;
import com.petrpol.hearthstonecards.room.dao.CardBackDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardBackRepository {

    public static CardBackRepository instance;

    private RetrofitController retrofitController;
    private CardBackDao cardBackDao;

    public CardBackRepository(CardBacksDatabase cardsDatabase) {
        retrofitController = RetrofitController.getInstance();
        cardBackDao = cardsDatabase.getCardBacksDao();
    }

    /** Returns instance (create if null) */
    public static synchronized CardBackRepository getInstance(CardBacksDatabase cardsDatabase){
        if (instance == null)
            instance = new CardBackRepository(cardsDatabase);

        return instance;
    }

    /** Gets list of cardbacks from DB
     *  Calls get card backs from server */
    public LiveData<List<CardBack>> getCardBacks(CardBacksRepositoryInterface callback){

        LiveData<List<CardBack>> data = cardBackDao.getAllCardBacks();

        Log.i("TEST DATA", (data==null)+"");

        retrofitController.getAllCardBacks(new Callback<List<CardBack>>() {
            @Override
            public void onResponse(Call<List<CardBack>> call, Response<List<CardBack>> response) {

                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onCardBackDataGetFail(response.message());
                    return;
                }

                if (response.body()!=null && response.body().size()>0) {
                    for (CardBack c:response.body())
                        cardBackDao.addCardBack(c);
                    callback.onCardBackDataGetSuccess();
                }
                else
                    callback.onCardBackDataGetFail("No card backs found");

            }

            @Override
            public void onFailure(Call<List<CardBack>> call, Throwable t) {
                if (t.getMessage()!=null) {
                    Log.e("RetroFailure", t.getMessage());
                    callback.onCardBackDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }
}
