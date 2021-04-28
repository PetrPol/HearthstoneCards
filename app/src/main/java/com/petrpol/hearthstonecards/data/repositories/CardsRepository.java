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

    public static synchronized CardsRepository getInstance(){
        if (instance == null) {
            instance = new CardsRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Card>> getFilteredCards(MutableLiveData<List<Card>> data, FilterType filterType, String filterString){

        data.postValue(null);

        Callback<List<Card>> callback = new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    return;
                }

                prepareData(response.body());
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("RetroError",t.getMessage());
            }
        };

        switch (filterType){
            case SET:
                retrofitCards.getCardsBySet(filterString,callback);
                break;
            case TYPE:
                retrofitCards.getCardsByType(filterString,callback);
                break;
            case CLASS:
                retrofitCards.getCardsByClass(filterString,callback);
                break;
        }

        return data;
    }

    public MutableLiveData<List<Card>> getCards(){

        final MutableLiveData<List<Card>> data = new MutableLiveData<>();

        retrofitCards.getAllCards(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    return;
                }

                prepareData(response.body());
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("RetroError",t.getMessage());
            }
        });

        return data;
    }

    public MutableLiveData<Filter> getFilter() {
        final MutableLiveData<Filter> data = new MutableLiveData<>();

        retrofitCards.getFilter(new Callback<Filter>() {
            @Override
            public void onResponse(Call<Filter> call, Response<Filter> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    return;
                }

                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Filter> call, Throwable t) {
                Log.e("RetroError",t.getMessage());
            }
        });

        return data;
    }

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
