package com.troyanskiievgen.omelet.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.network.RESTClient;
import com.troyanskiievgen.omelet.network.listeners.RequestReceiptsListener;
import com.troyanskiievgen.omelet.network.response.ReceiptsResult;
import com.troyanskiievgen.omelet.repository.DBRepository;
import com.troyanskiievgen.omelet.repository.listners.DBObtainReceiptsListener;
import com.troyanskiievgen.omelet.repository.listners.DBOperationCompleteListener;
import com.troyanskiievgen.omelet.ui.activitys.MainActivity;
import com.troyanskiievgen.omelet.view.MainActivityView;

import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView>
        implements RequestReceiptsListener, DBObtainReceiptsListener, DBOperationCompleteListener {

    private DBRepository dbRepository;
    private boolean isSearchRequest = false;

    public void setDbRepository(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void getAllReceipts() {
        dbRepository.getAllReceipts(this);
    }

    public void onSwipeForRefresh(String searchText, boolean isNetworkAvailable) {
        if (searchText.isEmpty()) {
            updateReceipts(isNetworkAvailable);
        } else {
            searchByFilter(searchText, isNetworkAvailable);
        }
    }

    private void updateReceipts(boolean isNetworkAvailable) {
        isSearchRequest = false;
        if (isNetworkAvailable) {
            RESTClient.getInstance().getReceipts("onions,garlic", "omelet", 3, this);
        } else {
            dbRepository.getAllReceipts(this);
        }
    }

    public void searchByFilter(String searchName, boolean isNetworkAvailable) {
        isSearchRequest = true;
        if(isNetworkAvailable) {
            RESTClient.getInstance().searchReceipts(searchName, this);
        } else {
            dbRepository.getReceiptsByName(searchName, this);
        }
    }

    @Override
    public void onDBReceiptsObtained(List<Receipt> receipts) {
        getViewState().updateAdapterItems(receipts);
    }

    @Override
    public void onReceiptUploaded(ReceiptsResult result, String error) {
        if (result == null) {
            return;
        }
        if (isSearchRequest) {
            getViewState().updateAdapterItems(result.getResults());
        } else {
            dbRepository.storeAllReceipts(result.getResults(), this);
        }
    }

    @Override
    public void onOperationComplete() {
        getAllReceipts();
    }
}
