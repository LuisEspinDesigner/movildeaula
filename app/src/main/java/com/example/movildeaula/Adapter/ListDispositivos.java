package com.example.movildeaula.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movildeaula.Models.Divice;
import com.example.movildeaula.R;
import com.example.movildeaula.VisualizacionDispositivo;

import java.util.List;

public class ListDispositivos extends RecyclerView.Adapter<ListDispositivos.ViewHolder> implements View.OnClickListener {
    List<Divice> ListDivice;
    private LayoutInflater myinflater;
    private Context context;
    private View.OnClickListener listener;

    public ListDispositivos(List<Divice> listDivice, Context context) {
        this.myinflater = LayoutInflater.from(context);
        this.ListDivice = listDivice;
        this.context = context;
    }

    @Override
    public ListDispositivos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = myinflater.inflate(R.layout.card_divice, null);
        view.setOnClickListener(this);
        return new ListDispositivos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListDispositivos.ViewHolder holder, final int position) {
        holder.bindData(ListDivice.get(position));
    }

    public void setItems(List<Divice> items) {
        ListDivice = items;
    }

    @Override
    public int getItemCount() {
        return ListDivice.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, mac, ubicacion;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtdiviceNombre);
            mac = itemView.findViewById(R.id.txtdiviceMac);
            ubicacion = itemView.findViewById(R.id.txtdiviceUbicacion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VisualizacionDispositivo.class);
                    context.startActivity(intent);
                }
            });
        }

        public void bindData(Divice divice) {
            nombre.setText(divice.getNombre());
            mac.setText(divice.getMac());
            ubicacion.setText(divice.getUbicacion());
        }
    }
}
