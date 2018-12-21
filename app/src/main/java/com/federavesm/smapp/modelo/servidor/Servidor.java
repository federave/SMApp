package com.federavesm.smapp.modelo.servidor;

/**
 * Created by Federico on 9/1/2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.federavesm.smapp.modelo.Comunicador;

public abstract class Servidor extends AsyncTask<String,Integer,String>
{


    public Servidor(Context activity)
    {
    this.activity = activity;
    this.conexionServidor = Comunicador.getConexionServidor();
    this.dialogoProgreso = new ProgressDialog(activity);
    this.dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    this.dialogoProgreso.setCancelable(false);
    this.dialogoProgreso.setIndeterminate(true);

    this.urlServidor = "http://" + this.conexionServidor.getIp() + ":" + this.conexionServidor.getPuerto()+"/";


    }




    

    protected ConexionServidor conexionServidor;
    protected Context activity;
    protected ProgressDialog dialogoProgreso;

    protected String urlServidor="";

    protected String url="";


    public void setCancelar(DialogInterface.OnClickListener listener)
    {
    this.dialogoProgreso.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar",listener);
    }

    public void removeCancelar()
    {try {
        this.dialogoProgreso.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);

    }catch (Exception e){}
    }



}
