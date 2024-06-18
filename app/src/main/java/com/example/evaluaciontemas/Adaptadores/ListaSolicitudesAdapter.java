package com.example.evaluaciontemas.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evaluaciontemas.R;
import com.example.evaluaciontemas.Solicitud;

import java.util.ArrayList;

public class ListaSolicitudesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Solicitud> solicitudes;
    LayoutInflater layoutInflater;
    Solicitud solicitud;

    public ListaSolicitudesAdapter(Context context, ArrayList<Solicitud> solicitudeds) {
        this.context = context;
        this.solicitudes = solicitudeds;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return solicitudes.size();
    }

    @Override
    public Object getItem(int i) {
        return solicitudes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.lista_solicitudes, null, true);
        }

        //Enlazar vistas
        TextView vPuesto = rowView.findViewById(R.id.vPuesto);
        TextView vNombre = rowView.findViewById(R.id.vNombre);
        TextView vFecha = rowView.findViewById(R.id.vFecha);

        solicitud = solicitudes.get(i);

        vPuesto.setText(solicitud.getPuesto());
        vNombre.setText(solicitud.getApellidoM() + " " + solicitud.getApellidoP() + " " + solicitud.getNombre());
        vFecha.setText(solicitud.getFecha());

        return rowView;
    }
}
