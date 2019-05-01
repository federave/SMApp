package com.federavesm.smapp.actividades.diaRepartidor.cargas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;

/**
 * Created by Federico on 22/2/2018.
 */



public class AVerCarga extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avercarga);


        buttonModificarCarga = (Button)findViewById(R.id.aVerCargaButtonModificarCarga);
        buttonModificarCarga.setOnClickListener(new ListenerClickButtonModificarCarga());

        buttonEliminarCarga = (Button)findViewById(R.id.aVerCargaButtonEliminarCarga);
        buttonEliminarCarga.setOnClickListener(new ListenerClickButtonEliminarCarga());

        textViewHora = (TextView)findViewById(R.id.aVerCargaTextViewHora);

        textViewBidones20L = (TextView)findViewById(R.id.aVerCargaTextViewBidones20L);
        textViewBidones12L = (TextView)findViewById(R.id.aVerCargaTextViewBidones12L);
        textViewBidones10L = (TextView)findViewById(R.id.aVerCargaTextViewBidones10L);
        textViewBidones8L = (TextView)findViewById(R.id.aVerCargaTextViewBidones8L);
        textViewBidones5L = (TextView)findViewById(R.id.aVerCargaTextViewBidones5L);
        textViewPackBotellas2L = (TextView)findViewById(R.id.aVerCargaTextViewPackBotellas2L);
        textViewPackBotellas500mL = (TextView)findViewById(R.id.aVerCargaTextViewPackBotellas500mL);
        textViewVertedores = (TextView)findViewById(R.id.aVerCargaTextViewVertedores);
        textViewDispensers = (TextView)findViewById(R.id.aVerCargaTextViewDispensers);


        actualizarCargamento();

        if(Comunicador.getDiaRepartidor().getRepartos().getEstado())
            {
            buttonEliminarCarga.setVisibility(View.GONE);
            buttonModificarCarga.setVisibility(View.GONE);
            }

        this.buttonRetornar = (Button) findViewById(R.id.aVerCargaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



    }



    private void actualizarCargamento()
    {
    textViewHora.setText("Hora: " + Comunicador.getCargaSeleccionada().getHora());
    textViewBidones20L.setText("Bidones 20L: " + Comunicador.getCargaSeleccionada().getRetornables().getBidones20L());
    textViewBidones20L.setText("Bidones 20L: " + Comunicador.getCargaSeleccionada().getRetornables().getBidones20L());
    textViewBidones12L.setText("Bidones 12L: " + Comunicador.getCargaSeleccionada().getRetornables().getBidones12L());
    textViewBidones10L.setText("Bidones 10L: " + Comunicador.getCargaSeleccionada().getDescartables().getBidones10L());
    textViewBidones8L.setText("Bidones 8L: " + Comunicador.getCargaSeleccionada().getDescartables().getBidones8L());
    textViewBidones5L.setText("Bidones 5L: " + Comunicador.getCargaSeleccionada().getDescartables().getBidones5L());
    textViewPackBotellas2L.setText("Pack Botellas 2L: " + Comunicador.getCargaSeleccionada().getDescartables().getPackBotellas2L());
    textViewPackBotellas500mL.setText("Pack Botellas 500mL: " + Comunicador.getCargaSeleccionada().getDescartables().getPackBotellas500mL());
    textViewVertedores.setText("Vertedores: " + Comunicador.getCargaSeleccionada().getVertedores().getCantidad());
    textViewDispensers.setText("Dispensers: " + Comunicador.getCargaSeleccionada().getDispensers().getCantidad());
    }











    private Button buttonModificarCarga;
    private Button buttonEliminarCarga;

    private TextView textViewHora;
    private TextView textViewBidones20L;
    private TextView textViewBidones12L;
    private TextView textViewBidones10L;
    private TextView textViewBidones8L;
    private TextView textViewBidones5L;
    private TextView textViewPackBotellas2L;
    private TextView textViewPackBotellas500mL;
    private TextView textViewVertedores;
    private TextView textViewDispensers;



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





    static class CodigosVerCarga extends CodigosActividades
    {
    public static int Modificar = 3;
    public static int Eliminar = 5;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosVerCarga.Modificar == requestCode)
        {
        Comunicador.setModificarCarga(false);
        if(CodigosActividades.OK == resultCode)
            {
            actualizarCargamento();
            Dialogo.aceptarVacio("Atención!","La carga se modificó correctamente",this);
            }
        }

    }





    ////// MODIFICAR CARGA

    class ListenerClickButtonModificarCarga implements View.OnClickListener
    {
    public void onClick(View e)
        {
            modificarCarga();
        }
    }

    private void modificarCarga()
    {
    Comunicador.setModificarCarga(true);
    Intent intentModificarCarga = new Intent(this,ACarga.class);
    startActivityForResult(intentModificarCarga, CodigosVerCarga.Modificar);
    }



    ////// ELIMINAR CARGA

    class ListenerClickButtonEliminarCarga implements View.OnClickListener
    {
    public void onClick(View e)
        {
            eliminarCarga();
        }
    }

    private void eliminarCarga()
    {
    Comunicador.getCargaSeleccionada().eliminar();
    Comunicador.getDiaRepartidor().getCargamento().getCargas().cargar();
    setResult(CodigosVerCarga.Eliminar,new Intent());
    this.finish();
    }







}