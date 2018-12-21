package com.federavesm.smapp.actividades.diaRepartidor.cargas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ACarga extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acarga);



        this.editTextBidones20L = (EditText) findViewById(R.id.aCargaEditTextBidones20L);
        this.editTextBidones12L = (EditText) findViewById(R.id.aCargaEditTextBidones12L);
        this.editTextBidones10L = (EditText) findViewById(R.id.aCargaEditTextBidones10L);
        this.editTextBidones8L = (EditText) findViewById(R.id.aCargaEditTextBidones8L);
        this.editTextBidones5L = (EditText) findViewById(R.id.aCargaEditTextBidones5L);
        this.editTextPackBotellas2L = (EditText) findViewById(R.id.aCargaEditTextPackBotellas2L);
        this.editTextPackBotellas500mL = (EditText) findViewById(R.id.aCargaEditTextPackBotellas500mL);

        this.buttonIngresarCarga = (Button) findViewById(R.id.aCargaButtonIngresarCarga);
        this.buttonIngresarCarga.setOnClickListener(new ListenerClickButtonIngresarCarga());

        this.buttonModificarCarga = (Button) findViewById(R.id.aCargaButtonModificarCarga);
        this.buttonModificarCarga.setOnClickListener(new ListenerClickButtonModificarCarga());

        this.buttonRetornar = (Button) findViewById(R.id.aCargaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        if(Comunicador.getNuevaCarga())
            {
            this.buttonModificarCarga.setVisibility(View.GONE);
            this.carga = new Carga(this);
            this.carga.setIdDiaRepartidor(Comunicador.getDiaRepartidor().getId());
            }
        else if(Comunicador.getModificarCarga())
            {
            this.buttonIngresarCarga.setVisibility(View.GONE);
            this.carga = (Carga)Comunicador.getCargaSeleccionada().getCopia();
            try
                {
                if(String.valueOf(this.carga.getRetornables().getBidones20L()).length() > 0)
                    {
                    this.editTextBidones20L.setText(String.valueOf(this.carga.getRetornables().getBidones20L()));
                    }
                if(String.valueOf(this.carga.getRetornables().getBidones12L()).length() > 0)
                    {
                    this.editTextBidones12L.setText(String.valueOf(this.carga.getRetornables().getBidones12L()));
                    }
                if(String.valueOf(this.carga.getDescartables().getBidones10L()).length() > 0)
                    {
                    this.editTextBidones10L.setText(String.valueOf(this.carga.getDescartables().getBidones10L()));
                    }
                if(String.valueOf(this.carga.getDescartables().getBidones8L()).length() > 0)
                    {
                    this.editTextBidones8L.setText(String.valueOf(this.carga.getDescartables().getBidones8L()));
                    }
                if(String.valueOf(this.carga.getDescartables().getBidones5L()).length() > 0)
                    {
                    this.editTextBidones5L.setText(String.valueOf(this.carga.getDescartables().getBidones5L()));
                    }
                if(String.valueOf(this.carga.getDescartables().getPackBotellas2L()).length() > 0)
                    {
                    this.editTextPackBotellas2L.setText(String.valueOf(this.carga.getDescartables().getPackBotellas2L()));
                    }
                if(String.valueOf(this.carga.getDescartables().getPackBotellas500mL()).length() > 0)
                    {
                    this.editTextPackBotellas500mL.setText(String.valueOf(this.carga.getDescartables().getPackBotellas500mL()));
                    }

                }
            catch (Exception e)
                {
                String s=e.toString();
                }

            }
        else
            {

            }


    }


    private Carga carga;


    private EditText editTextBidones20L;
    private EditText editTextBidones12L;
    private EditText editTextBidones10L;
    private EditText editTextBidones8L;
    private EditText editTextBidones5L;
    private EditText editTextPackBotellas2L;
    private EditText editTextPackBotellas500mL;


    private Button buttonIngresarCarga;
    private Button buttonModificarCarga;





    //////////////////////////////////////////////////////////////////////
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
    try
        {


        insertarDatos();

        if(this.carga.evaluar())
            {
            if(this.carga.guardar())
                {
                Comunicador.getDiaRepartidor().getCargamento().getCargas().getCargas().add(this.carga);
                Comunicador.getDiaRepartidor().getCargamento().getCargas().actualizar();
                setResult(CodigosActividades.OK,new Intent());
                this.finish();
                }
            else
                {
                Dialogo.aceptarVacioError("Atención!","La carga no se ingresó correctamente",this);
                }
            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
            }

        }
    catch (Exception e)
        {
        Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
        }
    }


    private void insertarDatos() throws Exception
    {

    if(this.editTextBidones20L.getText().length() > 0)
        {
        this.carga.getRetornables().setBidones20L(Integer.valueOf(this.editTextBidones20L.getText().toString()));
        }

    if(this.editTextBidones12L.getText().toString().length() > 0)
        {
        this.carga.getRetornables().setBidones12L(Integer.valueOf(this.editTextBidones12L.getText().toString()));
        }

    if(this.editTextBidones10L.getText().length() > 0)
        {
        this.carga.getDescartables().setBidones10L(Integer.valueOf(this.editTextBidones10L.getText().toString()));
        }

    if(this.editTextBidones8L.getText().length() > 0)
        {
        this.carga.getDescartables().setBidones8L(Integer.valueOf(this.editTextBidones8L.getText().toString()));
        }

    if(this.editTextBidones5L.getText().length() > 0)
        {
        this.carga.getDescartables().setBidones5L(Integer.valueOf(this.editTextBidones5L.getText().toString()));
        }
    if(this.editTextPackBotellas2L.getText().length() > 0)
        {
        this.carga.getDescartables().setPackBotellas2L(Integer.valueOf(this.editTextPackBotellas2L.getText().toString()));
        }
    if(this.editTextPackBotellas500mL.getText().length() > 0)
        {
        this.carga.getDescartables().setPackBotellas500mL(Integer.valueOf(this.editTextPackBotellas500mL.getText().toString()));
        }
    }




    //////////////////////////////////////////////////////////////////////
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
    try
        {
        insertarDatos();

        if(this.carga.evaluar())
            {
            Comunicador.getCargaSeleccionada().copiar(this.carga);
            if(Comunicador.getCargaSeleccionada().actualizar())
                {
                Comunicador.getDiaRepartidor().getCargamento().getCargas().actualizar();
                setResult(CodigosActividades.OK,new Intent());
                this.finish();
                }
            else
                {
                Dialogo.aceptarVacioError("Atención!","La carga no se modificó correctamente",this);
                }
            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
            }

        }
    catch (Exception e)
        {
        Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
        }
    }


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
