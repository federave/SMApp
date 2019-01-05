package com.federavesm.smapp.actividades.diaRepartidor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ListView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.clientes.AdaptadorClientes;
import com.federavesm.smapp.actividades.diaRepartidor.reparto.ADatosCliente;
import com.federavesm.smapp.actividades.diaRepartidor.reparto.alquiler.AEntregaAlquiler;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.ClienteBusqueda;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Clientes;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.TipoFueraDeRecorrido;


public class AAgregarCliente extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aclientes);


        this.clientes = new Clientes(this);



        listViewClientes = (ListView)findViewById(R.id.aClientesListViewRepartos);
        listViewClientes.setOnItemClickListener(new ListenerItemClickListView(this));

        //adaptadorClientes = new AdaptadorClientes(this,this.clientes.getClientes());
        //listViewClientes.setAdapter(adaptadorClientes);


        editTextBuscador =  (EditText)findViewById(R.id.aClientesEditTextBuscar);
        editTextBuscador.addTextChangedListener(new ListenerEditText(this));



        this.buttonRetornar = (Button) findViewById(R.id.aClientesButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        // para que no aparezca el teclado ni bien se inicia la activity
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }


    private AdaptadorClientes adaptadorClientes;
    private ListView listViewClientes;

    private EditText editTextBuscador;

    private Clientes clientes;

    private Fecha fecha = new Fecha();





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
                adaptadorClientes = new AdaptadorClientes(this.activity,clientes.buscar(editable.toString(),fecha));
                listViewClientes.setAdapter(adaptadorClientes);
                busqueda = true;
            }
            else if((editable.toString().length() == 0) && busqueda)
            {
                adaptadorClientes = new AdaptadorClientes(this.activity,clientes.buscar("",fecha));
                listViewClientes.setAdapter(adaptadorClientes);
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
            agregarCliente(position);
            }
        }
    }


    private ClienteBusqueda clienteBusqueda;

    private void agregarCliente(int posicion)
    {
    clienteBusqueda = (ClienteBusqueda)adaptadorClientes.getItem(posicion);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Atención!").setMessage("Esta Seguro que desea agregar al cliente: " + clienteBusqueda.getNombre())
            .setNegativeButton("No Agregar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {agregarNo();}
            })
            .setPositiveButton("Agregar",new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int id) {agregarSi();}});
    builder.create().show();

    }




    private void agregarSi()
    {
    Reparto reparto = new Reparto(getApplicationContext());
    reparto.getCliente().setId(clienteBusqueda.getId());
    reparto.getCliente().setPrecioProductos(Comunicador.getDiaRepartidor().getPrecios().getPrecioProductos());
    reparto.getCliente().getDatosAlquiler().setPrecioAlquileres(Comunicador.getDiaRepartidor().getPrecios().getPrecioAlquileres());

    reparto.getCliente().cargar(Comunicador.getDiaRepartidor().getFecha());
    reparto.getCliente().getEstadoInactividad().actualizar();
    reparto.getCliente().getEstadoBidonesDispenserFC().actualizar();
    reparto.getCliente().getDatosAlquiler().actualizar();
    reparto.getFueraDeRecorrido().setTipoFueraDeRecorrido(new TipoFueraDeRecorrido(this,1)); // llamado
        
    if(Comunicador.getDiaRepartidor().getRepartos().isReparto(reparto) == false) {
        Comunicador.getDiaRepartidor().getRepartos().addReparto(reparto);
        Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
        Dialogo.aceptar("Atencion!","El cliente se agregó correctamente",this);
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","El cliente seleccionado ya se encuentra en el reparto",this);
        }

    }

    private void agregarNo()
    {

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    }




    static class CodigosAAgregarCliente extends CodigosActividades
    {
    }









}
