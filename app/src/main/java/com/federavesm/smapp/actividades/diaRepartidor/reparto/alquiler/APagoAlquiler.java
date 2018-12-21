package com.federavesm.smapp.actividades.diaRepartidor.reparto.alquiler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.ListenerEditText;
import com.federavesm.smapp.actividades.diaRepartidor.reparto.AVentaProductos;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.ExcedenteAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.PagoAlquiler;

/**
 * Created by Federico on 1/3/2018.
 */



public class APagoAlquiler extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apagoalquiler);


        /////ALQUILERES 6 BIDONES

        textViewAlquileres6BidonesPagados = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres6BidonesPagados);
        textViewAlquileres6BidonesPagoCompleto = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres6BidonesPagoCompleto);
        textViewDineroAlquileres6Bidones = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres6BidonesDinero);
        editTextAlquileres6Bidones = (EditText) findViewById(R.id.aPagoAlquilerEditTextAlquiler6Bidones);
        textViewAlquileres6BidonesMaximo = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres6BidonesMaximo);
        linearLayoutAlquileres6Bidones = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres6Bidones);
        linearLayoutAlquileres6BidonesPago = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres6BidonesPagos);


        /////ALQUILERES 8 BIDONES

        textViewAlquileres8BidonesPagados = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres8BidonesPagados);
        textViewAlquileres8BidonesPagoCompleto = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres8BidonesPagoCompleto);
        textViewDineroAlquileres8Bidones = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres8BidonesDinero);
        editTextAlquileres8Bidones = (EditText) findViewById(R.id.aPagoAlquilerEditTextAlquiler8Bidones);
        textViewAlquileres8BidonesMaximo = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres8BidonesMaximo);
        linearLayoutAlquileres8Bidones = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres8Bidones);
        linearLayoutAlquileres8BidonesPago = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres8BidonesPagos);


        /////ALQUILERES 10 BIDONES

        textViewAlquileres10BidonesPagados = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres10BidonesPagados);
        textViewAlquileres10BidonesPagoCompleto = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres10BidonesPagoCompleto);
        textViewDineroAlquileres10Bidones = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres10BidonesDinero);
        editTextAlquileres10Bidones = (EditText) findViewById(R.id.aPagoAlquilerEditTextAlquiler10Bidones);
        textViewAlquileres10BidonesMaximo = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres10BidonesMaximo);
        linearLayoutAlquileres10Bidones = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres10Bidones);
        linearLayoutAlquileres10BidonesPago = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres10BidonesPagos);


        /////ALQUILERES 12 BIDONES

        textViewAlquileres12BidonesPagados = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres12BidonesPagados);
        textViewAlquileres12BidonesPagoCompleto = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres12BidonesPagoCompleto);
        textViewDineroAlquileres12Bidones = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres12BidonesDinero);
        editTextAlquileres12Bidones = (EditText) findViewById(R.id.aPagoAlquilerEditTextAlquiler12Bidones);
        textViewAlquileres12BidonesMaximo = (TextView)findViewById(R.id.aPagoAlquilerTextViewAlquileres12BidonesMaximo);
        linearLayoutAlquileres12Bidones = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres12Bidones);
        linearLayoutAlquileres12BidonesPago = (LinearLayout) findViewById(R.id.aPagoAlquilerLinearLayoutAlquileres12BidonesPagos);


        this.cliente = Comunicador.getCliente();
        this.pagoAlquiler = Comunicador.getReparto().getAlquiler().getPagoAlquiler();
        this.pagoAlquilerAux = (PagoAlquiler) this.pagoAlquiler.getCopia();

        cargarAlquileres6Bidones();
        cargarAlquileres8Bidones();
        cargarAlquileres10Bidones();
        cargarAlquileres12Bidones();

        this.buttonGuardar = (Button) findViewById(R.id.aPagoAlquilerButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        this.buttonBorrarDatos = (Button) findViewById(R.id.aPagoAlquilerButtonBorrarDatos);
        this.buttonBorrarDatos.setOnClickListener(new ListenerClickButtonBorrarDatos());

        this.buttonRetornar = (Button) findViewById(R.id.aPagoAlquilerButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        this.editTextAlquileres6Bidones.addTextChangedListener(new ListenerEditTextAlquiler6Bidones());
        this.editTextAlquileres8Bidones.addTextChangedListener(new ListenerEditTextAlquiler8Bidones());
        this.editTextAlquileres10Bidones.addTextChangedListener(new ListenerEditTextAlquiler10Bidones());
        this.editTextAlquileres12Bidones.addTextChangedListener(new ListenerEditTextAlquiler12Bidones());







    }


    private PagoAlquiler pagoAlquiler;
    private PagoAlquiler pagoAlquilerAux;
    private Cliente cliente;




    /////ALQUILERES 6 BIDONES

    private TextView textViewAlquileres6BidonesPagados;
    private TextView textViewAlquileres6BidonesPagoCompleto;
    private TextView textViewDineroAlquileres6Bidones;
    private EditText editTextAlquileres6Bidones;
    private TextView textViewAlquileres6BidonesMaximo;
    private LinearLayout linearLayoutAlquileres6Bidones;
    private LinearLayout linearLayoutAlquileres6BidonesPago;

    /////ALQUILERES 8 BIDONES

    private TextView textViewAlquileres8BidonesPagados;
    private TextView textViewAlquileres8BidonesPagoCompleto;
    private TextView textViewDineroAlquileres8Bidones;
    private EditText editTextAlquileres8Bidones;
    private TextView textViewAlquileres8BidonesMaximo;
    private LinearLayout linearLayoutAlquileres8Bidones;
    private LinearLayout linearLayoutAlquileres8BidonesPago;


    /////ALQUILERES 10 BIDONES

    private TextView textViewAlquileres10BidonesPagados;
    private TextView textViewAlquileres10BidonesPagoCompleto;
    private TextView textViewDineroAlquileres10Bidones;
    private EditText editTextAlquileres10Bidones;
    private TextView textViewAlquileres10BidonesMaximo;
    private LinearLayout linearLayoutAlquileres10Bidones;
    private LinearLayout linearLayoutAlquileres10BidonesPago;

    /////ALQUILERES 12 BIDONES

    private TextView textViewAlquileres12BidonesPagados;
    private TextView textViewAlquileres12BidonesPagoCompleto;
    private TextView textViewDineroAlquileres12Bidones;
    private EditText editTextAlquileres12Bidones;
    private TextView textViewAlquileres12BidonesMaximo;
    private LinearLayout linearLayoutAlquileres12Bidones;
    private LinearLayout linearLayoutAlquileres12BidonesPago;



    private void cargarAlquileres6Bidones()
    {
    if(cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones() > 0)
        {
        this.textViewAlquileres6BidonesPagados.setText("Alquileres 6 Bidones Pagados: " + this.cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres6Bidones());
        if(this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres6Bidones() == 0)
            {
            this.linearLayoutAlquileres6BidonesPago.setVisibility(View.GONE);
            if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones()> 1 )
                {
                this.textViewAlquileres6BidonesPagoCompleto.setText("Los alquileres de 6 Bidones están pagos");
                }
            else
                {
                this.textViewAlquileres6BidonesPagoCompleto.setText("El alquiler de 6 Bidones está pago");
                }
            }
        else
            {
            this.textViewAlquileres6BidonesPagoCompleto.setVisibility(View.GONE);
            try
                {
                if(this.pagoAlquilerAux.getAlquileres().getAlquileres6Bidones() > 0)
                    {
                    this.editTextAlquileres6Bidones.setText(String.valueOf(this.pagoAlquilerAux.getAlquileres().getAlquileres6Bidones()));
                    }
                }
            catch (Exception e)
            {}
            this.textViewAlquileres6BidonesMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres6Bidones());
            }
        }
    else
        {
        this.linearLayoutAlquileres6Bidones.setVisibility(View.GONE);
        }
    }




    private void cargarAlquileres8Bidones()
    {
        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones() > 0)
        {
            this.textViewAlquileres8BidonesPagados.setText("Alquileres 8 Bidones Pagados: " + this.cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres8Bidones());
            if(this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres8Bidones() == 0)
            {
                this.linearLayoutAlquileres8BidonesPago.setVisibility(View.GONE);
                if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones()> 1 )
                {
                    this.textViewAlquileres8BidonesPagoCompleto.setText("Los alquileres de 8 Bidones están pagos");
                }
                else
                {
                    this.textViewAlquileres8BidonesPagoCompleto.setText("El alquiler de 8 Bidones está pago");
                }
            }
            else
            {
                this.textViewAlquileres8BidonesPagoCompleto.setVisibility(View.GONE);
                try
                {
                    if(this.pagoAlquilerAux.getAlquileres().getAlquileres8Bidones() > 0)
                    {
                        this.editTextAlquileres8Bidones.setText(String.valueOf(this.pagoAlquilerAux.getAlquileres().getAlquileres8Bidones()));
                    }
                }
                catch (Exception e)
                {}
                this.textViewAlquileres8BidonesMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres8Bidones());
            }
        }
        else
        {
            this.linearLayoutAlquileres8Bidones.setVisibility(View.GONE);
        }
    }


    private void cargarAlquileres10Bidones()
    {
        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones() > 0)
        {
            this.textViewAlquileres10BidonesPagados.setText("Alquileres 10 Bidones Pagados: " + this.cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres10Bidones());
            if(this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres10Bidones() == 0)
            {
                this.linearLayoutAlquileres10BidonesPago.setVisibility(View.GONE);
                if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones()> 1 )
                {
                    this.textViewAlquileres10BidonesPagoCompleto.setText("Los alquileres de 10 Bidones están pagos");
                }
                else
                {
                    this.textViewAlquileres10BidonesPagoCompleto.setText("El alquiler de 10 Bidones está pago");
                }
            }
            else
            {
                this.textViewAlquileres10BidonesPagoCompleto.setVisibility(View.GONE);
                try
                {
                    if(this.pagoAlquilerAux.getAlquileres().getAlquileres10Bidones() > 0)
                    {
                        this.editTextAlquileres10Bidones.setText(String.valueOf(this.pagoAlquilerAux.getAlquileres().getAlquileres10Bidones()));
                    }
                }
                catch (Exception e)
                {}
                this.textViewAlquileres10BidonesMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres10Bidones());
            }
        }
        else
        {
            this.linearLayoutAlquileres10Bidones.setVisibility(View.GONE);
        }
    }



    private void cargarAlquileres12Bidones()
    {
        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones() > 0)
        {
            this.textViewAlquileres12BidonesPagados.setText("Alquileres 12 Bidones Pagados: " + this.cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres12Bidones());
            if(this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres12Bidones() == 0)
            {
                this.linearLayoutAlquileres12BidonesPago.setVisibility(View.GONE);
                if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones()> 1 )
                {
                    this.textViewAlquileres12BidonesPagoCompleto.setText("Los alquileres de 12 Bidones están pagos");
                }
                else
                {
                    this.textViewAlquileres12BidonesPagoCompleto.setText("El alquiler de 12 Bidones está pago");
                }
            }
            else
            {
                this.textViewAlquileres12BidonesPagoCompleto.setVisibility(View.GONE);
                try
                {
                    if(this.pagoAlquilerAux.getAlquileres().getAlquileres12Bidones() > 0)
                    {
                        this.editTextAlquileres12Bidones.setText(String.valueOf(this.pagoAlquilerAux.getAlquileres().getAlquileres12Bidones()));
                    }
                }
                catch (Exception e)
                {}
                this.textViewAlquileres12BidonesMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres12Bidones());
            }
        }
        else
        {
            this.linearLayoutAlquileres12Bidones.setVisibility(View.GONE);
        }
    }


    //////////////////////////////////////////////////////

    class ListenerEditTextAlquiler6Bidones extends ListenerEditText
    {

        public ListenerEditTextAlquiler6Bidones()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {if(estadoEventos){
            listenerEditTextAlquiler6Bidones();
        }}

    }

    boolean estadoEventos=true;

    class ListenerEditTextAlquiler8Bidones extends ListenerEditText
    {

        public ListenerEditTextAlquiler8Bidones()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {if(estadoEventos){
            listenerEditTextAlquiler8Bidones();
        }}

    }

    class ListenerEditTextAlquiler10Bidones extends ListenerEditText
    {

        public ListenerEditTextAlquiler10Bidones()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {if(estadoEventos){
            listenerEditTextAlquiler10Bidones();
        }}

    }

    class ListenerEditTextAlquiler12Bidones extends ListenerEditText
    {

        public ListenerEditTextAlquiler12Bidones()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {if(estadoEventos){
            listenerEditTextAlquiler12Bidones();
        }}

    }




    private void listenerEditTextAlquiler6Bidones()
    {
        if(estadoEventos)
        {
            try
            {
            if(editTextAlquileres6Bidones.getText().toString().length()>0)
                {
                if(Integer.valueOf(editTextAlquileres6Bidones.getText().toString()) > this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres6Bidones())
                    {
                    editTextAlquileres6Bidones.setText("");
                    this.pagoAlquilerAux.getAlquileres().setAlquileres6Bidones(0);
                    }
                else
                    {
                    this.pagoAlquilerAux.getAlquileres().setAlquileres6Bidones(Integer.valueOf(editTextAlquileres6Bidones.getText().toString()));
                    this.textViewDineroAlquileres6Bidones.setText("Dinero: "+this.pagoAlquilerAux.getAlquileres().getAlquileres6Bidones() * this.cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler6Bidones());
                    }
                }
            else
                {
                this.pagoAlquilerAux.getAlquileres().setAlquileres6Bidones(0);
                }

              }
            catch (Exception e)
            {

            }
        }

    }



    private void listenerEditTextAlquiler8Bidones()
    {
        if(estadoEventos)
        {
            try
            {


                if(editTextAlquileres8Bidones.getText().toString().length()>0)
                {
                    if(Integer.valueOf(editTextAlquileres8Bidones.getText().toString()) > this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres8Bidones())
                    {
                        editTextAlquileres8Bidones.setText("");
                        this.pagoAlquilerAux.getAlquileres().setAlquileres8Bidones(0);
                    }
                    else
                    {
                        this.pagoAlquilerAux.getAlquileres().setAlquileres8Bidones(Integer.valueOf(editTextAlquileres8Bidones.getText().toString()));
                        this.textViewDineroAlquileres8Bidones.setText("Dinero: "+this.pagoAlquilerAux.getAlquileres().getAlquileres8Bidones() * this.cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler8Bidones());

                    }
                }
                else
                {
                    this.pagoAlquilerAux.getAlquileres().setAlquileres8Bidones(0);
                }



            }
            catch (Exception e)
            {

            }
        }

    }



    private void listenerEditTextAlquiler10Bidones()
    {
        if(estadoEventos)
        {
            try
            {


                if(editTextAlquileres10Bidones.getText().toString().length()>0)
                {
                    if(Integer.valueOf(editTextAlquileres10Bidones.getText().toString()) > this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres10Bidones())
                    {
                        editTextAlquileres10Bidones.setText("");
                        this.pagoAlquilerAux.getAlquileres().setAlquileres10Bidones(0);
                    }
                    else
                    {
                        this.pagoAlquilerAux.getAlquileres().setAlquileres10Bidones(Integer.valueOf(editTextAlquileres10Bidones.getText().toString()));
                        this.textViewDineroAlquileres10Bidones.setText("Dinero: "+this.pagoAlquilerAux.getAlquileres().getAlquileres10Bidones() * this.cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler10Bidones());

                    }
                }
                else
                {
                    this.pagoAlquilerAux.getAlquileres().setAlquileres10Bidones(0);
                }



            }
            catch (Exception e)
            {

            }
        }

    }



    private void listenerEditTextAlquiler12Bidones()
    {
        if(estadoEventos)
        {
            try
            {


                if(editTextAlquileres12Bidones.getText().toString().length()>0)
                {
                    if(Integer.valueOf(editTextAlquileres12Bidones.getText().toString()) > this.cliente.getDatosAlquiler().getAlquileresAPagar().getAlquileres12Bidones())
                    {
                        editTextAlquileres12Bidones.setText("");
                        this.pagoAlquilerAux.getAlquileres().setAlquileres12Bidones(0);
                    }
                    else
                    {
                        this.pagoAlquilerAux.getAlquileres().setAlquileres12Bidones(Integer.valueOf(editTextAlquileres12Bidones.getText().toString()));
                        this.textViewDineroAlquileres12Bidones.setText("Dinero: "+this.pagoAlquilerAux.getAlquileres().getAlquileres12Bidones() * this.cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler12Bidones());

                    }
                }
                else
                {
                    this.pagoAlquilerAux.getAlquileres().setAlquileres12Bidones(0);
                }



            }
            catch (Exception e)
            {

            }
        }

    }







    //////////////////////////////////////////////////////
    ///////GUARDAR

    private Button buttonGuardar;

    class ListenerClickButtonGuardar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            guardar();
        }
    }


    private void guardar()
    {
    try
        {
        if(this.pagoAlquilerAux.getEstado())
            {
            if(this.pagoAlquilerAux.have())
                {
                this.pagoAlquiler.copiar(this.pagoAlquilerAux);
                if(this.pagoAlquiler.modificar())
                    {
                    Dialogo.aceptar("Atención!","El pago se guardó correctamente",this);
                    retornar();
                    }
                else
                    {
                    Dialogo.aceptar("Atención!","El pago no se guardó correctamente",this);
                    }
                }
            else
                {
                this.pagoAlquiler.copiar(this.pagoAlquilerAux);
                if(this.pagoAlquiler.eliminar())
                    {
                    Dialogo.aceptar("Atención!","El pago se guardó correctamente",this);
                    retornar();
                    }
                else
                    {
                    Dialogo.aceptar("Atención!","El pago no se guardó correctamente",this);
                    }
                }
            }
        else
            {
            Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
            }
        }
    catch (Exception e)
        {
        Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
        }
    }







    ///// BORRAR DATOS

    private Button buttonBorrarDatos;

    class ListenerClickButtonBorrarDatos implements View.OnClickListener
    {
    public void onClick(View e)
        {
            borrarDatos();
        }
    }


    private void borrarDatos()
    {
    this.estadoEventos = false;
    this.editTextAlquileres6Bidones.setText("");
    this.editTextAlquileres8Bidones.setText("");
    this.editTextAlquileres10Bidones.setText("");
    this.editTextAlquileres12Bidones.setText("");
    this.textViewDineroAlquileres6Bidones.setText("");
    this.textViewDineroAlquileres8Bidones.setText("");
    this.textViewDineroAlquileres10Bidones.setText("");
    this.textViewDineroAlquileres12Bidones.setText("");
    this.pagoAlquilerAux.limpiar();
    this.estadoEventos = true;
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