package com.federavesm.smapp.actividades.configuracion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.BaseDeDatos;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente.TipoClientes;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.TipoInactivos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.TiposFueraDeRecorrido;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisitas;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedores;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.ActualizarBaseDeDatosXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Federico on 13/2/2018.
 */



public class AConfiguracionBaseDeDatos extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aconfiguracionbasededatos);

        this.baseDeDatos = new BaseDeDatos(this);



        textViewInfoBD = (TextView) findViewById(R.id.aConfiguracionBaseDeDatosTextViewInfoBD);
        textViewInfoBD.setText(textViewInfoBD.getText().toString()+String.valueOf(this.baseDeDatos.getNumeroDias()));


        buttonActualizarBaseDeDatos = (Button)findViewById(R.id.aConfiguracionBaseDeDatosButtonActualizarBD);
        buttonActualizarBaseDeDatos.setOnClickListener(new AConfiguracionBaseDeDatos.ListenerClickButtonActualizarBaseDeDatos());


        buttonVaciarBD = (Button)findViewById(R.id.aConfiguracionBaseDeDatosButtonVaciarBD);
        buttonVaciarBD.setOnClickListener(new AConfiguracionBaseDeDatos.ListenerClickButtonVaciarBD());


        buttonRetornar = (Button)findViewById(R.id.aConfiguracionBaseDeDatosButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());






    }


    BaseDeDatos baseDeDatos;


    private Button buttonActualizarBaseDeDatos;
    private Button buttonVaciarBD;
    private TextView textViewInfoBD;







    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Vaciar BD         --------------



    class ListenerClickButtonVaciarBD implements View.OnClickListener
    {
        public void onClick(View e)
        {
            vaciarBD();
        }
    }


    private void vaciarBD()
    {
    vaciar();
    }


    private void vaciar()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención!")
                .setMessage("ESTÁ SEGURO QUE DESEA VACIAR LA BASE DE DATOS ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        vaciarNo();
                    }
                })
                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        vaciarSi();
                    }
                });

        builder.create().show();
    }

    private void vaciarSi()
    {
        if(baseDeDatos.vaciarBaseDeDatosDinamica())
        {
            Dialogo.aceptarVacio("Atención!","La Base de Datos se vació correctamente",this);
        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","La Base de Datos no se vació correctamente",this);
        }

        textViewInfoBD.setText("Número de Días en BD: " + String.valueOf(this.baseDeDatos.getNumeroDias()));

    }


    private void vaciarNo()
    {
    }






    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Actualizar Base de Datos         --------------


    class ListenerClickButtonActualizarBaseDeDatos implements View.OnClickListener
    {
        public void onClick(View e)
        {
            actualizarBaseDeDatosVerificarConexion();
        }
    }

    private void actualizarBaseDeDatosVerificarConexion()
    {
    if(Comunicador.getConexionInternet(this))
        {
        VerificarConexionActualizarBaseDeDatos verificarConexion = new VerificarConexionActualizarBaseDeDatos(this,Comunicador.getConexionServidor().getIp());
        verificarConexion.execute();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","No esta conectado a internet",this);
        }
    }



    class VerificarConexionActualizarBaseDeDatos extends VerificarConexion
    {
        public VerificarConexionActualizarBaseDeDatos(Context activity, String ip)
        {
            super(activity,ip);
        }
        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            actualizarBaseDeDatos(this.verificarConexionXML);
        }

    }


    private void actualizarBaseDeDatos(VerificarConexionXML verificarConexionXML)
    {
    if(verificarConexionXML.getEstado())
        {
        ActualizarBaseDeDatos actualizarBaseDeDatos = new ActualizarBaseDeDatos(this);
        actualizarBaseDeDatos.execute();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La conexion con el servidor no funciona",this);
        }
    }


    ////////////////Actualizar Base De Datos


    public class ActualizarBaseDeDatos extends Servidor
    {


        public ActualizarBaseDeDatos(Context activity)
        {
            super(activity);
            this.url = this.urlServidor + "AplicacionSM/servidor/getActualizarBaseDeDatos.php";
            this.activity = activity;
        }



        private Context activity;

        protected ActualizarBaseDeDatosXML actualizarBaseDeDatosXML = new ActualizarBaseDeDatosXML();

        @Override
        protected void onPreExecute()
        {
            this.dialogoProgreso.setMessage("Actualizando Base de Datos");
            this.dialogoProgreso.show();
        }


        @Override
        protected String doInBackground(String... strings) {

            try
            {
                HttpClient cliente = new DefaultHttpClient();
                HttpGet requerimiento = new HttpGet(this.url);
                HttpResponse respuesta = cliente.execute(requerimiento);

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                String line = "";
                String respuestaXML = "";
                while ((line = rd.readLine()) != null)
                {
                    respuestaXML += line;
                }

                this.actualizarBaseDeDatosXML = new ActualizarBaseDeDatosXML(respuestaXML,this.activity);

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
            actualizarBaseDeDatosFin(this.actualizarBaseDeDatosXML);
        }




    }

    private void actualizarBaseDeDatosFin(ActualizarBaseDeDatosXML actualizarBaseDeDatosXML)
    {
    if(actualizarBaseDeDatosXML.getEstado())
        {
        Repartidores repartidores = new Repartidores(this,actualizarBaseDeDatosXML.getRepartidores());
        Vendedores vendedores = new Vendedores(this,actualizarBaseDeDatosXML.getVendedores());
        TipoClientes tipoClientes = new TipoClientes(this,actualizarBaseDeDatosXML.getTipoClientes());
        TipoInactivos tipoInactivos = new TipoInactivos(this,actualizarBaseDeDatosXML.getTipoInactivos());
        TipoVisitas tipoVisitas = new TipoVisitas(this,actualizarBaseDeDatosXML.getTipoVisitas());
        TiposFueraDeRecorrido tiposFueraDeRecorrido = new TiposFueraDeRecorrido(this,actualizarBaseDeDatosXML.getTiposFueraDeRecorrido());


        if(repartidores.actualizar() && vendedores.actualizar() && tipoClientes.actualizar() && tipoInactivos.actualizar() && tipoVisitas.actualizar() && tiposFueraDeRecorrido.actualizar())
            {
            Comunicador.setRepartidores(new Repartidores(this));
            Dialogo.aceptarVacio("Atención!","Base de Datos actualizados",this);
            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","Hubo inconvenientes al actualizar la Base de Datos",this);
            }
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","Hubo inconvenientes en la transmision de datos",this);
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




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }



}
