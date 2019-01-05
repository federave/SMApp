package com.federavesm.smapp.actividades.clientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.ClienteBusqueda;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 19/2/2018.
 */

public class AdaptadorClientes extends BaseAdapter
{

    private LayoutInflater inflador;


    private ImageView tipoCliente;


    private TextView nombre;
    private TextView direccion;



    private List<ClienteBusqueda> clientes = new ArrayList<ClienteBusqueda>();


    public AdaptadorClientes(Context context, List<ClienteBusqueda> clientes)
    {
    this.clientes = clientes;
    inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null)
        {
            elementoListView = inflador.inflate(R.layout.lclientes,null);
        }

        try
        {
            this.tipoCliente = (ImageView) elementoListView.findViewById(R.id.lClientesTipo);

            this.nombre = (TextView)elementoListView.findViewById(R.id.lClientesNombre);
            this.direccion = (TextView)elementoListView.findViewById(R.id.lClientesDireccion);





            ClienteBusqueda cliente = this.clientes.get(posicion);

            this.nombre.setText(cliente.getNombre()+" Id: "+cliente.getIdCliente());
            this.direccion.setText(cliente.getDireccion());


            this.tipoCliente.setImageResource(cliente.getRecursoImagen());


        }
        catch (Exception e)
        {
            String e2=e.toString();

        }




        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.clientes.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.clientes.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
