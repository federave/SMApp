package com.federavesm.smapp.actividades.diaRepartidor.reparto.dispensers;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;



public class ADispensers extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adispensers);




        this.buttonRetornar = (Button) findViewById(R.id.aDispensersButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonVenta = (Button) findViewById(R.id.aDispensersButtonVenta);
        this.buttonVenta.setOnClickListener(new ListenerClickButtonVenta());

        this.buttonCambio = (Button) findViewById(R.id.aDispensersButtonCambio);
        this.buttonCambio.setOnClickListener(new ListenerClickButtonCambio());

        this.buttonEntrega = (Button) findViewById(R.id.aDispensersButtonEntrega);
        this.buttonEntrega.setOnClickListener(new ListenerClickButtonEntrega());


        this.buttonRetiro = (Button) findViewById(R.id.aDispensersButtonRetiro);
        this.buttonRetiro.setOnClickListener(new ListenerClickButtonRetiro());



    }




    static class CodigosDispensers extends CodigosActividades
    {
    public static int Venta = 1;
    public static int Cambio = 2;
    public static int Entrega = 3;
    public static int Retiro = 4;

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
    Intent intentVenta = new Intent(this,AVentaDispensers.class);
    startActivityForResult(intentVenta, CodigosDispensers.Venta);
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
        Intent intentEntrega = new Intent(this,AEntregaDispensers.class);
        startActivityForResult(intentEntrega, CodigosDispensers.Entrega);
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
        Intent intentCambio = new Intent(this,ACambioDispensers.class);
        startActivityForResult(intentCambio, CodigosDispensers.Cambio);
    }


    ///////Retiro

    private Button buttonRetiro;

    class ListenerClickButtonRetiro implements View.OnClickListener
    {
        public void onClick(View e)
        {
            retiro();
        }
    }

    private void retiro()
    {
        Intent intentRetiro = new Intent(this,ARetiroDispensers.class);
        startActivityForResult(intentRetiro, CodigosDispensers.Retiro);
    }

}

