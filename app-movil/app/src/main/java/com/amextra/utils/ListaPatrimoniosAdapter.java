package com.amextra.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.amextra.R;
import com.amextra.io.Response.ListaPatrimonio;


import java.util.ArrayList;

public class ListaPatrimoniosAdapter extends ArrayAdapter<ListaPatrimonio> {

    private Context mContext;
    int mResource;
    private int lastPosition = -1;

    Listener listener;
    private static class ViewHolder {
        TextView namePat;
        TextView montoPat;
        ImageButton deletePat;
        ImageButton editPat;


    }
    public ListaPatrimoniosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListaPatrimonio> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String monto =  getItem(position).getMonto();
        String name = getItem(position).getNombre();
        final View result;
        ListaPatrimoniosAdapter.ViewHolder holder;
        this.listener = (ListaPatrimoniosAdapter.Listener) getContext();


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaPatrimoniosAdapter.ViewHolder();
            holder.namePat = convertView.findViewById(R.id.namePat);
            holder.montoPat = convertView.findViewById(R.id.montoPat);
            holder.deletePat = convertView.findViewById(R.id.deletePat);
            holder.editPat = convertView.findViewById(R.id.editPat);
            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ListaPatrimoniosAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.namePat.setText(name);
        holder.montoPat.setText(monto);
        holder.deletePat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteItem(position);
            }
        });

        holder.editPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editItem(position);
            }
        });

        return convertView;

    }
    public interface Listener {
        public void deleteItem(int id);
        public void editItem(int id);
    }

}
