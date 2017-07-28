package com.troyanskiievgen.omelet.network.listeners;

import com.troyanskiievgen.omelet.network.response.ReceiptsResult;

/**
 * Created by Relax on 28.07.2017.
 */

public interface RequestReceiptsListener {

    void onReceiptUploaded(ReceiptsResult result, String error);

}
