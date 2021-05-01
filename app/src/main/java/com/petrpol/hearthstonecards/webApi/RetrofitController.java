package com.petrpol.hearthstonecards.webApi;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.petrpol.hearthstonecards.webApi.ApiValueHolder.*;

/** Retrofit controller to access data from call interface
 *  Singleton */
public class RetrofitController {

    private static RetrofitController instance;
    private RetrofitApi retrofitApi;

    /** Default constructor
     *  Crates retrofit instance */
    public RetrofitController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retrofit.create(RetrofitApi.class);
    }

    /** Returns instance (creates if null) */
    public static synchronized RetrofitController getInstance(){
        if (instance==null)
            instance = new RetrofitController();

        return instance;
    }

    /** Gets single card from server for given cardId */
    public void getCardDetail( String cardId, Callback<List<Card>> callback){
        Call<List<Card>> call = retrofitApi.getCardDetail(cardId,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets filter object from server */
    public void getFilter(Callback<Filter> callback){
        Call<Filter> call = retrofitApi.getFilter(AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets All cards from server */
    public void getAllCards(Callback<List<Card>> callback){
        Call<List<Card>> call = retrofitApi.getCards("classes/Priest",AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets cards from server based on given type */
    public void getCardsByType(String type, Callback<List<Card>> callback){
        String path = PATH_TYPE + type;
        Call<List<Card>> call = retrofitApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets cards from server based on given set */
    public void getCardsBySet(String set, Callback<List<Card>> callback){
        String path = PATH_SET + set;
        Call<List<Card>> call = retrofitApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets cards from server based on given class */
    public void getCardsByClass(String requiredClass, Callback<List<Card>> callback){
        String path = PATH_CLASS + requiredClass;
        Call<List<Card>> call = retrofitApi.getCards(path,AUTH_KEY,HOST);
        call.enqueue(callback);
    }

    /** Gets cards from server based on given class */
    public void getAllCardBacks(Callback<List<CardBack>> callback){
        Call<List<CardBack>> call = retrofitApi.getCardBacks(AUTH_KEY,HOST);
        call.enqueue(callback);
    }

}
