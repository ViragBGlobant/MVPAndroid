package com.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.myapplication.R;
import com.myapplication.models.Nation;
import com.myapplication.presenters.ListingDetailsPresenter;
import com.myapplication.views.ListingDetailsView;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.myapplication.utils.Constants.NATION;

public class ListingDetailsActivity extends AppCompatActivity implements ListingDetailsView {

    private static final String TAG = ListingDetailsActivity.class.getSimpleName();

    private ListingDetailsPresenter listingDetailsPresenter;
    private Nation nation;

    @BindView(R.id.flagImageView)
    ImageView flagImageView;

    @BindView(R.id.nameTextView)
    TextView nameTextView;

    @BindView(R.id.nativeNameTextView)
    TextView nativeNameTextView;

    @BindView(R.id.capitalTextView)
    TextView capitalTextView;

    @BindView(R.id.areaTextView)
    TextView areaTextView;

    @BindView(R.id.regionTextView)
    TextView regionTextView;

    @BindView(R.id.subregionTextView)
    TextView subregionTextView;

    @BindView(R.id.populationTextView)
    TextView populationTextView;

    @BindView(R.id.alphaCodeTextView)
    TextView alphaCodeTextView;

    @BindView(R.id.numericCodeTextView)
    TextView numericCodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        nation = Objects.requireNonNull(getIntent().getExtras()).getParcelable(NATION);

        listingDetailsPresenter = new ListingDetailsPresenter(this);
        listingDetailsPresenter.showListingDetails(nation);
    }

    @Override
    public void loadListingDetails(Nation nation) {
        SvgLoader.pluck().with(this).setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher).load(nation.getFlag(), flagImageView);
        nameTextView.setText(nation.getName());
        nativeNameTextView.setText(nation.getNativeName());
        capitalTextView.setText(nation.getCapital());
        areaTextView.setText(nation.getArea());
        regionTextView.setText(nation.getRegion());
        subregionTextView.setText(nation.getSubregion());
        populationTextView.setText(nation.getPopulation());
        alphaCodeTextView.setText(nation.getAlpha2Code());
        numericCodeTextView.setText(nation.getNumericCode());
    }

    @Override
    public void errorLoadingListingDetails() {
        Toast.makeText(this, getString(R.string.error_while_loading_nation_details), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapButtonClicked(String latitude, String longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.valueOf(latitude), Float.valueOf(longitude));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listing_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.map:
                if (nation != null && nation.getLatlng() != null) {
                    String[] latlong = nation.getLatlng();
                    if (latlong != null) {
                        String latitude = latlong[0];
                        String longitude = latlong[1];
                        listingDetailsPresenter.onMapButtonClicked(latitude, longitude);
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
