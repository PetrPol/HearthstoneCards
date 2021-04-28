package com.petrpol.hearthstonecards.webApi;

import android.util.Log;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.Filter;

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
    private final String PATH_TYPE = "types/";
    private final String PATH_SET = "sets/";
    private final String PATH_CLASS = "classes/";

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

    public void getFilter(Callback<Filter> callback){
        Call<Filter> call = cardsApi.getFilter(AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    public void getAllCards(Callback<List<Card>> callback){
        Call<List<Card>> call = cardsApi.getCards("classes/Priest",AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    public void getCardsByType(String type, Callback<List<Card>> callback){
        String path = PATH_TYPE + type;
        Log.d("TEST API TYPE", path);
        Call<List<Card>> call = cardsApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    public void getCardsBySet(String set, Callback<List<Card>> callback){
        String path = PATH_SET + set;
        Log.d("TEST API SET", path);
        Call<List<Card>> call = cardsApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    public void getCardsByClass(String requiredClass, Callback<List<Card>> callback){
        String path = PATH_CLASS + requiredClass;
        Log.d("TEST API CLASS", path);
        Call<List<Card>> call = cardsApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }
}
