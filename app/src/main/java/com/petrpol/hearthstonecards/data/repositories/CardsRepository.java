package com.petrpol.hearthstonecards.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.R;
import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.room.dao.CardDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Data repository for Cards
 *  Gets data from database and updates them from server
 *  Singleton pattern */
public class CardsRepository {

    private RetrofitController retrofitController;
    private CardDao cardDao;
    private Application application;

    /**
     * Default constructor
     *
     * @param application - application to access database
     */
    public CardsRepository(Application application) {
        this.cardDao = CardsDatabase.getInstance(application).getCardDao();
        this.application = application;
        retrofitController = RetrofitController.getInstance();
    }

    /** Gets Card list for given filter parameters from DB
     *  Calls request to server to get all matching cards
     *  Calls callback when success or fail */
    public LiveData<List<Card>> getFilteredCards(FilterType filterType, String filterString, CardsRepositoryInterface callback){

        Callback<List<Card>> retroCallback = new Callback<List<Card>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                //If server has no cards
                if (!response.isSuccessful()) {
                    Log.e("RetroError", response.message());
                    callback.onCardDataGetFailNoCards();
                    return;
                }

                //Prepare data to show
                if (response.body()!=null)
                    prepareData(response.body());

                //If after prepare no cards left
                if (response.body()==null || response.body().size()==0) {
                    callback.onCardDataGetFailNoCards();
                    return;
                }

                //Store cards to Database
                new Thread(() -> {
                    for (Card c: response.body()) {
                        cardDao.addCard(c);
                    }
                    callback.onCardDataGetSuccess();
                }).start();
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Card>> call, Throwable t) {
                //Print error and call callback with error message
                if (t.getMessage()!=null) {
                    Log.e("RetroErrorFail", t.getMessage());
                    callback.onCardDataGetFail(application.getString(R.string.error_no_connection));
                }
            }
        };

        //Select endpoint to call based on filter type
        switch (filterType){
            case SET:
                retrofitController.getCardsBySet(filterString,retroCallback);
                return cardDao.getCardsBySet(filterString);
            case TYPE:
                retrofitController.getCardsByType(filterString,retroCallback);
                return cardDao.getCardsByType(filterString);
            case CLASS:
                retrofitController.getCardsByClass(filterString,retroCallback);
                return cardDao.getCardsByClass(filterString);
            default:
                retrofitController.getAllCards(retroCallback);
                return cardDao.getAllCards();
        }
    }

    /** Gets Card list of all cards from DB
     *  Calls request to server to get all cards
     *  Calls callback when success or fail */
    public LiveData<List<Card>>  getCards(CardsRepositoryInterface callback){
        return getFilteredCards(FilterType.NONE,null,callback);
    }

    /** Prepares list of cards - removes cards without image  */
    private void prepareData(List<Card> cardList){
        int i = 0;
        while (i < cardList.size()){
            if (cardList.get(i).getImg()==null)
                cardList.remove(i);
            else
                i++;
        }
    }

}
