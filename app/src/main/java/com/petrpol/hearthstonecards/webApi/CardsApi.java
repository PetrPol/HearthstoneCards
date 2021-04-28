package com.petrpol.hearthstonecards.webApi;

import com.petrpol.hearthstonecards.data.model.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CardsApi {

    @GET("cards/{path}")
    Call<List<Card>> getCards(@Path("path") String path, @Header ("X-Rapidapi-Key") String authKey, @Header("X-Rapidapi-Host") String host);
}
