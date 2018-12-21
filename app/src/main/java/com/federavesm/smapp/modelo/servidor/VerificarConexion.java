package com.federavesm.smapp.modelo.servidor;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Federico on 10/1/2018.
 */

public class VerificarConexion extends Servidor
{


    public VerificarConexion(Context activity, String ip)
    {
    super(activity);
    this.url = this.urlServidor + "AplicacionSM/servidor/verificarConexion.php";
    this.dialogoProgreso.setCancelable(true);
    this.setCancelar(new Cancelar(this));
    }

    protected VerificarConexionXML verificarConexionXML = new VerificarConexionXML();

    @Override
    protected void onPreExecute()
    {
    this.dialogoProgreso.setMessage("Testeando Conexión");
    this.dialogoProgreso.show();
    }

    protected String url;

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
            String mensaje = "";
            while ((line = rd.readLine()) != null)
            {
                mensaje += line;
            }

            this.verificarConexionXML = new VerificarConexionXML(mensaje);

        }
        catch (Exception e)
        {
            String x = e.toString();
        }
        return "";
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
