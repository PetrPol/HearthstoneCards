package com.petrpol.hearthstonecards.data.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.room.dao.CardDao;
import com.petrpol.hearthstonecards.webApi.RetrofitCards;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Repository for cards list
 *  Singleton */
public class CardsRepository {

    public static CardsRepository instance;

    private RetrofitCards retrofitCards;
    private CardDao cardDao;

    public CardsRepository(CardsDatabase database) {
        this.cardDao = database.getCardDao();
        retrofitCards = RetrofitCards.getInstance();

    }

    /** Gets instance (creates if is null) */
    public static synchronized CardsRepository getInstance(CardsDatabase database){
        if (instance == null)
            instance = new CardsRepository(database);

        return instance;
    }

    /** Gets Card list for given filter parameters from DB
     *  Calls request to server to get all matching cards
     *  Calls callback when success or fail */
    public LiveData<List<Card>> getFilteredCards(FilterType filterType, String filterString, CardsRepositoryInterface callback){

        Callback<List<Card>> retroCallback = new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError", response.message());
                    callback.onCardDataGetFail(response.message());
                    return;
                }

                if (response.body()!=null)
                    prepareData(response.body());


                if (response.body()==null || response.body().size()==0) {
                    callback.onCardDataGetFail("No Data found for this filter");
                    return;
                }

                //Store cards TODO
                for (Card c: response.body()) {
                    cardDao.addCard(c);
                }

                callback.onCardDataGetSuccess();
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                if (t.getMessage()!=null) {
                    Log.e("RetroErrorFail", t.getMessage());
                    callback.onCardDataGetFail(t.getMessage());
                }
            }
        };

        switch (filterType){
            case SET:
                retrofitCards.getCardsBySet(filterString,retroCallback);
                return cardDao.getCardsBySet(filterString);
            case TYPE:
                retrofitCards.getCardsByType(filterString,retroCallback);
                return cardDao.getCardsByType(filterString);
            case CLASS:
                retrofitCards.getCardsByClass(filterString,retroCallback);
                return cardDao.getCardsByClass(filterString);
            default:
                retrofitCards.getAllCards(retroCallback);
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
