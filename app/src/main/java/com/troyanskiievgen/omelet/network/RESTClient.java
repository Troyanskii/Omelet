package com.troyanskiievgen.omelet.network;

import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Relax on 27.07.2017.
 */

public class RESTClient {

    private static RESTClient instance;

    private RESTClient() {
    }

    public static RESTClient getInstance() {
        if (instance == null) {
            instance = new RESTClient();
        }
        return instance;
    }

    public void getReceipts(String ingredients, String dishName, String page, Callback<List<Receipt>> callback) {
        NetworkManager.getRESTService().getReceipts(ingredients, dishName, page).enqueue(callback);
    }

    public void searchReceipts(String dishName, Callback<List<Receipt>> callback) {
        NetworkManager.getRESTService().searchReceipts(dishName).enqueue(callback);
    }
}