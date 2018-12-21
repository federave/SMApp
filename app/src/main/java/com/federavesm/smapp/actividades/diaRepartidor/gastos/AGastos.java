package com.federavesm.smapp.actividades.diaRepartidor.gastos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorGenerico;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gasto;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gastos;

/**
 * Created by Federico on 12/3/2018.
 */


public class AGastos extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agastos);



        this.buttonRetornar = (Button) findViewById(R.id.aGastosButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonIngresarGasto = (Button) findViewById(R.id.aGastosButtonIngresarGasto);
        this.buttonIngresarGasto.setOnClickListener(new ListenerClickButtonIngresarGasto());



        this.gastos = Comunicador.getDiaRepartidor().getGastos();


        adaptadorGenerico = new AdaptadorGenerico<Gasto>(this,this.gastos.getGastos());
        listViewGastos = (ListView)findViewById(R.id.aGastosListViewGastos);

        listViewGastos.setAdapter(adaptadorGenerico);

        listViewGastos.setOnItemClickListener(new ListenerItemClickListView(this));

        textViewDineroTotal = (TextView) findViewById(R.id.aGastosTextViewDineroTotal);

        if(gastos.getGastos().size()>0)
            textViewDineroTotal.setText("Dinero total gastado: "+this.gastos.getDineroTotal() + " $");
        else
            textViewDineroTotal.setText("Dinero total gastado: ");



    }

    private TextView textViewDineroTotal;
    private ListView listViewGastos;

    private Button buttonIngresarGasto;

    private AdaptadorGenerico<Gasto> adaptadorGenerico;

    private Gastos gastos;




    public int n1=-1;


    class ListenerItemClickListView implements AdapterView.OnItemClickListener
    {

        public ListenerItemClickListView(Context activity)
        {
            this.activity = activity;
        }

        private Context activity;

        @Override
        public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3)
        {
            if(n1!=position)
            {
                n1=position;
            }
            else
            {
            n1=-1;
            Comunicador.setGasto((Gasto)adaptadorGenerico.getItem(position));
            Comunicador.setNuevoGasto(false);
            verGasto();
            }
        }
    }


    private void verGasto()
    {
    Intent intentVerGasto = new Intent(this,AGasto.class);
    startActivityForResult(intentVerGasto, CodigosGastos.VerGasto);
    }



    static class CodigosGastos extends CodigosActividades
    {
    public static int IngresarGasto =3;
    public static int GastoNuevo = 4;
    public static int VerGasto = 5;
    public static int GastoModificado = 6;
    public static int GastoEliminado=7;
    }






    ////// INGRESAR CARGA

    class ListenerClickButtonIngresarGasto implements View.OnClickListener
    {
    public void onClick(View e)
        {
            ingresarGasto();
        }
    }

    private void ingresarGasto()
    {
    Comunicador.setNuevoGasto(true);
    Intent intentIngresarGasto = new Intent(this,AGasto.class);
    startActivityForResult(intentIngresarGasto, CodigosGastos.IngresarGasto);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosGastos.IngresarGasto == requestCode)
        {
        if(CodigosGastos.GastoNuevo == resultCode)
            {
            listViewGastos.setAdapter(adaptadorGenerico);
            }
        }
    else if(CodigosGastos.VerGasto == requestCode)
        {
        if(CodigosGastos.GastoModificado == resultCode)
            {
            listViewGastos.setAdapter(adaptadorGenerico);
            }
        if(CodigosGastos.GastoEliminado == resultCode)
            {
            listViewGastos.setAdapter(adaptadorGenerico);
            }
        }
    else
        {

        }

    if(gastos.getGastos().size()>0)
        textViewDineroTotal.setText("Dinero total gastado: "+this.gastos.getDineroTotal() + " $");
    else
        textViewDineroTotal.setText("Dinero total gastado: ");


    }






}
