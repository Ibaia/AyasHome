package com.dosdmtres.ayashome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dosdmtres.ayashome.Datos;
import com.dosdmtres.ayashome.R;
import com.dosdmtres.ayashome.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.LogRecord;

public class ItemsServiciosRecyclerAdapter extends RecyclerView.Adapter<ItemsServiciosRecyclerAdapter.ItemsServiciosViewHolder> {

    private final Context context;
    public List<Items> itemsList;


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
    public void onBindViewHolder(@NonNull ItemsServiciosViewHolder holder, int position)
    {
        final int fPosition = position;

        LoadImg loadImg = new LoadImg(holder, fPosition);

        new Thread(loadImg).start();

        holder.itemImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, Datos.class);
                intent.putExtra("NOMBRE", itemsList.get(fPosition).getNombre());
                intent.putExtra("DESCRIPCION", itemsList.get(fPosition).getDescripcion());
                intent.putExtra("PRECIO", itemsList.get(fPosition).getPrecio());
                intent.putExtra("IMAGEN",itemsList.get(fPosition).getImageLarge());

                context.startActivity(intent);
            }
        });
        holder.nombreItem.setText(itemsList.get(position).getNombre());
    }

    class LoadImg implements Runnable
    {
        ItemsServiciosViewHolder holder;
        int fPosition;

        public LoadImg(ItemsServiciosViewHolder holder, int fPosition)
        {
            this.holder = holder;
            this.fPosition = fPosition;
        }

        @Override
        public void run()
        {
            Handler threadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
            threadHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Picasso.get().load(itemsList.get(fPosition).getImageMini())
                                .fit()
                                .centerCrop()
                                .into(holder.itemImage);
                    } catch (Exception e) {
                        Log.e("ERROR", e.getMessage());
                        holder.itemImage.setImageResource(R.drawable.logo_icono);
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() { return itemsList.size();  }

    public static class ItemsServiciosViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView nombreItem;

        public ItemsServiciosViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            nombreItem = itemView.findViewById(R.id.nombre_item);
        }
    }
}
