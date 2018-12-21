package com.federavesm.smapp.actividades.diaRepartidor.gastos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.EventoAceptar;
import com.federavesm.smapp.actividades.ListenerEditText;
import com.federavesm.smapp.actividades.ListenerEventoAceptarInterfaz;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gasto;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gastos;

/**
 * Created by Federico on 12/3/2018.
 */


public class AGasto extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agasto);

        checkBoxCombustible = (CheckBox) findViewById(R.id.aGastoCheckBoxCombustible);
        checkBoxCombustibleConTarjeta = (CheckBox) findViewById(R.id.aGastoCheckBoxCombustibleConTarjeta);

        editTextDescripcion = (EditText) findViewById(R.id.aGastoEditTextDescripcion);
        editTextDinero = (EditText) findViewById(R.id.aGastoEditTextDinero);

        buttonRetornar = (Button) findViewById(R.id.aGastoButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        buttonGuardar = (Button) findViewById(R.id.aGastoButtonGuardar);
        buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        buttonBorrar = (Button) findViewById(R.id.aGastoButtonBorrar);
        buttonBorrar.setOnClickListener(new ListenerClickButtonBorrar());

        this.gastos = Comunicador.getDiaRepartidor().getGastos();


        if(Comunicador.getNuevoGasto() == false)
            {
            this.gastoOld = Comunicador.getGasto();
            this.editTextDescripcion.setText(this.gastoOld.getDescripcion());
            this.editTextDinero.setText(String.valueOf(this.gastoOld.getDinero()));
            this.checkBoxCombustible.setChecked(this.gastoOld.getCombustible());
            this.checkBoxCombustibleConTarjeta.setChecked(this.gastoOld.getCombustibleConTarjeta());
            }
        else
            {
            this.gastoOld = new Gasto(this);
            this.gastoOld.setIdDiaRepartidor(Comunicador.getDiaRepartidor().getId());
            }

        this.gastoNew = (Gasto)this.gastoOld.getCopia();

        this.editTextDinero.addTextChangedListener(new ListenerEditTextDinero());
        this.checkBoxCombustible.setOnCheckedChangeListener(new ListenerCheckedBoxCombustible());
        this.checkBoxCombustibleConTarjeta.setOnCheckedChangeListener(new ListenerCheckedBoxCombustibleConTarjeta());




    }


    private CheckBox checkBoxCombustible;
    private CheckBox checkBoxCombustibleConTarjeta;

    private EditText editTextDescripcion;
    private EditText editTextDinero;

    private Gasto gastoOld;
    private Gasto gastoNew;

    private Gastos gastos;



    class ListenerCheckedBoxCombustible implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(estadoEventosChecked)
                checkedChangedCombustible();
        }


    }

    private boolean estadoEventosChecked=true;

    private void checkedChangedCombustible()
    {
    if(checkBoxCombustible.isChecked())
        {
        estadoEventosChecked=false;
        checkBoxCombustibleConTarjeta.setChecked(false);
        editTextDinero.setVisibility(View.VISIBLE);
        estadoEventosChecked=true;
        }

    }


    class ListenerCheckedBoxCombustibleConTarjeta implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(estadoEventosChecked)
                checkedChangedCombustibleConTarjeta();
        }


    }


    private void checkedChangedCombustibleConTarjeta()
    {

        if(checkBoxCombustibleConTarjeta.isChecked())
        {
            estadoEventosChecked=false;
            checkBoxCombustible.setChecked(false);
            editTextDinero.setVisibility(View.GONE);
            estadoEventosChecked=true;
        }
        else
            {
                editTextDinero.setVisibility(View.VISIBLE);

            }


    }








    boolean estadoEventos = true;
    class ListenerEditTextDinero extends ListenerEditText
    {

        public ListenerEditTextDinero()
        {
        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            chequearDinero();
        }

    }

    private void chequearDinero() {
        if (estadoEventos) {

            try
                {
                float dinero = Float.valueOf(this.editTextDinero.getText().toString());
                if(dinero<0)
                    {
                    this.estadoEventos = false;
                    this.editTextDinero.setText("");
                    this.estadoEventos = true;
                    }


                }
            catch (Exception e)
                {

                }


        }
    }






    ////// GUARDAR

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
        actualizarGasto();
        if(Comunicador.getNuevoGasto())
            {
            if(this.gastoNew.getEstado())
                {
                if(this.gastoNew.evaluar())
                    {
                    this.gastoOld.copiar(this.gastoNew);
                    if(this.gastoOld.guardar())
                        {
                        this.gastos.getGastos().add(this.gastoOld);
                        this.gastos.actualizar();
                        Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoGuardar());
                        Dialogo.aceptar("Atención!","Gasto guardado",this);
                        }
                    else
                        {
                        Dialogo.aceptarVacioError("Atención!","El gasto no se guardó correctamente",this);
                        }
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!",this.gastoNew.getEvaluar(),this);
                    }
                }
            else
                {
                Dialogo.aceptarVacioError("Atención!","No hay datos para guardar",this);
                }
            }
        else
            {
            if(this.gastoNew.getEstado())
                {
                if(this.gastoNew.evaluar())
                    {
                    this.gastoOld.copiar(this.gastoNew);
                    if(this.gastoOld.modificar())
                        {
                        this.gastos.actualizar();
                        Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoModificar());
                        Dialogo.aceptar("Atención!","Gasto guardado",this);
                        }
                    else
                        {
                        Dialogo.aceptarVacioError("Atención!","El gasto no se guardó correctamente",this);
                        }
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!",this.gastoNew.getEvaluar(),this);
                    }
                }
            else
                {
                this.gastoOld.copiar(this.gastoNew);
                if(this.gastoOld.eliminar())
                    {
                    this.gastos.getGastos().remove(this.gastoOld);
                    this.gastos.actualizar();
                    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoEliminar());
                    Dialogo.aceptar("Atención!","Gasto guardado",this);
                    }
                else
                    {
                    Dialogo.aceptarVacioError("Atención!","El gasto no se guardó correctamente",this);
                    }
                }
            }
        }
    catch (Exception e)
        {
        Dialogo.aceptarVacioError("Atención!","El formato de datos no es correcto",this);
        }


    }




    public class ListenerEventoGuardar implements ListenerEventoAceptarInterfaz
    {
    @Override
    public void aceptar(EventoAceptar evento) {
            retornarGuardar();
        }
    }

    private void retornarGuardar()
    {
    setResult(CodigosGasto.Nuevo,new Intent());
    this.finish();
    }


    public class ListenerEventoModificar implements ListenerEventoAceptarInterfaz
    {
        @Override
        public void aceptar(EventoAceptar evento) {
            modificarGuardar();
        }
    }

    private void modificarGuardar()
    {
        setResult(CodigosGasto.Modificado,new Intent());
        this.finish();
    }


    public class ListenerEventoEliminar implements ListenerEventoAceptarInterfaz
    {
        @Override
        public void aceptar(EventoAceptar evento) {
            retornarEliminar();
        }
    }

    private void retornarEliminar()
    {
    setResult(CodigosGasto.Eliminado,new Intent());
    this.finish();
    }



    static class CodigosGasto extends CodigosActividades
    {
    public static int Nuevo =4;
    public static int Modificado = 6;
    public static int Eliminado = 7;
    }





    private void actualizarGasto()throws Exception
    {

    this.gastoNew.setCombustible(checkBoxCombustible.isChecked());
    this.gastoNew.setCombustibleConTarjeta(checkBoxCombustibleConTarjeta.isChecked());
    this.gastoNew.setDescripcion(editTextDescripcion.getText().toString());

    if(this.editTextDinero.getText().length()>0)
        this.gastoNew.setDinero(Float.valueOf(this.editTextDinero.getText().toString()));
    else
        this.gastoNew.setDinero(0);

    }



    ////// BORRAR DATOS

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
    this.estadoEventos = false;
    this.gastoNew.limpiar();
    this.editTextDescripcion.setText("");
    this.editTextDinero.setText("");
    this.checkBoxCombustible.setChecked(false);
    this.checkBoxCombustibleConTarjeta.setChecked(false);
    this.estadoEventos = true;
    }










}
