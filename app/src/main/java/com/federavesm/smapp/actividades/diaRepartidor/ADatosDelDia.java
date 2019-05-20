package com.federavesm.smapp.actividades.diaRepartidor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;

/**
 * Created by Federico on 19/2/2018.
 */


public class ADatosDelDia extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adatosdeldia);


        this.diaRepartidor = Comunicador.getDiaRepartidor();

        this.diaRepartidor.cargarDatosInforme();


        this.buttonRetornar = (Button) findViewById(R.id.aDatosDelDiaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



        ////////DINERO DEL DIA

        masDetalleDineroDia = (CheckBox) findViewById(R.id.aDatosDiaDineroDiaMasDetalle);
        linearLayoutDineroDiaMasDetalle = (LinearLayout) findViewById(R.id.aDatosDiaLinearLayoutDineroDiaMasDetalle);
        dineroRecaudado = (TextView) findViewById(R.id.aDatosDiaDineroRecaudado);
        dineroVentas = (TextView) findViewById(R.id.aDatosDiaDineroVentas);
        dineroPagoAlquileres = (TextView) findViewById(R.id.aDatosDiaDineroPagoAlquileres);
        dineroGastado = (TextView) findViewById(R.id.aDatosDiaDineroGastado);
        dineroAEntregar = (TextView) findViewById(R.id.aDatosDiaDineroAEntregar);
        dineroVentaVertedores = (TextView) findViewById(R.id.aDatosDiaDineroVentaVertedores);
        dineroVentaDispensers = (TextView) findViewById(R.id.aDatosDiaDineroVentaDispensers);


        masDetalleDineroDia.setChecked(true);
        masDetalleDineroDia.setOnCheckedChangeListener(new ListenerCheckedBoxDinero());

        cargarDineroDia();



        ////////DATOS PLANILLA

        planillaCantidad = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidad);
        planillaCantidadVisitados = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidadVisitados);
        planillaCantidadNoVisitados = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidadNoVisitados);
        planillaCantidadNoEncontrados = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidadNoEncontrados);
        planillaCantidadConVenta = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidadConVenta);
        planillaCantidadConEntregaAlquiler = (TextView) findViewById(R.id.aDatosDiaPlanillaCantidadConEntregaAlquiler);


        cargarDatosPlanilla();


        ////////PRODUCTOS REPARTIDOS

        masDetalleProductosRepartidos = (CheckBox) findViewById(R.id.aDatosDiaProductosRepartidosMasDetalle);

        productosRepartidosBidones20L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones20L);
        productosRepartidosBidones12L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones12L);
        productosRepartidosBidones10L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones10L);
        productosRepartidosBidones8L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones8L);
        productosRepartidosBidones5L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones5L);
        productosRepartidosPackBotellas2L = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosPackBotellas2L);
        productosRepartidosPackBotellas500mL = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosPackBotellas500mL);

        linearLayoutProductosRepartidosMasDetalle = (LinearLayout) findViewById(R.id.aDatosDiaLinearLayoutProductosRepartidosMasDetalle);

        productosRepartidosBidones20LAlquiler = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones20LAlquiler);
        productosRepartidosBidones12LAlquiler = (TextView) findViewById(R.id.aDatosDiaProductosRepartidosBidones12LAlquiler);

        masDetalleProductosRepartidos.setChecked(true);
        masDetalleProductosRepartidos.setOnCheckedChangeListener(new ListenerCheckedBoxProductosRepartidos());


        cargarProductosRepartidos();


        ////////ENTREGAS ALQUILERES

        totalEntregasAlquileres = (TextView) findViewById(R.id.aDatosDiaNumeroEntregasAlquileres);
        remitosEntregasAlquileres = (TextView) findViewById(R.id.aDatosDiaRemitosEntregasAlquileres);

        cargarEntregasAlquileres();



        ////////PAGOS ALQUILERES

        totalPagosRecibidos = (TextView) findViewById(R.id.aDatosDiaNumeroPagosAlquileres);
        remitosPagosAlquileres = (TextView) findViewById(R.id.aDatosDiaRemitosPagosAlquileres);

        cargarPagoAlquileres();




        ////////VACIOS RECUPERADOS

        vaciosRecuperadosBidones20L = (TextView) findViewById(R.id.aDatosDiaVaciosRecuperadosBidones20L);
        vaciosRecuperadosBidones12L = (TextView) findViewById(R.id.aDatosDiaVaciosRecuperadosBidones12L);

        cargarVaciosRecuperados();



        ////////REMITOS DEUDA

        deudasRemitos = (TextView) findViewById(R.id.aDatosDiaDeudasRemitos);
        totalRemitosDeudas = (TextView) findViewById(R.id.aDatosDiaTotalRemitos);

        cargarDeudas();


        ////////VALES

        valesPositivos = (TextView) findViewById(R.id.aDatosDiaValesPositivos);
        totalValesPositivos = (TextView) findViewById(R.id.aDatosDiaTotalValesPositivos);

        valesNegativos = (TextView) findViewById(R.id.aDatosDiaValesNegativos);
        totalValesNegativos = (TextView) findViewById(R.id.aDatosDiaTotalValesNegativos);

        cargarVales();




        ////////BOLETAS GASTOS

        boletasGastos = (TextView) findViewById(R.id.aDatosDiaBoletasGastos);
        totalBoletasGastos = (TextView) findViewById(R.id.aDatosDiaTotalBoletasGastos);

        cargarGastos();


        ////////CARGA

        cargaTotalBidones20L = (TextView) findViewById(R.id.aDatosDiaCargaTotalBidones20L);
        cargaTotalBidones12L = (TextView) findViewById(R.id.aDatosDiaCargaTotalBidones12L);
        cargaTotalBidones10L = (TextView) findViewById(R.id.aDatosDiaCargaTotalBidones10L);
        cargaTotalBidones8L = (TextView) findViewById(R.id.aDatosDiaCargaTotalBidones8L);
        cargaTotalBidones5L = (TextView) findViewById(R.id.aDatosDiaCargaTotalBidones5L);
        cargaTotalPackBotellas2L = (TextView) findViewById(R.id.aDatosDiaCargaTotalPackBotellas2L);
        cargaTotalPackBotellas500mL = (TextView) findViewById(R.id.aDatosDiaCargaTotalPackBotellas500mL);

        cargaTotalVertedores = (TextView) findViewById(R.id.aDatosDiaCargaTotalVertedores);
        cargaTotalDispensers = (TextView) findViewById(R.id.aDatosDiaCargaTotalDispensers);




        cargarCarga();

        ////////DESCARGA

        descargaTotalBidones20L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalBidones20L);
        descargaTotalBidones12L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalBidones12L);
        descargaTotalBidones10L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalBidones10L);
        descargaTotalBidones8L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalBidones8L);
        descargaTotalBidones5L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalBidones5L);
        descargaTotalPackBotellas2L = (TextView) findViewById(R.id.aDatosDiaDescargaTotalPackBotellas2L);
        descargaTotalPackBotellas500mL = (TextView) findViewById(R.id.aDatosDiaDescargaTotalPackBotellas500mL);

        descargaTotalVertedores = (TextView) findViewById(R.id.aDatosDiaDescargaTotalVertedores);
        descargaTotalDispensers = (TextView) findViewById(R.id.aDatosDiaDescargaTotalDispensers);



        cargarDescarga();

        ////////BALANCE

        linearLayoutBalance = (LinearLayout) findViewById(R.id.aDatosDiaLinearLayoutBalance);

        verInformacionBalance = (CheckBox) findViewById(R.id.aDatosDiaBalanceVerInformacion);
        informacionBalance = (TextView) findViewById(R.id.aDatosDiaBalanceInformacion);

        balanceBidones20L = (TextView) findViewById(R.id.aDatosDiaBalanceBidones20L);
        balanceBidones12L = (TextView) findViewById(R.id.aDatosDiaBalanceBidones12L);
        balanceBidones10L = (TextView) findViewById(R.id.aDatosDiaBalanceBidones10L);
        balanceBidones8L = (TextView) findViewById(R.id.aDatosDiaBalanceBidones8L);
        balanceBidones5L = (TextView) findViewById(R.id.aDatosDiaBalanceBidones5L);
        balancePackBotellas2L = (TextView) findViewById(R.id.aDatosDiaBalancePackBotellas2L);
        balancePackBotellas500mL = (TextView) findViewById(R.id.aDatosDiaBalancePackBotellas500mL);

        balanceVertedores = (TextView) findViewById(R.id.aDatosDiaBalanceVertedores);
        balanceDispensers = (TextView) findViewById(R.id.aDatosDiaBalanceDispensers);



        balanceBidones20LVacios = (TextView) findViewById(R.id.aDatosDiaBalanceBidones20LVacios);
        balanceBidones12LVacios = (TextView) findViewById(R.id.aDatosDiaBalanceBidones12LVacios);




        verInformacionBalance.setChecked(true);
        verInformacionBalance.setOnCheckedChangeListener(new ListenerCheckedBoxBalance());

        cargarBalance();



    }

    private DiaRepartidor diaRepartidor;

    ///////////////////////////////////////


    ////////DINERO DEL DIA

    private CheckBox masDetalleDineroDia;

    private LinearLayout linearLayoutDineroDiaMasDetalle;

    private TextView dineroRecaudado;
    private TextView dineroVentas;
    private TextView dineroPagoAlquileres;
    private TextView dineroGastado;
    private TextView dineroAEntregar;
    private TextView dineroVentaVertedores;
    private TextView dineroVentaDispensers;





    class ListenerCheckedBoxDinero implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedChangedDinero();
        }
    }

    private void checkedChangedDinero()
    {
    if(masDetalleDineroDia.isChecked())
        {
        linearLayoutDineroDiaMasDetalle.setVisibility(View.VISIBLE);
        }
    else
        {
        linearLayoutDineroDiaMasDetalle.setVisibility(View.GONE);
        }
    }


    private void cargarDineroDia()
    {
    dineroRecaudado.setText("Dinero total recaudado: "+this.diaRepartidor.getDineroRecaudado()+" $");
    dineroGastado.setText("Dinero gastado: "+this.diaRepartidor.getDineroGastos()+" $");
    dineroAEntregar.setText("Dinero a entregar: "+(this.diaRepartidor.getDineroRecaudado() - this.diaRepartidor.getDineroGastos())+" $");
    dineroVentas.setText("Dinero ventas: "+this.diaRepartidor.getDineroVentas()+" $");
    dineroPagoAlquileres.setText("Dinero pago alquileres: "+this.diaRepartidor.getDineroPagoAlquileres() + " $");


    dineroVentaVertedores.setText("Dinero venta vertedores: "+this.diaRepartidor.getDineroVentaVertedores() + " $");
    dineroVentaDispensers.setText("Dinero venta vertedores: "+this.diaRepartidor.getDineroVentaDispensers() + " $");

    }


    ///////////////////////////////////////////////////////////////////
    ////////DATOS PLANILLA

    private TextView planillaCantidad;
    private TextView planillaCantidadVisitados;
    private TextView planillaCantidadNoVisitados;
    private TextView planillaCantidadNoEncontrados;
    private TextView planillaCantidadConVenta;
    private TextView planillaCantidadConEntregaAlquiler;

    private void cargarDatosPlanilla()
    {
    planillaCantidad.setText("Cantida de Clientes: " + this.diaRepartidor.getPlanillaCantidad());
    planillaCantidadVisitados.setText("Cantida de Clientes Visitados: "+this.diaRepartidor.getPlanillaCantidadVisitados());
    planillaCantidadNoVisitados.setText("Cantida de Clientes No Visitados: " + this.diaRepartidor.getPlanillaCantidadNoVisitados());
    planillaCantidadNoEncontrados.setText("Cantida de Clientes No Encontrados: " + this.diaRepartidor.getPlanillaCantidadNoEncontrados());
    planillaCantidadConVenta.setText("Cantida de Clientes que Compraron: " + this.diaRepartidor.getPlanillaCantidadConVenta());
    planillaCantidadConEntregaAlquiler.setText("Cantida de Clientes con Entrega de Alquiler: " + this.diaRepartidor.getPlanillaCantidadConEntregaAlquiler());
    }



    ///////////////////////////////////////////////////////////////////
    ////////PRODUCTOS REPARTIDOS

    private CheckBox masDetalleProductosRepartidos;


    private TextView productosRepartidosBidones20L;
    private TextView productosRepartidosBidones12L;
    private TextView productosRepartidosBidones10L;
    private TextView productosRepartidosBidones8L;
    private TextView productosRepartidosBidones5L;
    private TextView productosRepartidosPackBotellas2L;
    private TextView productosRepartidosPackBotellas500mL;

    private LinearLayout linearLayoutProductosRepartidosMasDetalle;
    private TextView productosRepartidosBidones20LAlquiler;
    private TextView productosRepartidosBidones12LAlquiler;


    private void cargarProductosRepartidos()
    {
    productosRepartidosBidones20L.setText("Bidones20L: "+this.diaRepartidor.getRepartos().getRetornablesRepartidos().getBidones20L());
    productosRepartidosBidones12L.setText("Bidones12L: "+this.diaRepartidor.getRepartos().getRetornablesRepartidos().getBidones12L());

    productosRepartidosBidones10L.setText("Bidones10L: "+this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones10L());
    productosRepartidosBidones8L.setText("Bidones8L: "+this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones8L());
    productosRepartidosBidones5L.setText("Bidones5L: "+this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones5L());
    productosRepartidosPackBotellas2L.setText("Pack Botellas 2L: "+this.diaRepartidor.getRepartos().getDescartablesRepartidos().getPackBotellas2L());
    productosRepartidosPackBotellas500mL.setText("Pack Botellas 500mL: "+this.diaRepartidor.getRepartos().getDescartablesRepartidos().getPackBotellas500mL());

    productosRepartidosBidones20LAlquiler.setText("Bidones 20L Alquiler: "+this.diaRepartidor.getRetornablesRepartidosAlquiler().getBidones20L());
    productosRepartidosBidones12LAlquiler.setText("Bidones 12L Alquiler: "+this.diaRepartidor.getRetornablesRepartidosAlquiler().getBidones12L());


    }


    class ListenerCheckedBoxProductosRepartidos implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedChangedProductosRepartidos();
        }
    }


    private void checkedChangedProductosRepartidos()
    {
        if(masDetalleProductosRepartidos.isChecked())
        {
            linearLayoutProductosRepartidosMasDetalle.setVisibility(View.VISIBLE);
        }
        else
        {
            linearLayoutProductosRepartidosMasDetalle.setVisibility(View.GONE);
        }
    }


    ////////ENTREGAS ALQUILERES

    private TextView totalEntregasAlquileres;
    private TextView remitosEntregasAlquileres;

    private void cargarEntregasAlquileres()
    {
    totalEntregasAlquileres.setText("Cantidad de Entregas de Alquileres: " + this.diaRepartidor.getPlanillaCantidadConEntregaAlquiler());
    remitosEntregasAlquileres.setText("Remitos a Presentar: \n"+this.diaRepartidor.getRemitosEntregasAlquileres());
    }


    ////////PAGO ALQUILERES

    private TextView totalPagosRecibidos;
    private TextView remitosPagosAlquileres;

    private void cargarPagoAlquileres()
    {
    totalPagosRecibidos.setText("Total de pagos de alquileres recibidos: " + this.diaRepartidor.getNumeroPagosAlquileres());
    remitosPagosAlquileres.setText("Remitos de pagos a presentar: \n"+this.diaRepartidor.getRemitosPagosAlquileres());
    }




    ////////VACIOS RECUPERADOS

    private TextView vaciosRecuperadosBidones20L;
    private TextView vaciosRecuperadosBidones12L;

    private void cargarVaciosRecuperados()
    {
    vaciosRecuperadosBidones20L.setText("Bidones 20L Vacios: " + this.diaRepartidor.getRepartos().getRetornablesRecuperados().getBidones20L());
    vaciosRecuperadosBidones12L.setText("Bidones 12L Vacios: " + this.diaRepartidor.getRepartos().getRetornablesRecuperados().getBidones12L());
    }


    ////////REMITOS DEUDA

    private TextView totalRemitosDeudas;
    private TextView deudasRemitos;

    private void cargarDeudas()
    {
    totalRemitosDeudas.setText("Total de deudas de productos realizadas: " + this.diaRepartidor.getNumeroDeudasProductos());
    deudasRemitos.setText("Remitos de deudas a presentar: \n"+this.diaRepartidor.getRemitosDeudas());
    }


    ////////VALES

    private TextView totalValesPositivos;
    private TextView valesPositivos;
    private TextView totalValesNegativos;
    private TextView valesNegativos;

    private void cargarVales()
    {

    totalValesPositivos.setText("Total de vales positivos: " + this.diaRepartidor.getNumeroValesPositivos());
    valesPositivos.setText("Vales positivos a presentar: \n"+this.diaRepartidor.getValesPositivos());
    totalValesNegativos.setText("Total de vales negativos: " + this.diaRepartidor.getNumeroValesNegativos());
    valesNegativos.setText("Vales negativos a presentar: \n"+this.diaRepartidor.getValesNegativos());




    }





    ////////BOLETAS GASTOS

    private TextView totalBoletasGastos;
    private TextView boletasGastos;

    private void cargarGastos()
    {
    totalBoletasGastos.setText("Total de gastos realizados: " + this.diaRepartidor.getGastos().getGastos().size());
    boletasGastos.setText("Boletas de  gastos a presentar: \n"+this.diaRepartidor.getBoletasGastos());
    }





    ////////CARGA

    private TextView cargaTotalBidones20L;
    private TextView cargaTotalBidones12L;
    private TextView cargaTotalBidones10L;
    private TextView cargaTotalBidones8L;
    private TextView cargaTotalBidones5L;
    private TextView cargaTotalPackBotellas2L;
    private TextView cargaTotalPackBotellas500mL;
    private TextView cargaTotalVertedores;
    private TextView cargaTotalDispensers;


    private void cargarCarga()
    {
    cargaTotalBidones20L.setText("Bidones 20L: " + this.diaRepartidor.getCargamento().getCargas().getRetornables().getBidones20L());
    cargaTotalBidones12L.setText("Bidones 12L: " + this.diaRepartidor.getCargamento().getCargas().getRetornables().getBidones12L());
    cargaTotalBidones10L.setText("Bidones 10L: " + this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones10L());
    cargaTotalBidones8L.setText("Bidones 8L: " + this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones8L());
    cargaTotalBidones5L.setText("Bidones 5L: " + this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones5L());
    cargaTotalPackBotellas2L.setText("Pack Botellas 2L: " + this.diaRepartidor.getCargamento().getCargas().getDescartables().getPackBotellas2L());
    cargaTotalPackBotellas500mL.setText("Pack Botellas 500mL: " + this.diaRepartidor.getCargamento().getCargas().getDescartables().getPackBotellas500mL());


    cargaTotalVertedores.setText("Vertedores: " + this.diaRepartidor.getCargamento().getCargas().getVertedores().getCantidad());
    cargaTotalDispensers.setText("Dispensers: " + this.diaRepartidor.getCargamento().getCargas().getDispensers().getCantidad());

    }



    ////////DESCARGA

    private TextView descargaTotalBidones20L;
    private TextView descargaTotalBidones12L;
    private TextView descargaTotalBidones10L;
    private TextView descargaTotalBidones8L;
    private TextView descargaTotalBidones5L;
    private TextView descargaTotalPackBotellas2L;
    private TextView descargaTotalPackBotellas500mL;
    private TextView descargaTotalVertedores;
    private TextView descargaTotalDispensers;


    private void cargarDescarga()
    {
    descargaTotalBidones20L.setText("Bidones 20L: " + this.diaRepartidor.getCargamento().getDescargas().getRetornables().getBidones20L());
    descargaTotalBidones12L.setText("Bidones 12L: " + this.diaRepartidor.getCargamento().getDescargas().getRetornables().getBidones12L());
    descargaTotalBidones10L.setText("Bidones 10L: " + this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones10L());
    descargaTotalBidones8L.setText("Bidones 8L: " + this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones8L());
    descargaTotalBidones5L.setText("Bidones 5L: " + this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones5L());
    descargaTotalPackBotellas2L.setText("Pack Botellas 2L: " + this.diaRepartidor.getCargamento().getDescargas().getDescartables().getPackBotellas2L());
    descargaTotalPackBotellas500mL.setText("Pack Botellas 500mL: " + this.diaRepartidor.getCargamento().getDescargas().getDescartables().getPackBotellas500mL());


    descargaTotalVertedores.setText("Vertedores: " + this.diaRepartidor.getCargamento().getDescargas().getVertedores().getCantidad());
    descargaTotalDispensers.setText("Dispensers: " + this.diaRepartidor.getCargamento().getDescargas().getDispensers().getCantidad());

    }




    ////////BALANCE

    private LinearLayout linearLayoutBalance;

    private CheckBox verInformacionBalance;
    private TextView informacionBalance;

    private TextView balanceBidones20L;
    private TextView balanceBidones12L;
    private TextView balanceBidones10L;
    private TextView balanceBidones8L;
    private TextView balanceBidones5L;
    private TextView balancePackBotellas2L;
    private TextView balancePackBotellas500mL;
    private TextView balanceVertedores;
    private TextView balanceDispensers;




    private TextView balanceBidones20LVacios;
    private TextView balanceBidones12LVacios;


    private void cargarBalance()
    {

    balanceBidones20L.setText("Bidones 20L = " + (this.diaRepartidor.getCargamento().getCargas().getRetornables().getBidones20L() - this.diaRepartidor.getRepartos().getRetornablesRepartidos().getBidones20L() - this.diaRepartidor.getCargamento().getDescargas().getRetornables().getBidones20L()));
    balanceBidones12L.setText("Bidones 12L = " + (this.diaRepartidor.getCargamento().getCargas().getRetornables().getBidones12L() - this.diaRepartidor.getRepartos().getRetornablesRepartidos().getBidones12L() - this.diaRepartidor.getCargamento().getDescargas().getRetornables().getBidones12L()));

    balanceBidones10L.setText("Bidones 10L = " + (this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones10L() - this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones10L() - this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones10L()));
    balanceBidones8L.setText("Bidones 8L = " + (this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones8L() - this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones8L() - this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones8L()));
    balanceBidones5L.setText("Bidones 5L = " + (this.diaRepartidor.getCargamento().getCargas().getDescartables().getBidones5L() - this.diaRepartidor.getRepartos().getDescartablesRepartidos().getBidones5L() - this.diaRepartidor.getCargamento().getDescargas().getDescartables().getBidones5L()));
    balancePackBotellas2L.setText("Pack Botellas 2L = " + (this.diaRepartidor.getCargamento().getCargas().getDescartables().getPackBotellas2L() - this.diaRepartidor.getRepartos().getDescartablesRepartidos().getPackBotellas2L() - this.diaRepartidor.getCargamento().getDescargas().getDescartables().getPackBotellas2L()));
    balancePackBotellas500mL.setText("Pack Botellas 500mL = " + (this.diaRepartidor.getCargamento().getCargas().getDescartables().getPackBotellas500mL() - this.diaRepartidor.getRepartos().getDescartablesRepartidos().getPackBotellas500mL() - this.diaRepartidor.getCargamento().getDescargas().getDescartables().getPackBotellas500mL()));

    balanceBidones20LVacios.setText("Bidones 20L Vacios = " + (this.diaRepartidor.getCargamento().getDescargas().getRetornablesVacios().getBidones20L() - this.diaRepartidor.getRepartos().getRetornablesRecuperados().getBidones20L()));
    balanceBidones12LVacios.setText("Bidones 12L Vacios = " + (this.diaRepartidor.getCargamento().getDescargas().getRetornablesVacios().getBidones12L() - this.diaRepartidor.getRepartos().getRetornablesRecuperados().getBidones12L()));

    // falta balance dispensadores
    balanceVertedores.setText("Vertedores = " + (this.diaRepartidor.getCargamento().getVertedores().getCantidad() - this.diaRepartidor.getRepartos().getVertedoresRepartidos().getCantidad() - this.diaRepartidor.getCargamento().getDescargas().getVertedores().getCantidad()));
    balanceDispensers.setText("Dispensers = " + (this.diaRepartidor.getCargamento().getDispensers().getCantidad() + this.diaRepartidor.getRepartos().getDispensersRetirados().getCantidad() - this.diaRepartidor.getRepartos().getDispensersRepartidos().getCantidad() - this.diaRepartidor.getCargamento().getDescargas().getDispensers().getCantidad()));



    }







    class ListenerCheckedBoxBalance implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedChangedBalance();
        }
    }


    private void checkedChangedBalance()
    {
    if(verInformacionBalance.isChecked())
        {
        informacionBalance.setVisibility(View.VISIBLE);
        }
    else
        {
        informacionBalance.setVisibility(View.GONE);
        }
    }











}
