package com.troyanskiievgen.omelet.application;

import android.app.Application;
import android.content.Context;

import com.troyanskiievgen.omelet.repository.DBRepository;

/**
 * Created by Relax on 27.07.2017.
 */

public class OmeletApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBRepository.init(getApplicationContext());
    }
}
