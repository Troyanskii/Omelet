package com.troyanskiievgen.omelet.ui.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.troyanskiievgen.omelet.R;
import com.troyanskiievgen.omelet.model.Receipt;
import com.troyanskiievgen.omelet.network.NetworkManager;
import com.troyanskiievgen.omelet.presenter.MainActivityPresenter;
import com.troyanskiievgen.omelet.ui.activitys.base.BaseActivity;
import com.troyanskiievgen.omelet.ui.adapters.ReceiptAdapter;
import com.troyanskiievgen.omelet.ui.adapters.listeners.RecyclerItemClickListener;
import com.troyanskiievgen.omelet.utils.DelayedTextChangeListener;
import com.troyanskiievgen.omelet.view.MainActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityView, RecyclerItemClickListener<Receipt> {

    @BindView(R.id.receipt_list)
    RecyclerView receiptList;
    @BindView(R.id.et_search)
    EditText searchField;
    @BindView(R.id.search_icon)
    ImageView searchIcon;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    @InjectPresenter(type = PresenterType.GLOBAL)
    MainActivityPresenter mainActivityPresenter;

    private ReceiptAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupReceiptRecyclerView();
        setupSearchField();
        setupRefresh();
        mainActivityPresenter.setDbRepository(dbRepository);
        mainActivityPresenter.getAllReceipts();
    }

    private void setupReceiptRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        receiptList.setHasFixedSize(true);
        receiptList.setLayoutManager(llm);
        adapter = new ReceiptAdapter(this);
        receiptList.setAdapter(adapter);
    }

    private void setupRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainActivityPresenter.onSwipeForRefresh(searchField.getText().toString(),
                        NetworkManager.isNetworkAvailable(MainActivity.this));
            }
        });
    }

    private void setupSearchField() {
        searchField.addTextChangedListener(new DelayedTextChangeListener() {

            @Override
            protected void onDelayExceeded(String text) {
                mainActivityPresenter.searchByFilter(text, NetworkManager.isNetworkAvailable(MainActivity.this));
            }

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.toString().isEmpty()) {
                    mainActivityPresenter.getAllReceipts();
                    searchIcon.setVisibility(View.VISIBLE);
                } else {
                    searchIcon.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void updateAdapterItems(List<Receipt> receipts) {
        adapter.setItems(receipts);
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onItemClick(Receipt model) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getHref()));
        startActivity(browserIntent);
    }
}
