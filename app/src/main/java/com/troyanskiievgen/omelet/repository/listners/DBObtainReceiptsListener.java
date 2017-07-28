package com.troyanskiievgen.omelet.repository.listners;

import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

/**
 * Created by Relax on 28.07.2017.
 */

public interface DBObtainReceiptsListener {

    void onDBReceiptsObtained(List<Receipt> receipts);

}
