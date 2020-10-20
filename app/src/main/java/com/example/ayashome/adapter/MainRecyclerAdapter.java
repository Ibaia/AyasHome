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
   private List<Servicios> serviciosList;


    public MainRecyclerAdapter(Context context, List<Servicios> arrayListServicios) {
        this.context = context;
        this.serviciosList = arrayListServicios;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.servicios, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.nombreServicio.setText(serviciosList.get(position).getNombreServicio());
        setArrayListServicios(holder.rvServicios, serviciosList.get(position).getItemsArrayList());
    }

    @Override
    public int getItemCount() {

        return serviciosList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

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
