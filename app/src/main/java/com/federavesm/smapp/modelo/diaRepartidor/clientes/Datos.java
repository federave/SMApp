package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente.TipoCliente;

/**
 * Created by Federico on 8/2/2018.
 */




public class Datos extends GenericoDiaRepartidor {


    public Datos(Context context)
    {
    super(context);
    this.tipoCliente = new TipoCliente(context);
    }





    private String nombre;
    private String apellido;
    private String telefono;
    private TipoCliente tipoCliente;

    @Override
    public boolean modificar() {
        return false;
    }
    @Override
    public void copiar(Object object)
    {
    try
        {
        Datos datos = (Datos)object;
        this.id = datos.getId();
        this.nombre = datos.getNombre();
        this.apellido = datos.getApellido();
        this.telefono = datos.getTelefono();
        this.tipoCliente.copiar(datos);
        }
    catch (Exception e)
        {

        }
    }


    @Override
    public Object getCopia()
    {
    Datos datos = new Datos(context);
    datos.copiar(this);
    return datos;
    }














    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }


    @Override
    public String toString() {
        return this.nombre+" "+this.apellido;
    }


    @Override
    public boolean guardar() {
        return false;
    }

    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }


    @Override
    public boolean getEstado() {
        return false;
    }



    @Override
    public boolean cargar(){return true;}

}