package com.troyanskiievgen.omelet.network;

import com.troyanskiievgen.omelet.network.response.ReceiptsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Relax on 27.07.2017.
 */

interface RESTService {

    String API = "api/";

    @GET(API)
    Call<ReceiptsResult> getReceipts(@Query("i") String ingredients,
                                     @Query("q") String dishName,
                                     @Query("p") int page);

    @GET(API)
    Call<ReceiptsResult> searchReceipts(@Query("q") String dishName);
}
