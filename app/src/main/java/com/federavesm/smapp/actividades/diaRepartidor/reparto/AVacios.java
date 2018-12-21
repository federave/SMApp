package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.EventoAceptar;
import com.federavesm.smapp.actividades.ListenerEventoAceptarInterfaz;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.otros.Vacios;

/**
 * Created by Federico on 24/2/2018.
 */



public class AVacios extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avacios);



        editTextBidones20L = (EditText) findViewById(R.id.aVaciosEditTextBidones20L);
        editTextBidones12L = (EditText) findViewById(R.id.aVaciosEditTextBidones12L);


        this.buttonGuardar = (Button) findViewById(R.id.aVaciosButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        this.buttonBorrarDatos = (Button) findViewById(R.id.aVaciosButtonBorrarDatos);
        this.buttonBorrarDatos.setOnClickListener(new ListenerClickButtonBorrarDatos());

        this.buttonRetornar = (Button) findViewById(R.id.aVaciosButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        this.vaciosOld = Comunicador.getReparto().getVacios();
        this.vaciosNew = (Vacios) this.vaciosOld.getCopia();

        try
            {
            if(this.vaciosOld.getRetornables().getBidones20L()>0)
                editTextBidones20L.setText(String.valueOf(this.vaciosOld.getRetornables().getBidones20L()));
            if(this.vaciosOld.getRetornables().getBidones12L()>0)
                editTextBidones12L.setText(String.valueOf(this.vaciosOld.getRetornables().getBidones12L()));
            }
        catch (Exception e)
            {

            }









    }


    private EditText editTextBidones20L;
    private EditText editTextBidones12L;

    private Vacios vaciosOld;
    private Vacios vaciosNew;





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

        if(this.editTextBidones20L.getText().toString().length()>0)
            {
            this.vaciosNew.getRetornables().setBidones20L(Integer.valueOf(this.editTextBidones20L.getText().toString()));
            }
        else
            {
            this.vaciosNew.getRetornables().setBidones20L(0);
            }

        if(this.editTextBidones12L.getText().toString().length()>0)
            {
            this.vaciosNew.getRetornables().setBidones12L(Integer.valueOf(this.editTextBidones12L.getText().toString()));
            }
        else
            {
            this.vaciosNew.getRetornables().setBidones12L(0);
            }
        if(this.vaciosNew.getEstado())
            {
            if(this.vaciosNew.have())
                {
                this.vaciosOld.copiar(this.vaciosNew);
                if(this.vaciosOld.modificar())
                    {
                    Comunicador.getReparto().actualizar();
                    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                    Dialogo.aceptar("Atención!","Vacios guardados",this);
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!","Los vacios no se guardaron correctamente",this);
                    }
                }
            else
                {
                this.vaciosOld.copiar(this.vaciosNew);
                if(this.vaciosOld.eliminar())
                    {
                    Comunicador.getReparto().actualizar();
                    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                    Dialogo.aceptar("Atención!","Vacios guardados",this);
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!","Los vacios no se guardaron correctamente",this);
                    }
                }
            }
        else
            {
            Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
            }
        }
    catch (Exception e)
        {
        Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
        }
    }







    ///// BORRAR DATOS

    private Button buttonBorrarDatos;

    class ListenerClickButtonBorrarDatos implements View.OnClickListener
    {
        public void onClick(View e)
        {
            borrarDatos();
        }
    }


    private void borrarDatos()
    {
    this.vaciosNew.limpiar();
    this.editTextBidones20L.setText("");
    this.editTextBidones12L.setText("");
    }




















}

