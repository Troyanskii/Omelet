package com.troyanskiievgen.omelet.network;

import com.troyanskiievgen.omelet.network.listeners.RequestReceiptsListener;
import com.troyanskiievgen.omelet.network.response.ReceiptsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Relax on 27.07.2017.
 */

public class RESTClient {

    private static RESTClient instance;
    private static boolean requestLock = false;
    private RequestReceiptsListener listener;

    private RESTClient() {
    }

    public static RESTClient getInstance() {
        if (instance == null) {
            instance = new RESTClient();
        }
        return instance;
    }

    public void getReceipts(String ingredients, String dishName, int page, RequestReceiptsListener listener) {
        if (!requestLock) {
            requestLock = true;
            this.listener = listener;
            NetworkManager.getRESTService().getReceipts(ingredients, dishName, page).enqueue(callback);
        }
    }

    public void searchReceipts(String dishName, RequestReceiptsListener listener) {
        if (!requestLock) {
            requestLock = true;
            this.listener = listener;
            NetworkManager.getRESTService().searchReceipts(dishName).enqueue(callback);
        }
    }

    private Callback<ReceiptsResult> callback = new Callback<ReceiptsResult>() {
        @Override
        public void onResponse(Call<ReceiptsResult> call, Response<ReceiptsResult> response) {
            if(response.body() != null) {
                listener.onReceiptUploaded(response.body(), null);
            } else {
                listener.onReceiptUploaded(null, response.message());
            }
            requestLock = false;
        }

        @Override
        public void onFailure(Call<ReceiptsResult> call, Throwable t) {
            listener.onReceiptUploaded(null, t.getMessage());
            requestLock = false;
        }
    };
}