package com.federavesm.smapp.actividades.configuracion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Federico on 5/1/2018.
 *
 * */


import com.federavesm.smapp.MainActivity;
import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.ADiaRepartidor;
import com.federavesm.smapp.actividades.diaRepartidor.ASeleccionarDiaRepartidor;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.servidor.ConexionServidor;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class AConfiguracion extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aconfiguracion);



        buttonConexion = (Button)findViewById(R.id.aConfiguracionButtonConexion);
        buttonConexion.setOnClickListener(new ListenerClickButtonConexion());


        linearLayoutConexion = (LinearLayout) findViewById(R.id.aConfiguracionLinearLayoutConexion);

        editTextConexion = (EditText)findViewById(R.id.aConfiguracionEditTextConexion);
        this.conexionServidor = Comunicador.getConexionServidor();
        editTextConexion.setText(this.conexionServidor.getIp());

        editTextPuerto = (EditText)findViewById(R.id.aConfiguracionEditTextPuerto);
        editTextPuerto.setText(this.conexionServidor.getPuerto());


        buttonVerificar = (Button)findViewById(R.id.aConfiguracionButtonVerificar);
        buttonVerificar.setOnClickListener(new ListenerClickButtonVerificar());


        buttonGuardar = (Button)findViewById(R.id.aConfiguracionButtonGuardar);
        buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());


        buttonConexionRetornar = (Button)findViewById(R.id.aConfiguracionConexionButtonRetornar);
        buttonConexionRetornar.setOnClickListener(new ListenerClickButtonConexionRetornar());


        buttonBaseDeDatos = (Button)findViewById(R.id.aConfiguracionButtonBaseDeDatos);
        buttonBaseDeDatos.setOnClickListener(new ListenerClickButtonBaseDeDatos());

        linearLayoutMenu = (LinearLayout) findViewById(R.id.aConfiguracionLinearLayoutMenu);

        buttonRetornar = (Button)findViewById(R.id.aConfiguracionButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

    }


    private ConexionServidor conexionServidor;
    private Button buttonConexion;
    private LinearLayout linearLayoutConexion;

    private LinearLayout linearLayoutMenu;

    private EditText editTextConexion;
    private EditText editTextPuerto;

    private Button buttonVerificar;
    private Button buttonGuardar;
    private Button buttonConexionRetornar;

    private Button buttonBaseDeDatos;




    ///// -------------   Conexion         --------------

    class ListenerClickButtonConexion implements View.OnClickListener
    {
    public void onClick(View e)
        {
            conexion();
        }
    }


    private void conexion()
    {
    this.linearLayoutConexion.setVisibility(View.VISIBLE);
    this.linearLayoutMenu.setVisibility(View.GONE);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///// -------------   Retornar desde Configuracion conexion       --------------

    class ListenerClickButtonConexionRetornar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            conexionRetornar();
        }
    }


    private void conexionRetornar()
    {
        this.linearLayoutConexion.setVisibility(View.GONE);
        this.linearLayoutMenu.setVisibility(View.VISIBLE);
    }



    ///// -------------   Guardar Conexion         --------------

    class ListenerClickButtonGuardar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            guardarConexion();
        }
    }

    private void guardarConexion()
    {
    this.conexionServidor.setIp(this.editTextConexion.getText().toString());
    this.conexionServidor.setPuerto(this.editTextPuerto.getText().toString());
    if(this.conexionServidor.guardar())
        {
        Dialogo.aceptarVacio("Atención!","Conexion Guardada",this);
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La conexion no se pudo guardar",this);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Verificar Conexion         --------------

    class ListenerClickButtonVerificar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            verificarConexion();
        }
    }

    private void verificarConexion()
    {
    if(Comunicador.getConexionInternet(this))
        {
        VerificarConexionConfiguracion verificarConexion = new VerificarConexionConfiguracion(this,this.editTextConexion.getText().toString(),this.editTextPuerto.getText().toString());
        verificarConexion.execute();
        }
     }

    private void resultadoVerificarConexion(VerificarConexionXML verificarConexionXML)
    {
    if(verificarConexionXML.getEstado())
        {
        Dialogo.aceptarVacio("Atención!","La conexion funciona",this);
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La conexion no funciona",this);
        }
    }


    class VerificarConexionConfiguracion extends VerificarConexion
    {
        public VerificarConexionConfiguracion(Context activity,String ip,String puerto)
        {
        super(activity,ip);
        this.urlServidor = "http://" + ip + ":" + puerto +"/";
        this.url = this.urlServidor + "AplicacionSM/servidor/verificarConexion.php";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {


            switch (values[0])
            {
                case 1:
                {
                break;
                }

                case 2:
                {
                break;
                }

                case 3:
                {break;}

                default:{break;}

            }


        }


        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            resultadoVerificarConexion(this.verificarConexionXML);
        }

    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    class ListenerClickButtonBaseDeDatos implements View.OnClickListener
    {
        public void onClick(View e)
        {
            mostrarConfiguracionBaseDeDatos();
        }
    }


    private void mostrarConfiguracionBaseDeDatos()
    {
    Intent intentConfiguracionBD = new Intent(this,AConfiguracionBaseDeDatos.class);
    startActivityForResult(intentConfiguracionBD, CodigosAC.ConfiguracionBD);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    }

    static class CodigosAC extends CodigosActividades
    {
    public static int ConfiguracionBD = 2;
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








    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }



}
