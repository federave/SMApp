package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.otros.Observacion;

/**
 * Created by Federico on 24/2/2018.
 */


public class AObservacion extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aobservacion);

        this.buttonRetornar = (Button) findViewById(R.id.aObservacionButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());
        this.buttonGuardar = (Button) findViewById(R.id.aObservacionButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());
        this.buttonBorrar = (Button) findViewById(R.id.aObservacionButtonBorrar);
        this.buttonBorrar.setOnClickListener(new ListenerClickButtonBorrar());

        editTextObservacion = (EditText) findViewById(R.id.aObservacionObservacion);

        observacionOld = Comunicador.getReparto().getObservacion();
        observacionNew = (Observacion)observacionOld.getCopia();

        editTextObservacion.setText(observacionOld.getObservacion());


    }


    private Observacion observacionOld;
    private Observacion observacionNew;


    private EditText editTextObservacion;

    ///////GUARDAR

    private Button buttonGuardar;

    class ListenerClickButtonGuardar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            guardar();
        }
    }



    private void guardar()
    {
    try
        {
        this.observacionNew.setObservacion(this.editTextObservacion.getText().toString());
        if(this.observacionNew.getEstado())
            {
            this.observacionOld.copiar(this.observacionNew);
            if(this.observacionOld.modificar())
                {
                Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                Dialogo.aceptar("Atención!","La observación se guardó correctamente",this);
                }
            else
                {
                Dialogo.aceptarVacioError("Atención!","La observación no se guardó correctamente",this);
                }
            }
        else
            {
            this.observacionOld.copiar(this.observacionNew);
            if(this.observacionOld.eliminar())
                {
                Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                Dialogo.aceptar("Atención!","La observación se guardó correctamente",this);
                }
            else
                {
                Dialogo.aceptarVacioError("Atención!","La observación no se guardó correctamente",this);
                }
            }
        }
    catch (Exception e)
        {
        Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
        }
    }





    ///// BORRAR DATOS

    private Button buttonBorrar;

    class ListenerClickButtonBorrar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            borrar();
        }
    }

    private void borrar()
    {
    this.observacionNew.limpiar();
    this.editTextObservacion.setText("");
    }

















}
