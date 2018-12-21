package com.federavesm.smapp.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.Comunicador;


public class ActivityGenerica extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    protected Button buttonRetornar;

    public class ListenerClickButtonRetornar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            retornar();
        }
    }



    public class ListenerEventoAceptarVacio implements ListenerEventoAceptarInterfaz
    {
        @Override
        public void aceptar(EventoAceptar evento) {
        }
    }

    public class ListenerEventoAceptar implements ListenerEventoAceptarInterfaz
    {
        @Override
        public void aceptar(EventoAceptar evento) {
          retornar();
        }
    }

    protected void retornar()
    {
    setResult(CodigosActividades.SALIR,new Intent());
    this.finish();
    }



}

