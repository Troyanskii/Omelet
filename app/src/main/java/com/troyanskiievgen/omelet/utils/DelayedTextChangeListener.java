package com.troyanskiievgen.omelet.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Relax on 28.07.2017.
 */

public abstract class DelayedTextChangeListener implements TextWatcher {

    private final static int SEARCH_DELAY = 600;

    private final Handler handler;
    private Runnable workRunnable;

    protected DelayedTextChangeListener() {
        handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(final Editable s) {
        handler.removeCallbacks(workRunnable);
        if (s.toString().isEmpty()) {
            return;
        }
        workRunnable = new Runnable() {
            @Override
            public void run() {
                onDelayExceeded(s.toString());
            }
        };
        handler.postDelayed(workRunnable, SEARCH_DELAY);
    }

    protected abstract void onDelayExceeded(String text);
}
