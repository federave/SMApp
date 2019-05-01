package com.federavesm.smapp.actividades.diaRepartidor.cargas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Carga;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Descarga;

/**
 * Created by Federico on 21/2/2018.
 */





public class ADescarga extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adescarga);



        this.editTextBidones20L = (EditText) findViewById(R.id.aDescargaEditTextBidones20L);
        this.editTextBidones20LVacios = (EditText) findViewById(R.id.aDescargaEditTextBidones20LVacios);
        this.editTextBidones12L = (EditText) findViewById(R.id.aDescargaEditTextBidones12L);
        this.editTextBidones12LVacios = (EditText) findViewById(R.id.aDescargaEditTextBidones12LVacios);
        this.editTextBidones10L = (EditText) findViewById(R.id.aDescargaEditTextBidones10L);
        this.editTextBidones8L = (EditText) findViewById(R.id.aDescargaEditTextBidones8L);
        this.editTextBidones5L = (EditText) findViewById(R.id.aDescargaEditTextBidones5L);
        this.editTextPackBotellas2L = (EditText) findViewById(R.id.aDescargaEditTextPackBotellas2L);
        this.editTextPackBotellas500mL = (EditText) findViewById(R.id.aDescargaEditTextPackBotellas500mL);
        this.editTextVertedores = (EditText) findViewById(R.id.aDescargaEditTextVertedores);
        this.editTextDispensers = (EditText) findViewById(R.id.aDescargaEditDispensers);

        this.buttonIngresarDescarga = (Button) findViewById(R.id.aDescargaButtonIngresarDescarga);
        this.buttonIngresarDescarga.setOnClickListener(new ListenerClickButtonIngresarDescarga());

        this.buttonRetornar = (Button) findViewById(R.id.aDescargaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        if(Comunicador.getNuevaDescarga())
            {
            this.descarga = new Descarga(this);
            this.descarga.setIdDiaRepartidor(Comunicador.getDiaRepartidor().getId());
            }


    }


    private Descarga descarga;


    private EditText editTextBidones20L;
    private EditText editTextBidones20LVacios;
    private EditText editTextBidones12L;
    private EditText editTextBidones12LVacios;
    private EditText editTextBidones10L;
    private EditText editTextBidones8L;
    private EditText editTextBidones5L;
    private EditText editTextPackBotellas2L;
    private EditText editTextPackBotellas500mL;
    private EditText editTextVertedores;
    private EditText editTextDispensers;


    private Button buttonIngresarDescarga;





    //////////////////////////////////////////////////////////////////////
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
        try
        {

        insertarDatos();
        if(this.descarga.getEstado())
            {
            if(this.descarga.evaluar())
                {
                if(this.descarga.guardar())
                    {
                    Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescargas().add(this.descarga);
                    Comunicador.getDiaRepartidor().getCargamento().getDescargas().actualizar();
                    setResult(CodigosActividades.OK,new Intent());
                    this.finish();
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!","La descarga no se ingresó correctamente",this);
                    }
                }
            else
                {
                descargaIncoherente();
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


    private void descargaIncoherente()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención!")
                .setMessage(this.descarga.getEvaluar())
                .setNegativeButton("No Ingresar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ingresarNo();
                    }
                })
                .setPositiveButton("Ingresar igual",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ingresarSi();
                    }
                });

        builder.create().show();
    }

    private void ingresarSi()
    {
    if(this.descarga.guardar())
        {
        Comunicador.getDiaRepartidor().getCargamento().getDescargas().getDescargas().add(this.descarga);
        Comunicador.getDiaRepartidor().getCargamento().getDescargas().actualizar();
        setResult(CodigosActividades.OK,new Intent());
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La descarga no se ingresó correctamente",this);
        }
    }


    private void ingresarNo()
    {
    }



    //////////////////////////////////////////////////////////////////////






    private void insertarDatos() throws Exception
    {

        if(this.editTextBidones20L.getText().length() > 0)
        {
            this.descarga.getRetornables().setBidones20L(Integer.valueOf(this.editTextBidones20L.getText().toString()));
        }
        if(this.editTextBidones20LVacios.getText().length() > 0)
        {
            this.descarga.getRetornablesVacios().setBidones20L(Integer.valueOf(this.editTextBidones20LVacios.getText().toString()));
        }
        if(this.editTextBidones12L.getText().toString().length() > 0)
        {
            this.descarga.getRetornables().setBidones12L(Integer.valueOf(this.editTextBidones12L.getText().toString()));
        }
        if(this.editTextBidones12LVacios.getText().toString().length() > 0)
        {
            this.descarga.getRetornablesVacios().setBidones12L(Integer.valueOf(this.editTextBidones12LVacios.getText().toString()));
        }
        if(this.editTextBidones10L.getText().length() > 0)
        {
            this.descarga.getDescartables().setBidones10L(Integer.valueOf(this.editTextBidones10L.getText().toString()));
        }

        if(this.editTextBidones8L.getText().length() > 0)
        {
            this.descarga.getDescartables().setBidones8L(Integer.valueOf(this.editTextBidones8L.getText().toString()));
        }

        if(this.editTextBidones5L.getText().length() > 0)
        {
            this.descarga.getDescartables().setBidones5L(Integer.valueOf(this.editTextBidones5L.getText().toString()));
        }
        if(this.editTextPackBotellas2L.getText().length() > 0)
        {
            this.descarga.getDescartables().setPackBotellas2L(Integer.valueOf(this.editTextPackBotellas2L.getText().toString()));
        }
        if(this.editTextPackBotellas500mL.getText().length() > 0)
        {
            this.descarga.getDescartables().setPackBotellas500mL(Integer.valueOf(this.editTextPackBotellas500mL.getText().toString()));
        }

        if(this.editTextVertedores.getText().length() > 0)
        {
            this.descarga.getVertedores().setCantidad(Integer.valueOf(this.editTextVertedores.getText().toString()));
        }
        if(this.editTextDispensers.getText().length() > 0)
        {
            this.descarga.getDispensers().setCantidad(Integer.valueOf(this.editTextDispensers.getText().toString()));
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
