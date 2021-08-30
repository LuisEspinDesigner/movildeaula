package com.example.movildeaula.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movildeaula.Models.Notify;
import com.example.movildeaula.R;
import com.example.movildeaula.VisulaizarNotificacion;

import java.util.List;

public class ListanotifyDivice extends RecyclerView.Adapter<ListanotifyDivice.ViewHolder> implements View.OnClickListener {
    List<Notify> lista_notify;
    private LayoutInflater myinflater;
    private Context context;
    private android.view.View.OnClickListener listener;

    public ListanotifyDivice(List<Notify> lista_notify, Context context) {
        this.lista_notify = lista_notify;
        this.myinflater = LayoutInflater.from(context);
        this.context = context;
    }

    public ListanotifyDivice.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = myinflater.inflate(R.layout.card_notify, null);
        view.setOnClickListener(this);
        return new ListanotifyDivice.ViewHolder(view);
    }


    public void onBindViewHolder(ListanotifyDivice.ViewHolder holder, int position) {
        holder.bindData(lista_notify.get(position));
    }


    public int getItemCount() {
        return lista_notify.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView intruder, divice;

        public ViewHolder(android.view.View itemView) {
            super(itemView);
            intruder = itemView.findViewById(R.id.textIntruder);
            divice = itemView.findViewById(R.id.txtNombreDispositivo);

        }

        public void bindData(Notify notify) {
            intruder.setText(notify.getStatus());
            divice.setText(notify.getMac());
            if (intruder.getText().equals("Revisado")) {
                itemView.setBackgroundColor(Color.GREEN);
            } else {
                itemView.setBackgroundColor(Color.YELLOW);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VisulaizarNotificacion.class)
                            //.putExtra("NombreDivice",nombre.getText().toString());
                            .putExtra("imagen", notify.getImage());
                    context.startActivity(intent);
                }
            });
        }
    }
}
