package com.federavesm.smapp.actividades.diaRepartidor;

/**
 * Created by Federico on 2/1/2018.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;

/**
 * Created by Federico on 27/4/2017.
 */

public class ASeleccionarDiaRepartidor extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aseleccionardiarepartidor);


        repartidores = Comunicador.getRepartidores();



        spinnerRepartidores = (Spinner) findViewById(R.id.aSeleccionarDiaRepartidorSpinnerRepartidores);


        adaptadorRepartidores = new AdaptadorRepartidores(this,repartidores.getRepartidores());
        spinnerRepartidores.setAdapter(adaptadorRepartidores);



        fechaSeleccionada  = (DatePicker) findViewById(R.id.aSeleccionarDiaRepartidorDatePicker);

        buttonSeleccionar = (Button)findViewById(R.id.aSeleccionarDiaRepartidorBotonSeleccionar);
        buttonSeleccionar.setOnClickListener(new ASeleccionarDiaRepartidor.ListenerClickButtonSeleccionar());

        this.diaRepartidor = Comunicador.getDiaRepartidor();

        this.buttonRetornar = (Button) findViewById(R.id.aSeleccionarDiaRepartidorButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



    }

    private DiaRepartidor diaRepartidor;


    class ListenerClickButtonSeleccionar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            seleccionar();
        }
    }

    private void seleccionar()
    {


    Fecha fecha = new Fecha(fechaSeleccionada.getYear(),fechaSeleccionada.getMonth()+1,fechaSeleccionada.getDayOfMonth());

    //Fecha fecha = new Fecha(2018,1,13);


    if(spinnerRepartidores.getSelectedItemPosition()>=0)
        {
        this.diaRepartidor.setFecha(fecha);
        this.diaRepartidor.setIdRepartidor(((Repartidor)spinnerRepartidores.getSelectedItem()).getId());

        if(diaRepartidor.getEstado())
            {
            CargarDia cargarDia = new CargarDia(this);
            Comunicador.setInfoRepartos(false);
            Comunicador.setCargandoRepartos(false);
            Comunicador.setNumeroReparto(0);
            Comunicador.setNumeroRepartos(0);
            Comunicador.setHiloCargarDia(cargarDia);
            cargarDia.execute();
            }
        else
            {
            setResult(CodigosActividades.OK,new Intent());
            this.finish();
            }


        }
    else
        {
        Toast.makeText(this,"no worker selected",Toast.LENGTH_SHORT).show();
        }
    }


    private Button buttonSeleccionar;

    //private ArrayAdapter<Repartidor> adaptadorRepartidores;

    private AdaptadorRepartidores adaptadorRepartidores;

    private Spinner spinnerRepartidores;

    private Fecha fecha;
    private Repartidores repartidores;


    private DatePicker fechaSeleccionada;




    public class CargarDia extends AsyncTask<String,Integer,String>
    {


        public CargarDia(Context activity)
        {
            this.dialogoProgreso = new ProgressDialog(activity);
            this.dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.dialogoProgreso.setCancelable(false);
            this.dialogoProgreso.setIndeterminate(true);

        }

        protected ProgressDialog dialogoProgreso;


        @Override
        protected void onPreExecute()
        {
            this.dialogoProgreso.setMessage("Cargando Dia");
            this.dialogoProgreso.show();

        }

        boolean resultado;

        @Override
        protected String doInBackground(String... strings) {

            try
            {
            Comunicador.getDiaRepartidor().cargar();
            }
            catch (Exception e)
            {
                String x = e.toString();
            }
            return "";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {

            if(Comunicador.getInfoRepartos())
            {
            this.dialogoProgreso.setIndeterminate(false);
            this.dialogoProgreso.setMessage("Cargando Repartos");
            this.dialogoProgreso.setMax(Comunicador.getNumeroRepartos());
            this.dialogoProgreso.setProgress(0);
            Comunicador.setInfoRepartos(false);
            Comunicador.setCargandoRepartos(true);
            }
            else if(Comunicador.getCargandoRepartos())
            {
            this.dialogoProgreso.setProgress(Comunicador.getNumeroReparto());
            }
            else
            {

            }

        }



        public void publicarProgreso()
        {
            publishProgress();
        }







        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            mostrarDiaRepartidor();
        }


    }


        private void mostrarDiaRepartidor()
        {
        setResult(CodigosActividades.OK,new Intent());
        this.finish();
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


