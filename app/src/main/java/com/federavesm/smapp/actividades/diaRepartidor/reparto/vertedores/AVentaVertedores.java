package com.federavesm.smapp.actividades.diaRepartidor.reparto.vertedores;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
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
import com.federavesm.smapp.actividades.ListenerEditText;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores.VentaVertedores;

public class AVentaVertedores extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aventavertedores);


        this.buttonRetornar = (Button) findViewById(R.id.aVentaVertedoresButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonGuardar = (Button) findViewById(R.id.aVentaVertedoresButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        this.buttonBorrar = (Button) findViewById(R.id.aVentaVertedoresButtonBorrar);
        this.buttonBorrar.setOnClickListener(new ListenerClickButtonBorrar());

        linearLayoutPrecioEspecial = (LinearLayout) findViewById(R.id.aVentaVertedoresLinearLayoutPrecioEspecial);
        editTextCantidad = (EditText) findViewById(R.id.aVentaVertedoresEditTextCantidad);
        editTextCantidad.addTextChangedListener(new ListenerEditTexts());

        textViewDinero = (TextView) findViewById(R.id.aVentaVertedoresTextViewDinero);
        editTextPrecioEspecial = (EditText) findViewById(R.id.aVentaVertedoresEditTextPrecioEspecial);
        editTextPrecioEspecial.addTextChangedListener(new ListenerEditTexts());

        checkBoxEstadoPrecioEspecial = (CheckBox) findViewById(R.id.aVentaVertedoresCheckBoxEstadoPrecioEspecial);
        checkBoxEstadoPrecioEspecial.setOnCheckedChangeListener(new ListenerCheckedBoxEstadoPrecioEspecial());

        reparto = Comunicador.getReparto();

        ventaVertedoresOld = reparto.getVentaVertedores();

        if(ventaVertedoresOld.getEstado())
        {
            this.editTextCantidad.setText(String.valueOf(ventaVertedoresOld.getCantidad()));
            if(this.ventaVertedoresOld.getEspecial())
            {
                this.linearLayoutPrecioEspecial.setVisibility(View.VISIBLE);
                this.checkBoxEstadoPrecioEspecial.setChecked(true);
                this.editTextPrecioEspecial.setText(String.valueOf(this.ventaVertedoresOld.getPrecioespecial()));
            }
            else
            {
                this.linearLayoutPrecioEspecial.setVisibility(View.GONE);
                this.checkBoxEstadoPrecioEspecial.setChecked(false);
            }
            this.textViewDinero.setText("Dinero: "+String.valueOf(ventaVertedoresOld.getDinero()) + " $");

        }

        ventaVertedoresNew = (VentaVertedores) ventaVertedoresOld.getCopia();
        this.estadoEventos=true;

    }

    private Reparto reparto;

    VentaVertedores ventaVertedoresOld;
    VentaVertedores ventaVertedoresNew;

    private EditText editTextCantidad;
    private TextView textViewDinero;
    private EditText editTextPrecioEspecial;
    private CheckBox checkBoxEstadoPrecioEspecial;
    private LinearLayout linearLayoutPrecioEspecial;


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

    boolean estadoEventos = false;
    private void actualizarDinero()
    {

        if(estadoEventos)
        {
            try
            {
                actualizarVentaDispensers();
                if(this.ventaVertedoresNew.getEstado())
                {
                    this.textViewDinero.setText("Dinero: "+String.valueOf(ventaVertedoresNew.getDinero()) + " $");
                }
            }
            catch (Exception e)
            {

            }
        }
    }


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

    try
        {
        actualizarVentaDispensers();
        if(this.ventaVertedoresNew.getEstado())
            {
            this.textViewDinero.setText("Dinero: "+String.valueOf(ventaVertedoresNew.getDinero()) + " $");
            }
        }
    catch (Exception e)
        {

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

        if(editTextCantidad.getText().toString().length()>0){this.ventaVertedoresNew.setCantidad(Integer.valueOf(editTextCantidad.getText().toString()));}
        else{this.ventaVertedoresNew.setCantidad(0);}


        if(checkBoxEstadoPrecioEspecial.isChecked())
        {
            this.ventaVertedoresNew.setEspecial(true);
            if(editTextPrecioEspecial.getText().toString().length()>0){this.ventaVertedoresNew.setPrecioespecial(Float.valueOf(editTextPrecioEspecial.getText().toString()));}
            else{this.ventaVertedoresNew.setPrecioespecial(0);}
        }
        else
        {
            this.ventaVertedoresNew.setEspecial(false);
        }

    }


    private void guardar()
    {
        try
        {
            actualizarVentaDispensers();
            if(this.ventaVertedoresNew.getEstado())
            {
                if(ventaVertedoresNew.have())
                {
                    if(this.ventaVertedoresNew.evaluar())
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
        builder.setTitle("Atención!").setMessage(this.ventaVertedoresNew.getEvaluar())
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
        this.ventaVertedoresOld.copiar(this.ventaVertedoresNew);
        if(this.ventaVertedoresOld.modificar())
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
        this.ventaVertedoresOld.copiar(this.ventaVertedoresNew);
        if(this.ventaVertedoresOld.eliminar())
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
        this.ventaVertedoresNew.limpiar();
        this.linearLayoutPrecioEspecial.setVisibility(View.GONE);
        this.checkBoxEstadoPrecioEspecial.setChecked(false);
        this.editTextPrecioEspecial.setText("");
        this.editTextCantidad.setText("");
        this.textViewDinero.setText("");
    }




}

