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
import com.amextra.io.Response.DtReferenciasPersonalesList;
import com.amextra.io.Response.ListaCliente;

public class ListaReferenciasAdapter extends ArrayAdapter <DtReferenciasPersonalesList>  {
    private Context mContext;
    int mResource;
    private int lastPosition = -1;
    private static class ViewHolder {

        TextView referencia;
        TextView nombre;
        TextView telefono;
        TextView direccion;
        TextView parentescco;
    }

    public ListaReferenciasAdapter(@NonNull Context context, int resource, @NonNull DtReferenciasPersonalesList[] objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getNombreCompleto()+" "+getItem(position).getApellidoPaterno()+" "+getItem(position).getApellidoMaterno();
        String telefono = getItem(position).getTelefono();
        String parentesco = getItem(position).idParentesco.nombre;
        String direccion = getItem(position).idDireccion.calle+" Num Ext : "
                +getItem(position).idDireccion.numeroExterior+" Num Int : "
                +getItem(position).idDireccion.numeroInterior+ " Estado : "
                +getItem(position).idDireccion.idEstado.nombre+" ,Municipio : "
                +getItem(position).idDireccion.idMunicipio.nombre+", Colonia : "
                +getItem(position).idDireccion.idColonia.nombre+" ,CP : "
                +getItem(position).idDireccion.cp;
        //String curp = getItem(position).getCurp();

        final View result;
        ListaReferenciasAdapter.ViewHolder holder;




        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaReferenciasAdapter.ViewHolder();
            holder.nombre = convertView.findViewById(R.id.nombre);
            holder.direccion = convertView.findViewById(R.id.direccion);
            holder.parentescco = convertView.findViewById(R.id.parentescco);
            holder.referencia = convertView.findViewById(R.id.referencia);
            holder.telefono = convertView.findViewById(R.id.telefono);

            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ListaReferenciasAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.referencia.setText("Referencia "+(position+1));
        holder.nombre.setText(name);
        holder.telefono.setText(telefono);
        holder.direccion.setText(direccion);
        holder.parentescco.setText(parentesco);


        return convertView;

    }

}
