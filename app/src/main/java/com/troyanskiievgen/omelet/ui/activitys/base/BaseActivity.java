package com.troyanskiievgen.omelet.ui.activitys.base;

import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.troyanskiievgen.omelet.repository.DBRepository;

/**
 * Created by Relax on 28.07.2017.
 */

public class BaseActivity extends MvpActivity {

    protected DBRepository dbRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbRepository = DBRepository.getInstance(this);
    }
}
