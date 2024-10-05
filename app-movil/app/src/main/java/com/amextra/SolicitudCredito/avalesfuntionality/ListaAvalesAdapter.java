package com.amextra.SolicitudCredito.avalesfuntionality;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.amextra.io.Request.InformacionAval;

import java.util.ArrayList;

public class ListaAvalesAdapter extends ArrayAdapter<InformacionAval> {

    private final Context mContext;
    int mResource;
    private int lastPosition = -1;

    ListaAvalesAdapter.Listener listener;

    private static class ViewHolder {
        TextView nombreCompleto;
        TextView telefono;
        ImageButton delete;
        ImageButton edit;
        ImageButton confirmSms;


    }

    public ListaAvalesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<InformacionAval> identificacions) {
        super(context, resource, identificacions);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nc = getItem(position).getNombreCompleto();
        String pho = getItem(position).getTelefono();
        boolean confirm = getItem(position).isConfimaSms();
        int id = mContext.getResources().getIdentifier(confirm ? "ic_done_all_24" : "ic_sharp_close_24", "drawable", mContext.getPackageName());
        Drawable drawable = mContext.getDrawable(id);

        final View result;
        ListaAvalesAdapter.ViewHolder holder;
        this.listener = (ListaAvalesAdapter.Listener) getContext();


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ListaAvalesAdapter.ViewHolder();
            holder.nombreCompleto = convertView.findViewById(R.id.nombreCompleto);
            holder.telefono = convertView.findViewById(R.id.telefono);
            holder.delete = convertView.findViewById(R.id.delete);
            holder.edit = convertView.findViewById(R.id.edit);
            holder.confirmSms = convertView.findViewById(R.id.confirmSms);
            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ListaAvalesAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.nombreCompleto.setText(nc);
        holder.telefono.setText(pho);
        holder.confirmSms.setImageDrawable(drawable);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteItem(position);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editItem(position);
            }
        });

        return convertView;

    }

    public interface Listener {
        void deleteItem(int id);

        void editItem(int id);
    }

}
