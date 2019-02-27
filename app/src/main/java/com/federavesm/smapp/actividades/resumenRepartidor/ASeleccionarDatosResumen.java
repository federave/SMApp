package com.federavesm.smapp.actividades.resumenRepartidor;

/**
 * Created by Federico on 2/1/2018.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorRepartidores;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.resumenRepartidor.ResumenRepartidor;

/**
 * Created by Federico on 27/4/2017.
 */

public class ASeleccionarDatosResumen extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aseleccionardatosresumen);


        repartidores = Comunicador.getRepartidores();



        spinnerRepartidores = (Spinner) findViewById(R.id.aSeleccionarDatosResumenSpinnerRepartidores);
        adaptadorRepartidores = new AdaptadorRepartidores(this,repartidores.getRepartidores());
        spinnerRepartidores.setAdapter(adaptadorRepartidores);


        fechaInicioDatePicker  = (DatePicker) findViewById(R.id.aSeleccionarDatosResumenDatePickerFechaInicio);
        fechaFinDatePicker  = (DatePicker) findViewById(R.id.aSeleccionarDatosResumenDatePickerFechaFin);

        buttonSeleccionar = (Button)findViewById(R.id.aSeleccionarDatosResumenBotonSeleccionar);
        buttonSeleccionar.setOnClickListener(new ListenerClickButtonSeleccionar());

        this.diaRepartidor = Comunicador.getDiaRepartidor();

        this.buttonRetornar = (Button) findViewById(R.id.aSeleccionarDatosResumenButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



    }

    private DiaRepartidor diaRepartidor;


    class ListenerClickButtonSeleccionar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            seleccionar();
        }
    }

    private void seleccionar()
    {


    Fecha fechaInicio = new Fecha(fechaInicioDatePicker.getYear(),fechaInicioDatePicker.getMonth()+1,fechaInicioDatePicker.getDayOfMonth());
    Fecha fechaFin = new Fecha(fechaFinDatePicker.getYear(),fechaFinDatePicker.getMonth()+1,fechaFinDatePicker.getDayOfMonth());
    if(fechaInicio.comparar(fechaFin)==1)
        {
        if(spinnerRepartidores.getSelectedItemPosition()>=0)
            {
            int idRepartidor = ((Repartidor)spinnerRepartidores.getSelectedItem()).getId();

            ResumenRepartidor resumenRepartidor = new ResumenRepartidor(this);
            resumenRepartidor.setFechaInicio(fechaInicio);
            resumenRepartidor.setFechaFin(fechaFin);
            resumenRepartidor.setIdRepartidor(idRepartidor);
            Comunicador.setResumenRepartidor(resumenRepartidor);


            Intent intentDatosResumen = new Intent(this, ADatosResumen.class);
            startActivityForResult(intentDatosResumen,1);

            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","Debe seleccionar un repartidor",this);
            }
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La fecha de Inicio debe ser menor que la de Fin",this);
        }






    }


    private Button buttonSeleccionar;

    private AdaptadorRepartidores adaptadorRepartidores;

    private Spinner spinnerRepartidores;
    private Repartidores repartidores;

    private DatePicker fechaInicioDatePicker;
    private DatePicker fechaFinDatePicker;






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


}


