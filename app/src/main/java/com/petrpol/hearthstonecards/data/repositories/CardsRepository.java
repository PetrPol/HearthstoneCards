package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.webApi.RetrofitCards;

import java.util.ArrayList;
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

    public MutableLiveData<List<Card>> getCards(){

        final MutableLiveData<List<Card>> data = new MutableLiveData<>();

        retrofitCards.getAllCards(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    return;
                }

                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("RetroError",t.getMessage());
            }
        });

        return data;
    }
}
