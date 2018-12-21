package com.federavesm.smapp.actividades.diaRepartidor.reparto.alquiler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.ListenerEditText;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;

/**
 * Created by Federico on 24/2/2018.
 */


public class AAlquiler extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aalquiler);



        textViewBidones20LEntregados = (TextView)findViewById(R.id.aAlquilerTextViewBidones20LEntregados);
        textViewBidones12LEntregados = (TextView)findViewById(R.id.aAlquilerTextViewBidones12LEntregados);

        textViewBidonesAlquileres6Bidones = (TextView)findViewById(R.id.aAlquilerTextViewAlquileres6Bidones);
        textViewBidonesAlquileres8Bidones = (TextView)findViewById(R.id.aAlquilerTextViewAlquileres8Bidones);
        textViewBidonesAlquileres10Bidones = (TextView)findViewById(R.id.aAlquilerTextViewAlquileres10Bidones);
        textViewBidonesAlquileres12Bidones = (TextView)findViewById(R.id.aAlquilerTextViewAlquileres12Bidones);



        this.buttonEntrega = (Button) findViewById(R.id.aAlquilerButtonEntrega);
        this.buttonEntrega.setOnClickListener(new ListenerClickButtonEntrega());

        this.buttonPagoAlquiler = (Button) findViewById(R.id.aAlquilerButtonPagoAlquiler);
        this.buttonPagoAlquiler.setOnClickListener(new ListenerClickButtonPagoAlquiler());

        this.buttonRetornar = (Button) findViewById(R.id.aAlquilerButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



        this.reparto = Comunicador.getReparto();
        this.alquiler = this.reparto.getAlquiler();
        this.cliente = this.reparto.getCliente();

        cargarViews();








    }

    private Reparto reparto;
    private Alquiler alquiler;
    private Cliente cliente;


    private Button buttonEntrega;
    private Button buttonPagoAlquiler;


    private TextView textViewBidones20LEntregados;
    private TextView textViewBidones12LEntregados;
    private TextView textViewBidonesAlquileres6Bidones;
    private TextView textViewBidonesAlquileres8Bidones;
    private TextView textViewBidonesAlquileres10Bidones;
    private TextView textViewBidonesAlquileres12Bidones;





    ///////////////////////////////////////////////////////////////////////////////////////////////////

    private void cargarViews()
    {

    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones() > 0)
        {
        textViewBidonesAlquileres6Bidones.setVisibility(View.VISIBLE);
        textViewBidonesAlquileres6Bidones.setText("Alquileres 6 Bidones: " + this.cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones());
        }
    else
        {
        textViewBidonesAlquileres6Bidones.setVisibility(View.GONE);
        }

    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones() > 0)
        {
        textViewBidonesAlquileres8Bidones.setVisibility(View.VISIBLE);
        textViewBidonesAlquileres8Bidones.setText("Alquileres 8 Bidones: " + this.cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones());
        }
    else
        {
        textViewBidonesAlquileres8Bidones.setVisibility(View.GONE);
        }

    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones() > 0)
        {
        textViewBidonesAlquileres10Bidones.setVisibility(View.VISIBLE);
        textViewBidonesAlquileres10Bidones.setText("Alquileres 10 Bidones: " + this.cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones());
        }
    else
        {
        textViewBidonesAlquileres10Bidones.setVisibility(View.GONE);
        }

    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones() > 0)
        {
        textViewBidonesAlquileres12Bidones.setVisibility(View.VISIBLE);
        textViewBidonesAlquileres12Bidones.setText("Alquileres 12 Bidones: " + this.cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones());
        }
    else
        {
        textViewBidonesAlquileres12Bidones.setVisibility(View.GONE);
        }


    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
        {
        textViewBidones20LEntregados.setVisibility(View.VISIBLE);
        textViewBidones20LEntregados.setText("Bidones 20L Entregados: " + this.cliente.getDatosAlquiler().getRetornablesEntregados().getBidones20L());
        }
    else
        {
        textViewBidones20LEntregados.setVisibility(View.GONE);
        }


    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
        {
        textViewBidones12LEntregados.setVisibility(View.VISIBLE);
        textViewBidones12LEntregados.setText("Bidones 12L Entregados: " + this.cliente.getDatosAlquiler().getRetornablesEntregados().getBidones12L());
        }
    else
        {
        textViewBidones12LEntregados.setVisibility(View.GONE);
        }

    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////






    static class CodigosAlquiler extends CodigosActividades
    {
    public static int Entrega = 3;
    public static int EntregaGuardar = 4;

    public static int PagoAlquiler = 5;
    public static int PagoAlquilerGuardar = 6;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    if(CodigosAlquiler.Entrega == requestCode)
        {
        if(CodigosAlquiler.EntregaGuardar == resultCode)
            {

            }
        else
            {

            }
        }

        if(CodigosAlquiler.PagoAlquiler == requestCode)
        {
            if(CodigosAlquiler.PagoAlquilerGuardar == resultCode)
            {

            }
            else
            {

            }
        }

    }



    ///// ENTREGA


    class ListenerClickButtonEntrega implements View.OnClickListener
    {
    public void onClick(View e)
        {
            entrega();
        }
    }


    private void entrega()
    {
    Intent intentEntregaAlquiler = new Intent(this,AEntregaAlquiler.class);
    startActivityForResult(intentEntregaAlquiler, CodigosAlquiler.Entrega);
    }




    ///// PAGO ALQUILER


    class ListenerClickButtonPagoAlquiler implements View.OnClickListener
    {
    public void onClick(View e)
        {
            pagoAlquiler();
        }
    }


    private void pagoAlquiler()
    {
    Intent intentPagoAlquiler = new Intent(this,APagoAlquiler.class);
    startActivityForResult(intentPagoAlquiler, CodigosAlquiler.PagoAlquiler);
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
