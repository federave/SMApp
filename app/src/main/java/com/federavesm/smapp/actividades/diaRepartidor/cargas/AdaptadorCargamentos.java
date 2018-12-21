package com.federavesm.smapp.actividades.diaRepartidor.cargas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.federavesm.smapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 22/2/2018.
 */


public class AdaptadorCargamentos<T> extends BaseAdapter
{

    private LayoutInflater inflador;


    private TextView textViewCargamento;

    private List<T> cargamentos = new ArrayList<T>();


    public AdaptadorCargamentos(Context context, List<T> cargamentos)
    {
    this.cargamentos = cargamentos;
    inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


    if(elementoListView == null){elementoListView = inflador.inflate(R.layout.lcargamentos,null);}

    this.textViewCargamento = (TextView)elementoListView.findViewById(R.id.lCargamentoNombre);
    T cargamento = this.cargamentos.get(posicion);
    this.textViewCargamento.setText(cargamento.toString());

    return elementoListView;
    }



    @Override
    public int getCount() {
        return this.cargamentos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.cargamentos.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
