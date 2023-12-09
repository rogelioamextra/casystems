package com.amextra.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amextra.amextra.R;
import com.amextra.io.Response.ListaCliente;

import java.util.ArrayList;

public class ListaClienteAdapter extends ArrayAdapter<ListaCliente> {
    private Context mContext;
    int mResource;
    private int lastPosition = -1;
    private ArrayList<ListaCliente> originalList;
    private ArrayList<ListaCliente> clientList;
    private ListaClienteFilter filter;

    private static class ViewHolder {
        TextView nombre;
        TextView idCliente;
        TextView curp;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new ListaClienteFilter();
        }
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getNombre();
        String idCliente = getItem(position).getId();
        String curp = getItem(position).getCurp();

        ListaCliente cliente = new ListaCliente(name, idCliente, curp);
        final View result;
        ViewHolder holder;




        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.nombre = convertView.findViewById(R.id.nombre);
            holder.idCliente = convertView.findViewById(R.id.idCliente);
            holder.curp = convertView.findViewById(R.id.curp);

            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;


        holder.nombre.setText(name);
        holder.idCliente.setText(idCliente);
        holder.curp.setText(curp);

        return convertView;

    }

    public ListaClienteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListaCliente> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.clientList = new ArrayList<ListaCliente>();
        clientList.addAll(objects);
        this.originalList = new ArrayList<ListaCliente>();
        originalList.addAll(clientList);
    }


    private class ListaClienteFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toUpperCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<ListaCliente> filteredItems = new ArrayList<ListaCliente>();

                for(int i = 0, l = originalList.size(); i < l; i++)
                {
                    ListaCliente cliente = originalList.get(i);
                    if(cliente.toString().toUpperCase().contains(constraint)){
                        filteredItems.add(cliente);
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            clientList = (ArrayList<ListaCliente>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = clientList.size(); i < l; i++)
                add(clientList.get(i));
            notifyDataSetInvalidated();
        }
    }






}
