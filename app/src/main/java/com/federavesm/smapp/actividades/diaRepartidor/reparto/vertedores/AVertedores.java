package com.federavesm.smapp.actividades.diaRepartidor.reparto.vertedores;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;





public class AVertedores extends ActivityGenerica
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avertedores);

        this.buttonRetornar = (Button) findViewById(R.id.aVertedoresButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonVenta = (Button) findViewById(R.id.aVertedoresButtonVenta);
        this.buttonVenta.setOnClickListener(new ListenerClickButtonVenta());

        this.buttonCambio = (Button) findViewById(R.id.aVertedoresButtonCambio);
        this.buttonCambio.setOnClickListener(new ListenerClickButtonCambio());

        this.buttonEntrega = (Button) findViewById(R.id.aVertedoresButtonEntrega);
        this.buttonEntrega.setOnClickListener(new ListenerClickButtonEntrega());




    }




    static class CodigosVertedores extends CodigosActividades
    {
        public static int Venta = 1;
        public static int Cambio = 1;
        public static int Entrega = 1;
    }

    ///////Venta

    private Button buttonVenta;

    class ListenerClickButtonVenta implements View.OnClickListener
    {
        public void onClick(View e)
        {
            venta();
        }
    }

    private void venta()
    {
        Intent intentVenta = new Intent(this,AVentaVertedores.class);
        startActivityForResult(intentVenta, CodigosVertedores.Venta);
    }

    ///////Entrega

    private Button buttonEntrega;

    class ListenerClickButtonEntrega implements View.OnClickListener
    {
        public void onClick(View e)
        {
            entrega();
        }
    }

    private void entrega()
    {
        Intent intentEntrega = new Intent(this,AEntregaVertedores.class);
        startActivityForResult(intentEntrega, CodigosVertedores.Entrega);
    }

    ///////Cambio

    private Button buttonCambio;

    class ListenerClickButtonCambio implements View.OnClickListener
    {
        public void onClick(View e)
        {
            cambio();
        }
    }

    private void cambio()
    {
        Intent intentCambio = new Intent(this,ACambioVertedores.class);
        startActivityForResult(intentCambio, CodigosVertedores.Cambio);
    }




}





