package com.federavesm.smapp.actividades.diaRepartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 27/2/2018.
 */



public class AdaptadorVendedores extends BaseAdapter
{

    private LayoutInflater inflador;




    private TextView nombre;

    private List<Vendedor> vendedores = new ArrayList<Vendedor>();


    public AdaptadorVendedores(Context context, List<Vendedor> vendedores)
    {
        this.vendedores = vendedores;
        inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null)
        {
            elementoListView = inflador.inflate(R.layout.srepartidores,null);
        }

        this.nombre = (TextView)elementoListView.findViewById(R.id.sRepartidoresNombre);
        Vendedor vendedor = this.vendedores.get(posicion);
        this.nombre.setText(vendedor.toString());



        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.vendedores.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.vendedores.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
