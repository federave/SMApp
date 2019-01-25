package com.federavesm.smapp.actividades.configuracion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorRepartidores;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorVendedores;
import com.federavesm.smapp.modelo.BaseDeDatos;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedores;
import com.federavesm.smapp.modelo.servidor.RequerimientoGET;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.DatosBasicosClienteXML;
import com.federavesm.smapp.modelo.servidor.datosXML.InfoClientesRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;





public class AEliminarDiaRepartidor extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aeliminardiarepartidor);



        buttonEliminarDia = (Button)findViewById(R.id.aEliminarDiaRepartidorButtonEliminar);
        buttonEliminarDia.setOnClickListener(new ListenerClickButtonEliminarDia());


        buttonRetornar = (Button)findViewById(R.id.aEliminarDiaRepartidorButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        repartidores = Comunicador.getRepartidores();
        spinnerRepartidores = (Spinner) findViewById(R.id.aEliminarDiaRepartidorSpinnerRepartidores);
        adaptadorRepartidores = new AdaptadorRepartidores(this,repartidores.getRepartidores());
        spinnerRepartidores.setAdapter(adaptadorRepartidores);

        fechaSeleccionada  = (DatePicker) findViewById(R.id.aEliminarDiaRepartidorDatePicker);







    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // EN TEORIA PARA QUE NO SE BLOQUE LA PANTALLA
    }

    private Button buttonEliminarDia;

    private AdaptadorRepartidores adaptadorRepartidores;
    private Spinner spinnerRepartidores;
    private Repartidores repartidores;

    BaseDeDatos baseDeDatos = new BaseDeDatos(this);

    private DatePicker fechaSeleccionada;





    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Eliminar Dia Repartidor         --------------




    class ListenerClickButtonEliminarDia implements View.OnClickListener
    {
        public void onClick(View e)
        {
            eliminarDiaRepartidor();
        }
    }



    private void eliminarDiaRepartidor()
    {

    if(spinnerRepartidores.getSelectedItemPosition()>=0)
        {

        Fecha fecha = new Fecha(fechaSeleccionada.getYear(),fechaSeleccionada.getMonth()+1,fechaSeleccionada.getDayOfMonth());
        int idRepartidor = ((Repartidor)spinnerRepartidores.getSelectedItem()).getId();

        if(baseDeDatos.eliminarDiaRepartidor(idRepartidor,fecha))
            {
            Dialogo.aceptarVacio("Atención!","El dia se eliminó correctamente",this);
            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","No se pudo eliminar el dia",this);
            }

        }
    else
        {
        Toast.makeText(this,"there is no worker selected",Toast.LENGTH_SHORT).show();
        }


    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }



}

