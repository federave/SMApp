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
 * Created by Federico on 23/2/2018.
 */




public class AVerDescarga extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.averdescarga);


    buttonEliminarDescarga = (Button)findViewById(R.id.aVerDescargaButtonEliminarDescarga);
    buttonEliminarDescarga.setOnClickListener(new ListenerClickButtonEliminarDescarga());

    textViewHora = (TextView)findViewById(R.id.aVerDescargaTextViewHora);

    textViewBidones20L = (TextView)findViewById(R.id.aVerDescargaTextViewBidones20L);
    textViewBidones20LVacios = (TextView)findViewById(R.id.aVerDescargaTextViewBidones20LVacios);
    textViewBidones12L = (TextView)findViewById(R.id.aVerDescargaTextViewBidones12L);
    textViewBidones12LVacios = (TextView)findViewById(R.id.aVerDescargaTextViewBidones12LVacios);
    textViewBidones10L = (TextView)findViewById(R.id.aVerDescargaTextViewBidones10L);
    textViewBidones8L = (TextView)findViewById(R.id.aVerDescargaTextViewBidones8L);
    textViewBidones5L = (TextView)findViewById(R.id.aVerDescargaTextViewBidones5L);
    textViewPackBotellas2L = (TextView)findViewById(R.id.aVerDescargaTextViewPackBotellas2L);
    textViewPackBotellas500mL = (TextView)findViewById(R.id.aVerDescargaTextViewPackBotellas500mL);
    textViewVertedores = (TextView)findViewById(R.id.aVerDescargaTextViewVertedores);
    textViewDispensers = (TextView)findViewById(R.id.aVerDescargaTextViewDispensers);

    actualizarCargamento();


    this.buttonRetornar = (Button) findViewById(R.id.aVerDescargaButtonRetornar);
    this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



    }



    private void actualizarCargamento()
    {
    textViewHora.setText("Hora: " + Comunicador.getDescargaSeleccionada().getHora());
    textViewBidones20L.setText("Bidones 20L: " + Comunicador.getDescargaSeleccionada().getRetornables().getBidones20L());
    textViewBidones20LVacios.setText("Bidones 20L Vacios: " + Comunicador.getDescargaSeleccionada().getRetornablesVacios().getBidones20L());
    textViewBidones12L.setText("Bidones 12L: " + Comunicador.getDescargaSeleccionada().getRetornables().getBidones12L());
    textViewBidones12LVacios.setText("Bidones 12L Vacios: " + Comunicador.getDescargaSeleccionada().getRetornablesVacios().getBidones12L());
    textViewBidones10L.setText("Bidones 10L: " + Comunicador.getDescargaSeleccionada().getDescartables().getBidones10L());
    textViewBidones8L.setText("Bidones 8L: " + Comunicador.getDescargaSeleccionada().getDescartables().getBidones8L());
    textViewBidones5L.setText("Bidones 5L: " + Comunicador.getDescargaSeleccionada().getDescartables().getBidones5L());
    textViewPackBotellas2L.setText("Pack Botellas 2L: " + Comunicador.getDescargaSeleccionada().getDescartables().getPackBotellas2L());
    textViewPackBotellas500mL.setText("Pack Botellas 500mL: " + Comunicador.getDescargaSeleccionada().getDescartables().getPackBotellas500mL());
    textViewVertedores.setText("Vertedores: " + Comunicador.getDescargaSeleccionada().getVertedores().getCantidad());
    textViewDispensers.setText("Dispensers: " + Comunicador.getDescargaSeleccionada().getDispensers().getCantidad());

    }










    private Button buttonEliminarDescarga;

    private TextView textViewHora;
    private TextView textViewBidones20L;
    private TextView textViewBidones20LVacios;
    private TextView textViewBidones12LVacios;
    private TextView textViewBidones12L;
    private TextView textViewBidones10L;
    private TextView textViewBidones8L;
    private TextView textViewBidones5L;
    private TextView textViewPackBotellas2L;
    private TextView textViewPackBotellas500mL;
    private TextView textViewVertedores;
    private TextView textViewDispensers;


    /////////////////////////////////
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



    ////// ELIMINAR DESCARGA

    class ListenerClickButtonEliminarDescarga implements View.OnClickListener
    {
    public void onClick(View e)
        {
            eliminarDescarga();
        }
    }

    private void eliminarDescarga()
    {
    if(Comunicador.getDescargaSeleccionada().eliminar())
        {
        Comunicador.getDiaRepartidor().getCargamento().getDescargas().cargar();
        setResult(CodigosVerDescarga.Eliminar,new Intent());
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La descarga no se pudo eliminar",this);
        }
    }


    static class CodigosVerDescarga extends CodigosActividades
    {
        public static int Eliminar = 5;
    }





}