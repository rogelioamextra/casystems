package com.amextra.utils;

import android.content.Context;
import android.text.Html;
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
import com.amextra.io.Response.Aviso;

import java.util.ArrayList;

public class ListAvisosAdapter extends ArrayAdapter<Aviso> {

    private Context mContext;
    int mResource;
    private int lastPosition = -1;


    private static class ViewHolder {
        TextView tituloAviso;
        TextView contenidoAviso;


    }

    public ListAvisosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Aviso> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String contenidoAviso = getItem(position).getContenidoAviso();
        String tituloAviso = getItem(position).getTituloAviso();


        final View result;
        ListAvisosAdapter.ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListAvisosAdapter.ViewHolder();

            holder.contenidoAviso = convertView.findViewById(R.id.contenido);
            holder.tituloAviso = convertView.findViewById(R.id.titulo);


            result = convertView;

            convertView.setTag(holder);

        } else {
            holder = (ListAvisosAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.anim_left);
        result.startAnimation(animation);
        lastPosition = position;

        holder.contenidoAviso.setText(Html.fromHtml(contenidoAviso));
        holder.tituloAviso.setText(tituloAviso);


        return convertView;

    }

}
