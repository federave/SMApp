package com.federavesm.smapp.actividades.resumenRepartidor;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.diaRepartidor.ADiaRepartidor;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.resumenRepartidor.ResumenRepartidor;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ADatosResumen extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.adatosresumen);


    this.textViewDatos = (TextView) findViewById(R.id.aDatosResumenTextViewDatos);

    this.buttonRetornar = (Button) findViewById(R.id.aDatosResumenButtonRetornar);
    this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

    this.resumenRepartidor = Comunicador.getResumenRepartidor();

    try
        {
            CargarDatosResumen cargarDatosResumen = new CargarDatosResumen(this);
            cargarDatosResumen.execute();

        }
    catch (Exception e)
        {
            String e2=e.toString();
        }


    }



    private TextView textViewDatos;

    private ResumenRepartidor resumenRepartidor;

    public class CargarDatosResumen extends AsyncTask<String,Integer,String>
    {


        public CargarDatosResumen(Context activity)
        {
            this.activity = activity;
            this.dialogoProgreso = new ProgressDialog(activity);
            this.dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.dialogoProgreso.setCancelable(false);
            this.dialogoProgreso.setIndeterminate(true);
            this.dialogoProgreso.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar",new Cancelar(this));
        }

        protected Context activity;
        protected ProgressDialog dialogoProgreso;


        @Override
        protected void onPreExecute()
        {
        this.dialogoProgreso.setMessage("Cargando Resumen");
        this.dialogoProgreso.show();
        }

        protected String url;




        @Override
        protected void onProgressUpdate(Integer... values)
        {

        }

        boolean resultado = true;




        @Override
        protected String doInBackground(String... strings) {

            try
            {

            resumenRepartidor.cargar();

            }
            catch (Exception e)
            {
                String x = e.toString();
            }
            return "";
        }



        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();

            mostrarDatosResumen();
        }





        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Toast.makeText(activity,"Tarea cancelada",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(activity,"Tarea cancelada",Toast.LENGTH_SHORT).show();

        }


        class Cancelar implements DialogInterface.OnClickListener {


            public Cancelar(AsyncTask<String,Integer,String> tarea)
            {
                this.tarea=tarea;
            }

            private AsyncTask<String,Integer,String> tarea;

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tarea.cancel(true);
            }
        }

    }




    private void mostrarDatosResumen()
    {
    String dinero ="Dinero Venta Bidones 20L: " + this.resumenRepartidor.getDineroBidones20LVendidos();
    String datos ="Venta Bidones 20L: " + this.resumenRepartidor.getTotalBidones20LVendidos();
    this.textViewDatos.setText(dinero+datos);
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


