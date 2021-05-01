package com.petrpol.hearthstonecards.webApi;

import com.petrpol.hearthstonecards.data.model.Card;
import com.petrpol.hearthstonecards.data.model.CardBack;
import com.petrpol.hearthstonecards.data.model.Filter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/** Interface for server calls
 *  All calls requires key and host value */
public interface RetrofitApi {

    /** Gets all cards from server of given path endpoint */
    @GET("cards/{path}")
    Call<List<Card>> getCards (@Path("path") String path, @Header ("X-Rapidapi-Key") String authKey, @Header("X-Rapidapi-Host") String host);

    /** Gets filter from server */
    @GET("info")
    Call<Filter> getFilter ( @Header ("X-Rapidapi-Key") String authKey, @Header("X-Rapidapi-Host") String host);

    /** Gets single card detail of given card id */
    @GET("cards/{id}")
    Call<List<Card>> getCardDetail (@Path("id") String id, @Header ("X-Rapidapi-Key") String authKey, @Header("X-Rapidapi-Host") String host);

    /** Gets list of all card backs */
    @GET("cardbacks")
    Call<List<CardBack>> getCardBacks (@Header ("X-Rapidapi-Key") String authKey, @Header("X-Rapidapi-Host") String host);

}
