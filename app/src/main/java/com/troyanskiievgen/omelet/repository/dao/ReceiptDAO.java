package com.troyanskiievgen.omelet.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

@Dao
public interface ReceiptDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReceipts(List<Receipt> receipts);

    @Query("SELECT * FROM receipts WHERE title LIKE :title")
    List<Receipt> getReceiptByTitle(String title);

    @Query("SELECT * FROM receipts")
    List<Receipt> getAllReceipts();

    @Update
    void updateReceipt(Receipt receipt);

    @Insert
    long insertReceipt(Receipt receipt);

    @Delete
    void deleteReceipt(Receipt receipt);
}
