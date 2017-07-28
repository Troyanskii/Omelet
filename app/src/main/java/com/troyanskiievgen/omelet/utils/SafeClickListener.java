package com.troyanskiievgen.omelet.utils;

import android.view.View;

import java.util.Date;

/**
 * Created by Relax on 27.07.2017.
 */

public abstract class SafeClickListener implements View.OnClickListener {

    private final static int TIME_CLICK_LOCK = 500;
    private static long lastTimeClick;

    @Override
    public void onClick(View v) {
        long currentTime = new Date().getTime();
        if(currentTime - lastTimeClick > TIME_CLICK_LOCK) {
            lastTimeClick = currentTime;
            onSafeClick(v);
        }
    }

    protected abstract void onSafeClick(View v);
}
