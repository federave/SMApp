package com.federavesm.smapp.actividades.diaRepartidor.reparto.dispensers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.CambioDispensers;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.RetiroDispensers;


public class ARetiroDispensers extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ageneraldispensadores);


        this.buttonRetornar = (Button) findViewById(R.id.aGeneralDispensadoresButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());
        this.buttonGuardar = (Button) findViewById(R.id.aGeneralDispensadoresButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());
        this.buttonBorrar = (Button) findViewById(R.id.aGeneralDispensadoresButtonBorrar);
        this.buttonBorrar.setOnClickListener(new ListenerClickButtonBorrar());
        editTextCantidad = (EditText) findViewById(R.id.aGeneralDispensadoresEditTextCantidad);
        textViewTitulo = (TextView) findViewById(R.id.aGeneralDispensadoresTextViewTitulo);

        this.textViewTitulo.setText("Retiro Dispensers");

        reparto = Comunicador.getReparto();

        datoOld = reparto.getRetiroDispensers(); // Varia

        if(datoOld.getEstado())
        {
            this.editTextCantidad.setText(String.valueOf(datoOld.getCantidad()));
        }

        datoNew = (RetiroDispensers) datoOld.getCopia();  // Varia







    }


    private Reparto reparto;
    private EditText editTextCantidad;
    private TextView textViewTitulo;
    private RetiroDispensers datoNew;
    private RetiroDispensers datoOld;






    private void actualizarDatos() throws Exception
    {

        if(editTextCantidad.getText().toString().length()>0)
        {this.datoNew.setCantidad(Integer.valueOf(editTextCantidad.getText().toString()));}
        else
        {this.datoNew.setCantidad(0);}

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

    private void guardar()
    {
        try
        {
            actualizarDatos();
            if(this.datoNew.getEstado())
            {
                if(datoNew.have())
                {
                    if(this.datoNew.evaluar())
                    {
                        guardarFinal();
                    }
                    else
                    {
                        incoherencia();
                    }
                }
                else
                {
                    eliminar();
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



    private void incoherencia()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención!").setMessage(this.datoNew.getEvaluar())
                .setNegativeButton("No Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {guardarNo();}
                })
                .setPositiveButton("Guardar",new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int id) {guardarSi();}});
        builder.create().show();
    }

    private void guardarSi()
    {
        guardarFinal();
    }

    private void guardarNo(){}


    private void guardarFinal()
    {
        this.datoOld.copiar(this.datoNew);
        if(this.datoOld.modificar())
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

    private void eliminar()
    {
        this.datoOld.copiar(this.datoNew);
        if(this.datoOld.eliminar())
        {
            this.reparto.actualizar();
            this.finish();
        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","La entrega no se guardó correctamente",this);
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
        this.datoNew.limpiar();
        this.editTextCantidad.setText("");
    }


















}

