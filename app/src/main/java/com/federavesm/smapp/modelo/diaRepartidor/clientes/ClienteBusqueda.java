package com.federavesm.smapp.modelo.diaRepartidor.clientes;


import com.federavesm.smapp.R;

public class ClienteBusqueda
{

    public ClienteBusqueda(int id,int idCliente,String nombre,String direccion,int idTipoCliente)
    {
        this.id = id;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.idTipoCliente = idTipoCliente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    private int id;

    private String nombre;
    private String direccion;
    private int idCliente;
    private int idTipoCliente;

    public int getRecursoImagen()
    {

        switch (this.idTipoCliente)
        {
            case 1:
            {
                return R.drawable.domicilio;
            }
            case 2:
            {
                return R.drawable.comercio;
            }
            case 3:
            {
                return R.drawable.comercio;
            }
            case 4:
            {
                return R.drawable.comercio;
            }
            case 5:
            {
                return R.drawable.supermercado;
            }
            case 6:
            {
                return R.drawable.supermercado;
            }
            case 7:
            {
                return R.drawable.centrodeportivo;
            }
            case 8:
            {
                return R.drawable.institucion;
            }
            case 9:
            {
                return R.drawable.hospital;
            }
            case 10:
            {
                return R.drawable.institucion;
            }
            case 11:
            {
                return R.drawable.institucion;
            }
            case 12:
            {
                return -1;
            }
            case 13:
            {
                return R.drawable.universidad;
            }
            default:
            {
                return R.drawable.incognita;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteBusqueda that = (ClienteBusqueda) o;
        return id == that.id;
    }

}


