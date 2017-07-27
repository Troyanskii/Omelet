package com.troyanskiievgen.omelet.repository;

import android.arch.persistence.room.Room;

import com.troyanskiievgen.omelet.application.OmeletApplication;

/**
 * Created by Relax on 27.07.2017.
 */

public class DBRepository {

    private static DBRepository instance;
    private OmeletDataBase omeletDataBase;

    private DBRepository() {
        omeletDataBase = Room.databaseBuilder(OmeletApplication.getContext(),
                OmeletDataBase.class, "database-omlet").build();
    }

    public static void init() {
        instance = new DBRepository();
    }

    public static DBRepository getInstance() {
        if(instance == null) {
            instance = new DBRepository();
        }
        return instance;
    }
}
