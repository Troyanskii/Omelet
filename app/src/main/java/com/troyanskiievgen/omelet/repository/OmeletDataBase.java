package com.troyanskiievgen.omelet.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.repository.dao.ReceiptDAO;

import static com.troyanskiievgen.omelet.repository.OmeletDataBase.DATABASE_VERSION;

/**
 * Created by Relax on 27.07.2017.
 */

@Database(entities = (Receipt.class), version = DATABASE_VERSION)
abstract class OmeletDataBase extends RoomDatabase implements DAOInterfaces{

    static final int DATABASE_VERSION = 1;

    @Override
    public abstract ReceiptDAO getReceiptDao();
}

