package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.reparto.alquiler.AAlquiler;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.deudaProductos.DeudaProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

/**
 * Created by Federico on 21/2/2018.
 */



public class AReparto extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areparto);


        this.diaRepartidor = Comunicador.getDiaRepartidor();
        this.reparto = Comunicador.getReparto();


        textViewNombreCliente = (TextView) findViewById(R.id.aRepartoTextViewNombreCliente);
        textViewNombreCliente.setText("Cliente: "+this.reparto.getCliente().getDatos().toString());
        buttonVentaProductos = (Button)findViewById(R.id.aRepartoButtonVentaProductos);
        buttonVentaProductos.setOnClickListener(new ListenerClickButtonVentaProductos());

        buttonDeudaProductos = (Button)findViewById(R.id.aRepartoButtonDeudaProductos);
        buttonDeudaProductos.setOnClickListener(new ListenerClickButtonDeudaProductos());

        buttonAlquiler = (Button)findViewById(R.id.aRepartoButtonAlquiler);
        buttonAlquiler.setOnClickListener(new ListenerClickButtonAlquiler());

        buttonVacios = (Button)findViewById(R.id.aRepartoButtonVacios);
        buttonVacios.setOnClickListener(new ListenerClickButtonVacios());

        buttonDatosCliente = (Button)findViewById(R.id.aRepartoButtonDatosCliente);
        buttonDatosCliente.setOnClickListener(new ListenerClickButtonDatosCliente());

        buttonObservacion = (Button)findViewById(R.id.aRepartoButtonObservacion);
        buttonObservacion.setOnClickListener(new ListenerClickButtonObservacion());

        buttonVisita = (Button)findViewById(R.id.aRepartoButtonVisita);
        buttonVisita.setOnClickListener(new ListenerClickButtonVisita());

        buttonVertedores = (Button)findViewById(R.id.aRepartoButtonVertedores);
        buttonVertedores.setOnClickListener(new ListenerClickButtonVertedores());

        buttonDispensers = (Button)findViewById(R.id.aRepartoButtonDispensers);
        buttonDispensers.setOnClickListener(new ListenerClickButtonDispensers());





        if(!(this.reparto.getCliente().getDatosAlquiler().getEstado()))
            {
            buttonAlquiler.setVisibility(View.GONE);
            }



        this.buttonRetornar = (Button) findViewById(R.id.aRepartoButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



    }

    private DiaRepartidor diaRepartidor;
    private Reparto reparto;

    private TextView textViewNombreCliente;




    static class CodigosReparto extends CodigosActividades
    {
    public static int VentaProductos = 3;
    public static int VentaProductosGuardar = 4;

    public static int DeudaProductos = 5;
    public static int DeudaProductosGuardar = 6;

    public static int Alquiler = 6;
    public static int Vacios = 7;
    public static int DatosCliente = 8;
    public static int Observacion = 9;
    public static int Visita = 9;
    public static int Vertedores = 10;
    public static int Dispensers = 11;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosReparto.VentaProductos == requestCode)
        {
        if(CodigosReparto.VentaProductosGuardar == resultCode)
            {
            Dialogo.aceptarVacio("Atención!","La venta se guardó correctamente",this);
            }
        else
            {

            }
        }


    if(CodigosReparto.DeudaProductos == requestCode)
        {
        if(CodigosReparto.DeudaProductosGuardar == resultCode)
            {
            Dialogo.aceptarVacio("Atención!","La deuda se guardó correctamente",this);
            }
            else
            {

            }
        }


    }



    /////////////////////////////////////////////////////////
    ////// VERTEDORES


    private Button buttonVertedores;

    class ListenerClickButtonVertedores implements View.OnClickListener
    {
        public void onClick(View e)
        {
            vertedores();
        }
    }


    private void vertedores()
    {
        Comunicador.setVentaProductosAux((VentaProductos)this.reparto.getVentaProductos().getCopia());
        Intent intentVentaProductos = new Intent(this,AVentaProductos.class);
        startActivityForResult(intentVentaProductos, CodigosReparto.VentaProductos);
    }





    /////////////////////////////////////////////////////////
    ////// DISPENSERS


    private Button buttonDispensers;

    class ListenerClickButtonDispensers implements View.OnClickListener
    {
        public void onClick(View e)
        {
            dispensers();
        }
    }


    private void dispensers()
    {
        Comunicador.setVentaProductosAux((VentaProductos)this.reparto.getVentaProductos().getCopia());
        Intent intentVentaProductos = new Intent(this,AVentaProductos.class);
        startActivityForResult(intentVentaProductos, CodigosReparto.VentaProductos);
    }







    /////////////////////////////////////////////////////////
    ////// VENTA PRODUCTOS



    private Button buttonVentaProductos;

    class ListenerClickButtonVentaProductos implements View.OnClickListener
    {
    public void onClick(View e)
        {
            ventaProductos();
        }
    }


    private void ventaProductos()
    {
    Comunicador.setVentaProductosAux((VentaProductos)this.reparto.getVentaProductos().getCopia());
    Intent intentVentaProductos = new Intent(this,AVentaProductos.class);
    startActivityForResult(intentVentaProductos, CodigosReparto.VentaProductos);
    }





    ////// DEUDA PRODUCTOS


    private Button buttonDeudaProductos;

    class ListenerClickButtonDeudaProductos implements View.OnClickListener
    {
        public void onClick(View e)
        {
            deudaProductos();
        }
    }


    private void deudaProductos()
    {
    Comunicador.setDeudaProductosAux((DeudaProductos)this.reparto.getDeudaProductos().getCopia());
    Intent intentDeudaProductos = new Intent(this,ADeudaProductos.class);
    startActivityForResult(intentDeudaProductos, CodigosReparto.DeudaProductos);

    }


    ////// ALQUILER


    private Button buttonAlquiler;

    class ListenerClickButtonAlquiler implements View.OnClickListener
    {
    public void onClick(View e)
        {
            alquiler();
        }
    }


    private void alquiler()
    {
    Intent intentAlquiler = new Intent(this,AAlquiler.class);
    startActivityForResult(intentAlquiler, CodigosReparto.Alquiler);
    }



    ////// VACIOS


    private Button buttonVacios;

    class ListenerClickButtonVacios implements View.OnClickListener
    {
        public void onClick(View e)
        {
            vacios();
        }
    }


    private void vacios()
    {
    Intent intentVacios = new Intent(this,AVacios.class);
    startActivityForResult(intentVacios, CodigosReparto.Vacios);
    }



    ////// DATOS CLIENTE


    private Button buttonDatosCliente;

    class ListenerClickButtonDatosCliente implements View.OnClickListener
    {
        public void onClick(View e)
        {
            datosCliente();
        }
    }


    private void datosCliente()
    {
    Intent intentDatosCliente = new Intent(this,ADatosCliente.class);
    startActivityForResult(intentDatosCliente, CodigosReparto.DatosCliente);
    }



    ////// OBSERVACION


    private Button buttonObservacion;

    class ListenerClickButtonObservacion implements View.OnClickListener
    {
        public void onClick(View e)
        {
            observacion();
        }
    }


    private void observacion()
    {

    Intent intentObservacion = new Intent(this,AObservacion.class);
    startActivityForResult(intentObservacion, CodigosReparto.Observacion);

    }



    ////// VISITA


    private Button buttonVisita;

    class ListenerClickButtonVisita implements View.OnClickListener
    {
        public void onClick(View e)
        {
            visita();
        }
    }


    private void visita()
    {
    Intent intentVisita = new Intent(this,AVisita.class);
    startActivityForResult(intentVisita, CodigosReparto.Visita);
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


    @Override
    public void onBackPressed() {
        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }







}
