package com.troyanskiievgen.omelet.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Relax on 28.07.2017.
 */

public interface SplashActivityView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startMainActivity();
}
