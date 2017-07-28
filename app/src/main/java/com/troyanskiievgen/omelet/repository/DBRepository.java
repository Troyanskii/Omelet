package com.troyanskiievgen.omelet.repository;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.repository.listners.DBObtainReceiptsListener;
import com.troyanskiievgen.omelet.repository.listners.DBOperationCompleteListener;

import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

public class DBRepository {

    private static DBRepository instance;
    private OmeletDataBase omeletDataBase;

    private DBRepository(Context context) {
        omeletDataBase = Room.databaseBuilder(context,
                OmeletDataBase.class, "database-omelet").build();
    }

    public static void init(Context context) {
        instance = new DBRepository(context);
    }

    public static DBRepository getInstance(Context context) {
        if(instance == null) {
            instance = new DBRepository(context);
        }
        return instance;
    }

    public void storeAllReceipts(final List<Receipt> receipts, final DBOperationCompleteListener listener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                omeletDataBase.getReceiptDao().insertReceipts(receipts);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.onOperationComplete();
            }
        }.execute();
    }

    public void getAllReceipts(final DBObtainReceiptsListener listener) {
        new AsyncTask<Void, Void, Void>() {
            List<Receipt> receipts;
            @Override
            protected Void doInBackground(Void... params) {
                receipts = omeletDataBase.getReceiptDao().getAllReceipts();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.onDBReceiptsObtained(receipts);
            }
        }.execute();
    }
    public void getReceiptsByName(final String name, final DBObtainReceiptsListener listener) {
        new AsyncTask<Void, Void, Void>() {
            List<Receipt> receipts;
            @Override
            protected Void doInBackground(Void... params) {
                receipts = omeletDataBase.getReceiptDao().getReceiptByTitle("%" + name + "%");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.onDBReceiptsObtained(receipts);
            }
        }.execute();
    }
}
