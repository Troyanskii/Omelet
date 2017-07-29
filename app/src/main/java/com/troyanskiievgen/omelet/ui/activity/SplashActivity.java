package com.troyanskiievgen.omelet.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.network.NetworkManager;
import com.troyanskiievgen.omelet.presenter.SplashActivityPresenter;
import com.troyanskiievgen.omelet.ui.activity.base.BaseActivity;
import com.troyanskiievgen.omelet.view.SplashActivityView;

/**
 * Created by Relax on 27.07.2017.
 */

public class SplashActivity extends BaseActivity implements SplashActivityView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    SplashActivityPresenter splashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivityPresenter.setDbRepository(dbRepository);
        if(NetworkManager.isNetworkAvailable(this)) {
            splashActivityPresenter.getReceipts();
        } else {
            startMainActivity();
        }
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
