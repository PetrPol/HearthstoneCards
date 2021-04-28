package com.petrpol.hearthstonecards.webApi;

import com.petrpol.hearthstonecards.data.model.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Retrofit controller to access data from API
 *  Singleton */
public class RetrofitCards {

    private final String BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/";
    private final String AUTH_KEY = "44a61c4a77msh6ad106c9d375692p1e57f7jsn5766b90e1590";
    private final String HOST = "omgvamp-hearthstone-v1.p.rapidapi.com";

    private static RetrofitCards instance;
    private Retrofit retrofit;
    private CardsApi cardsApi;

    public RetrofitCards() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cardsApi = retrofit.create(CardsApi.class);
    }

    public static synchronized RetrofitCards getInstance(){
        if (instance==null)
            instance = new RetrofitCards();

        return instance;
    }


    public void getAllCards(Callback<List<Card>> callback){
        Call<List<Card>> call = cardsApi.getCards("sets/Classic",AUTH_KEY,HOST);
        call.enqueue(callback);
    }
}
