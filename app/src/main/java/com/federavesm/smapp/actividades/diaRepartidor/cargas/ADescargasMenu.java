package com.federavesm.smapp.actividades.diaRepartidor.cargas;

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
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Carga;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Descarga;

/**
 * Created by Federico on 21/2/2018.
 */





public class ADescargasMenu extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adescargasmenu);



        adaptadorDescargas = new AdaptadorCargamentos<Descarga>(this,Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescargas());
        listViewDescargas = (ListView)findViewById(R.id.aDescargasMenuListViewDescargas);

        listViewDescargas.setAdapter(adaptadorDescargas);
        listViewDescargas.setOnItemClickListener(new ListenerItemClickListView(this));


        buttonIngresarDescarga =  (Button) findViewById(R.id.aDescargasMenuButtonIngresarDescarga);
        buttonIngresarDescarga.setOnClickListener(new ListenerClickButtonIngresarDescarga());

        this.buttonRetornar = (Button) findViewById(R.id.aDescargasMenuButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



        textViewBidones20L = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones20L);
        textViewBidones20LVacios = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones20LVacios);
        textViewBidones12L = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones12L);
        textViewBidones12LVacios = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones12LVacios);
        textViewBidones10L = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones10L);
        textViewBidones8L = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones8L);
        textViewBidones5L = (TextView)findViewById(R.id.aDescargasMenuTextViewBidones5L);
        textViewPackBotellas2L = (TextView)findViewById(R.id.aDescargasMenuTextViewPackBotellas2L);
        textViewPackBotellas500mL = (TextView)findViewById(R.id.aDescargasMenuTextViewPackBotellas500mL);



        textViewIncoherencias =  (TextView) findViewById(R.id.aDescargasTextViewIncoherencias);


        actualizarIncoherencias();
        actualizarCargamento();




    }

    private void actualizarIncoherencias()
    {
    if(Comunicador.getDiaRepartidor().getCargamento().getDescargas().evaluar() == false)
        {
        textViewIncoherencias.setVisibility(View.VISIBLE);
        textViewIncoherencias.setText("Incoherencias: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getEvaluar());
        }
    }


    private void actualizarCargamento()
    {
    textViewBidones20L.setText("Bidones 20L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getRetornables().getBidones20L());
    textViewBidones20LVacios.setText("Bidones 20L Vacios: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getRetornablesVacios().getBidones20L());
    textViewBidones12L.setText("Bidones 12L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getRetornables().getBidones12L());
    textViewBidones12LVacios.setText("Bidones 12L Vacios: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getRetornablesVacios().getBidones12L());
    textViewBidones10L.setText("Bidones 10L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescartables().getBidones10L());
    textViewBidones8L.setText("Bidones 8L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescartables().getBidones8L());
    textViewBidones5L.setText("Bidones 5L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescartables().getBidones5L());
    textViewPackBotellas2L.setText("Pack Botellas 2L: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescartables().getPackBotellas2L());
    textViewPackBotellas500mL.setText("Pack Botellas 500mL: " + Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescartables().getPackBotellas500mL());
    }


    private AdaptadorCargamentos<Descarga> adaptadorDescargas;
    private ListView listViewDescargas;
    private Button buttonIngresarDescarga;

    private TextView textViewIncoherencias;


    private TextView textViewBidones20L;
    private TextView textViewBidones20LVacios;
    private TextView textViewBidones12LVacios;
    private TextView textViewBidones12L;
    private TextView textViewBidones10L;
    private TextView textViewBidones8L;
    private TextView textViewBidones5L;
    private TextView textViewPackBotellas2L;
    private TextView textViewPackBotellas500mL;



    static class CodigosDescargasMenu extends CodigosActividades
    {
        public static int IngresarDescarga =3;
        public static int VerDescarga = 4;
        public static int VerDescargaEliminar = 5;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosDescargasMenu.IngresarDescarga == requestCode)
        {
        Comunicador.setNuevaDescarga(false);
        if(CodigosActividades.OK == resultCode)
            {
            adaptadorDescargas = new AdaptadorCargamentos<Descarga>(this,Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescargas());
            listViewDescargas.setAdapter(adaptadorDescargas);
            actualizarCargamento();
            actualizarIncoherencias();
            Dialogo.aceptarVacio("Atención!","La descarga se ingresó correctamente",this);
            }
        }
    else if(CodigosDescargasMenu.VerDescarga == requestCode)
        {
        if(CodigosDescargasMenu.VerDescargaEliminar == resultCode)
            {
            adaptadorDescargas = new AdaptadorCargamentos<Descarga>(this,Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescargas());
            listViewDescargas.setAdapter(adaptadorDescargas);
            actualizarIncoherencias();
            actualizarCargamento();
            Dialogo.aceptarVacio("Atención!","La descarga se eliminó correctamente",this);


            }
        }
        else
        {

        }




    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////


    ///// RETORNAR
    private Button buttonRetornar;

    class ListenerClickButtonRetornar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            retornar();
        }
    }


    private void retornar()
    {
        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }






    ////// INGRESAR DESCARGA

    class ListenerClickButtonIngresarDescarga implements View.OnClickListener
    {
        public void onClick(View e)
        {
            ingresarDescarga();
        }
    }

    private void ingresarDescarga()
    {
    Comunicador.setNuevaDescarga(true);
    Intent intentIngresarDescarga = new Intent(this,ADescarga.class);
    startActivityForResult(intentIngresarDescarga, CodigosDescargasMenu.IngresarDescarga);
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// VER DESCARGA



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
                Comunicador.setDescargaSeleccionada((Descarga)adaptadorDescargas.getItem(position));
                verDescarga();
            }
        }
    }


    private void verDescarga()
    {
    Intent intentVerDescarga = new Intent(this,AVerDescarga.class);
            startActivityForResult(intentVerDescarga, CodigosDescargasMenu.VerDescarga);
    }








}




