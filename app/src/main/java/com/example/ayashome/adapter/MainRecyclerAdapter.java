package com.example.ayashome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayashome.R;
import com.example.ayashome.model.Items;
import com.example.ayashome.model.Servicios;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {


   private Context context;
   private List<Servicios> todosServicios;


    public MainRecyclerAdapter(Context context, List<Servicios> arrayListServicios) {
        this.context = context;
        this.todosServicios = arrayListServicios;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.nombreServicio.setText(todosServicios.get(position).getNombreServicio());
        setArrayListServicios(holder.itemRecycler, todosServicios.get(position).getItemsArrayList());
    }

    @Override
    public int getItemCount() {

        return todosServicios.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView nombreServicio;
        RecyclerView itemRecycler;



        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreServicio = itemView.findViewById(R.id.nombre_servicio);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }

    private void setArrayListServicios (RecyclerView recyclerView, List<Items> itemsArrayList) {

        ItemsServiciosRecyclerAdapter itemsServiciosRecyclerAdapter = new ItemsServiciosRecyclerAdapter(context, itemsArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemsServiciosRecyclerAdapter);
    }

}
