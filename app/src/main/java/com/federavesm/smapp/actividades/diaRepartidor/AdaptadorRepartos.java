package com.federavesm.smapp.actividades.diaRepartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 19/2/2018.
 */

public class AdaptadorRepartos extends BaseAdapter
{

    private LayoutInflater inflador;


    private ImageView tipoCliente;


    private TextView nombre;
    private TextView direccion;

    private ImageView llamado;
    private ImageView visita;
    private ImageView inactividad;
    private ImageView pagoalquiler;
    private ImageView entrega;
    private ImageView vacios;


    private List<Reparto> repartos = new ArrayList<Reparto>();


    public AdaptadorRepartos(Context context, List<Reparto> repartos)
    {
    this.repartos = repartos;
    inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null)
        {
            elementoListView = inflador.inflate(R.layout.lrepartos,null);
        }

        try
        {
            this.tipoCliente = (ImageView) elementoListView.findViewById(R.id.lRepartosTipo);

            this.nombre = (TextView)elementoListView.findViewById(R.id.lRepartosNombre);
            this.direccion = (TextView)elementoListView.findViewById(R.id.lRepartosDireccion);

            this.visita = (ImageView) elementoListView.findViewById(R.id.lRepartosVisita);
            this.llamado = (ImageView) elementoListView.findViewById(R.id.lRepartosLlamado);

            this.inactividad = (ImageView) elementoListView.findViewById(R.id.lRepartosInactividad);

            this.pagoalquiler = (ImageView) elementoListView.findViewById(R.id.lRepartosPagoAlquiler);
            this.entrega = (ImageView) elementoListView.findViewById(R.id.lRepartosEntrega);
            this.vacios = (ImageView) elementoListView.findViewById(R.id.lRepartosVacios);




            Reparto reparto = this.repartos.get(posicion);

            this.nombre.setText(reparto.getCliente().getDatos().toString()+" Id: "+reparto.getCliente().getDatos().getId());
            this.direccion.setText(reparto.getCliente().getDireccion().toString());

            this.pagoalquiler.setVisibility(View.VISIBLE);
            if(reparto.getTipoVisita().getPagoAlquiler())
                this.pagoalquiler.setImageResource(reparto.getTipoVisita().getRecursoPagoAlquiler());
            else
                this.pagoalquiler.setVisibility(View.GONE);

            this.entrega.setVisibility(View.VISIBLE);
            if(reparto.getTipoVisita().getEntrega())
                this.entrega.setImageResource(reparto.getTipoVisita().getRecursoEntrega());
            else
                this.entrega.setVisibility(View.GONE);


            this.vacios.setVisibility(View.VISIBLE);
            if(reparto.getTipoVisita().getVacios())
                this.vacios.setImageResource(reparto.getTipoVisita().getRecursoVacios());
            else
                this.vacios.setVisibility(View.GONE);





            if(reparto.getTipoVisita().getRecursoImagen()!=-1)
            {this.visita.setImageResource(reparto.getTipoVisita().getRecursoImagen());}
            else
            {this.visita.setImageResource(reparto.getTipoVisita().getRecursoImagen());}

            if(reparto.getCliente().getDatos().getTipoCliente().getRecursoImagen()!=-1){this.tipoCliente.setImageResource(reparto.getCliente().getDatos().getTipoCliente().getRecursoImagen());}
            if(reparto.getCliente().getEstadoInactividad().getTipoInactivo().getRecursoImagen()!=-1){this.inactividad.setImageResource(reparto.getCliente().getEstadoInactividad().getTipoInactivo().getRecursoImagen());}

            if(reparto.getFueraDeRecorrido().getTipoFueraDeRecorrido().getRecursoImagen()!=-1)
            {
                this.llamado.setVisibility(View.VISIBLE);
                this.llamado.setImageResource(reparto.getFueraDeRecorrido().getTipoFueraDeRecorrido().getRecursoImagen());
            }
            else
            {
                this.llamado.setVisibility(View.GONE);
            }

        }
        catch (Exception e)
        {
            String e2=e.toString();

        }




        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.repartos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.repartos.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
