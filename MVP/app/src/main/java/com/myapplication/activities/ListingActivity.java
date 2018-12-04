package com.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.adapters.ListingsRecyclerViewAdapter;
import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.presenters.ListingsPresenter;
import com.myapplication.views.ListingsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListingActivity extends AppCompatActivity implements ListingsView {

    private static final String TAG = ListingActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ListingsPresenter listingsPresenter = new ListingsPresenter(this, new ListingsInteractor());
        listingsPresenter.startListingsDownload();
    }

    @Override
    public void startLoadingNations() {
        Toast.makeText(this, R.string.started_loading_nations, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<Nation> nations) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListingsRecyclerViewAdapter adapter = new ListingsRecyclerViewAdapter(this, nations);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, R.string.error_while_loading_nations, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyListReceived() {
        Toast.makeText(this, R.string.nations_information_not_available, Toast.LENGTH_SHORT).show();
    }
}

