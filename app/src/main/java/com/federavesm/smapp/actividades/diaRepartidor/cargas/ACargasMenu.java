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

/**
 * Created by Federico on 21/2/2018.
 */


public class ACargasMenu extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acargasmenu);



                this.diaRepartidor = Comunicador.getDiaRepartidor();


                adaptadorCargas = new AdaptadorCargamentos<Carga>(this,this.diaRepartidor.getCargamento().getCargas().getCargas());
                listViewCargas = (ListView)findViewById(R.id.aCargasMenuListViewCargas);

                listViewCargas.setAdapter(adaptadorCargas);
                listViewCargas.setOnItemClickListener(new ListenerItemClickListView(this));


                buttonIngresarCarga =  (Button) findViewById(R.id.aCargasMenuButtonIngresarCarga);
                buttonIngresarCarga.setOnClickListener(new ListenerClickButtonIngresarCarga());

                this.buttonRetornar = (Button) findViewById(R.id.aCargasMenuButtonRetornar);
                this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

                textViewBidones20L = (TextView)findViewById(R.id.aCargasMenuTextViewBidones20L);
                textViewBidones12L = (TextView)findViewById(R.id.aCargasMenuTextViewBidones12L);
                textViewBidones10L = (TextView)findViewById(R.id.aCargasMenuTextViewBidones10L);
                textViewBidones8L = (TextView)findViewById(R.id.aCargasMenuTextViewBidones8L);
                textViewBidones5L = (TextView)findViewById(R.id.aCargasMenuTextViewBidones5L);
                textViewPackBotellas2L = (TextView)findViewById(R.id.aCargasMenuTextViewPackBotellas2L);
                textViewPackBotellas500mL = (TextView)findViewById(R.id.aCargasMenuTextViewPackBotellas500mL);

                actualizarCargamento();







    }

    private DiaRepartidor diaRepartidor;

    private AdaptadorCargamentos<Carga> adaptadorCargas;
    private ListView listViewCargas;
    private Button buttonIngresarCarga;



    private TextView textViewBidones20L;
    private TextView textViewBidones12L;
    private TextView textViewBidones10L;
    private TextView textViewBidones8L;
    private TextView textViewBidones5L;
    private TextView textViewPackBotellas2L;
    private TextView textViewPackBotellas500mL;


    static class CodigosCargasMenu extends CodigosActividades
    {
    public static int IngresarCarga =3;
    public static int VerCarga = 4;
    public static int VerCargaEliminar = 5;
    }


    private void actualizarCargamento()
    {
        textViewBidones20L.setText("Bidones 20L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getRetornables().getBidones20L());
        textViewBidones20L.setText("Bidones 20L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getRetornables().getBidones20L());
        textViewBidones12L.setText("Bidones 12L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getRetornables().getBidones12L());
        textViewBidones10L.setText("Bidones 10L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getDescartables().getBidones10L());
        textViewBidones8L.setText("Bidones 8L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getDescartables().getBidones8L());
        textViewBidones5L.setText("Bidones 5L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getDescartables().getBidones5L());
        textViewPackBotellas2L.setText("Pack Botellas 2L: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getDescartables().getPackBotellas2L());
        textViewPackBotellas500mL.setText("Pack Botellas 500mL: " + Comunicador.getDiaRepartidor().getCargamento().getCargas().getDescartables().getPackBotellas500mL());
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosCargasMenu.IngresarCarga == requestCode)
        {
        Comunicador.setNuevaCarga(false);
        if(CodigosActividades.OK == resultCode)
            {
            adaptadorCargas = new AdaptadorCargamentos<Carga>(this,this.diaRepartidor.getCargamento().getCargas().getCargas());
            listViewCargas.setAdapter(adaptadorCargas);
            actualizarCargamento();
            Dialogo.aceptarVacio("Atención!","La carga se ingresó correctamente",this);
            }
        }
    else if(CodigosCargasMenu.VerCarga == requestCode)
        {
        actualizarCargamento();
        if(CodigosCargasMenu.VerCargaEliminar == resultCode)
            {
            adaptadorCargas = new AdaptadorCargamentos<Carga>(this,this.diaRepartidor.getCargamento().getCargas().getCargas());
            listViewCargas.setAdapter(adaptadorCargas);
            Dialogo.aceptarVacio("Atención!","La carga se eliminó correctamente",this);
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






    ////// INGRESAR CARGA

    class ListenerClickButtonIngresarCarga implements View.OnClickListener
    {
    public void onClick(View e)
        {
        ingresarCarga();
        }
    }

    private void ingresarCarga()
    {
    Comunicador.setNuevaCarga(true);
    Intent intentIngresarCarga = new Intent(this,ACarga.class);
    startActivityForResult(intentIngresarCarga, CodigosCargasMenu.IngresarCarga);
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// VER CARGA



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
            Comunicador.setCargaSeleccionada((Carga)adaptadorCargas.getItem(position));
            verCarga();
            }
        }
    }


    private void verCarga()
    {
    Intent intentVerCarga = new Intent(this,AVerCarga.class);
    startActivityForResult(intentVerCarga, CodigosCargasMenu.VerCarga);
    }








}
