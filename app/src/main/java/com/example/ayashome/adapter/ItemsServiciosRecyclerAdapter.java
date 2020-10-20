package com.example.ayashome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayashome.R;
import com.example.ayashome.model.Items;

import java.util.List;

public class ItemsServiciosRecyclerAdapter extends RecyclerView.Adapter<ItemsServiciosRecyclerAdapter.ItemsServiciosViewHolder> {

    private Context context;
    private List<Items> itemsList;

    public ItemsServiciosRecyclerAdapter(Context context, List<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemsServiciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsServiciosViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsServiciosViewHolder holder, int position) {
        // pick the image and name of to each item and show it
        holder.itemImage.setImageResource(itemsList.get(position).getImageMini());
        holder.nombreItem.setText(itemsList.get(position).getNombre());
    }

    @Override
    public int getItemCount() { return itemsList.size();  }

    public class ItemsServiciosViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView nombreItem;

        public ItemsServiciosViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            nombreItem = itemView.findViewById(R.id.nombre_item);


        }
    }
}
