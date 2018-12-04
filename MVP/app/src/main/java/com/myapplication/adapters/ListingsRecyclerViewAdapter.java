package com.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.myapplication.R;
import com.myapplication.activities.ListingDetailsActivity;
import com.myapplication.models.Nation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.myapplication.utils.Constants.NATION;

public class ListingsRecyclerViewAdapter extends RecyclerView.Adapter<ListingsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = ListingsRecyclerViewAdapter.class.getSimpleName();

    private Context context;
    private List<Nation> nationsData;
    private LayoutInflater layoutInflater;

    public ListingsRecyclerViewAdapter(Context context, List<Nation> nationsData) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.nationsData = nationsData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_listings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Nation nation = nationsData.get(position);
        holder.textView.setText(nation.getName());
        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(nation.getFlag(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return nationsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        @BindView(R.id.imageView)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListingDetailsActivity.class);
                    intent.putExtra(NATION, getItem(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

    Nation getItem(int id) {
        return nationsData.get(id);
    }
}