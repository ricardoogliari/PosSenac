package com.senac.rsilva.possenac;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by rsilva on 18/06/16.
 */
public interface MyInterfaceRetrofit {

    @GET("v2/576539791100005a0ea92a52")
    Call<Posicoes> searchPositions();

    //https://maps.googleapis.com/maps/api/directions/json?
    // origin=Toronto&destination=Montreal&key=YOUR_API_KEY

    //baseurl: https://maps.googleapis.com
    //endpoint: maps/api/directions/json
    //query: origin, destination e key
    @GET("maps/api/directions/json")
    Call<Retorno> buscaRota(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );

}
