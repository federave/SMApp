package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;

/**
 * Created by Federico on 24/2/2018.
 */



public class ADatosCliente extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adatoscliente);

        this.buttonRetornar = (Button) findViewById(R.id.aDatosClienteButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        /////Datos Cliente

        textViewIdCliente = (TextView) findViewById(R.id.aDatosClienteIdCliente);

        textViewNombre = (TextView) findViewById(R.id.aDatosClienteNombre);
        textViewApellido = (TextView) findViewById(R.id.aDatosClienteApellido);
        textViewTelefono = (TextView) findViewById(R.id.aDatosClienteTelefono);
        textViewTipo = (TextView) findViewById(R.id.aDatosClienteTipo);
        textViewDireccion = (TextView) findViewById(R.id.aDatosClienteDireccion);


        /////Precio Especial Productos

        linearLayoutPrecioEspecialProductos = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutPrecioEspecialProductos);
        textViewPrecioEspecialBidon20L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon20L);
        textViewPrecioEspecialBidon12L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon12L);
        textViewPrecioEspecialBidon10L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon10L);
        textViewPrecioEspecialBidon8L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon8L);
        textViewPrecioEspecialBidon5L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon5L);
        textViewPrecioEspecialPackBotellas2L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialPackBotellas2L);
        textViewPrecioEspecialPackBotellas500mL = (TextView) findViewById(R.id.aDatosClientePrecioEspecialPackBotellas500mL);


        /////Alquiler

        linearLayoutAlquiler = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutAlquiler);

        textViewAlquileres6Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres6Bidones);
        textViewAlquileres6BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres6BidonesPagados);
        textViewAlquileres8Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres8Bidones);
        textViewAlquileres8BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres8BidonesPagados);
        textViewAlquileres10Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres10Bidones);
        textViewAlquileres10BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres10BidonesPagados);
        textViewAlquileres12Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres12Bidones);
        textViewAlquileres12BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres12BidonesPagados);

        textViewBidones20LEntregados = (TextView) findViewById(R.id.aDatosClienteBidones20LEntregados);
        textViewBidones12LEntregados = (TextView) findViewById(R.id.aDatosClienteBidones12LEntregados);

        linearLayoutPrecioEspecialAlquiler = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutPrecioEspecialAlquiler);
        textViewPrecioEspecialAlquiler6Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler6Bidones);
        textViewPrecioEspecialAlquiler8Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler8Bidones);
        textViewPrecioEspecialAlquiler10Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler10Bidones);
        textViewPrecioEspecialAlquiler12Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler12Bidones);

        /////INACTIVIDAD

        textViewInactividad = (TextView) findViewById(R.id.aDatosClienteInactividad);
        textViewUltimoConsumo = (TextView) findViewById(R.id.aDatosClienteUltimoConsumo);


        /////BIDONES/DISPENSER FC

        textViewBidones20L = (TextView) findViewById(R.id.aDatosClienteBidones20L);
        textViewBidones12L = (TextView) findViewById(R.id.aDatosClienteBidones12L);
        textViewDispenserFC = (TextView) findViewById(R.id.aDatosClienteDispenserFC);

        cliente = Comunicador.getReparto().getCliente();

        cargarViews();


    }

    private Cliente cliente;

    /////////////////////

    /////Datos Cliente

    private TextView textViewIdCliente;
    private TextView textViewNombre;
    private TextView textViewApellido;
    private TextView textViewTelefono;
    private TextView textViewTipo;
    private TextView textViewDireccion;


    /////Precio Especial Productos

    private LinearLayout linearLayoutPrecioEspecialProductos;

    private TextView textViewPrecioEspecialBidon20L;
    private TextView textViewPrecioEspecialBidon12L;
    private TextView textViewPrecioEspecialBidon10L;
    private TextView textViewPrecioEspecialBidon8L;
    private TextView textViewPrecioEspecialBidon5L;
    private TextView textViewPrecioEspecialPackBotellas2L;
    private TextView textViewPrecioEspecialPackBotellas500mL;


    /////Alquiler


    private LinearLayout linearLayoutAlquiler;

    private TextView textViewAlquileres6Bidones;
    private TextView textViewAlquileres6BidonesPagados;
    private TextView textViewAlquileres8Bidones;
    private TextView textViewAlquileres8BidonesPagados;
    private TextView textViewAlquileres10Bidones;
    private TextView textViewAlquileres10BidonesPagados;
    private TextView textViewAlquileres12Bidones;
    private TextView textViewAlquileres12BidonesPagados;
    private TextView textViewBidones20LEntregados;
    private TextView textViewBidones12LEntregados;

    private LinearLayout linearLayoutPrecioEspecialAlquiler;

    private TextView textViewPrecioEspecialAlquiler6Bidones;
    private TextView textViewPrecioEspecialAlquiler8Bidones;
    private TextView textViewPrecioEspecialAlquiler10Bidones;
    private TextView textViewPrecioEspecialAlquiler12Bidones;



    /////INACTIVIDAD

    private TextView textViewInactividad;
    private TextView textViewUltimoConsumo;


    /////BIDONES/DISPENSER FC

    private TextView textViewBidones20L;
    private TextView textViewBidones12L;
    private TextView textViewDispenserFC;



    private void cargarViews()
    {
    cargarDatosCliente();
    cargarPrecioEspecialProductos();
    cargarAlquileres();
    cargarInactividad();
    cargarBidonesDispenserFC();
    }


    private void cargarDatosCliente()
    {
    textViewIdCliente.setText("Id: " +  cliente.getDatos().getId());
    textViewNombre.setText("Nombre: " +  cliente.getDatos().getNombre());
    textViewApellido.setText("Apellido: " + cliente.getDatos().getApellido());
    textViewTelefono.setText("Telefono: " + cliente.getDatos().getTelefono());
    textViewTipo.setText("Tipo de Cliente: " + cliente.getDatos().getTipoCliente().getTipoCliente());
    textViewDireccion.setText("Direccion: " + cliente.getDireccion().toString());
    }


    private void cargarPrecioEspecialProductos()
    {
    if(cliente.getPrecioProductos().getEspecial())
        {
        textViewPrecioEspecialBidon20L.setText("Bidon20L: "+cliente.getPrecioProductos().getPrecioRetornables().getBidon20L()+" $");
        textViewPrecioEspecialBidon12L.setText("Bidon12L: "+cliente.getPrecioProductos().getPrecioRetornables().getBidon12L()+" $");
        textViewPrecioEspecialBidon10L.setText("Bidon10L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon10L()+" $");
        textViewPrecioEspecialBidon8L.setText("Bidon8L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon8L()+" $");
        textViewPrecioEspecialBidon5L.setText("Bidon5L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon5L()+" $");
        textViewPrecioEspecialPackBotellas2L.setText("Pack Botellas 2L: "+cliente.getPrecioProductos().getPrecioDescartables().getPackBotellas2L()+" $");
        textViewPrecioEspecialPackBotellas500mL.setText("Pack Botellas 500 mL: "+cliente.getPrecioProductos().getPrecioDescartables().getPackBotellas500mL()+" $");
        }
    else
        {
        linearLayoutPrecioEspecialProductos.setVisibility(View.GONE);
        }
    }



    private void cargarAlquileres()
    {
    if(cliente.getDatosAlquiler().getEstado())
        {

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones()>0)
            {
            textViewAlquileres6Bidones.setText("Alquileres 6 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones());
            textViewAlquileres6BidonesPagados.setText("Alquileres 6 Bidones Pagados: " + cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres6Bidones());
            }
        else
            {
            textViewAlquileres6Bidones.setVisibility(View.GONE);
            textViewAlquileres6BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones()>0)
            {
            textViewAlquileres8Bidones.setText("Alquileres 8 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones());
            textViewAlquileres8BidonesPagados.setText("Alquileres 8 Bidones Pagados: " + cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres8Bidones());
            }
        else
            {
            textViewAlquileres8Bidones.setVisibility(View.GONE);
            textViewAlquileres8BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones()>0)
            {
            textViewAlquileres10Bidones.setText("Alquileres 10 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones());
            textViewAlquileres10BidonesPagados.setText("Alquileres 10 Bidones Pagados: " + cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres10Bidones());
            }
        else
            {
            textViewAlquileres10Bidones.setVisibility(View.GONE);
            textViewAlquileres10BidonesPagados.setVisibility(View.GONE);
            }


        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones()>0)
            {
            textViewAlquileres12Bidones.setText("Alquileres 12 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones());
            textViewAlquileres12BidonesPagados.setText("Alquileres 12 Bidones Pagados: " + cliente.getDatosAlquiler().getAlquileresPagados().getAlquileres12Bidones());
            }
        else
            {
            textViewAlquileres12Bidones.setVisibility(View.GONE);
            textViewAlquileres12BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
            {
            textViewBidones20LEntregados.setText("Bidones 20L Entregados: " + cliente.getDatosAlquiler().getRetornablesEntregados().getBidones20L());
            }
        else
            {
            textViewBidones20LEntregados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            textViewBidones12LEntregados.setText("Bidones 12L Entregados: " + cliente.getDatosAlquiler().getRetornablesEntregados().getBidones12L());
            }
        else
            {
            textViewBidones12LEntregados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getPrecioAlquileres().getEspecial())
            {
            textViewPrecioEspecialAlquiler6Bidones.setText("Alquiler 6 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler6Bidones()+" $");
            textViewPrecioEspecialAlquiler8Bidones.setText("Alquiler 8 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler8Bidones()+" $");
            textViewPrecioEspecialAlquiler10Bidones.setText("Alquiler 10 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler10Bidones()+" $");
            textViewPrecioEspecialAlquiler12Bidones.setText("Alquiler 12 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler12Bidones()+" $");
            }
        else
            {
            linearLayoutPrecioEspecialAlquiler.setVisibility(View.GONE);
            }

        }
    else
        {
        linearLayoutAlquiler.setVisibility(View.GONE);
        }
    }


    private void cargarInactividad()
    {
    textViewInactividad.setText("Estado: "+cliente.getInactividad().getTipoInactivo().getTipoInactivo());
    textViewUltimoConsumo.setText("Ultimo Consumo: "+cliente.getInactividad().getUltimoConsumo());
    }




    private void cargarBidonesDispenserFC()
    {
    textViewBidones20L.setText("Bidones 20L: "+cliente.getBidonesDispenserFC().getBidones20L());
    textViewBidones12L.setText("Bidones 12L: "+cliente.getBidonesDispenserFC().getBidones12L());
    textViewDispenserFC.setText("Dispenser FC: "+cliente.getBidonesDispenserFC().getDispenserFC());
    }












}
