package com.federavesm.smapp.actividades.diaRepartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 10/3/2018.
 */


public class AdaptadorVisitas extends BaseAdapter
{

    private LayoutInflater inflador;




    private TextView textViewTipoVisita;

    private List<TipoVisita> visitas = new ArrayList<TipoVisita>();


    public AdaptadorVisitas(Context context, List<TipoVisita> visitas)
    {
        this.visitas = visitas;
        inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null)
        {
            elementoListView = inflador.inflate(R.layout.svisitas,null);
        }

        this.textViewTipoVisita = (TextView)elementoListView.findViewById(R.id.sVisitasTipoVisita);
        TipoVisita tipoVisita = this.visitas.get(posicion);
        this.textViewTipoVisita.setText(tipoVisita.getTipoVisita());



        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.visitas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.visitas.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
