package com.federavesm.smapp.modelo;

import android.content.Context;

/**
 * Created by Federico on 9/2/2018.
 */

public abstract class Trabajador extends Generico
{


    public Trabajador(Context context)
    {
        super(context);
    }


    protected String nombre;
    protected String apellido;
    protected String dni;

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    @Override
    public String toString() {
        return nombre+" "+apellido;
    }




    @Override
    public void copiar(Object object)
    {
        try
        {
            Trabajador trabajador = (Trabajador)object;
            this.id = trabajador.getId();
            this.nombre = trabajador.getNombre();
            this.apellido = trabajador.getApellido();
            this.dni = trabajador.getDni();

        }
        catch (Exception e)
        {
        }
    }











}
