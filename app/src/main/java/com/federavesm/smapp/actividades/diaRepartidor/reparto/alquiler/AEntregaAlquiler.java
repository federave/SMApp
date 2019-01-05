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
import com.federavesm.smapp.actividades.EventoAceptar;
import com.federavesm.smapp.actividades.ListenerEditText;
import com.federavesm.smapp.actividades.ListenerEventoAceptarInterfaz;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;

/**
 * Created by Federico on 1/3/2018.
 */


public class AEntregaAlquiler extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aentregaalquiler);

        ////ENTREGA DE BIDONES

        editTextBidones20L = (EditText) findViewById(R.id.aAlquilerEditTextBidones20L);
        editTextBidones12L = (EditText) findViewById(R.id.aAlquilerEditTextBidones12L);

        textViewBidones20LMaximo = (TextView)findViewById(R.id.aAlquilerTextViewBidones20LMaximo);
        textViewBidones12LMaximo = (TextView)findViewById(R.id.aAlquilerTextViewBidones12LMaximo);

        this.linearLayoutEntregaBidones20L = (LinearLayout) findViewById(R.id.aAlquilerLinearLayoutEntregaBidones20L);
        this.linearLayoutEntregaBidones12L = (LinearLayout) findViewById(R.id.aAlquilerLinearLayoutEntregaBidones12L);

        textViewEntregaBidones = (TextView)findViewById(R.id.aEntregaAlquilerTextViewEntregaBidones);
        textViewEntrega20LCompleta = (TextView)findViewById(R.id.aEntregaAlquilerTextViewEntrega20LCompleta);
        textViewEntrega12LCompleta = (TextView)findViewById(R.id.aEntregaAlquilerTextViewEntrega12LCompleta);




        ////EXCEDENTE DE BIDONES


        textViewDineroVenta = (TextView)findViewById(R.id.aExcedenteTextViewDineroVenta);

        editTextExcedenteBidones20L = (EditText) findViewById(R.id.aExcedenteEditTextBidones20L);
        editTextExcedenteBidones20LACobrar = (EditText) findViewById(R.id.aExcedenteEditTextBidones20LACobrar);

        editTextExcedenteBidones12L = (EditText) findViewById(R.id.aExcedenteEditTextBidones12L);
        editTextExcedenteBidones12LACobrar = (EditText) findViewById(R.id.aExcedenteEditTextBidones12LACobrar);

        this.buttonGuardar = (Button) findViewById(R.id.aExcedenteButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        this.buttonBorrarDatos = (Button) findViewById(R.id.aExcedenteButtonBorrarDatos);
        this.buttonBorrarDatos.setOnClickListener(new ListenerClickButtonBorrarDatos());

        this.buttonRetornar = (Button) findViewById(R.id.aExcedenteButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.linearLayoutExcedenteBidones20L = (LinearLayout) findViewById(R.id.aExcedenteLinearLayoutBidones20L);
        this.linearLayoutExcedenteBidones12L = (LinearLayout) findViewById(R.id.aExcedenteLinearLayoutBidones12L);
        this.linearLayoutExcedente = (LinearLayout) findViewById(R.id.aExcedenteAlquilerLinearLayoutExcedente);


        this.alquilerOld = Comunicador.getReparto().getAlquiler();
        this.alquilerNew = (Alquiler) this.alquilerOld.getCopia();
        this.cliente = Comunicador.getReparto().getCliente();


        cargarViews();


        this.editTextBidones20L.addTextChangedListener(new ListenerEditTextsEntrega());
        this.editTextBidones12L.addTextChangedListener(new ListenerEditTextsEntrega());


        this.editTextExcedenteBidones20L.addTextChangedListener(new ListenerEditTextsExcedente());
        this.editTextExcedenteBidones20LACobrar.addTextChangedListener(new ListenerEditTextsExcedente());
        this.editTextExcedenteBidones12L.addTextChangedListener(new ListenerEditTextsExcedente());
        this.editTextExcedenteBidones12LACobrar.addTextChangedListener(new ListenerEditTextsExcedente());







    }




    private TextView textViewBidones20LMaximo;
    private TextView textViewEntrega20LCompleta;

    private TextView textViewBidones12LMaximo;
    private TextView textViewEntrega12LCompleta;

    private EditText editTextBidones20L;
    private EditText editTextBidones12L;

    private TextView textViewEntregaBidones;


    private LinearLayout linearLayoutEntregaBidones20L;
    private LinearLayout linearLayoutEntregaBidones12L;






    private Alquiler alquilerOld;
    private Alquiler alquilerNew;
    private Cliente cliente;


    private Button buttonGuardar;


    private TextView textViewDineroVenta;

    private EditText editTextExcedenteBidones20L;
    private EditText editTextExcedenteBidones20LACobrar;

    private EditText editTextExcedenteBidones12L;
    private EditText editTextExcedenteBidones12LACobrar;

    private LinearLayout linearLayoutExcedenteBidones20L;
    private LinearLayout linearLayoutExcedenteBidones12L;
    private LinearLayout linearLayoutExcedente;



    ///////////////////////////////////////////////////////////////////////////////////////////////////


    private void cargarViews()
    {
    try
        {
        if(this.alquilerOld.getRetornables().getBidones20L() > 0)
            this.editTextBidones20L.setText(String.valueOf(this.alquilerOld.getRetornables().getBidones20L()));

        if(this.alquilerOld.getRetornables().getBidones12L() > 0)
            this.editTextBidones12L.setText(String.valueOf(this.alquilerOld.getRetornables().getBidones12L()));

        if(this.alquilerOld.getExcedenteAlquiler().getRetornables().getBidones20L() > 0)
            this.editTextExcedenteBidones20L.setText(String.valueOf(this.alquilerOld.getExcedenteAlquiler().getRetornables().getBidones20L()));

        if(this.alquilerOld.getExcedenteAlquiler().getRetornablesDeudados().getBidones20L() > 0)
            this.editTextExcedenteBidones20LACobrar.setText(String.valueOf(this.alquilerOld.getExcedenteAlquiler().getRetornablesDeudados().getBidones20L()));

        if(this.alquilerOld.getExcedenteAlquiler().getRetornables().getBidones12L() > 0)
            this.editTextExcedenteBidones12L.setText(String.valueOf(this.alquilerOld.getExcedenteAlquiler().getRetornables().getBidones12L()));

        if(this.alquilerOld.getExcedenteAlquiler().getRetornablesDeudados().getBidones12L() > 0)
            this.editTextExcedenteBidones12LACobrar.setText(String.valueOf(this.alquilerOld.getExcedenteAlquiler().getRetornablesDeudados().getBidones12L()));

        this.textViewDineroVenta.setText("Dinero: " + this.alquilerOld.getExcedenteAlquiler().getDineroVenta() + " $");



        if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
            {
            if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() > 0)
                {
                textViewBidones20LMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L());
                }
            }

        if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() > 0)
                {
                textViewBidones12LMaximo.setText("Max: " + this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L());
                }
            }



        if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L() && this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == 0  && this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == 0)
                {
                this.linearLayoutExcedente.setVisibility(View.VISIBLE);
                this.linearLayoutEntregaBidones20L.setVisibility(View.GONE);
                this.linearLayoutEntregaBidones12L.setVisibility(View.GONE);
                this.textViewEntregaBidones.setText("Entrega de Bidones Completa");
                this.textViewEntrega20LCompleta.setVisibility(View.VISIBLE);
                this.textViewEntrega12LCompleta.setVisibility(View.VISIBLE);
                }
            else
                {
                this.linearLayoutExcedente.setVisibility(View.GONE);

                if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == 0)
                    {
                    this.linearLayoutEntregaBidones20L.setVisibility(View.GONE);
                    this.textViewEntrega20LCompleta.setVisibility(View.VISIBLE);
                    }
                if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == 0)
                    {
                    this.linearLayoutEntregaBidones12L.setVisibility(View.GONE);
                    this.textViewEntrega12LCompleta.setVisibility(View.VISIBLE);
                    }

                }


            }
        else if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
            {
            this.linearLayoutEntregaBidones12L.setVisibility(View.GONE);
            if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == 0)
                {
                this.linearLayoutEntregaBidones20L.setVisibility(View.GONE);
                this.textViewEntrega20LCompleta.setVisibility(View.VISIBLE);
                this.linearLayoutExcedente.setVisibility(View.VISIBLE);
                }
            }
        else if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            this.linearLayoutEntregaBidones20L.setVisibility(View.GONE);
            if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == 0)
                {
                this.linearLayoutEntregaBidones12L.setVisibility(View.GONE);
                this.textViewEntrega12LCompleta.setVisibility(View.VISIBLE);
                this.linearLayoutExcedente.setVisibility(View.VISIBLE);
                }
            }
        else
            {

            }

        verificarPresenciaExcedente();

        }
    catch (Exception e)
        {

        }

    }

    //////////////////////////////////////////////////////



    class ListenerEditTextsEntrega extends ListenerEditText
    {

        public ListenerEditTextsEntrega(){}

        @Override
        public void afterTextChanged(Editable editable)
        {
            if(estadoEventosEntrega)
                listenerEditTextsEntrega();
        }

    }


    private boolean estadoEventosEntrega = true;

    private void listenerEditTextsEntrega()
    {
    try
        {


        if(editTextBidones20L.getText().toString().length()>0){this.alquilerNew.getRetornables().setBidones20L(Integer.valueOf(editTextBidones20L.getText().toString()));}
        if(editTextBidones12L.getText().toString().length()>0){this.alquilerNew.getRetornables().setBidones12L(Integer.valueOf(editTextBidones12L.getText().toString()));}

        if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
            {
            if(this.alquilerNew.getRetornables().getBidones20L() > this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L())
                {
                estadoEventosEntrega=false;
                this.editTextBidones20L.setText("");
                this.alquilerNew.getRetornables().setBidones20L(0);
                estadoEventosEntrega=true;
                }
            }

        if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            if(this.alquilerNew.getRetornables().getBidones12L() > this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L())
                {
                estadoEventosEntrega=false;
                this.editTextBidones12L.setText("");
                this.alquilerNew.getRetornables().setBidones12L(0);
                estadoEventosEntrega=true;
                }
            }

        verificarPresenciaExcedente();

        }
    catch (Exception e)
    {}


    }

    private void verificarPresenciaExcedente()
    {
    if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L() && this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
    {
        if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == this.alquilerNew.getRetornables().getBidones20L()  && this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == this.alquilerNew.getRetornables().getBidones12L())
        {
            this.linearLayoutExcedente.setVisibility(View.VISIBLE);
        }
        else
        {
            this.linearLayoutExcedente.setVisibility(View.GONE);
            borrarDatosExcedente();
        }
    }
    else if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
    {
        if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == this.alquilerNew.getRetornables().getBidones20L())
        {
            this.linearLayoutExcedente.setVisibility(View.VISIBLE);
        }
        else
        {
            this.linearLayoutExcedente.setVisibility(View.GONE);
            borrarDatosExcedente();
        }
    }
    else if(this.cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
    {
        if(this.cliente.getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == this.alquilerNew.getRetornables().getBidones12L())
        {
            this.linearLayoutExcedente.setVisibility(View.VISIBLE);
        }
        else
        {
            this.linearLayoutExcedente.setVisibility(View.GONE);
            borrarDatosExcedente();
        }
    }
    else
    {

    }
    }

    private void borrarDatosExcedente()
    {
    this.estadoEventosExcedente = false;
    this.alquilerNew.getExcedenteAlquiler().limpiar();
    this.editTextExcedenteBidones20L.setText("");
    this.editTextExcedenteBidones20LACobrar.setText("");
    this.editTextExcedenteBidones12L.setText("");
    this.editTextExcedenteBidones12LACobrar.setText("");
    this.textViewDineroVenta.setText("Dinero: ");
    this.estadoEventosExcedente = true;
    }



    class ListenerEditTextsExcedente extends ListenerEditText
    {

        public ListenerEditTextsExcedente(){}

        @Override
        public void afterTextChanged(Editable editable)
        {
            if(estadoEventosExcedente)
                listenerEditTextsExcedente();
        }

    }


    private boolean estadoEventosExcedente = true;


    private void listenerEditTextsExcedente()
    {

        try
        {
            actualizarExcedenteAlquiler();
            this.textViewDineroVenta.setText("Dinero: " + this.alquilerNew.getExcedenteAlquiler().getDineroVenta() +" $");
        }
        catch (Exception e)
        {
        }

    }



    private void actualizarExcedenteAlquiler()throws Exception
    {
    if(editTextExcedenteBidones20L.getText().toString().length()>0){this.alquilerNew.getExcedenteAlquiler().getRetornables().setBidones20L(Integer.valueOf(editTextExcedenteBidones20L.getText().toString()));}
    else{this.alquilerNew.getExcedenteAlquiler().getRetornables().setBidones20L(0);}

    if(editTextExcedenteBidones20LACobrar.getText().toString().length()>0){this.alquilerNew.getExcedenteAlquiler().getRetornablesDeudados().setBidones20L(Integer.valueOf(editTextExcedenteBidones20LACobrar.getText().toString()));}
    else{this.alquilerNew.getExcedenteAlquiler().getRetornablesDeudados().setBidones20L(0);}

    if(editTextExcedenteBidones12L.getText().toString().length()>0){this.alquilerNew.getExcedenteAlquiler().getRetornables().setBidones12L(Integer.valueOf(editTextExcedenteBidones12L.getText().toString()));}
    else{this.alquilerNew.getExcedenteAlquiler().getRetornables().setBidones12L(0);}

    if(editTextExcedenteBidones12LACobrar.getText().toString().length()>0){this.alquilerNew.getExcedenteAlquiler().getRetornablesDeudados().setBidones12L(Integer.valueOf(editTextExcedenteBidones12LACobrar.getText().toString()));}
    else{this.alquilerNew.getExcedenteAlquiler().getRetornablesDeudados().setBidones12L(0);}


    this.alquilerNew.getExcedenteAlquiler().setDineroVenta();


    }






    ///////////////////////////////////////////////////////////////////////////////////////////////////






    static class CodigosEntregaAlquiler extends CodigosActividades
    {
    public static int Guardar = 4;
    }




    ///// GUARDAR


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
        actualizarAlquiler();
        if(this.alquilerNew.getEstado())
            {
            if(this.alquilerNew.have())
                {
                if(this.alquilerNew.evaluar())
                    {
                    Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().restarRetornablesEntregados(this.alquilerOld.getRetornables());
                    this.alquilerOld.copiar(this.alquilerNew);
                    this.alquilerOld.getExcedenteAlquiler().modificar();
                    this.alquilerOld.modificar();
                    this.alquilerOld.actualizar();
                    Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().sumarRetornablesEntregados(this.alquilerOld.getRetornables());

                    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                    Dialogo.aceptar("Atencion!","Entrega de Bidones guardada",this);
                    }
                else
                    {
                    entregaIncoherente();
                    }
                }
            else
                {
                Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().restarRetornablesEntregados(this.alquilerOld.getRetornables());
                this.alquilerOld.copiar(this.alquilerNew);
                this.alquilerOld.getExcedenteAlquiler().eliminar();
                this.alquilerOld.modificar();
                this.alquilerOld.actualizar();
                Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().sumarRetornablesEntregados(this.alquilerOld.getRetornables());

                Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                Dialogo.aceptar("Atencion!","Entrega de Bidones guardada",this);
                }
            }
        else
            {
                Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
            }
        }
    catch (Exception e)
        {
            Dialogo.aceptarVacioError("Atención!","Los datos ingresados no son coherentes",this);
        }
    }


    private void actualizarAlquiler()throws Exception
    {
    if(editTextBidones20L.getText().toString().length()>0){this.alquilerNew.getRetornables().setBidones20L(Integer.valueOf(editTextBidones20L.getText().toString()));}
    else{this.alquilerNew.getRetornables().setBidones20L(0);}

    if(editTextBidones12L.getText().toString().length()>0){this.alquilerNew.getRetornables().setBidones12L(Integer.valueOf(editTextBidones12L.getText().toString()));}
    else{this.alquilerNew.getRetornables().setBidones12L(0);}

    actualizarExcedenteAlquiler();
    }


    class ListenerEventoAceptar implements ListenerEventoAceptarInterfaz
    {
        @Override
        public void aceptar(EventoAceptar evento) {
            retornar();
        }
    }

    private void entregaIncoherente()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención!").setMessage(this.alquilerNew.getEvaluar())
                .setNegativeButton("No Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {guardarNo();}
                })
                .setPositiveButton("Guardar",new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int id) {guardarSi();}});
        builder.create().show();
    }

    private void guardarSi()
    {
        guardarEntrega();
    }

    private void guardarNo(){}


    private void guardarEntrega()
    {
    Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().restarRetornablesEntregados(this.alquilerOld.getRetornables());
    this.alquilerOld.copiar(this.alquilerNew);
    this.alquilerOld.getExcedenteAlquiler().modificar();
    this.alquilerOld.modificar();
    this.alquilerOld.actualizar();
    Comunicador.getReparto().getCliente().getDatosAlquiler().getEstadoAlquiler().sumarRetornablesEntregados(this.alquilerOld.getRetornables());

    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
    Dialogo.aceptar("Atencion!","Entrega de Bidones guardada",this);
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

    this.estadoEventosEntrega = false;
    this.alquilerNew.setRetornables(new Retornables());
    this.editTextBidones20L.setText("");
    this.editTextBidones12L.setText("");
    borrarDatosExcedente();
    verificarPresenciaExcedente();
    this.estadoEventosEntrega=true;
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
