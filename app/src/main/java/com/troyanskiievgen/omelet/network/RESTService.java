package com.troyanskiievgen.omelet.network;

import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.network.response.ReceiptsResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Relax on 27.07.2017.
 */

interface RESTService {

    @GET(ConstantsAPI.API)
    Call<ReceiptsResult> getReceipts(@Query("i") String ingredients,
                                     @Query("q") String dishName,
                                     @Query("p") int page);

    @GET(ConstantsAPI.API)
    Call<ReceiptsResult> searchReceipts(@Query("q") String dishName);
}
