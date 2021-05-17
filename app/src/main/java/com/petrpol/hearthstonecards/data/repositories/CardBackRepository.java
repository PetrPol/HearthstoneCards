package com.petrpol.hearthstonecards.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.room.dao.CardBackDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Data repository for Card backs
 *  Gets data from database and updates them from server
 *  Singleton pattern */
public class CardBackRepository {

    private RetrofitController retrofitController;
    private CardBackDao cardBackDao;

    /**
     * Default constructor
     * @param application - application to access database
     */
    public CardBackRepository(Application application) {
        retrofitController = RetrofitController.getInstance();
        cardBackDao = CardsDatabase.getInstance(application).getCardBacksDao();
    }

    /** Gets list of cardbacks from DB
     *  Calls get card backs from server */
    public LiveData<List<CardBack>> getCardBacks(CardBacksRepositoryInterface callback){

        LiveData<List<CardBack>> data = cardBackDao.getAllCardBacks();

        retrofitController.getAllCardBacks(new Callback<List<CardBack>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse( Call<List<CardBack>> call, Response<List<CardBack>> response) {

                //If server has no card backs
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onCardBackDataGetFail(response.message());
                    return;
                }

                //Store card backs to database
                if (response.body()!=null && response.body().size()>0) {
                    new Thread(() -> {
                        for (CardBack c:response.body())
                            cardBackDao.addCardBack(c);
                        callback.onCardBackDataGetSuccess();
                    }).start();
                }
                else
                    callback.onCardBackDataGetFail("No card backs found");

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<CardBack>> call, Throwable t) {
                //Print error and call callback with error message
                if (t.getMessage()!=null) {
                    Log.e("RetroFailure", t.getMessage());
                    callback.onCardBackDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }
}
