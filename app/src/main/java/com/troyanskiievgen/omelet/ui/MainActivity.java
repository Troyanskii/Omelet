package com.troyanskiievgen.omelet.ui;

import android.content.IntentFilter;
import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.network.NetworkReceiver;
import com.troyanskiievgen.omelet.presenter.MainActivityPresenter;
import com.troyanskiievgen.omelet.view.MainActivityView;

public class MainActivity extends MvpActivity implements MainActivityView, NetworkReceiver.NetworkStateReceiverListener {

    @InjectPresenter(type = PresenterType.GLOBAL)
    MainActivityPresenter mainActivityPresenter;


    private NetworkReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onResume() {
        super.onResume();
        registerNetworkListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterNetworkListener();
    }

    public void registerNetworkListener() {
        if (networkStateReceiver == null) {
            networkStateReceiver = new NetworkReceiver(this);
            registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void unregisterNetworkListener() {
        if (networkStateReceiver != null) {
            unregisterReceiver(networkStateReceiver);
            networkStateReceiver = null;
        }
    }

    @Override
    public void networkAvailable() {

    }

    @Override
    public void networkUnavailable() {

    }
}
