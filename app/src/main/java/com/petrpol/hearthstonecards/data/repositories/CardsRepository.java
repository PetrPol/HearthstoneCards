package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.hearthstonecards.data.enums.FilterType;
import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;
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

    public CardsRepository() {
        retrofitCards = RetrofitCards.getInstance();
    }

    /** Gets instance (creates if is null) */
    public static synchronized CardsRepository getInstance(){
        if (instance == null)
            instance = new CardsRepository();

        return instance;
    }

    /** Gets Card list for given filter parameters
     *  Calls callback when success or fail */
    public void getFilteredCards(MutableLiveData<List<Card>> data, FilterType filterType, String filterString, CardsRepositoryInterface callback){

        //Clear data
        data.postValue(null);

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

                data.postValue(response.body());

                if (response.body()==null || response.body().size()==0)
                    callback.onCardDataGetFail("No Data found for this filter");

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
                break;
            case TYPE:
                retrofitCards.getCardsByType(filterString,retroCallback);
                break;
            case CLASS:
                retrofitCards.getCardsByClass(filterString,retroCallback);
                break;
            case NONE:
                retrofitCards.getAllCards(retroCallback);
        }
    }

    /** Gets Card list of all cards
     *  Calls callback when success or fail */
    public void getCards(MutableLiveData<List<Card>> data, CardsRepositoryInterface callback){
        getFilteredCards(data,FilterType.NONE,null,callback);
    }

    /** Gets filter object
     *  Calls callback when success or fail */
    public MutableLiveData<Filter> getFilter(MutableLiveData<Filter> data, CardsRepositoryInterface callback) {

        retrofitCards.getFilter(new Callback<Filter>() {
            @Override
            public void onResponse(Call<Filter> call, Response<Filter> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onFilterDataGetFail(response.message());
                    return;
                }

                data.postValue(response.body());

                callback.onFilterDataGetSuccess();
            }

            @Override
            public void onFailure(Call<Filter> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.e("RetroErrorFail", t.getMessage());
                    callback.onFilterDataGetFail(t.getMessage());
                }
            }
        });

        return data;
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
