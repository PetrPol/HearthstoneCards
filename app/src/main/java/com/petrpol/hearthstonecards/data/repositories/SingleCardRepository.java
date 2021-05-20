package com.petrpol.hearthstonecards.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.R;
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

    private RetrofitController retrofitController;
    private CardDao cardDao;
    private Application application;

    /**
     * Default constructor
     *
     * @param application - application to access database
     */
    public SingleCardRepository(Application application) {
        this.application = application;
        retrofitController = RetrofitController.getInstance();
        cardDao = CardsDatabase.getInstance(application).getCardDao();
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
                    callback.onCardDataGetFail(application.getString(R.string.error_no_card));

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Card>> call, Throwable t) {
                //Print error and call callback with error message
                if (t.getMessage()!=null) {
                    Log.e("RetroFailure", t.getMessage());
                    callback.onCardDataGetFail(application.getString(R.string.error_no_connection));
                }
            }
        });

        return data;
    }

}
