package com.federavesm.smapp.actividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;

import com.federavesm.smapp.R;

/**
 * Created by Federico on 2/3/2018.
 */

public class Dialogo {

    public static void aceptar(String titulo, String mensaje, Context context)
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(titulo).setMessage(mensaje)
    .setPositiveButton("Ok",
        new DialogInterface.OnClickListener()
        {
        public void onClick(DialogInterface dialog, int id)
        {
        listenerEventoAceptarInterfaz.aceptar(new EventoAceptar(new Object()));
        }
        });
        Dialog dialogo = builder.create();
        dialogo.getWindow().setBackgroundDrawableResource(R.drawable.colordialogoaceptar);
        dialogo.show();
    }

    private static ListenerEventoAceptarInterfaz listenerEventoAceptarInterfaz;

    public static void setListenerEventoAceptarInterfaz(ListenerEventoAceptarInterfaz listenerEventoAceptarInterfaz) {
        Dialogo.listenerEventoAceptarInterfaz = listenerEventoAceptarInterfaz;
    }


    public static void aceptarVacio(String titulo, String mensaje, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo).setMessage(mensaje)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                            }
                        });
        Dialog dialogo = builder.create();
        dialogo.getWindow().setBackgroundDrawableResource(R.drawable.colordialogoaceptar);
        dialogo.show();

    }

    public static void aceptarVacioError(String titulo, String mensaje, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo).setMessage(mensaje)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                            }
                        });
        Dialog dialogo = builder.create();
        dialogo.getWindow().setBackgroundDrawableResource(R.drawable.colordialogoaceptarerror);
        dialogo.show();

    }


    public static void aceptarError(String titulo, String mensaje, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo).setMessage(mensaje)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                listenerEventoAceptarInterfaz.aceptar(new EventoAceptar(new Object()));
                            }
                        });
        Dialog dialogo = builder.create();
        dialogo.getWindow().setBackgroundDrawableResource(R.drawable.colordialogoaceptarerror);
        dialogo.show();
    }




}


