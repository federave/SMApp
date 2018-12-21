package com.federavesm.smapp.actividades.diaRepartidor.reparto;

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
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.deudaProductos.DeudaProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

/**
 * Created by Federico on 24/2/2018.
 */


public class ADeudaProductos extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arepartoproductos);


        this.linearLayoutContenedor = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutContenedor);
        this.linearLayoutContenedor.setBackgroundResource(R.drawable.fondodeuda);


        this.textViewTitulo = (TextView) findViewById(R.id.aRepartoProductosTextViewTitulo);
        this.textViewTitulo.setText("Deuda Productos");

        this.editTextBidones20L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones20L);
        this.editTextBidones12L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones12L);
        this.editTextBidones10L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones10L);
        this.editTextBidones8L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones8L);
        this.editTextBidones5L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones5L);
        this.editTextPackBotellas2L = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas2L);
        this.editTextPackBotellas500mL = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas500mL);


        this.textViewDinero = (TextView) findViewById(R.id.aRepartoProductosTextViewDinero);



        this.buttonGuardar = (Button) findViewById(R.id.aRepartoProductosButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());
        this.buttonGuardar.setText("Guardar Deuda");

        this.buttonRetornar = (Button) findViewById(R.id.aRepartoProductosButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.linearLayoutBonificados = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBonificados);
        this.linearLayoutBonificados.setVisibility(View.GONE);

        this.buttonBorrarDatos = (Button) findViewById(R.id.aRepartoProductosButtonBorrarDatos);
        this.buttonBorrarDatos.setOnClickListener(new ListenerClickButtonBorrarDatos());


        this.deudaProductos = (DeudaProductos) Comunicador.getReparto().getDeudaProductos().getCopia();

        this.reparto = Comunicador.getReparto();
        this.deudaProductos =  this.reparto.getDeudaProductos();
        this.deudaProductosAux = (DeudaProductos) this.deudaProductos.getCopia();


        this.linearLayoutBidones20L = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones20L);
        this.linearLayoutBidones12L = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones12L);



        if((this.reparto.getCliente().getDatosAlquiler().getEstado()))
            {
            linearLayoutBidones20L.setVisibility(View.GONE);
            linearLayoutBidones12L.setVisibility(View.GONE);
            }



        cargarEditTexts();


        this.editTextBidones20L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones12L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones10L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones8L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones5L.addTextChangedListener(new ListenerEditTexts());
        this.editTextPackBotellas2L.addTextChangedListener(new ListenerEditTexts());
        this.editTextPackBotellas500mL.addTextChangedListener(new ListenerEditTexts());


    }

    private DeudaProductos deudaProductos;


    private DeudaProductos deudaProductosAux;
    private Reparto reparto;




    boolean estadoEventos = false;



    private LinearLayout linearLayoutContenedor;

    private EditText editTextBidones20L;
    private EditText editTextBidones12L;
    private EditText editTextBidones10L;
    private EditText editTextBidones8L;
    private EditText editTextBidones5L;
    private EditText editTextPackBotellas2L;
    private EditText editTextPackBotellas500mL;



    private TextView textViewTitulo;
    private TextView textViewDinero;
    private LinearLayout linearLayoutBonificados;

    private LinearLayout linearLayoutBidones20L;
    private LinearLayout linearLayoutBidones12L;

    //////////////////////////////////////////////////


    private void cargarEditTexts()
    {
        try
        {
        if(this.deudaProductos.getRetornables().getBidones20L() > 0)
            {
            this.editTextBidones20L.setText(String.valueOf(this.deudaProductos.getRetornables().getBidones20L()));
            }
        if(this.deudaProductos.getRetornables().getBidones12L() > 0)
            {
            this.editTextBidones12L.setText(String.valueOf(this.deudaProductos.getRetornables().getBidones12L()));
            }
        if(this.deudaProductos.getDescartables().getBidones10L() > 0)
            {
            this.editTextBidones10L.setText(String.valueOf(this.deudaProductos.getDescartables().getBidones10L()));
            }
        if(this.deudaProductos.getDescartables().getBidones8L()> 0)
            {
            this.editTextBidones8L.setText(String.valueOf(this.deudaProductos.getDescartables().getBidones8L()));
            }
        if(this.deudaProductos.getDescartables().getBidones5L() > 0)
            {
            this.editTextBidones5L.setText(String.valueOf(this.deudaProductos.getDescartables().getBidones5L()));
            }
        if(this.deudaProductos.getDescartables().getPackBotellas2L() > 0)
            {
            this.editTextPackBotellas2L.setText(String.valueOf(this.deudaProductos.getDescartables().getPackBotellas2L()));
            }
        if(this.deudaProductos.getDescartables().getPackBotellas500mL()> 0)
            {
            this.editTextPackBotellas500mL.setText(String.valueOf(this.deudaProductos.getDescartables().getPackBotellas500mL()));
            }

        if(this.deudaProductos.getDinero()>0)
            {
            this.textViewDinero.setText("Dinero: " + String.valueOf(this.deudaProductos.getDinero()) + " $");
            }

        }
        catch (Exception e)
        {
            String s=e.toString();
        }
    }


    class ListenerEditTexts extends ListenerEditText
    {

        public ListenerEditTexts()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            actualizarDinero();
        }

    }

    private void actualizarDinero()
    {
    try
        {
        actualizarDeudaProductos();
        if(this.deudaProductosAux.getEstado())
            {
            this.textViewDinero.setText("Dinero: " + String.valueOf(this.deudaProductosAux.getDinero()) + " $");
            }
        }
    catch (Exception e)
        {
        }
    }

    private void actualizarDeudaProductos() throws Exception
    {
    if(editTextBidones20L.getText().toString().length()>0){this.deudaProductosAux.getRetornables().setBidones20L(Integer.valueOf(editTextBidones20L.getText().toString()));}
    else{this.deudaProductosAux.getRetornables().setBidones20L(0);}

    if(editTextBidones12L.getText().toString().length()>0){this.deudaProductosAux.getRetornables().setBidones12L(Integer.valueOf(editTextBidones12L.getText().toString()));}
    else{this.deudaProductosAux.getRetornables().setBidones12L(0);}

    if(editTextBidones10L.getText().toString().length()>0){this.deudaProductosAux.getDescartables().setBidones10L(Integer.valueOf(editTextBidones10L.getText().toString()));}
    else{this.deudaProductosAux.getDescartables().setBidones10L(0);}

    if(editTextBidones8L.getText().toString().length()>0){this.deudaProductosAux.getDescartables().setBidones8L(Integer.valueOf(editTextBidones8L.getText().toString()));}
    else{this.deudaProductosAux.getDescartables().setBidones8L(0);}

    if(editTextBidones5L.getText().toString().length()>0){this.deudaProductosAux.getDescartables().setBidones5L(Integer.valueOf(editTextBidones5L.getText().toString()));}
    else{this.deudaProductosAux.getDescartables().setBidones5L(0);}

    if(editTextPackBotellas2L.getText().toString().length()>0){this.deudaProductosAux.getDescartables().setPackBotellas2L(Integer.valueOf(editTextPackBotellas2L.getText().toString()));}
    else{this.deudaProductosAux.getDescartables().setPackBotellas2L(0);}

    if(editTextPackBotellas500mL.getText().toString().length()>0){this.deudaProductosAux.getDescartables().setPackBotellas500mL(Integer.valueOf(editTextPackBotellas500mL.getText().toString()));}
    else{this.deudaProductosAux.getDescartables().setPackBotellas500mL(0);}

    }




    //////////////////////////////////////////////////
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
        this.estadoEventos=false;

        this.editTextBidones20L.setText("");
        this.editTextBidones12L.setText("");
        this.editTextBidones10L.setText("");
        this.editTextBidones8L.setText("");
        this.editTextBidones5L.setText("");
        this.editTextPackBotellas2L.setText("");
        this.editTextPackBotellas500mL.setText("");

        this.textViewDinero.setText("Dinero: ");
        this.deudaProductosAux.borrarDatos();



        this.estadoEventos=true;

    }










    //////////////////////////////////////////////////

    static class CodigosDeudaProductos extends CodigosActividades
    {
    public static int Guardar = 6;
    }



    //////////////////////////////////////////////////
    ///// GUARDAR

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
            actualizarDeudaProductos();
            if(this.deudaProductosAux.getEstado())
            {
                if(deudaProductosAux.have())
                {
                    if(this.deudaProductosAux.evaluar())
                    {
                    guardarDeudaProductos();


                    }
                    else
                    {
                        deudaIncoherente();
                    }
                }
            else
                {
                eliminarDeudaProductos();
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



    private void deudaIncoherente()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención!")
                .setMessage(this.deudaProductosAux.getEvaluar())
                .setNegativeButton("No Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        guardarNo();
                    }
                })
                .setPositiveButton("Guardar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        guardarSi();
                    }
                });

        builder.create().show();
    }

    private void guardarSi()
    {
        guardarDeudaProductos();
    }


    private void guardarNo()
    {
    }



    private void guardarDeudaProductos()
    {
    this.deudaProductos.copiar(this.deudaProductosAux);
    if(this.deudaProductos.modificar())
        {
        reparto.actualizar();
        setResult(CodigosDeudaProductos.Guardar,new Intent());
        this.finish();
        }
    else
        {
            Dialogo.aceptarVacioError("Atención!","La deuda no se guardo correctamente",this);
        }
    }


    private void eliminarDeudaProductos()
    {
    this.deudaProductos.copiar(this.deudaProductosAux);
    if(this.deudaProductos.eliminar())
        {
        reparto.actualizar();
        setResult(CodigosDeudaProductos.Guardar,new Intent());
        this.finish();
        }
    else
        {
            Dialogo.aceptarVacioError("Atención!","La deuda no se guardo correctamente",this);
        }
    }













    //////////////////////////////////////////////////
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