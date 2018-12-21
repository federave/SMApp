package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.federavesm.smapp.actividades.diaRepartidor.cargas.ACarga;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

/**
 * Created by Federico on 24/2/2018.
 */



public class AVentaProductos extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arepartoproductos);


        this.textViewTitulo = (TextView) findViewById(R.id.aRepartoProductosTextViewTitulo);
        this.textViewTitulo.setText("Venta Productos");

        this.editTextBidones20L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones20L);
        this.editTextBidones12L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones12L);
        this.editTextBidones10L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones10L);
        this.editTextBidones8L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones8L);
        this.editTextBidones5L = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones5L);
        this.editTextPackBotellas2L = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas2L);
        this.editTextPackBotellas500mL = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas500mL);

        this.editTextBidones20LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones20LBonificados);
        this.editTextBidones12LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones12LBonificados);
        this.editTextBidones10LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones10LBonificados);
        this.editTextBidones8LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones8LBonificados);
        this.editTextBidones5LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextBidones5LBonificados);
        this.editTextPackBotellas2LBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas2LBonificados);
        this.editTextPackBotellas500mLBonificados = (EditText) findViewById(R.id.aRepartoProductosEditTextPackBotellas500mLBonificados);




        this.textViewDinero = (TextView) findViewById(R.id.aRepartoProductosTextViewDinero);




        this.buttonGuardar = (Button) findViewById(R.id.aRepartoProductosButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());
        this.buttonGuardar.setText("Guardar Venta");

        this.buttonRetornar = (Button) findViewById(R.id.aRepartoProductosButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonBorrarDatos = (Button) findViewById(R.id.aRepartoProductosButtonBorrarDatos);
        this.buttonBorrarDatos.setOnClickListener(new ListenerClickButtonBorrarDatos());




        this.linearLayoutBonificados = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBonificados);
        this.linearLayoutBonificados.setVisibility(View.VISIBLE);

        this.reparto = Comunicador.getReparto();
        this.ventaProductos =  this.reparto.getVentaProductos();
        this.ventaProductosAux = (VentaProductos)this.ventaProductos.getCopia();


        this.linearLayoutBidones20L = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones20L);
        this.linearLayoutBidones12L = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones12L);

        this.linearLayoutBidones20LBonificados = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones20LBonificados);
        this.linearLayoutBidones12LBonificados = (LinearLayout) findViewById(R.id.aRepartoProductosLinearLayoutBidones12LBonificados);



        if((this.reparto.getCliente().getDatosAlquiler().getEstado()))
            {
            linearLayoutBidones20L.setVisibility(View.GONE);
            linearLayoutBidones12L.setVisibility(View.GONE);
            linearLayoutBidones20LBonificados.setVisibility(View.GONE);
            linearLayoutBidones12LBonificados.setVisibility(View.GONE);
            }





        cargarEditTexts();

        this.editTextBidones20L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones12L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones10L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones8L.addTextChangedListener(new ListenerEditTexts());
        this.editTextBidones5L.addTextChangedListener(new ListenerEditTexts());
        this.editTextPackBotellas2L.addTextChangedListener(new ListenerEditTexts());
        this.editTextPackBotellas500mL.addTextChangedListener(new ListenerEditTexts());

        this.estadoEventos=true;
    }

    private VentaProductos ventaProductos;
    private VentaProductos ventaProductosAux;

    private Reparto reparto;







    private EditText editTextBidones20L;
    private EditText editTextBidones12L;
    private EditText editTextBidones10L;
    private EditText editTextBidones8L;
    private EditText editTextBidones5L;
    private EditText editTextPackBotellas2L;
    private EditText editTextPackBotellas500mL;

    private EditText editTextBidones20LBonificados;
    private EditText editTextBidones12LBonificados;
    private EditText editTextBidones10LBonificados;
    private EditText editTextBidones8LBonificados;
    private EditText editTextBidones5LBonificados;
    private EditText editTextPackBotellas2LBonificados;
    private EditText editTextPackBotellas500mLBonificados;


    private TextView textViewTitulo;
    private TextView textViewDinero;
    private LinearLayout linearLayoutBonificados;

    private LinearLayout linearLayoutBidones20L;
    private LinearLayout linearLayoutBidones12L;
    private LinearLayout linearLayoutBidones20LBonificados;
    private LinearLayout linearLayoutBidones12LBonificados;


    //////////////////////////////////////////////////


    private void cargarEditTexts()
    {
    try
        {
        if(this.ventaProductos.getRetornables().getBidones20L() > 0)
            {
            this.editTextBidones20L.setText(String.valueOf(this.ventaProductos.getRetornables().getBidones20L()));
            }
        if(this.ventaProductos.getRetornables().getBidones12L() > 0)
            {
            this.editTextBidones12L.setText(String.valueOf(this.ventaProductos.getRetornables().getBidones12L()));
            }
        if(this.ventaProductos.getDescartables().getBidones10L() > 0)
            {
            this.editTextBidones10L.setText(String.valueOf(this.ventaProductos.getDescartables().getBidones10L()));
            }
        if(this.ventaProductos.getDescartables().getBidones8L()> 0)
            {
            this.editTextBidones8L.setText(String.valueOf(this.ventaProductos.getDescartables().getBidones8L()));
            }
        if(this.ventaProductos.getDescartables().getBidones5L() > 0)
            {
            this.editTextBidones5L.setText(String.valueOf(this.ventaProductos.getDescartables().getBidones5L()));
            }
        if(this.ventaProductos.getDescartables().getPackBotellas2L() > 0)
            {
            this.editTextPackBotellas2L.setText(String.valueOf(this.ventaProductos.getDescartables().getPackBotellas2L()));
            }
        if(this.ventaProductos.getDescartables().getPackBotellas500mL()> 0)
            {
            this.editTextPackBotellas500mL.setText(String.valueOf(this.ventaProductos.getDescartables().getPackBotellas500mL()));
            }
        if(this.ventaProductos.getRetornablesBonificados().getBidones20L() > 0)
            {
            this.editTextBidones20LBonificados.setText(String.valueOf(this.ventaProductos.getRetornablesBonificados().getBidones20L()));
            }
        if(this.ventaProductos.getRetornablesBonificados().getBidones12L() > 0)
            {
            this.editTextBidones12LBonificados.setText(String.valueOf(this.ventaProductos.getRetornablesBonificados().getBidones12L()));
            }
        if(this.ventaProductos.getDescartablesBonificados().getBidones10L() > 0)
            {
            this.editTextBidones10LBonificados.setText(String.valueOf(this.ventaProductos.getDescartablesBonificados().getBidones10L()));
            }
        if(this.ventaProductos.getDescartablesBonificados().getBidones8L()> 0)
            {
            this.editTextBidones8LBonificados.setText(String.valueOf(this.ventaProductos.getDescartablesBonificados().getBidones8L()));
            }
        if(this.ventaProductos.getDescartablesBonificados().getBidones5L() > 0)
            {
            this.editTextBidones5LBonificados.setText(String.valueOf(this.ventaProductos.getDescartablesBonificados().getBidones5L()));
            }
        if(this.ventaProductos.getDescartablesBonificados().getPackBotellas2L() > 0)
            {
            this.editTextPackBotellas2LBonificados.setText(String.valueOf(this.ventaProductos.getDescartablesBonificados().getPackBotellas2L()));
            }
        if(this.ventaProductos.getDescartablesBonificados().getPackBotellas500mL()> 0)
            {
            this.editTextPackBotellas500mLBonificados.setText(String.valueOf(this.ventaProductos.getDescartablesBonificados().getPackBotellas500mL()));
            }
        if(this.ventaProductos.getDinero()>0)
            {
            this.textViewDinero.setText("Dinero: " + String.valueOf(this.ventaProductos.getDinero()) + " $");
            }



        }
    catch (Exception e)
        {
        String s=e.toString();
        }
    }

    boolean estadoEventos = false;
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

    if(estadoEventos)
    {
        try
        {
            actualizarVentaProductos();
            if(this.ventaProductosAux.getEstado())
            {
                this.textViewDinero.setText("Dinero: " + String.valueOf(this.ventaProductosAux.getDinero()) + " $");
            }
        }
        catch (Exception e)
        {

        }

    }


    }

    private void actualizarVentaProductos() throws Exception
    {

    if(editTextBidones20L.getText().toString().length()>0){this.ventaProductosAux.getRetornables().setBidones20L(Integer.valueOf(editTextBidones20L.getText().toString()));}
    else{this.ventaProductosAux.getRetornables().setBidones20L(0);}

    if(editTextBidones12L.getText().toString().length()>0){this.ventaProductosAux.getRetornables().setBidones12L(Integer.valueOf(editTextBidones12L.getText().toString()));}
    else{this.ventaProductosAux.getRetornables().setBidones12L(0);}

    if(editTextBidones10L.getText().toString().length()>0){this.ventaProductosAux.getDescartables().setBidones10L(Integer.valueOf(editTextBidones10L.getText().toString()));}
    else{this.ventaProductosAux.getDescartables().setBidones10L(0);}

    if(editTextBidones8L.getText().toString().length()>0){this.ventaProductosAux.getDescartables().setBidones8L(Integer.valueOf(editTextBidones8L.getText().toString()));}
    else{this.ventaProductosAux.getDescartables().setBidones8L(0);}

    if(editTextBidones5L.getText().toString().length()>0){this.ventaProductosAux.getDescartables().setBidones5L(Integer.valueOf(editTextBidones5L.getText().toString()));}
    else{this.ventaProductosAux.getDescartables().setBidones5L(0);}

    if(editTextPackBotellas2L.getText().toString().length()>0){this.ventaProductosAux.getDescartables().setPackBotellas2L(Integer.valueOf(editTextPackBotellas2L.getText().toString()));}
    else{this.ventaProductosAux.getDescartables().setPackBotellas2L(0);}

    if(editTextPackBotellas500mL.getText().toString().length()>0){this.ventaProductosAux.getDescartables().setPackBotellas500mL(Integer.valueOf(editTextPackBotellas500mL.getText().toString()));}
    else{this.ventaProductosAux.getDescartables().setPackBotellas500mL(0);}

    if(editTextBidones20LBonificados.getText().toString().length()>0){this.ventaProductosAux.getRetornablesBonificados().setBidones20L(Integer.valueOf(editTextBidones20LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getRetornablesBonificados().setBidones20L(0);}

    if(editTextBidones12LBonificados.getText().toString().length()>0){this.ventaProductosAux.getRetornablesBonificados().setBidones12L(Integer.valueOf(editTextBidones12LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getRetornablesBonificados().setBidones12L(0);}

    if(editTextBidones10LBonificados.getText().toString().length()>0){this.ventaProductosAux.getDescartablesBonificados().setBidones10L(Integer.valueOf(editTextBidones10LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getDescartablesBonificados().setBidones10L(0);}

    if(editTextBidones8LBonificados.getText().toString().length()>0){this.ventaProductosAux.getDescartablesBonificados().setBidones8L(Integer.valueOf(editTextBidones8LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getDescartablesBonificados().setBidones8L(0);}

    if(editTextBidones5LBonificados.getText().toString().length()>0){this.ventaProductosAux.getDescartablesBonificados().setBidones5L(Integer.valueOf(editTextBidones5LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getDescartablesBonificados().setBidones5L(0);}

    if(editTextPackBotellas2LBonificados.getText().toString().length()>0){this.ventaProductosAux.getDescartablesBonificados().setPackBotellas2L(Integer.valueOf(editTextPackBotellas2LBonificados.getText().toString()));}
    else{this.ventaProductosAux.getDescartablesBonificados().setPackBotellas2L(0);}

    if(editTextPackBotellas500mLBonificados.getText().toString().length()>0){this.ventaProductosAux.getDescartablesBonificados().setPackBotellas500mL(Integer.valueOf(editTextPackBotellas500mLBonificados.getText().toString()));}
    else{this.ventaProductosAux.getDescartablesBonificados().setPackBotellas500mL(0);}

    this.ventaProductosAux.setDinero();


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
    this.editTextBidones20LBonificados.setText("");
    this.editTextBidones12LBonificados.setText("");
    this.editTextBidones10LBonificados.setText("");
    this.editTextBidones8LBonificados.setText("");
    this.editTextBidones5LBonificados.setText("");
    this.editTextPackBotellas2LBonificados.setText("");
    this.editTextPackBotellas500mLBonificados.setText("");
    this.textViewDinero.setText("Dinero: ");
    this.ventaProductosAux.borrarDatos();



        this.estadoEventos=true;

    }






    //////////////////////////////////////////////////

    static class CodigosVentaProductos extends CodigosActividades
    {
    public static int Guardar = 4;
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
        actualizarVentaProductos();
        if(this.ventaProductosAux.getEstado())
            {
            if(ventaProductosAux.have())
                {
                if(this.ventaProductosAux.evaluar())
                    {
                    guardarVentaProductos();
                    }
                else
                    {
                    ventaIncoherente();
                    }
                }
            else
                {
                eliminarVentaProductos();
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



    private void ventaIncoherente()
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Atención!").setMessage(this.ventaProductosAux.getEvaluar())
                                 .setNegativeButton("No Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {guardarNo();}
                })
                                 .setPositiveButton("Guardar",new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int id) {guardarSi();}});
    builder.create().show();
    }

    private void guardarSi()
    {
    guardarVentaProductos();
    }

    private void guardarNo(){}


    private void guardarVentaProductos()
    {
    this.ventaProductos.copiar(this.ventaProductosAux);
    if(this.ventaProductos.modificar())
        {
        this.reparto.actualizar();
        setResult(CodigosVentaProductos.Guardar,new Intent());
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La venta no se guardó correctamente",this);
        }
    }

    private void eliminarVentaProductos()
    {
    this.ventaProductos.copiar(this.ventaProductosAux);
    if(this.ventaProductos.eliminar())
        {
        this.reparto.actualizar();
        setResult(CodigosVentaProductos.Guardar,new Intent());
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La venta no se guardó correctamente",this);
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