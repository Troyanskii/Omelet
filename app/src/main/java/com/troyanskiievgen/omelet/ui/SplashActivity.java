package com.troyanskiievgen.omelet.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpActivity;
import com.troyanskiievgen.omelet.R;

/**
 * Created by Relax on 27.07.2017.
 */

public class SplashActivity extends MvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
