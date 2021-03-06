package com.troyanskiievgen.omelet.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

public interface MainActivityView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateAdapterItems(List<Receipt> receipts);
}
