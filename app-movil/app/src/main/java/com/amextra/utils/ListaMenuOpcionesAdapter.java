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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.amextra.R;
import com.amextra.io.Request.MenuOption;

import java.util.ArrayList;

public class ListaMenuOpcionesAdapter extends ArrayAdapter<MenuOption> {

    private final Context mContext;
    int mResource;
    private int lastPosition = -1;
    ListaMenuOpcionesAdapter.Listener listener;


    private static class ViewHolder {
        TextView etiqueta;
        ImageView icono;
        LinearLayout rowMenu;

    }

    public ListaMenuOpcionesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuOption> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String etiqueta = getItem(position).getLabel();
        String icon = getItem(position).getIcon();
        String to = (getItem(position)).getOptionId();

        int id = mContext.getResources().getIdentifier(icon, "drawable", mContext.getPackageName());
        Drawable drawable = mContext.getDrawable(id);

        final View result;
        ListaMenuOpcionesAdapter.ViewHolder holder;

        this.listener = (ListaMenuOpcionesAdapter.Listener) getContext();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaMenuOpcionesAdapter.ViewHolder();

            holder.etiqueta = convertView.findViewById(R.id.text);
            holder.icono = convertView.findViewById(R.id.icon);
            holder.rowMenu = convertView.findViewById(R.id.rowMenu);


            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ListaMenuOpcionesAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_left);
        result.startAnimation(animation);
        lastPosition = position;
        holder.etiqueta.setText(etiqueta);
        holder.icono.setImageDrawable(drawable);
        holder.rowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.generaRegistro(position, to);
            }
        });


        return convertView;

    }

    public interface Listener {
        void generaRegistro(long id, String label);
    }

}
