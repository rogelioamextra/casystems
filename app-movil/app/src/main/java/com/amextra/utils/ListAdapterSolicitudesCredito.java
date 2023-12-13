package com.amextra.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.amextra.R;
import com.amextra.io.Response.ListaProyeccion;
import com.amextra.io.Response.SolicitudesCredito;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListAdapterSolicitudesCredito extends ArrayAdapter<SolicitudesCredito> {

    DecimalFormat formatter = new DecimalFormat("###,###.###");

    private Context mContext;
    int mResource;
    private int lastPosition = -1;
    private ListaProyeccion[] originalList;
    private ListaProyeccion[]  proyectList;

    private static class ViewHolder {
        TextView producto;
        TextView fecha;
        TextView idSolicitud;
        TextView estatus;
        ImageView imgStatus;
        TextView monto;


    }

    public ListAdapterSolicitudesCredito(@NonNull Context context, int resource, @NonNull ArrayList<SolicitudesCredito> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        final View result;
        ListAdapterSolicitudesCredito.ViewHolder holder;

        String producto = getItem(position).producto;
        String fecha = getItem(position).fecha;
        Long idSolicitud = getItem(position).idSolicitud;
        String estatus = getItem(position).estatus;
        String monto = getItem(position).monto;




        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListAdapterSolicitudesCredito.ViewHolder();
            holder.producto = convertView.findViewById(R.id.producto);
            holder.fecha = convertView.findViewById(R.id.fecha);
            holder.idSolicitud = convertView.findViewById(R.id.idSolicitud);
            holder.estatus = convertView.findViewById(R.id.estatus);
            holder.imgStatus = convertView.findViewById(R.id.imgStatus);
            holder.monto = convertView.findViewById(R.id.monto);

            result = convertView;

            convertView.setTag(holder);

        } else {
            holder = (ListAdapterSolicitudesCredito.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;


        holder.idSolicitud.setText(String.valueOf(idSolicitud));
        holder.fecha.setText(fecha.substring(0,10));
        holder.monto.setText(monto);
        holder.estatus.setText(estatus);
        holder.producto.setText(producto);
        Drawable ok = mContext.getDrawable(R.drawable.ic_playlist_add_check_circle);
        Drawable error = mContext.getDrawable(R.drawable.ic_nearby_error_24);
        Drawable pending = mContext.getDrawable(R.drawable.ic_pending_actions);
        if(estatus.equals("ACEPTADA")){

            holder.imgStatus.setImageDrawable(ok);
            
        } else if (estatus.equals("PENDIENTE")) {
            holder.imgStatus.setImageDrawable(pending);
        }else{
            holder.imgStatus.setImageDrawable(error);
        }

        return convertView;

    }
}
