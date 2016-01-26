package com.rubino.pmdm4_broadcastreciver.recView;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubino.pmdm4_broadcastreciver.R;
import com.rubino.pmdm4_broadcastreciver.llamadas.Llamada;


/**
 * Created by marco on 03/12/2015.
 */
public class AdaptadorRv extends RecyclerView.Adapter<AdaptadorRv.ViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private Cursor datos;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        itemView.setOnClickListener(this);
        //android:background="?android:attr/selectableItemBackground"

        ViewHolder tvh = new ViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        datos.moveToPosition(pos);
        Llamada item = new Llamada(datos.getString(1),datos.getString(2));

        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return datos.getCount();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


/**********************************Clase ViewHolder************************************************/

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private TextView tvNombre;
        private TextView tvEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNombre = (TextView)itemView.findViewById(R.id.LblNombre);
            tvEmail = (TextView)itemView.findViewById(R.id.LblEmail);
        }

        public void bind(Llamada t) {
            tvNombre.setText(t.getNumero());
            tvEmail.setText(t.getFecha());
        }
    }

    public AdaptadorRv(Cursor datos) {
        this.datos = datos;
    }
}
