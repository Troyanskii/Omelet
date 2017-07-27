package com.troyanskiievgen.omelet.network;

import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Relax on 27.07.2017.
 */

public interface RESTService {

    @GET
    Call<List<Receipt>> getReceipts(@Query("i") String ingredients,
                                    @Query("q") String dishName,
                                    @Query("p") String page);

    @GET
    Call<List<Receipt>> searchReceipts(@Query("q") String dishName);
}
