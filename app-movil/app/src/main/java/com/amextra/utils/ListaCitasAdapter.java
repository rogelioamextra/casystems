package com.amextra.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.Beans.ImagesIdentificacion;
import com.amextra.amextra.R;
import com.amextra.io.Response.Agenda;
import com.amextra.io.Response.ListaProyeccion;

import java.util.ArrayList;

public class ListaCitasAdapter extends ArrayAdapter<Agenda> {

    private Context mContext;
    int mResource;
    private int lastPosition = -1;

    Listener listener;
    private static class ViewHolder {
        TextView fecha;
        TextView codCliente;
        TextView codAsesor;
        TextView comentario;
        Button registrar;
        Button posponer;
    }

    public ListaCitasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Agenda> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String fecha = getItem(position).getFecha();
        String codCliente = getItem(position).getCodCliente();
        String codAsesor = getItem(position).getCodAsesor();
        String comentario = getItem(position).getComentario();


        final View result;
        ListaCitasAdapter.ViewHolder holder;

        this.listener = (Listener) getContext();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaCitasAdapter.ViewHolder();

            holder.codAsesor = convertView.findViewById(R.id.codAsesor);
            holder.fecha = convertView.findViewById(R.id.fecha);
            holder.codCliente = convertView.findViewById(R.id.codCliente);
            holder.comentario = convertView.findViewById(R.id.comentario);
            holder.posponer =convertView.findViewById(R.id.posponer);
            holder.registrar =convertView.findViewById(R.id.registrar);

            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ListaCitasAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.codAsesor.setText(codAsesor);
        holder.fecha.setText(fecha);
        holder.codCliente.setText(codCliente);
        holder.comentario.setText(comentario);

        holder.registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clickbtn", "onClick: "+getItem(position).getIDAgenda());
                listener.generaRegistro(getItem(position).getIDAgenda(),"1");
            }
        });

        holder.posponer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.generaRegistro(getItem(position).getIDAgenda(),"2");
            }
        });
        return convertView;

    }


    public interface Listener {
        public void generaRegistro(long id,String tipo);
    }
}