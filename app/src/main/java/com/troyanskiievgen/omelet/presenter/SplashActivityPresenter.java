package com.troyanskiievgen.omelet.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.troyanskiievgen.omelet.network.RESTClient;
import com.troyanskiievgen.omelet.network.listeners.RequestReceiptsListener;
import com.troyanskiievgen.omelet.network.response.ReceiptsResult;
import com.troyanskiievgen.omelet.repository.DBRepository;
import com.troyanskiievgen.omelet.repository.listners.DBOperationCompleteListener;
import com.troyanskiievgen.omelet.view.SplashActivityView;

/**
 * Created by Relax on 28.07.2017.
 */

@InjectViewState
public class SplashActivityPresenter extends MvpPresenter<SplashActivityView>
        implements RequestReceiptsListener , DBOperationCompleteListener {

    private DBRepository dbRepository;

    public void setDbRepository(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void getReceipts() {
        RESTClient.getInstance().getReceipts("onions,garlic", "omelet", 3, this);
    }

    @Override
    public void onReceiptUploaded(ReceiptsResult result, String error) {
        if(result == null) {
            getViewState().startMainActivity();
        } else {
            dbRepository.storeAllReceipts(result.getResults(), this);
        }
    }

    @Override
    public void onOperationComplete() {
        getViewState().startMainActivity();
    }
}
