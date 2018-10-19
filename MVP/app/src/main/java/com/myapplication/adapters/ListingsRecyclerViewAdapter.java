package com.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.myapplication.R;
import com.myapplication.models.Nation;

import java.util.List;

public class ListingsRecyclerViewAdapter extends RecyclerView.Adapter<ListingsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Nation> nationsData;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Nation getItem(int id) {
        return nationsData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}