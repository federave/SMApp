package com.federavesm.smapp.actividades.diaRepartidor;

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
 * Created by Federico on 12/3/2018.
 */


public class AdaptadorGenerico<T> extends BaseAdapter
{

    private LayoutInflater inflador;


    private TextView textView;

    private List<T> items = new ArrayList<T>();


    public AdaptadorGenerico(Context context, List<T> items)
    {
    this.items = items;
    inflador = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int posicion, View elementoListView, ViewGroup padre) {


        if(elementoListView == null){elementoListView = inflador.inflate(R.layout.lgenerico,null);}

        this.textView = (TextView)elementoListView.findViewById(R.id.lGenericoTextView);
        T item = this.items.get(posicion);
        this.textView.setText(item.toString());

        return elementoListView;
    }



    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int posicion) {
        return this.items.get(posicion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
