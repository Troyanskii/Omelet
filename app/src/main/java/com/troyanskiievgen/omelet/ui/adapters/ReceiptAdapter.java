package com.troyanskiievgen.omelet.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.ui.adapters.holder.ReceiptHolder;
import com.troyanskiievgen.omelet.ui.adapters.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptHolder> {

    private final RecyclerItemClickListener<Receipt> listener;
    private List<Receipt> items;

    public ReceiptAdapter(@NonNull RecyclerItemClickListener<Receipt> listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @Override
    public ReceiptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceiptHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_receipt, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ReceiptHolder holder, int position) {
        holder.bindContent(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Receipt> items) {
        this.items = items;
    }
}
