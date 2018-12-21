package com.federavesm.smapp.actividades.diaRepartidor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.diaRepartidor.reparto.AReparto;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Repartos;

/**
 * Created by Federico on 19/2/2018.
 */



public class ARepartos extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arepartos);


        this.diaRepartidor = Comunicador.getDiaRepartidor();

        adaptadorRepartos = new AdaptadorRepartos(this,this.diaRepartidor.getRepartos().getRepartos());

        listViewRepartos = (ListView)findViewById(R.id.aRepartosListViewRepartos);
        listViewRepartos.setAdapter(adaptadorRepartos);
        listViewRepartos.setOnItemClickListener(new ListenerItemClickListView(this));


        editTextBuscador =  (EditText)findViewById(R.id.aRepartoEditTextBuscar);
        editTextBuscador.addTextChangedListener(new ListenerEditText(this));
        this.repartos = Comunicador.getDiaRepartidor().getRepartos();

        /*
        imageViewLeftCabecera = (ImageView) findViewById(R.id.imageViewLeftCabecera);

        imageViewLeftCabecera.setOnClickListener(new ListenerClickRetornar());

*/

        this.buttonRetornar = (Button) findViewById(R.id.aRepartosButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        // para que no aparezca el teclado ni bien se inicia la activity
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    private DiaRepartidor diaRepartidor;

    private AdaptadorRepartos adaptadorRepartos;
    private ListView listViewRepartos;

    private EditText editTextBuscador;

    private Repartos repartos;






    ///// RETORNAR

    /*
    private ImageView imageViewLeftCabecera;

    class ListenerClickRetornar implements View.OnClickListener
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
*/

    ///////////////////


    class ListenerEditText implements TextWatcher
    {

        public ListenerEditText(Context activity)
        {
            this.activity = activity;
        }

        private Context activity;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        boolean aux=false;


        boolean busqueda = false;

        @Override
        public void afterTextChanged(Editable editable)
        {
        if(editable.toString().length()>0)
            {
            adaptadorRepartos = new AdaptadorRepartos(this.activity,diaRepartidor.getRepartos().buscarRepartos(editable.toString()));
            listViewRepartos.setAdapter(adaptadorRepartos);
            busqueda = true;
            }
        else if((editable.toString().length() == 0) && busqueda)
            {
            adaptadorRepartos = new AdaptadorRepartos(this.activity,diaRepartidor.getRepartos().getRepartos());
            listViewRepartos.setAdapter(adaptadorRepartos);
            busqueda = false;
            }
        else
            {

            }
        }

    }



    ///////////////////


    public int n1=-1;


    class ListenerItemClickListView implements AdapterView.OnItemClickListener
    {

        public ListenerItemClickListView(Context activity)
        {
        this.activity = activity;
        }

        private Context activity;

        @Override
        public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3)
        {

        if(n1!=position)
            {
            n1=position;
            }
        else
            {
            n1=-1;

            Comunicador.setReparto((Reparto)adaptadorRepartos.getItem(position));
            Comunicador.setRepartoSeleccionado(position);

            Comunicador.getReparto().limpiarVisitaCambio();
            mostrarReparto();
            }


        }
    }


    private void mostrarReparto()
    {
    this.repartos.guardarEstadoRepartoSeleccionado();
    Intent intentRepartos = new Intent(this,AReparto.class);
    startActivityForResult(intentRepartos, CodigosRepartos.Reparto);
    }






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if(CodigosRepartos.Reparto == requestCode)
        {
        if(Comunicador.getReparto().getVisitaCambio())
            {
            listViewRepartos.setAdapter(adaptadorRepartos);
            }

            listViewRepartos.setSelection(Comunicador.getRepartoSeleccionado());

        }


    }



    static class CodigosRepartos extends CodigosActividades
    {
    public static int Reparto = 3;
    }









}
