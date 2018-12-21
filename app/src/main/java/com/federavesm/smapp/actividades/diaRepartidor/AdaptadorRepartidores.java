package com.federavesm.smapp.actividades.diaRepartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 27/2/2018.
 */



public class AdaptadorRepartidores extends BaseAdapter
{

    private LayoutInflater inflador;




    private TextView nombre;

    private List<Repartidor> repartidores = new ArrayList<Repartidor>();


    public AdaptadorRepartidores(Context context, List<Repartidor> repartidores)
    {
        this.repartidores = repartidores;
        inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null)
        {
            elementoListView = inflador.inflate(R.layout.srepartidores,null);
        }

        this.nombre = (TextView)elementoListView.findViewById(R.id.sRepartidoresNombre);
        Repartidor repartidor = this.repartidores.get(posicion);
        this.nombre.setText(repartidor.toString());



        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.repartidores.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.repartidores.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
