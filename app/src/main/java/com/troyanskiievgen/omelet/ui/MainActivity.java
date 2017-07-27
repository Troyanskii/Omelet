package com.troyanskiievgen.omelet.ui;

import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.presenter.MainActivityPresenter;
import com.troyanskiievgen.omelet.view.MainActivityView;

public class MainActivity extends MvpActivity implements MainActivityView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
