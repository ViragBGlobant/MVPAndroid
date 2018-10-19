package com.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.adapters.ListingsRecyclerViewAdapter;
import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.networks.NetworkStatusUtils;
import com.myapplication.presenters.ListingsPresenter;
import com.myapplication.views.ListingsView;

import java.util.List;

public class ListingActivity extends AppCompatActivity implements ListingsView, ListingsRecyclerViewAdapter.ItemClickListener {

    private static final String TAG = ListingActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Nation> nationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        initUI();
    }

    private void initUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        if (NetworkStatusUtils.isInternetAvailable(this)) {
            ListingsPresenter listingsPresenter = new ListingsPresenter(this, new ListingsInteractor());
            listingsPresenter.startListingsDownload();
        } else {
            Toast.makeText(this, R.string.no_network_available, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startLoadingNations() {
        Toast.makeText(this, R.string.started_loading_nations, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<Nation> nations) {
        if (nations != null && !nations.isEmpty()) {
            nationList = nations;
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ListingsRecyclerViewAdapter adapter = new ListingsRecyclerViewAdapter(this, nations);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, R.string.nations_information_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, R.string.error_while_loading_nations, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        if (nationList != null && !nationList.isEmpty()) {
            Toast.makeText(this, nationList.get(position).getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ListingActivity.this, ListingDetailsActivity.class);
            startActivity(intent);
        }
    }
}
