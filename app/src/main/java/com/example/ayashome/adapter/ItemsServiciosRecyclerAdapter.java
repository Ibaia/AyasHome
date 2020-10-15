package com.example.ayashome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayashome.R;
import com.example.ayashome.model.ItemsServicios;

import java.util.List;

public class ItemsServiciosRecyclerAdapter extends RecyclerView.Adapter<ItemsServiciosRecyclerAdapter.ItemsServiciosViewHolder> {

    private Context context;
    private List<ItemsServicios> itemsServiciosList;

    public ItemsServiciosRecyclerAdapter(Context context, List<ItemsServicios> itemsServiciosList) {
        this.context = context;
        this.itemsServiciosList = itemsServiciosList;
    }

    @NonNull
    @Override
    public ItemsServiciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsServiciosViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsServiciosViewHolder holder, int position) {

        holder.itemImage.setImageResource(itemsServiciosList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() { return itemsServiciosList.size();  }

    public class ItemsServiciosViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;

        public ItemsServiciosViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
