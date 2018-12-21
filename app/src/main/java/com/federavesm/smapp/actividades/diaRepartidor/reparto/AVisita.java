package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorRepartidores;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorVisitas;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisita;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisitas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 24/2/2018.
 */



public class AVisita extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avisita);




        this.buttonRetornar = (Button) findViewById(R.id.aVisitaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonGuardar = (Button) findViewById(R.id.aVisitaButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());

        tipoVisitaOld = Comunicador.getReparto().getTipoVisita();

        spinnerVisitas = (Spinner) findViewById(R.id.aVisitaSpinner);
        TipoVisitas tipoVisitas = new TipoVisitas(this);
        List<TipoVisita> visitas = new ArrayList<TipoVisita>();




        if(Comunicador.getReparto().getEstadoClienteAtendido())
            {
            visitas.add(tipoVisitas.getTipoVisitas().get(0));
            adaptadorVisitas = new AdaptadorVisitas(this,visitas);
            spinnerVisitas.setAdapter(adaptadorVisitas);

            }
        else
            {
            visitas.add(tipoVisitas.getTipoVisitas().get(0));
            visitas.add(tipoVisitas.getTipoVisitas().get(1));
            visitas.add(tipoVisitas.getTipoVisitas().get(2));
            adaptadorVisitas = new AdaptadorVisitas(this,visitas);
            spinnerVisitas.setAdapter(adaptadorVisitas);
            if(tipoVisitaOld.getId() != -1)
                {
                spinnerVisitas.setSelection(tipoVisitaOld.getId()-1);
                }
            }



    }


    private TipoVisita tipoVisitaOld;


    private Spinner spinnerVisitas;
    private AdaptadorVisitas adaptadorVisitas;


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
        if(Comunicador.getReparto().getEstadoClienteAtendido() == false)
            {
            this.tipoVisitaOld.copiar(spinnerVisitas.getSelectedItem());
            Comunicador.getReparto().modificar();
            Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
            Dialogo.aceptar("Atención!","La visita se guardó correctamente",this);
            }
        }
        catch (Exception e)
        {
        Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
        }
    }



}
