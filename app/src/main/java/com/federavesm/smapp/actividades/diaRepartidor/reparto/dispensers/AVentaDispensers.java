package com.federavesm.smapp.actividades.diaRepartidor.reparto.dispensers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.VentaDispensers;


public class AVentaDispensers extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aventadispenser);


        this.buttonRetornar = (Button) findViewById(R.id.aVentaDispensersButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonGuardar = (Button) findViewById(R.id.aVentaDispensersButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        this.buttonBorrar = (Button) findViewById(R.id.aVentaDispensersButtonBorrar);
        this.buttonBorrar.setOnClickListener(new ListenerClickButtonBorrar());

        linearLayoutPrecioEspecial = (LinearLayout) findViewById(R.id.aVentaDispensersLinearLayoutPrecioEspecial);
        editTextCantidad = (EditText) findViewById(R.id.aVentaDispensersEditTextCantidad);
        textViewDinero = (TextView) findViewById(R.id.aVentaDispensersTextViewDinero);
        editTextPrecioEspecial = (EditText) findViewById(R.id.aVentaDispensersEditTextPrecioEspecial);
        checkBoxEstadoPrecioEspecial = (CheckBox) findViewById(R.id.aVentaDispensersCheckBoxEstadoPrecioEspecial);
        checkBoxEstadoPrecioEspecial.setOnCheckedChangeListener(new ListenerCheckedBoxEstadoPrecioEspecial());

        reparto = Comunicador.getReparto();

        ventaDispensersOld = reparto.getVentaDispensers();

        if(ventaDispensersOld.getEstado())
            {
            this.editTextCantidad.setText(String.valueOf(ventaDispensersOld.getCantidad()));
            this.textViewDinero.setText("Dinero: "+String.valueOf(ventaDispensersOld.getCantidad() * reparto.getCliente().getPrecioDispensadores().getDispenser() + " $") );
            if(this.ventaDispensersOld.getEspecial())
                {
                this.linearLayoutPrecioEspecial.setVisibility(View.VISIBLE);
                this.checkBoxEstadoPrecioEspecial.setChecked(true);
                this.editTextPrecioEspecial.setText(String.valueOf(this.ventaDispensersOld.getPrecioespecial()));
                }
            else
                {
                this.linearLayoutPrecioEspecial.setVisibility(View.GONE);
                this.checkBoxEstadoPrecioEspecial.setChecked(false);
                }

            }

        ventaDispensersNew = (VentaDispensers) ventaDispensersOld.getCopia();

    }

    private Reparto reparto;

    VentaDispensers ventaDispensersOld;
    VentaDispensers ventaDispensersNew;

    private EditText editTextCantidad;
    private TextView textViewDinero;
    private EditText editTextPrecioEspecial;
    private CheckBox checkBoxEstadoPrecioEspecial;
    private LinearLayout linearLayoutPrecioEspecial;





    class ListenerCheckedBoxEstadoPrecioEspecial implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedChangedEstadoPrecioEspecial();
        }
    }

    private void checkedChangedEstadoPrecioEspecial()
    {
    if(checkBoxEstadoPrecioEspecial.isChecked())
        {
        linearLayoutPrecioEspecial.setVisibility(View.VISIBLE);
        }
    else
        {
        linearLayoutPrecioEspecial.setVisibility(View.GONE);
        }
    }



    ///////GUARDAR

    private Button buttonGuardar;

    class ListenerClickButtonGuardar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            guardar();
        }
    }



    private void actualizarVentaDispensers() throws Exception
    {

    if(editTextCantidad.getText().toString().length()>0){this.ventaDispensersNew.setCantidad(Integer.valueOf(editTextCantidad.getText().toString()));}
    else{this.ventaDispensersNew.setCantidad(0);}

    if(checkBoxEstadoPrecioEspecial.isChecked())
        {
        this.ventaDispensersNew.setEspecial(true);
        if(editTextPrecioEspecial.getText().toString().length()>0){this.ventaDispensersNew.setPrecioespecial(Float.valueOf(editTextPrecioEspecial.getText().toString()));}
        else{this.ventaDispensersNew.setPrecioespecial(0);}
        }
    else
        {
        this.ventaDispensersNew.setEspecial(false);
        }

    }


    private void guardar()
    {
    try
        {
        actualizarVentaDispensers();
        if(this.ventaDispensersNew.getEstado())
            {
            if(ventaDispensersNew.have())
                {
                if(this.ventaDispensersNew.evaluar())
                    {
                    guardarVentaDispensers();
                    }
                else
                    {
                    ventaIncoherente();
                    }
                }
            else
                {
                eliminarVentaDispensers();
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
    builder.setTitle("Atención!").setMessage(this.ventaDispensersNew.getEvaluar())
            .setNegativeButton("No Guardar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {guardarNo();}
            })
            .setPositiveButton("Guardar",new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int id) {guardarSi();}});
    builder.create().show();
    }

    private void guardarSi()
    {
        guardarVentaDispensers();
    }

    private void guardarNo(){}


    private void guardarVentaDispensers()
    {
    this.ventaDispensersOld.copiar(this.ventaDispensersNew);
    if(this.ventaDispensersOld.modificar())
        {
        this.reparto.actualizar();
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La venta no se guardó correctamente",this);
        }
    }

    /////////////////////////////////////////

    private void eliminarVentaDispensers()
    {
    this.ventaDispensersOld.copiar(this.ventaDispensersNew);
    if(this.ventaDispensersOld.eliminar())
        {
        this.reparto.actualizar();
        this.finish();
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","La venta no se guardó correctamente",this);
        }
    }




    ///// BORRAR DATOS

    private Button buttonBorrar;

    class ListenerClickButtonBorrar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            borrar();
        }
    }


    private void borrar()
    {
    this.ventaDispensersNew.limpiar();
    this.linearLayoutPrecioEspecial.setVisibility(View.GONE);
    this.checkBoxEstadoPrecioEspecial.setChecked(false);
    this.editTextPrecioEspecial.setText("");
    this.editTextCantidad.setText("");
    this.textViewDinero.setText("");
    }







}

