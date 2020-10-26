package com.dosdmtres.ayashome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dosdmtres.ayashome.R;
import com.dosdmtres.ayashome.model.Items;
import com.dosdmtres.ayashome.model.Servicios;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {


   private final Context context;
   private final List<Servicios> todosServicios;


    public MainRecyclerAdapter(Context context, List<Servicios> todosServicios) {
        this.context = context;
        this.todosServicios = todosServicios;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.servicios, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.nombreServicio.setText(todosServicios.get(position).getNombreServicio());
        setArrayListServicios(holder.rvServicios, todosServicios.get(position).getItemsArrayList());
    }

    @Override
    public int getItemCount() {

        return todosServicios.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        TextView nombreServicio;
        RecyclerView rvServicios;



        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreServicio = itemView.findViewById(R.id.nombre_servicio);
            rvServicios = itemView.findViewById(R.id.rvServicios);
        }
    }

    private void setArrayListServicios (RecyclerView recyclerView, List<Items> itemsArrayList) {

        ItemsServiciosRecyclerAdapter itemsServiciosRecyclerAdapter = new ItemsServiciosRecyclerAdapter(context, itemsArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemsServiciosRecyclerAdapter);
    }

}
