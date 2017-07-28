package com.troyanskiievgen.omelet.ui.adapters.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.ui.adapters.listeners.RecyclerItemClickListener;
import com.troyanskiievgen.omelet.utils.SafeClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Relax on 27.07.2017.
 */

public class ReceiptHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.receipt_image)
    CircleImageView receiptImage;
    @BindView(R.id.receipt_title)
    TextView receiptTitle;
    @BindView(R.id.receipt_ingredients)
    TextView receiptIngredients;
    private Receipt receipt;
    private Context context;

    public ReceiptHolder(View itemView, final RecyclerItemClickListener<Receipt> listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(new SafeClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if (listener != null) {
                    listener.onItemClick(receipt);
                }
            }
        });
    }

    public void bindContent(Receipt receipt) {
        this.receipt = receipt;
        Glide.with(context)
                .load(receipt.getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(receiptImage);
        receiptTitle.setText(receipt.getTitle());
        receiptIngredients.setText(receipt.getIngredients());
    }
}
