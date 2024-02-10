package com.amextra.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.amextra.R;
import com.amextra.io.Response.ListaProyeccion;

import java.util.ArrayList;

public class ListaProyeccionAdapter extends ArrayAdapter<ListaProyeccion> {

    private Context mContext;
    int mResource;
    private int lastPosition = -1;
    private ListaProyeccion[] originalList;
    private ListaProyeccion[]  proyectList;

    private static class ViewHolder {
        TextView cuota;
        TextView capital;
        TextView interes;
        TextView iva;
        TextView saldo;
        TextView no;

    }

    public ListaProyeccionAdapter(@NonNull Context context, int resource, @NonNull  ArrayList<ListaProyeccion>   objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String no =  getItem(position).getN();
        String cuota = getItem(position).getCuota();
        String capital = getItem(position).getCapital();
        String interes = getItem(position).getInteres();
        String iva = getItem(position).getIva();
        String saldo = getItem(position).getSaldo();


        final View result;
        ListaProyeccionAdapter.ViewHolder holder;




        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaProyeccionAdapter.ViewHolder();
            holder.capital = convertView.findViewById(R.id.capital);
            holder.no = convertView.findViewById(R.id.no);
            holder.saldo = convertView.findViewById(R.id.saldo);
            holder.iva = convertView.findViewById(R.id.iva);
            holder.cuota = convertView.findViewById(R.id.cuota);
            holder.interes = convertView.findViewById(R.id.interes);

            result = convertView;

            convertView.setTag(holder);

        } else {
            holder = (ListaProyeccionAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.cuota.setText(cuota);
        holder.capital.setText(capital);
        holder.interes.setText(interes);
        holder.saldo.setText(saldo);
        holder.iva.setText(iva);
        holder.no.setText(no);


        return convertView;

    }

}
