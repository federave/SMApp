package com.federavesm.smapp.modelo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.federavesm.smapp.actividades.diaRepartidor.ASeleccionarDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Carga;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Descarga;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gasto;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.deudaProductos.DeudaProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;
import com.federavesm.smapp.modelo.servidor.ConexionServidor;

/**
 * Created by Federico on 2/1/2018.
 */

public class Comunicador {

    private static Usuario usuario;
    private static ConexionServidor conexionServidor;


    public static ConexionServidor getConexionServidor() {
        return conexionServidor;
    }

    public static void setConexionServidor(ConexionServidor conexionServidor) {
        Comunicador.conexionServidor = conexionServidor;
    }

    private static Usuario usuarioAdministrador;

    public static Usuario getUsuarioAdministrador() {
        return usuarioAdministrador;
    }

    public static void setUsuarioAdministrador(Usuario usuarioAdministrador) {
        Comunicador.usuarioAdministrador = usuarioAdministrador;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Comunicador.usuario = usuario;
    }

    private static Repartidores repartidores;

    public static Repartidores getRepartidores() {
        return repartidores;
    }

    public static void setRepartidores(Repartidores repartidores) {
        Comunicador.repartidores = repartidores;
    }





    //////////////////////////CLIENTES ////////////////////////


    private static Cliente clienteBusqueda;
    private static boolean clienteSeleccionado = false;

    public static Cliente getClienteBusqueda() {
        return clienteBusqueda;
    }

    public static void setClienteBusqueda(Cliente clienteBusqueda) {
        Comunicador.clienteBusqueda = clienteBusqueda;
    }



    public static boolean getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public static void setClienteSeleccionado(boolean clienteSeleccionado) {
        Comunicador.clienteSeleccionado = clienteSeleccionado;
    }

//////////////////////////DIA REPARTIDOR ////////////////////////

    private static DiaRepartidor diaRepartidor;

    public static DiaRepartidor getDiaRepartidor() {return diaRepartidor;}
    public static void setDiaRepartidor(DiaRepartidor diaRepartidor) {Comunicador.diaRepartidor = diaRepartidor;}


    //////////////Comunicar datos Cargar


    private static boolean infoRepartos=false;
    private static boolean cargandoRepartos=false;

    private static int numeroRepartos;
    private static int numeroReparto;


    private static ASeleccionarDiaRepartidor.CargarDia hiloCargarDia;

    public static ASeleccionarDiaRepartidor.CargarDia getHiloCargarDia() {
        return hiloCargarDia;
    }

    public static void setHiloCargarDia(ASeleccionarDiaRepartidor.CargarDia hiloCargarDia) {
        Comunicador.hiloCargarDia = hiloCargarDia;
    }

    public static boolean getInfoRepartos() {
        return infoRepartos;
    }

    public static void setInfoRepartos(boolean infoRepartos) {
        Comunicador.infoRepartos = infoRepartos;
    }

    public static boolean getCargandoRepartos() {
        return cargandoRepartos;
    }

    public static void setCargandoRepartos(boolean cargandoRepartos) {
        Comunicador.cargandoRepartos = cargandoRepartos;
    }

    public static int getNumeroRepartos() {
        return numeroRepartos;
    }

    public static void setNumeroRepartos(int numeroRepartos) {
        Comunicador.numeroRepartos = numeroRepartos;
    }

    public static int getNumeroReparto() {
        return numeroReparto;
    }

    public static void setNumeroReparto(int numeroReparto) {
        Comunicador.numeroReparto = numeroReparto;
    }

    //////////////GASTOS



    private static boolean nuevoGasto;
    private static Gasto gasto;

    public static boolean getNuevoGasto() {
        return nuevoGasto;
    }

    public static void setNuevoGasto(boolean nuevoGasto) {
        Comunicador.nuevoGasto = nuevoGasto;
    }

    public static Gasto getGasto() {
        return gasto;
    }

    public static void setGasto(Gasto gasto) {
        Comunicador.gasto = gasto;
    }

    //////////////REPARTO

    private static Reparto reparto;

    public static int getRepartoSeleccionado() {
        return repartoSeleccionado;
    }

    public static void setRepartoSeleccionado(int repartoSeleccionado) {
        Comunicador.repartoSeleccionado = repartoSeleccionado;
    }

    private static int repartoSeleccionado;

    public static Reparto getReparto() {
        return reparto;
    }
    public static void setReparto(Reparto reparto)
    {
    Comunicador.reparto = reparto;
    }

    public static Cliente getCliente() {
        return reparto.getCliente();
    }


    /*

    public static Reparto getRepartoAux() {return repartoAux;}
    public static void setRepartoAux(Reparto repartoAux) {
        Comunicador.repartoAux = repartoAux;
    }
    private static Reparto repartoAux;

    */

    //////////////VENTA PRODUCTOS

    private static VentaProductos ventaProductosAux;
    public static VentaProductos getVentaProductosAux() {return ventaProductosAux;}
    public static void setVentaProductosAux(VentaProductos ventaProductosAux) {Comunicador.ventaProductosAux = ventaProductosAux;}

    //////////////DEUDA PRODUCTOS

    public static DeudaProductos getDeudaProductosAux() {return deudaProductosAux;}
    public static void setDeudaProductosAux(DeudaProductos deudaProductosAux) {Comunicador.deudaProductosAux = deudaProductosAux;}
    private static DeudaProductos deudaProductosAux;






    //////////////ALQUILER


    private static Alquiler alquilerOld;

    public static Alquiler getAlquilerOld() {
        return alquilerOld;
    }

    public static void setAlquilerOld(Alquiler alquilerOld) {
        Comunicador.alquilerOld = alquilerOld;
    }

    //////////////////////////////////////////////////////

    ////////////////////CARGAS

    private static boolean nuevaCarga = false;
    private static boolean modificarCarga = false;

    public static boolean getNuevaCarga() {return nuevaCarga;}
    public static void setNuevaCarga(boolean nuevaCarga) {Comunicador.nuevaCarga = nuevaCarga;}

    public static boolean getModificarCarga() {return modificarCarga;}
    public static void setModificarCarga(boolean modificarCarga) {Comunicador.modificarCarga = modificarCarga;}


    private static Carga cargaSeleccionada;

    public static Carga getCargaSeleccionada() {
        return cargaSeleccionada;
    }

    public static void setCargaSeleccionada(Carga cargaSeleccionada) {
        Comunicador.cargaSeleccionada = cargaSeleccionada;
    }


    ////////////////////DESCARGAS


    private static boolean nuevaDescarga = false;
    private static Descarga descargaSeleccionada;

    public static boolean getNuevaDescarga() {
        return nuevaDescarga;
    }

    public static void setNuevaDescarga(boolean nuevaDescarga) {
        Comunicador.nuevaDescarga = nuevaDescarga;
    }

    public static Descarga getDescargaSeleccionada() {
        return descargaSeleccionada;
    }

    public static void setDescargaSeleccionada(Descarga descargaSeleccionada) {
        Comunicador.descargaSeleccionada = descargaSeleccionada;
    }

    //////////////////////////////////////////////////////
    public static boolean getConexionInternet(Context activity)
    {
        try
        {
            ConnectivityManager connMgr = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG).show();
            return false;
        }

    }



}
